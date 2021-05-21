package com.bykenyodarz.gestionciudadela.utils;

import com.bykenyodarz.gestionciudadela.models.Orden;
import com.bykenyodarz.gestionciudadela.services.apis.OrdenServiceAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class HiloDiarioPM extends TimerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(HiloDiarioPM.class);

    private final OrdenServiceAPI serviceAPI;

    public HiloDiarioPM(OrdenServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @Override
    public void run() {

    }
}