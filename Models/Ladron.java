package Models;

import Exceptions.InventarioLlenoException;

public class Ladron extends NPC_base{

    private static final int LIMITE_ITEMS = 3;
    private static final double DESGASTE_AGREGAR = 25.0;

    public Ladron (String name, String ciudad){

        super(name, ciudad);

    }

    @Override
    public void venderItem(String nombreItem) {
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombreItem)) {
                System.out.println("El Ladrón ha vendido " + nombreItem + " por " + item.getPrecio() + " monedas (sin impuestos).");
                return;
            }
        }
        System.out.println("El ítem " + nombreItem + " no está disponible con el Ladrón.");
    }



    @Override
    public void agregarItem(Item item) throws InventarioLlenoException  {
        if (items.size() < LIMITE_ITEMS) {
            item.aumentarDesgaste(DESGASTE_AGREGAR);
            items.add(item);
            System.out.println("El Ladrón agregó el ítem: " + item);
        } else {

            throw new InventarioLlenoException("Inventario lleno!- El vendedor no puede comprar el ítem");
        }
    }



}
