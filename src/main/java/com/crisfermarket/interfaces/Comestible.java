package com.crisfermarket.interfaces;

import java.time.LocalDate;

public interface Comestible {

    void setFechaDeVencimiento(LocalDate fechaDeVencimiento);
    LocalDate getFechaDeVencimiento();

    void setCalorias(Integer calorias);
    Integer getCalorias();
}
