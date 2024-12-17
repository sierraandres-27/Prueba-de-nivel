package Models;

import Exceptions.InventarioLlenoException;

import java.util.ArrayList;
import java.util.List;

public abstract class NPC_base {

    protected String name;

    protected String ciudad;

    protected List<Item> items;


    public NPC_base(String name, String ciudad) {

        this.name = name;

        this.ciudad = ciudad;

        items = new ArrayList<>();


    }

    public String getNombre() {
        return name;
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<Item> getItems() {
        return items;
    }


    public void mostrarInformacion() {
        System.out.println("Nombre: " + name);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Items a la venta:");
        for (Item item : items) {
            System.out.println(" - " + item.getNombre());
        }
    }


    public abstract void venderItem(String nombreItem);

    protected double aplicarImpuestos(double precioBase, double porcentajeImpuesto) {
        return precioBase + (precioBase * porcentajeImpuesto / 100);


    }


    public abstract void agregarItem(Item item)throws InventarioLlenoException;


}



