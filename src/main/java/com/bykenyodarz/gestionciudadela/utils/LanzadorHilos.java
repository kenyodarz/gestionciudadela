package com.bykenyodarz.gestionciudadela.utils;

import com.bykenyodarz.gestionciudadela.models.Orden;
import com.bykenyodarz.gestionciudadela.services.apis.OrdenServiceAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class LanzadorHilos {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanzadorHilos.class);

    private final OrdenServiceAPI serviceAPI;

    public LanzadorHilos(OrdenServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
//        startScheduleAtFixedRateTask();
    }

    @PostConstruct
    public void startScheduleAtFixedRateTask() {
        final LocalTime rn1 = LocalTime.of(6, 0, 0);
        final LocalTime rn2 = LocalTime.of(18, 0, 0);
        Timer timer = new Timer();
        // Tarea que toma alrededor de 2s


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
                    LocalDate today = LocalDate.now().plusDays(1);
                    if ((
                            today.isAfter(ChronoLocalDate.from(orderOldest.get().getStartDate()))
                                    || today.isEqual(ChronoLocalDate.from(orderOldest.get().getStartDate()))
                    )
                    ) {
                        LOGGER.info("Ejecutando -> {}", today.plusDays(1));
                        orderOldest.get().setEstado("En Progreso");
                        serviceAPI.save(orderOldest.get());
                    }
                }
                LOGGER.info("Fin del Hilo {}", Thread.currentThread().getName());
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
                    LocalDate today = LocalDate.now().plusDays(4);
                    if ((
                            today.isAfter(ChronoLocalDate.from(orderOldest.get().getEndDate())) ||
                                    today.isEqual(ChronoLocalDate.from(orderOldest.get().getEndDate())))
                    ) {
                        LOGGER.info("Ejecutando -> {}", today.plusDays(1));
                        orderOldest.get().setEstado("Finalizado");
                        serviceAPI.save(orderOldest.get());
                    }
                }
                LOGGER.info("Fin del Hilo {}", Thread.currentThread().getName());
            }
        };
        LocalDateTime localDateTime1 = LocalDateTime.now().plusSeconds(-11);
        Date date = Date.from(localDateTime1.atZone(ZoneId.systemDefault()).toInstant());

        LocalDate dateNow = LocalDate.now();

        LocalDateTime localDateTime = LocalDateTime.of(dateNow, rn1);
        LocalDateTime localDateTime2 =LocalDateTime.of(dateNow, rn2);

        Date dia =  Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date noche =  Date.from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());

        LOGGER.info("Ahora: -> {}", new Date());
        LOGGER.info("Hilo dia Start: -> {}", dia);
        LOGGER.info("Hilo noche Start: -> {}", noche);

        System.out.println("now: " + new Date());
        System.out.println("timer start: " + date);
        timer.scheduleAtFixedRate(task1, dia, 864000000);
        timer.scheduleAtFixedRate(task2, noche, 864000000);

    }
}
