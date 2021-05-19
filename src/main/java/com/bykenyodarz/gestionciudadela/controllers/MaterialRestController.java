package com.bykenyodarz.gestionciudadela.controllers;

import com.bykenyodarz.gestionciudadela.models.Material;
import com.bykenyodarz.gestionciudadela.services.apis.MaterialServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/material")
public class MaterialRestController extends GenericRestController<Material, String> {
    private final MaterialServiceAPI serviceAPI;

    public MaterialRestController(MaterialServiceAPI serviceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/with-stock/{id}")
    @ApiOperation(value = "Obtener un material", notes = "Servicio para obtener un material con Stock mayor a 0",
            authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Material encontrado correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Material no encontrado")})
    public ResponseEntity<?> findMaterialWithStock(@PathVariable String id) {
        Material material = this.serviceAPI.findMaterialByWithStock(id);
        if (material == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no Encontrado");
        } else {
            return ResponseEntity.ok().body(material);
        }
    }

    @GetMapping("/actualizar/{id}/{cantidad}")
    @ApiOperation(value = "Actualiza un material", notes = "Servicio para Actualizar el stock"
            , authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Material encontrado correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Material no encontrado")})
    public ResponseEntity<?> actualizarStock(@PathVariable String id, @PathVariable int cantidad) {
        Material material = this.serviceAPI.findByIdentificador(id);
        if (material == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no Encontrado");
        } else {
            material.setStock(material.getStock() + cantidad);
            return ResponseEntity.ok().body(material);
        }
    }

}
