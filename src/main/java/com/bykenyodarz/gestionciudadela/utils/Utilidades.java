package com.bykenyodarz.gestionciudadela.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class Utilidades {


    public static long calcularDias(LocalDateTime date1, LocalDateTime date2) {
        long daysBetween = Duration.between(date1, date2).toDays();
        System.out.println("Days: " + daysBetween);
        return daysBetween;
    }

}
