package com.crisfermarket.models;

import com.crisfermarket.enums.TipoEnvase;
import com.crisfermarket.interfaces.Comestible;
import com.crisfermarket.interfaces.Descuento;

import java.time.LocalDate;

public class Envasado extends Producto implements Comestible, Descuento {

    private TipoEnvase tipoEnvase;
    private Boolean importado = false;

    private LocalDate fechaDeVencimiento;
    private Integer calorias;

    private Integer porcentajeDescuento = 0;
    private Double precioVentaConDescuento;


    public Envasado(Integer id, String descripcion, Integer cantidadStock, Double precioPorUnidad, Double costoPorUnidad, TipoEnvase tipo, Boolean importado) {
        super(descripcion, cantidadStock, precioPorUnidad, costoPorUnidad);
        setId(id);
        this.tipoEnvase = tipo;
        this.importado = importado;
    }

    public void setId(Integer id){
        if(id > 99 && id < 1000){
            super.id = "AB" + id;
        }else{
            System.out.println("Ingrese 3 digitos");
        }
    }

    public TipoEnvase getTipo() {
        return tipoEnvase;
    }

    public void setTipo(TipoEnvase tipo) {
        this.tipoEnvase = tipo;
    }

    public Boolean getImportado() {
        return importado;
    }

    public void setImportado(Boolean importado) {
        this.importado = importado;
    }


    @Override
    public void setFechaDeVencimiento(LocalDate fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    @Override
    public LocalDate getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    @Override
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    @Override
    public Integer getCalorias() {
        return calorias;
    }

    @Override
    public void setPorcentajeDescuento(Integer porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public Integer getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public Double getPrecioVentaConDescuento() {
        return precioPorUnidad - ((precioPorUnidad * porcentajeDescuento) /100);
    }
}
