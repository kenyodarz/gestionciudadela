package com.bykenyodarz.gestionciudadela.controllers;

import com.bykenyodarz.gestionciudadela.models.CantidadMateriales;
import com.bykenyodarz.gestionciudadela.services.apis.CantidadMaterialesServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericRestController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/cantidad")
@Api(tags = "Cantidades_de_Material")
public class CantidadMaterialesRestController extends GenericRestController<CantidadMateriales, String> {
    public CantidadMaterialesRestController(CantidadMaterialesServiceAPI serviceAPI) {
        super(serviceAPI);
    }
}
