package com.bykenyodarz.gestionciudadela.controllers;

import com.bykenyodarz.gestionciudadela.models.CantidadMateriales;
import com.bykenyodarz.gestionciudadela.models.Edificacion;
import com.bykenyodarz.gestionciudadela.models.Material;
import com.bykenyodarz.gestionciudadela.models.Orden;
import com.bykenyodarz.gestionciudadela.services.apis.CantidadMaterialesServiceAPI;
import com.bykenyodarz.gestionciudadela.services.apis.EdificacionServiceAPI;
import com.bykenyodarz.gestionciudadela.services.apis.MaterialServiceAPI;
import com.bykenyodarz.gestionciudadela.services.apis.OrdenServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericRestController;
import com.bykenyodarz.gestionciudadela.utils.Utilidades;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/orden")
@Api(tags = "Ordenes")
public class OrdenRestController extends GenericRestController<Orden, String> {
    private final OrdenServiceAPI serviceAPI;
    private final CantidadMaterialesServiceAPI cantidadMaterialesServiceAPI;
    private final EdificacionServiceAPI edificacionServiceAPI;
    private final MaterialServiceAPI materialServiceAPI;

    public OrdenRestController(OrdenServiceAPI serviceAPI,
                               CantidadMaterialesServiceAPI cantidadMaterialesServiceAPI,
                               EdificacionServiceAPI edificacionServiceAPI,
                               MaterialServiceAPI materialServiceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
        this.cantidadMaterialesServiceAPI = cantidadMaterialesServiceAPI;
        this.edificacionServiceAPI = edificacionServiceAPI;
        this.materialServiceAPI = materialServiceAPI;
    }


    @PostMapping("/crear/{edificio}/{cooX}/{cooY}")
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    @ApiOperation(value = "Crear Ordenes Construcción", notes = "servicio para crear o editar entidades",
            authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Orden Creada Correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    public ResponseEntity<?> crearOrden(@PathVariable String edificio,
                                        @PathVariable Double cooX,
                                        @PathVariable Double cooY
    ) {
        if (this.serviceAPI.existsByCoordenadaXAndCoordenadaY(cooX, cooY)) {
            return ResponseEntity.badRequest().body("ya existe un predio en esa coordenada");
        }

        Edificacion edificacion = this.edificacionServiceAPI.findByNombre(edificio);
        if (edificacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo edificio no encontrado");
        }

        List<CantidadMateriales> listMateriales = this.cantidadMaterialesServiceAPI.findByEdificacion(edificio);
        AtomicBoolean recursos = new AtomicBoolean(false);
        for (CantidadMateriales material : listMateriales) {
            int cantidadDisponible = material.getMaterial().getStock();
            int cantidadNecesaria = material.getCantidad();
            recursos.set(cantidadDisponible < cantidadNecesaria);
            if (recursos.get()) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Material Insuficiente: " + material.getMaterial().getNombre());
        }

        listMateriales.forEach(recurso -> {
            Material material = materialServiceAPI.get(recurso.getMaterial().getIdMaterial());
            material.setStock(material.getStock() - recurso.getCantidad());
            materialServiceAPI.save(material);
        });

        List<Orden> listOrden = this.serviceAPI.findAllByEstado();
        Orden orden = new Orden();

        if (!listOrden.isEmpty()) {
            AtomicReference<Orden> orderOldest = new AtomicReference<>(listOrden.get(0));
            AtomicReference<Orden> ordenNewest = new AtomicReference<>(listOrden.get(0));

            if (listOrden.size() > 1) {
                listOrden.forEach(ordenI -> {
                    if (ordenI.getCreatedAt().isBefore(orderOldest.get().getCreatedAt())) {
                        orderOldest.set(ordenI);
                    }
                    if (ordenI.getCreatedAt().isAfter(ordenNewest.get().getCreatedAt())) {
                        ordenNewest.set(ordenI);
                    }
                });
            }
            LocalDateTime today = LocalDateTime.now().plusDays(1);
            if ((
                    today.isAfter(orderOldest.get().getStartDate())
                            || today.isEqual(orderOldest.get().getStartDate())
            )
                    && (
                    today.isBefore(ordenNewest.get().getEndDate())
                            || today.isEqual(ordenNewest.get().getEndDate())
            )
            ) {
                orden.setStartDate(ordenNewest.get().getEndDate().plusDays(1));
                orden.setEndDate(orden.getStartDate().plusDays(edificacion.getCantidadDias()));
            }
        } else {
            orden.setStartDate(LocalDateTime.now().plusDays(1));
            orden.setEndDate(orden.getStartDate().plusDays(edificacion.getCantidadDias()));
        }

        orden.setCoordenadaX(cooX);
        orden.setCoordenadaY(cooY);
        orden.setEdificacion(edificacion);
        orden.setEstado("Pendiente");

        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(orden));
    }

    @GetMapping("/dias")
    @ApiOperation(value = "Entrega los Dias", notes = "servicio para crear o editar entidades",
            authorizations = {@Authorization(value = "jwtToken")})
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerDias() {
        List<Orden> listOrden = serviceAPI.getAll();
        AtomicReference<Orden> orderOldest = new AtomicReference<>(listOrden.get(0));
        AtomicReference<Orden> ordenNewest = new AtomicReference<>(listOrden.get(0));

        listOrden.forEach(ordenI -> {
            if (ordenI.getCreatedAt().isBefore(orderOldest.get().getCreatedAt())) {
                orderOldest.set(ordenI);
            }
            if (ordenI.getCreatedAt().isAfter(ordenNewest.get().getCreatedAt())) {
                ordenNewest.set(ordenI);
            }
        });

        return ResponseEntity.ok().body(LocalDate.now()
                .plusDays(Utilidades.calcularDias(LocalDateTime.now(),ordenNewest.get().getEndDate())));
    }

}
