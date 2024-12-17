package Models;

import Exceptions.InventarioLlenoException;

public class Campesino extends NPC_base{

    private static final double IMPUESTO = 2.0;
    private static final int LIMITE_ITEMS = 5;
    private static final double DESGASTE_AGREGAR = 15.0;


    public Campesino (String name,String ciudad) {

        super(name,ciudad);


    }

    @Override
    public void venderItem(String nombreItem) {
        for (Item item : items) {
        if (item.getNombre().equalsIgnoreCase(nombreItem)) {
            double precioFinal = aplicarImpuestos(item.getPrecio(), IMPUESTO);
            System.out.println("El Campesino ha vendido " + nombreItem + " por " + precioFinal + " monedas (con 2% de impuesto).");
            return;
        }
    }
        System.out.println("El ítem " + nombreItem + " no está disponible con el Campesino.");

}

    @Override
    public void agregarItem(Item item)throws InventarioLlenoException {
        if (items.size() < LIMITE_ITEMS) {
            item.aumentarDesgaste(DESGASTE_AGREGAR);
            items.add(item);
            System.out.println("El Campesino agregó el ítem: " + item);
        } else {

            throw new InventarioLlenoException("Inventario lleno!- El vendedor no puede comprar el ítem");
            
        }
    }









}



