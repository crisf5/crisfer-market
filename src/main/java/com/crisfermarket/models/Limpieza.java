package com.crisfermarket.models;

import com.crisfermarket.enums.TipoAplicacion;
import com.crisfermarket.interfaces.Descuento;

public class Limpieza extends Producto implements Descuento {

    private TipoAplicacion tipoAplicacion;

    private Integer porcentajeDescuento = 0;
    private Double precioVentaConDescuento;


    public Limpieza(Integer id, String descripcion, Integer cantidadStock, Double precioPorUnidad, Double costoPorUnidad, TipoAplicacion tipoAplicacion) {
        super(descripcion, cantidadStock, precioPorUnidad, costoPorUnidad);
        setId(id);
        this.tipoAplicacion = tipoAplicacion;
    }

    public void setId(Integer id){
        if(id > 99 && id < 1000){
            super.id = "AZ" + id;
        }else{
            System.out.println("Ingrese 3 digitos");
        }
    }

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(TipoAplicacion tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }

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
