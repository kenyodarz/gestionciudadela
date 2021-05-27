package com.bykenyodarz.gestionciudadela.controllers;

import com.bykenyodarz.gestionciudadela.models.Material;
import com.bykenyodarz.gestionciudadela.security.utils.messages.MessageResponse;
import com.bykenyodarz.gestionciudadela.services.apis.MaterialServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericRestController;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/material")
@Api(tags = "Materiales")
public class MaterialRestController extends GenericRestController<Material, String> {
    private final MaterialServiceAPI serviceAPI;

    public MaterialRestController(MaterialServiceAPI serviceAPI) {
        super(serviceAPI);
        this.serviceAPI = serviceAPI;
    }


    @Override
    @PostMapping("/save")
    @ApiOperation(value = "Crear/Editar una Entidad", notes = "servicio para crear o editar entidades",
            authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entidad creada correctamente"),
            @ApiResponse(code = 400, message = "Error en la Solicitud"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Entidad no encontrada")})
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> save(@Valid @RequestBody Material entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        if (serviceAPI.existsByIdentificador(entity.getIdentificador()).booleanValue()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este Identificador ya lo tiene otro producto!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    @GetMapping("/with-stock/{identificador}")
    @ApiOperation(value = "Obtener un material", notes = "Servicio para obtener un material con Stock mayor a 0",
            authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Material encontrado correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Material no encontrado")})
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> findMaterialWithStock(@PathVariable String identificador) {
        var material = this.serviceAPI.findMaterialByWithStock(identificador);
        if (material == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no Encontrado");
        } else {
            return ResponseEntity.ok().body(material);
        }
    }

    @GetMapping("/actualizar/{identificador}/{cantidad}")
    @ApiOperation(value = "Actualiza un material", notes = "Servicio para Actualizar el stock"
            , authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Material encontrado correctamente"),
            @ApiResponse(code = 401, message = "Usuario No Autorizado"),
            @ApiResponse(code = 403, message = "Solicitud prohibida por el servidor"),
            @ApiResponse(code = 404, message = "Material no encontrado")})
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> actualizarStock(@PathVariable String identificador, @PathVariable int cantidad) {
        var material = this.serviceAPI.findByIdentificador(identificador);
        if (material == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no Encontrado");
        } else {
            material.setStock(material.getStock() + cantidad);
            return ResponseEntity.ok().body(serviceAPI.save(material));
        }
    }

}
