package logica;

import datos.RegistroDAO;
import entidades.Registro;
import java.util.List;

public class RegistroService {

    private final RegistroDAO registroDAO;

    public RegistroService() {
        registroDAO = new RegistroDAO();
    }

    public String agregarRegistro(Registro registro) {
        String mensajeValidacion = validarRegistro(registro);
        if (mensajeValidacion != null) {
            return mensajeValidacion;
        }

        normalizarRegistro(registro);

        Registro existente = registroDAO.buscarRegistroPorId(registro.getId());
        if (existente != null) {
            return "Error: ya existe un registro con ese id.";
        }

        boolean guardado = registroDAO.guardarRegistro(registro);
        if (guardado) {
            return "Registro guardado correctamente.";
        }
        return "Error: no se pudo guardar el registro.";
    }

    public List<Registro> listarRegistros() {
        return registroDAO.listarRegistros();
    }

    public Registro buscarRegistroPorId(int id) {
        if (id <= 0) {
            return null;
        }

        return registroDAO.buscarRegistroPorId(id);
    }

    public String actualizarRegistro(Registro registro) {
        String mensajeValidacion = validarRegistro(registro);
        if (mensajeValidacion != null) {
            return mensajeValidacion;
        }

        normalizarRegistro(registro);

        Registro existente = registroDAO.buscarRegistroPorId(registro.getId());
        if (existente == null) {
            return "Error: no existe un registro con ese id.";
        }

        boolean actualizado = registroDAO.actualizarRegistro(registro);
        if (actualizado) {
            return "Registro actualizado correctamente.";
        }
        return "Error: no se pudo actualizar el registro.";
    }

    public String eliminarRegistro(int id) {
        if (id <= 0) {
            return "Error: el id debe ser mayor que 0.";
        }

        Registro existente = registroDAO.buscarRegistroPorId(id);
        if (existente == null) {
            return "Error: no existe un registro con ese id.";
        }

        boolean eliminado = registroDAO.eliminarRegistro(id);
        if (eliminado) {
            return "Registro eliminado correctamente.";
        }
        return "Error: no se pudo eliminar el registro.";
    }

    private String validarRegistro(Registro registro) {
        if (registro == null) {
            return "Error: el registro no puede ser nulo.";
        }

        if (registro.getId() <= 0) {
            return "Error: el id debe ser mayor que 0.";
        }

        if (estaVacio(registro.getNombre())) {
            return "Error: el nombre no puede estar vacío.";
        }

        if (estaVacio(registro.getDato1())) {
            return "Error: dato1 no puede estar vacío.";
        }

        if (estaVacio(registro.getDato2())) {
            return "Error: dato2 no puede estar vacío.";
        }

        return null;
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private void normalizarRegistro(Registro registro) {
        registro.setNombre(registro.getNombre().trim());
        registro.setDato1(registro.getDato1().trim());
        registro.setDato2(registro.getDato2().trim());
    }
}
