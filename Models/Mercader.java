package Models;

import Exceptions.InventarioLlenoException;

public class Mercader extends NPC_base{

    private static final double IMPUESTO = 4.0;
    private static final int LIMITE_ITEMS = 7;

    public Mercader (String name, String ciudad){

        super (name,ciudad);

    }


    @Override
    public void venderItem(String nombreItem) {
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombreItem)) {
                double precioFinal = aplicarImpuestos(item.getPrecio(), IMPUESTO);
                System.out.println("El Mercader ha vendido " + nombreItem + " por " + precioFinal + " monedas (con 4% de impuesto).");
                return;
            }
        }
        System.out.println("El ítem " + nombreItem + " no está disponible con el Mercader.");
    }


    @Override
    public void agregarItem(Item item)throws InventarioLlenoException {
        if (items.size() < LIMITE_ITEMS) {
            items.add(item);
            System.out.println("El Mercader agregó el ítem (sin desgaste): " + item);
        } else {

            throw new InventarioLlenoException("Inventario lleno!- El vendedor no puede comprar el ítem");
        }
    }



}
