package src;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        ArrayList<Producto> listaProductos = new ArrayList<>();
        HashMap<String, ArrayList<Producto>> categorias = new HashMap<>();
        Producto p1 = new Producto("001", "Teclado", "Electronica", 20.0, 10);
        Producto p2 = new Producto("002", "Raton", "Electronica", 15.0, 3);
        Producto p3 = new Producto("003", "Camiseta", "Ropa", 10.0, 8);
        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        for (Producto p : listaProductos) {
            if (!categorias.containsKey(p.getCategoria())) {
                categorias.put(p.getCategoria(), new ArrayList<>());
            }
            categorias.get(p.getCategoria()).add(p);
        }
        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());
            sc.nextLine();

            switch (opcion) {

                case 1:
                    mostrarCategorias(categorias);
                    break;

                case 2:
                    mostrarStockBajo(listaProductos);
                    break;

                case 3:
                    System.out.println("Valor total: " + calcularValorTotal(listaProductos));
                    break;

                case 4:
                    venderProducto(listaProductos, sc);
                    break;

                // --- NUEVO CASO 5 ---
                case 5:
                    buscarProducto(listaProductos, sc);
                    break;
                // --------------------

                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }
    // --- NUEVO MÉTODO DE BÚSQUEDA ---
    public static void buscarProducto(ArrayList<Producto> lista, Scanner sc) {
        System.out.print("Introduce el nombre del producto a buscar: ");
        String nombreBuscado = sc.nextLine();
        boolean encontrado = false;

        for (Producto p : lista) {
            // Utilizamos equalsIgnoreCase para que no importe si el usuario escribe en mayúsculas o minúsculas
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("\n--- Producto Encontrado ---");
                System.out.println("Código: " + p.getCodigo());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Categoría: " + p.getCategoria());
                System.out.println("Precio: " + p.getPrecio() + "€");
                System.out.println("Stock: " + p.getStock() + " unidades");
                encontrado = true;
                break; // Terminamos la búsqueda al encontrarlo
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún producto con el nombre: " + nombreBuscado);
        }
    }
    public static void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Mostrar por categorias");
        System.out.println("2. Mostrar stock bajo");
        System.out.println("3. Valor total inventario");
        System.out.println("4. Vender producto");
        System.out.println("5. Buscar producto por nombre"); // <-- NUEVA LÍNEA
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }

    

    // Muestra las categorias
    public static void mostrarCategorias(HashMap<String, ArrayList<Producto>> categorias) {

        for (String categoria : categorias.keySet()) {
            System.out.println("Categoria: " + categoria);

            for (Producto p : categorias.get(categoria)) {
                System.out.println(" - " + p.getNombre());
            }
        }
    }

    // Mensaje de stock bajo, en este caso he puesto por debajo de 5 como bajo
    public static void mostrarStockBajo(ArrayList<Producto> lista) {

        for (Producto p : lista) {
            if (p.getStock() < 5) {
                System.out.println("Stock bajo: " + p.getNombre());
            }
        }
    }

    public static double calcularValorTotal(ArrayList<Producto> lista) {

        double total = 0;

        for (Producto p : lista) {
            total += p.getPrecio() * p.getStock();
        }

        return total;
    }
    //Ventas

public static void venderProducto(ArrayList<Producto> lista, Scanner sc) {

    System.out.print("Codigo producto: ");
    String codigo = sc.nextLine();

    System.out.print("Cantidad: ");
    int cantidad = sc.nextInt();

    for (Producto p : lista) {
        if (p.getCodigo().equals(codigo)) {

            if (p.getStock() >= cantidad) {
                p.setStock(p.getStock() - cantidad);
                System.out.println("Venta realizada de: " + p.getNombre());
            } else {
                System.out.println("No hay suficiente stock");
            }

            return;
        }
    }

    System.out.println("src.Producto no encontrado");
}}

