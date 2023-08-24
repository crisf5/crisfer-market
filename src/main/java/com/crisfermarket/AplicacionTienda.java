package com.crisfermarket;

import com.crisfermarket.enums.TipoAplicacion;
import com.crisfermarket.enums.TipoEnvase;
import com.crisfermarket.models.Bebida;
import com.crisfermarket.models.Envasado;
import com.crisfermarket.models.Limpieza;
import com.crisfermarket.models.Tienda;

import java.util.HashMap;

public class AplicacionTienda {
    public static void main(String[] args) {

        Bebida leche = new Bebida(123, "Leche Vaquita", 6, 5d, 3d,
                false, 0, false);

        Bebida cerveza = new Bebida(456, "Cerveza Alemana", 18, 15d, 10d,
                true, 5, true);

        Bebida gaseosa = new Bebida(789, "Gaseosa MasCoca", 4, 10d, 6d,
                false, 0 , false );

        Bebida jugo = new Bebida(147, "Jugo Sabroso", 4, 6d, 2d,
                false, 0, false);
        jugo.setPorcentajeDescuento(20);


        Envasado arroz = new Envasado(159, "Arroz Arrocito", 4, 4d, 2d, TipoEnvase.PLASTICO, false);
        arroz.setPorcentajeDescuento(15);
        Envasado salsaTomate = new Envasado(784, "Salsa TomaT", 6, 3d, 1d, TipoEnvase.LATA, false);
        salsaTomate.setPorcentajeDescuento(10);

        Limpieza jabon = new Limpieza(654, "Jabon el Limpiador", 6, 2d,1d, TipoAplicacion.MULTIUSO);
        Limpieza detergente = new Limpieza(777, "Detergente Burbujas", 4, 4d, 2d, TipoAplicacion.COCINA);


        Tienda tienda = new Tienda("Cordobes", 50, 500d);
        System.out.println("---------------------");
        System.out.println("Saldo inicial de la tienda '"+ tienda.getNombre() +"' es de $" + tienda.getSaldoCaja());
        System.out.println("El espacio maximo para stock es " + tienda.getMaximoProductosStock());
        System.out.println();
        System.out.println("Compra de Stock:");
        tienda.agregarProductosTienda(leche);
        tienda.agregarProductosTienda(cerveza);
        tienda.agregarProductosTienda(gaseosa);
        tienda.agregarProductosTienda(jugo);
        tienda.agregarProductosTienda(arroz);
        tienda.agregarProductosTienda(salsaTomate);
        tienda.agregarProductosTienda(jabon);
        tienda.agregarProductosTienda(detergente);
        System.out.println();
        tienda.getProductosEnStock().values().stream().forEach(p->p.stream()
                .forEach(pc-> System.out.println(pc.getDescripcion() + " " + pc.getCantidadStock() + " unidades")));
        System.out.println();
        System.out.println("El saldo despues de la compra de stock es de $" + tienda.getSaldoCaja());
        System.out.println("---------------------");


        System.out.println("Primera venta:");
        HashMap<String, Integer> primeraVenta = new HashMap<>();
        primeraVenta.put("ac123", 2);
        primeraVenta.put("ac456", 6);
        primeraVenta.put("ac789", 2);
        tienda.ventaProductos(primeraVenta);

        System.out.println();
        System.out.println("El saldo total de la tienda queda en $" + tienda.getSaldoCaja());
        System.out.println("---------------------");


        System.out.println("Segunda venta:");
        HashMap<String, Integer> segundaVenta = new HashMap<>();
        segundaVenta.put("ac147", 12);
        segundaVenta.put("ab159", 5);
        segundaVenta.put("ab784", 6);
        tienda.ventaProductos(segundaVenta);

        System.out.println();
        System.out.println("El saldo total de la tienda queda en $" + tienda.getSaldoCaja());
        System.out.println("---------------------");


        System.out.println("Tercera venta:");
        HashMap<String, Integer> terceraVenta = new HashMap<>();
        terceraVenta.put("ac789", 11);
        terceraVenta.put("ac147", 9);
        tienda.ventaProductos(terceraVenta);

        System.out.println();
        System.out.println("El saldo total de la tienda queda en $" + tienda.getSaldoCaja());
        System.out.println("---------------------");



        System.out.println("Productos comestibles no importados con descuento menor a 15%: " + tienda.obtenerComestibleConMenorDescuento(15));
        System.out.println("---------------------");


        System.out.println("Productos que generan ganancias menor al 20% :");
        tienda.listarProductosConUtilidadesInferiores(60d);
        System.out.println("---------------------");


    }
}