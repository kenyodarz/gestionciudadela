package com.bykenyodarz.gestionciudadela.utils;

import com.bykenyodarz.gestionciudadela.models.Orden;
import com.bykenyodarz.gestionciudadela.services.apis.OrdenServiceAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class Hilos {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hilos.class);

    private final OrdenServiceAPI serviceAPI;

    public Hilos(OrdenServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
//        startScheduleAtFixedRateTask();
    }

    @PostConstruct
    public void startScheduleAtFixedRateTask() {
        final var rn1 = LocalTime.of(6, 0, 0);
        final var rn2 = LocalTime.of(18, 0, 0);
        var dateNow = LocalDate.now();
        var horaDiurna = LocalDateTime.of(dateNow, rn1);
        var horaNocturna = LocalDateTime.of(dateNow, rn2);
        var dia = Date.from(horaDiurna.atZone(ZoneId.systemDefault()).toInstant());
        var noche = Date.from(horaNocturna.atZone(ZoneId.systemDefault()).toInstant());
        var timer = new Timer();

        LOGGER.info("Ahora: -> {}", new Date());
        LOGGER.info("Hilo dia Start: -> {}", dia);
        LOGGER.info("Hilo noche Start: -> {}", noche);


        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                List<Orden> listOrden = serviceAPI.findAllByEstado();
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
                    var today = LocalDate.now();
                    if ((
                            today.isAfter(ChronoLocalDate.from(orderOldest.get().getStartDate()))
                                    || today.isEqual(ChronoLocalDate.from(orderOldest.get().getStartDate()))
                    )
                    ) {
                        LOGGER.info("Ejecutando -> Hilo Diurno");
                        orderOldest.get().setEstado("En Progreso");
                        serviceAPI.save(orderOldest.get());
                    }
                }
                LOGGER.info("Fin del Hilo -> Diurno");
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                List<Orden> listOrden = serviceAPI.findAllByEstado();
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
                    var today = LocalDate.now();
                    if ((
                            today.isAfter(ChronoLocalDate.from(orderOldest.get().getEndDate())) ||
                                    today.isEqual(ChronoLocalDate.from(orderOldest.get().getEndDate())))
                    ) {
                        LOGGER.info("Ejecutando -> Hilo Nocturno");
                        orderOldest.get().setEstado("Finalizado");
                        serviceAPI.save(orderOldest.get());
                    }
                }
                LOGGER.info("Fin del Hilo -> Nocturno");
            }
        };
        timer.scheduleAtFixedRate(task1, dia, 864000000);
        timer.scheduleAtFixedRate(task2, noche, 864000000);

    }
}
