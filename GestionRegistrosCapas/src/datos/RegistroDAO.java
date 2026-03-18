package datos;

import entidades.Registro;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {

    private static final String NOMBRE_ARCHIVO = "registros.txt";
    private static final String NOMBRE_ARCHIVO_TEMPORAL = "registros_temp.txt";
    private final Path archivo;
    private final Path archivoTemporal;

    public RegistroDAO() {
        Path directorioProyecto = Paths.get("GestionRegistrosCapas");
        if (Files.exists(directorioProyecto) && Files.isDirectory(directorioProyecto)) {
            archivo = directorioProyecto.resolve(NOMBRE_ARCHIVO);
            archivoTemporal = directorioProyecto.resolve(NOMBRE_ARCHIVO_TEMPORAL);
        } else {
            archivo = Paths.get(NOMBRE_ARCHIVO);
            archivoTemporal = Paths.get(NOMBRE_ARCHIVO_TEMPORAL);
        }
    }

    public boolean guardarRegistro(Registro registro) {
        try {
            asegurarArchivo();
            try (BufferedWriter bw = Files.newBufferedWriter(
                    archivo,
                    StandardCharsets.UTF_8,
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND)) {
                bw.write(convertirALinea(registro));
                bw.newLine();
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el registro: " + e.getMessage());
            return false;
        }
    }

    public List<Registro> listarRegistros() {
        List<Registro> listaRegistros = new ArrayList<>();

        if (!Files.exists(archivo)) {
            return listaRegistros;
        }

        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null) {
                Registro registro = convertirDesdeLinea(linea);
                if (registro != null) {
                    listaRegistros.add(registro);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar los registros: " + e.getMessage());
        }

        return listaRegistros;
    }

    public Registro buscarRegistroPorId(int idBuscado) {
        if (!Files.exists(archivo)) {
            return null;
        }

        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null) {
                Registro registro = convertirDesdeLinea(linea);
                if (registro != null && registro.getId() == idBuscado) {
                    return registro;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el registro: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizarRegistro(Registro registroActualizado) {
        if (!Files.exists(archivo)) {
            return false;
        }

        boolean actualizado = false;

        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8);
             BufferedWriter bw = Files.newBufferedWriter(archivoTemporal, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                Registro registro = convertirDesdeLinea(linea);

                if (registro != null && registro.getId() == registroActualizado.getId()) {
                    bw.write(convertirALinea(registroActualizado));
                    actualizado = true;
                } else {
                    bw.write(linea);
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al actualizar el registro: " + e.getMessage());
            return false;
        }

        if (!actualizado) {
            eliminarTemporalSiExiste();
            return false;
        }

        return reemplazarArchivoOriginal();
    }

    public boolean eliminarRegistro(int idEliminar) {
        if (!Files.exists(archivo)) {
            return false;
        }

        boolean eliminado = false;

        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8);
             BufferedWriter bw = Files.newBufferedWriter(archivoTemporal, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                Registro registro = convertirDesdeLinea(linea);

                if (registro != null && registro.getId() == idEliminar) {
                    eliminado = true;
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
            return false;
        }

        if (!eliminado) {
            eliminarTemporalSiExiste();
            return false;
        }

        return reemplazarArchivoOriginal();
    }

    private Registro convertirDesdeLinea(String linea) {
        String[] datos = linea.split("\\|", -1);

        if (datos.length != 4) {
            return null;
        }

        try {
            int id = Integer.parseInt(datos[0].trim());
            String nombre = datos[1].trim();
            String dato1 = datos[2].trim();
            String dato2 = datos[3].trim();
            return new Registro(id, nombre, dato1, dato2);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String convertirALinea(Registro registro) {
        return registro.getId() + "|"
                + limpiarTexto(registro.getNombre()) + "|"
                + limpiarTexto(registro.getDato1()) + "|"
                + limpiarTexto(registro.getDato2());
    }

    private String limpiarTexto(String texto) {
        return texto.trim().replace("|", "/");
    }

    private void asegurarArchivo() throws IOException {
        Path directorioPadre = archivo.getParent();
        if (directorioPadre != null && !Files.exists(directorioPadre)) {
            Files.createDirectories(directorioPadre);
        }
        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
        }
    }

    private boolean reemplazarArchivoOriginal() {
        try {
            Files.move(archivoTemporal, archivo, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("No se pudo reemplazar el archivo original: " + e.getMessage());
            eliminarTemporalSiExiste();
            return false;
        }
    }

    private void eliminarTemporalSiExiste() {
        try {
            Files.deleteIfExists(archivoTemporal);
        } catch (IOException e) {
            System.out.println("No se pudo eliminar el archivo temporal: " + e.getMessage());
        }
    }
}
