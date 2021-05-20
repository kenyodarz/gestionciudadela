package com.bykenyodarz.gestionciudadela.controllers;

import com.bykenyodarz.gestionciudadela.models.Orden;
import com.bykenyodarz.gestionciudadela.services.apis.OrdenServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericRestController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/orden")
@Api(tags = "Ordenes")
public class OrdenRestController extends GenericRestController<Orden, String> {

    public OrdenRestController(OrdenServiceAPI serviceAPI) {
        super(serviceAPI);
    }
}
