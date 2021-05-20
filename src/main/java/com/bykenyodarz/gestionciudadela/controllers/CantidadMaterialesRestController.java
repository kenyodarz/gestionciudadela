package com.bykenyodarz.gestionciudadela.controllers;

import com.bykenyodarz.gestionciudadela.models.CantidadMateriales;
import com.bykenyodarz.gestionciudadela.models.Edificacion;
import com.bykenyodarz.gestionciudadela.models.Material;
import com.bykenyodarz.gestionciudadela.services.apis.CantidadMaterialesServiceAPI;
import com.bykenyodarz.gestionciudadela.services.apis.EdificacionServiceAPI;
import com.bykenyodarz.gestionciudadela.services.apis.MaterialServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericRestController;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/cantidad")
@Api(tags = "Cantidades_de_Material")
public class CantidadMaterialesRestController extends GenericRestController<CantidadMateriales, String> {
    private final CantidadMaterialesServiceAPI serviceAPI;
    private final EdificacionServiceAPI edificacionServiceAPI;
    private  final MaterialServiceAPI materialServiceAPI;

    public CantidadMaterialesRestController(CantidadMaterialesServiceAPI serviceAPI,
                                            EdificacionServiceAPI edificacionServiceAPI,
                                            MaterialServiceAPI materialServiceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
        this.edificacionServiceAPI = edificacionServiceAPI;
        this.materialServiceAPI = materialServiceAPI;
    }

    @PostMapping("/crear/{nombre}/{identificador}/{cantidad}")
    @ApiOperation(value = "Crear/Editar una Entidad", notes = "servicio para crear y asigna materiales",
            authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entidad creada correctamente"),
            @ApiResponse(code = 400, message = "Error en la Solicitud"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<?> crearMateriales(@PathVariable String nombre,
                                             @PathVariable String identificador,
                                             @PathVariable Integer cantidad){
        Material material = materialServiceAPI.findByIdentificador(identificador);
        if (material == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el material");
        }
        Edificacion edificacion = edificacionServiceAPI.findByNombre(nombre);
        if (edificacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró tipo de edificio");
        }
        CantidadMateriales cantidadMateriales = new CantidadMateriales();
        cantidadMateriales.setCantidad(cantidad);
        cantidadMateriales.setMaterial(material);
        cantidadMateriales.setEdificacion(edificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceAPI.save(cantidadMateriales));
    }

    @GetMapping("/materiales/{nombre}")
    @ApiOperation(value = "Lista los Materiales", notes = "Servicio que lista los materiales necesarios para un edificio",
            authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista todos los materiales"),
            @ApiResponse(code = 400, message = "Error en la Solicitud"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findByEdificacion(@PathVariable String nombre) {
        return ResponseEntity.ok().body(this.serviceAPI.findByEdificacion(nombre));
    }

}
