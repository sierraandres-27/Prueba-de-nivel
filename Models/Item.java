package Models;

public class Item {


    private String nombre;

    private String tipo;

    private  double precio;

    private  double desgaste;

    public Item (String nombre, String tipo, double precio, double desgaste){

        this.nombre=nombre;

        this.tipo=tipo;

        this.precio=precio;

        this.desgaste=desgaste;

    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public double getDesgaste() {
        return desgaste;
    }

    public void aumentarDesgaste (double porcentaje){

        this.desgaste=+porcentaje;

    }



}
