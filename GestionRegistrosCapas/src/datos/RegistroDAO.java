package datos;

import entidades.Registro;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {

    private final String ARCHIVO = "registros.txt";
    private final String ARCHIVO_TEMPORAL = "registros_temp.txt";

    public boolean guardarRegistro(Registro registro) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(registro.getId() + "|" +
                     registro.getNombre() + "|" +
                     registro.getDato1() + "|" +
                     registro.getDato2());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar el registro: " + e.getMessage());
            return false;
        }
    }

    public List<Registro> listarRegistros() {
        List<Registro> listaRegistros = new ArrayList<>();

        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return listaRegistros;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");

                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String dato1 = datos[2];
                    String dato2 = datos[3];

                    Registro registro = new Registro(id, nombre, dato1, dato2);
                    listaRegistros.add(registro);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al listar los registros: " + e.getMessage());
        }

        return listaRegistros;
    }

    public Registro buscarRegistroPorId(int idBuscado) {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");

                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0]);

                    if (id == idBuscado) {
                        String nombre = datos[1];
                        String dato1 = datos[2];
                        String dato2 = datos[3];

                        return new Registro(id, nombre, dato1, dato2);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al buscar el registro: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizarRegistro(Registro registroActualizado) {
        File archivoOriginal = new File(ARCHIVO);
        File archivoTemporal = new File(ARCHIVO_TEMPORAL);

        if (!archivoOriginal.exists()) {
            return false;
        }

        boolean actualizado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");

                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0]);

                    if (id == registroActualizado.getId()) {
                        bw.write(registroActualizado.getId() + "|" +
                                 registroActualizado.getNombre() + "|" +
                                 registroActualizado.getDato1() + "|" +
                                 registroActualizado.getDato2());
                        actualizado = true;
                    } else {
                        bw.write(linea);
                    }
                    bw.newLine();
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al actualizar el registro: " + e.getMessage());
            return false;
        }

        if (actualizado) {
            if (archivoOriginal.delete()) {
                return archivoTemporal.renameTo(archivoOriginal);
            } else {
                System.out.println("No se pudo eliminar el archivo original.");
                return false;
            }
        } else {
            archivoTemporal.delete();
            return false;
        }
    }

    public boolean eliminarRegistro(int idEliminar) {
        File archivoOriginal = new File(ARCHIVO);
        File archivoTemporal = new File(ARCHIVO_TEMPORAL);

        if (!archivoOriginal.exists()) {
            return false;
        }

        boolean eliminado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");

                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0]);

                    if (id == idEliminar) {
                        eliminado = true;
                    } else {
                        bw.write(linea);
                        bw.newLine();
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
            return false;
        }

        if (eliminado) {
            if (archivoOriginal.delete()) {
                return archivoTemporal.renameTo(archivoOriginal);
            } else {
                System.out.println("No se pudo eliminar el archivo original.");
                return false;
            }
        } else {
            archivoTemporal.delete();
            return false;
        }
    }
}