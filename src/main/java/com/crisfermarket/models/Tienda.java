package com.crisfermarket.models;

import com.crisfermarket.interfaces.Descuento;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tienda {
    private String nombre;
    private Integer maximoProductosStock;
    private Double saldoCaja;
    private Map<String, List<Producto>> productosEnStock;

    public Tienda(String nombre, Integer maximoProductosStock, Double saldoCaja) {
        this.nombre = nombre;
        this.maximoProductosStock = maximoProductosStock;
        this.saldoCaja = saldoCaja;
        productosEnStock = new HashMap<>();
        productosEnStock.put("ENVASADOS", new ArrayList<>());
        productosEnStock.put("BEBIDAS", new ArrayList<>());
        productosEnStock.put("LIMPIEZA", new ArrayList<>());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMaximoProductosStock() {
        return maximoProductosStock;
    }

    public void setMaximoProductosStock(Integer maximoProductosStock) {
        this.maximoProductosStock = maximoProductosStock;
    }

    public Double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(Double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public Map<String, List<Producto>> getProductosEnStock() {
        return productosEnStock;
    }

    public void setProductosEnStock(Map<String, List<Producto>> productosEnStock) {
        this.productosEnStock = productosEnStock;
    }


    public void agregarProductosTienda(Producto productoParaStock){

        Double importeTotalProductos = productoParaStock.getCostoPorUnidad() * productoParaStock.getCantidadStock();

        if(saldoCaja >= importeTotalProductos){

            if(getTotalProductosEnStock() + productoParaStock.getCantidadStock() <= getMaximoProductosStock()){
                productoParaStock.setDisponible(true);
                productosEnStock.get(tipoProducto(productoParaStock)).add(productoParaStock);
                saldoCaja -= importeTotalProductos;
            }else if(getMaximoProductosStock() - getTotalProductosEnStock() == 0){
                System.out.println("No se pudo agregar stock del producto " + productoParaStock.getDescripcion() + " por falta de espacio");
            }else{
                int restanteEspacio = getMaximoProductosStock() - getTotalProductosEnStock();
                productoParaStock.setCantidadStock(restanteEspacio);
                productoParaStock.setDisponible(true);
                productosEnStock.get(tipoProducto(productoParaStock)).add(productoParaStock);
                System.out.println("Se agregaron solamente " + restanteEspacio + " unidades del producto " + productoParaStock.getDescripcion() + " por falta de espacio");
            }

        }else{
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
        }
    }

    private String tipoProducto(Producto producto){
        if(producto instanceof Envasado){
            return "ENVASADOS";
        } else if (producto instanceof Bebida) {
            return "BEBIDAS";
        } else if (producto instanceof Limpieza) {
            return "LIMPIEZA";
        }
        return null;
    }

    private Integer getTotalProductosEnStock(){
        return productosEnStock.values().stream().flatMapToInt(p->p.stream().mapToInt(c->c.getCantidadStock())).sum();
    }

    public void ventaProductos(HashMap<String, Integer> idProductoCantidadStock){

        List<String> productosVendidos = new ArrayList<>();

            double totalVenta = idProductoCantidadStock.entrySet()
                .stream()
                .mapToDouble(entry -> {
                    String idProductoVenta = entry.getKey().toUpperCase();
                    int cantidadStock = Math.min(entry.getValue(),10);

                    Producto producto = productosEnStock.entrySet()
                            .stream()
                            .flatMap(e -> e.getValue().stream())
                            .filter(p -> p.getId().equals(idProductoVenta))
                            .findFirst()
                            .orElse(null);

                    if (producto == null) {
                        System.out.println("El producto " + idProductoVenta + " no se encuentra disponible");
                        return 0;
                    }

                    if(producto.getCantidadStock() == 0){
                        producto.setDisponible(false);
                        return 0;
                    }

                    if(productosVendidos.size() >= 3){
                        System.out.println("Solo esta permitido comprar 3 productos");
                        return 0;
                    }

                    if(entry.getValue()>10){
                        System.out.println("Solo esta permitido comprar hasta 10 unidades de producto, la compra de " + producto.getDescripcion() + " estara limitada");
                    }

                    if (cantidadStock > producto.getCantidadStock()) {
                        cantidadStock = producto.getCantidadStock();
                        System.out.println("Hay productos con stock disponible menor al solicitado. " + producto.getDescripcion() + ".");
                    }

                    if (!producto.getDisponible()) {
                        System.out.println("El producto " + producto.getId() + " no se encuentra disponible");
                        return 0;
                    }

                    double precioVenta = producto.getPrecioPorUnidad();
                    double totalPrecio = precioVenta * cantidadStock;
                    producto.setCantidadStock(producto.getCantidadStock() - cantidadStock);

                    productosVendidos.add(producto.getId() + " " + producto.getDescripcion() + " " + cantidadStock + " x " + precioVenta);

                    return totalPrecio;
                })
                .sum();

        System.out.println();
        productosVendidos.forEach(pv-> System.out.println(pv));
        System.out.println("TOTAL VENTA: " + totalVenta);

    }


  public Set<String> obtenerComestibleConMenorDescuento(Integer porcentajeDescuento){

      Stream<Producto> productosComestibles = Stream.concat(
              productosEnStock.get("ENVASADOS").stream()
                      .filter(p -> !(((Envasado) p).getImportado()) && ((Envasado) p).getPorcentajeDescuento() <= porcentajeDescuento)
                      .map(p -> (Envasado) p),
              productosEnStock.get("BEBIDAS").stream()
                      .filter(p -> !(((Bebida) p).getImportado()) && ((Bebida) p).getPorcentajeDescuento() <= porcentajeDescuento)
                      .map(p -> (Bebida) p)
      );

      return productosComestibles
              .sorted(Comparator.comparingDouble(Producto::getPrecioPorUnidad))
              .map(p -> p.getDescripcion())
              .collect(Collectors.toSet());
  }


    public void listarProductosConUtilidadesInferiores(Double porcentajeUtilidad) {
        productosEnStock.values().stream()
                .flatMap(List::stream)
                .filter(p -> p instanceof Descuento)
                .map(p -> (Descuento) p)
                .filter(p -> {
                    double costoPorUnidad = ((Producto) p).getCostoPorUnidad();
                    double precioVentaConDescuento = p.getPrecioVentaConDescuento();
                    double ganancia = precioVentaConDescuento - costoPorUnidad;
                    double porcentajeGanancia = (ganancia / costoPorUnidad) * 100;
                    return porcentajeGanancia < porcentajeUtilidad;
                })
                .forEach(p -> System.out.println(((Producto) p).getId() + " " + ((Producto) p).getDescripcion() +
                        " " + ((Producto) p).getCantidadStock()));
    }

}
