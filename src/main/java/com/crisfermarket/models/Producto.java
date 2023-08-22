package com.crisfermarket.models;

public class Producto {

    String id;
    String descripcion;
    Integer cantidadStock;
    Double precioPorUnidad;
    Double costoPorUnidad;
    Boolean disponible;


    public Producto(String descripcion, Integer cantidadStock, Double precioPorUnidad, Double costoPorUnidad) {
        this.descripcion = descripcion;
        this.precioPorUnidad = precioPorUnidad;
        this.costoPorUnidad = costoPorUnidad;

        if (cantidadStock >= 1) {
            this.cantidadStock = cantidadStock;
        } else {
            throw new IllegalArgumentException("La cantidad de stock debe ser igual o mayor 1.");
        }
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.toUpperCase();
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(Double precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public Double getCostoPorUnidad() {
        return costoPorUnidad;
    }

    public void setCostoPorUnidad(Double costoPorUnidad) {
        this.costoPorUnidad = costoPorUnidad;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

}
