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
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/orden")
@Api(tags = "Ordenes")
public class OrdenRestController extends GenericRestController<Orden, String> {
    private final OrdenServiceAPI serviceAPI;
    private final CantidadMaterialesServiceAPI cantidadMaterialesServiceAPI;
    private final EdificacionServiceAPI edificacionServiceAPI;
    private  final MaterialServiceAPI materialServiceAPI;

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
        if (this.serviceAPI.existsByCoordenadaXAndCoordenadaY(cooX, cooY)){
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
            if (recursos.get())  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Material Insuficiente: " + material.getMaterial().getNombre());
        }

        listMateriales.forEach(recurso -> {
            Material material = materialServiceAPI.get(recurso.getMaterial().getIdMaterial());
            material.setStock(material.getStock() - recurso.getCantidad());
            materialServiceAPI.save(material);
        });

        Orden orden = new Orden();

        orden.setCoordenadaX(cooX);
        orden.setCoordenadaY(cooY);
        orden.setEdificacion(edificacion);
        orden.setEstado("Pendiente");

        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(orden));
    }
}
