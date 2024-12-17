package Menu;

import Exceptions.InventarioLlenoException;
import Models.*;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    private static List<NPC_base> npcs = new ArrayList<>();

    public static void start ()  {


        Scanner scanner = new Scanner(System.in);

        // Inicialización de NPCs
        inicializarNPCs();

        int opcion;
        do {
            mostrarMenu();
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> consultarItemsNPC(scanner);
                case 2 -> consultarNPCsCiudad(scanner);
                case 3 -> mostrarItemMasBarato(scanner);
                case 4 -> mostrarItemsPorTipo(scanner);
                case 5 -> simularCompraItem(scanner);
                case 6 -> simularVentaItem(scanner);
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();

    }


    private static void mostrarMenu() {
        System.out.println("\n*** MENÚ DE FUNCIONALIDADES ***");
        System.out.println("1 - Consultar los ítems de un NPC, tenemos a Pedro, Garrick y Marco");
        System.out.println("2 - Consultar los NPC que hay en una ciudad, tenemos las ciudades de : Villa verde, Ciudad Oscura y Puerto Rico");
        System.out.println("3 - Mostrar el ítem más barato de todos los NPC de una ciudad,tenemos las ciudades de : Villa verde, Ciudad Oscura y Puerto Rico");
        System.out.println("4 - Mostrar todos los ítems de un determinado tipo ordenados por precio (ascendente), entre los tipos tenemos: Arma, Tesoro y Bbeida");
        System.out.println("5 - Simular que un NPC compra un ítem, recuerda tenemos los siguientes NPC: Pedro, Garrick y Marco. Y los siguientes Items: Espada, Joya y Poción");
        System.out.println("6 - Simular que un NPC vende un ítem");
        System.out.println("0 - Salir");


    }

    private static void inicializarNPCs() {
        Campesino campesino = new Campesino("Pedro", "Villa Verde");
        Ladron ladron = new Ladron("Garrick", "Ciudad Oscura");
        Mercader mercader = new Mercader("Marco",  "Puerto Rico");

        try {
            campesino.agregarItem(new Item("Espada", "Arma", 100, 10));
        } catch (InventarioLlenoException e) {
            System.out.println(e.getMessage());
        }
        try {
            ladron.agregarItem(new Item("Joya", "Tesoro", 300, 5));
        } catch (InventarioLlenoException e) {
            e.getMessage();
        }
        try {
            mercader.agregarItem(new Item("Poción", "Bebida", 50, 2));
        } catch (InventarioLlenoException e) {

            e.getMessage();
        }

        npcs.addAll(List.of(campesino, ladron, mercader));
    }

    private static void consultarItemsNPC(Scanner scanner) {
        System.out.print("Introduce el nombre del NPC: ");
        String nombre = scanner.nextLine();
        for (NPC_base npc : npcs) {
            if (npc.getNombre().equalsIgnoreCase(nombre)) {
                npc.mostrarInformacion();
                return;
            }
        }
        System.out.println("NPC no encontrado.");
    }

    private static void consultarNPCsCiudad(Scanner scanner) {
        System.out.print("Introduce el nombre de la ciudad: ");
        String ciudad = scanner.nextLine();
        npcs.stream()
                .filter(npc -> npc.getCiudad().equalsIgnoreCase(ciudad))
                .forEach(npc -> System.out.println(" - " + npc.getNombre()));
    }

    private static void mostrarItemMasBarato(Scanner scanner) {
        System.out.print("Introduce el nombre de la ciudad: ");
        String ciudad = scanner.nextLine();
        Optional<Item> itemMasBarato = npcs.stream()
                .filter(npc -> npc.getCiudad().equalsIgnoreCase(ciudad))
                .flatMap(npc -> npc.getItems().stream())
                .min(Comparator.comparing(Item::getPrecio));

        itemMasBarato.ifPresentOrElse(
                item -> System.out.println("El ítem más barato es: " + item.getNombre()),
                () -> System.out.println("No se encontraron ítems en esta ciudad.")
        );
    }


    private static void mostrarItemsPorTipo(Scanner scanner) {
        System.out.print("Introduce el tipo de ítem: ");
        String tipo = scanner.nextLine();
        List<Item> itemsOrdenados = npcs.stream()
                .flatMap(npc -> npc.getItems().stream())
                .filter(item -> item.getNombre().equalsIgnoreCase(tipo))
                .sorted(Comparator.comparing(Item::getPrecio))
                .collect(Collectors.toList());

        itemsOrdenados.forEach(System.out::println);
    }


    private static void simularCompraItem(Scanner scanner) {
        System.out.print("Introduce el nombre del NPC comprador: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce el nombre del ítem: ");
        String nombreItem = scanner.nextLine();

        NPC_base npcComprador = npcs.stream()
                .filter(npc -> npc.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        if (npcComprador != null) {
            try {
                npcComprador.agregarItem(new Item(nombreItem, "Desconocido", 0, 0));
                System.out.println("Ítem comprado correctamente.");
            } catch (InventarioLlenoException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("NPC no encontrado.");
        }
    }

    private static void simularVentaItem(Scanner scanner) {
        System.out.print("Introduce el nombre del NPC vendedor: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce el nombre del ítem a vender: ");
        String nombreItem = scanner.nextLine();

        for (NPC_base npc : npcs) {
            if (npc.getNombre().equalsIgnoreCase(nombre)) {
                npc.venderItem(nombreItem);
                return;
            }
        }
        System.out.println("NPC no encontrado.");
    }
}




