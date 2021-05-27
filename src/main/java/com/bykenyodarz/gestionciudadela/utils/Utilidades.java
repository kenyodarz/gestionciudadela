package com.bykenyodarz.gestionciudadela.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public final class Utilidades {

    private Utilidades() {
        throw new IllegalStateException("Utility class");
    }


    public static long calcularDias(LocalDateTime date1, LocalDateTime date2) {
        return Duration.between(date1, date2).toDays();
    }

}
