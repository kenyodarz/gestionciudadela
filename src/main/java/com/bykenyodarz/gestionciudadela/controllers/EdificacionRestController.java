package com.bykenyodarz.gestionciudadela.controllers;

import com.bykenyodarz.gestionciudadela.models.Edificacion;
import com.bykenyodarz.gestionciudadela.models.Material;
import com.bykenyodarz.gestionciudadela.security.utils.messages.MessageResponse;
import com.bykenyodarz.gestionciudadela.services.apis.EdificacionServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericRestController;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceAPI;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/edificaciones")
@Api(tags = "Edificaciones")
public class EdificacionRestController extends GenericRestController<Edificacion, String> {

    private final EdificacionServiceAPI serviceAPI;

    public EdificacionRestController(EdificacionServiceAPI serviceAPI) {
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
    public ResponseEntity<?> save(@Valid @RequestBody Edificacion entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        if(serviceAPI.existsByNombre(entity.getNombre())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este edificio ya existe!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

}
