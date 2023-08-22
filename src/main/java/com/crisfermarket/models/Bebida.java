package com.crisfermarket.models;

import com.crisfermarket.interfaces.Comestible;
import com.crisfermarket.interfaces.Descuento;

import java.time.LocalDate;

public class Bebida extends Producto implements Comestible, Descuento {

    private Boolean alcoholica;
    private Integer graduacionAlcoholica;
    private Boolean importado = false;

    private LocalDate fechaDeVencimiento;
    private Integer calorias;

    private Integer porcentajeDescuento = 0;
    private Double precioVentaConDescuento;


    public Bebida(Integer id, String descripcion, Integer cantidadStock, Double precioPorUnidad, Double costoPorUnidad, Boolean alcoholica, Integer graduacionAlcoholica, Boolean importado) {
        super(descripcion, cantidadStock, precioPorUnidad, costoPorUnidad);
        setId(id);
        this.alcoholica = alcoholica;
        if(this.alcoholica == true){
            this.graduacionAlcoholica = graduacionAlcoholica;
        }else{
            this.graduacionAlcoholica = 0;
        }
        this.importado = importado;
    }

    public void setId(Integer id){
        if(id > 99 && id < 1000){
            super.id = "AC" + id;
        }else{
            System.out.println("Ingrese 3 digitos");
        }
    }

    public Boolean getAlcoholica() {
        return alcoholica;
    }

    public void setAlcoholica(Boolean alcoholica) {
        this.alcoholica = alcoholica;
    }

    public Integer getGraduacionAlcoholica() {
        if(this.graduacionAlcoholica==null) return 0;
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(Integer graduacionAlcoholica){
        if(this.alcoholica && graduacionAlcoholica > 0 && graduacionAlcoholica < 100){
            this.graduacionAlcoholica = graduacionAlcoholica;
        }else{
            System.out.println("Para agregar graduacion alcoholica la bebida debe ser alcoholica");
            this.graduacionAlcoholica = 0;
        }
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
