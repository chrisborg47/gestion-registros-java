package presentacion;

import entidades.Registro;
import java.util.List;
import java.util.Scanner;
import logica.RegistroService;

public class MenuConsola {

    private final Scanner scanner;
    private final RegistroService registroService;

    public MenuConsola() {
        scanner = new Scanner(System.in);
        registroService = new RegistroService();
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    agregarRegistro();
                    break;
                case 2:
                    listarRegistros();
                    break;
                case 3:
                    buscarRegistroPorId();
                    break;
                case 4:
                    actualizarRegistro();
                    break;
                case 5:
                    eliminarRegistro();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

            System.out.println();
        } while (opcion != 6);
    }

    public void mostrarMenu() {
        System.out.println("===== MENÚ DE REGISTROS =====");
        System.out.println("1. Agregar registro");
        System.out.println("2. Listar registros");
        System.out.println("3. Buscar registro por id");
        System.out.println("4. Actualizar registro");
        System.out.println("5. Eliminar registro");
        System.out.println("6. Salir");
    }

    private void agregarRegistro() {
        System.out.println("----- Agregar registro -----");

        int id = leerEntero("Ingrese el id: ");
        String nombre = leerTexto("Ingrese el nombre: ");
        String dato1 = leerTexto("Ingrese el dato1: ");
        String dato2 = leerTexto("Ingrese el dato2: ");

        Registro registro = new Registro(id, nombre, dato1, dato2);
        String mensaje = registroService.agregarRegistro(registro);
        System.out.println(mensaje);
    }

    private void listarRegistros() {
        System.out.println("----- Lista de registros -----");

        List<Registro> registros = registroService.listarRegistros();

        if (registros.isEmpty()) {
            System.out.println("No hay registros guardados.");
            return;
        }

        for (Registro registro : registros) {
            System.out.println(registro);
        }
    }

    private void buscarRegistroPorId() {
        System.out.println("----- Buscar registro por id -----");

        int id = leerEntero("Ingrese el id a buscar: ");
        Registro registro = registroService.buscarRegistroPorId(id);

        if (registro != null) {
            System.out.println("Registro encontrado:");
            System.out.println(registro);
        } else {
            System.out.println("No se encontró ningún registro con ese id.");
        }
    }

    private void actualizarRegistro() {
        System.out.println("----- Actualizar registro -----");

        int id = leerEntero("Ingrese el id del registro a actualizar: ");
        Registro existente = registroService.buscarRegistroPorId(id);

        if (existente == null) {
            System.out.println("No existe un registro con ese id.");
            return;
        }

        System.out.println("Registro actual: " + existente);

        String nombre = leerTexto("Ingrese el nuevo nombre: ");
        String dato1 = leerTexto("Ingrese el nuevo dato1: ");
        String dato2 = leerTexto("Ingrese el nuevo dato2: ");

        Registro registroActualizado = new Registro(id, nombre, dato1, dato2);
        String mensaje = registroService.actualizarRegistro(registroActualizado);
        System.out.println(mensaje);
    }

    private void eliminarRegistro() {
        System.out.println("----- Eliminar registro -----");

        int id = leerEntero("Ingrese el id del registro a eliminar: ");
        String mensaje = registroService.eliminarRegistro(id);
        System.out.println(mensaje);
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
            }
        }
    }

    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("La entrada no puede estar vacía. Intente de nuevo.");
        }
    }
}
