package logica;

import datos.RegistroDAO;
import entidades.Registro;
import java.util.List;

public class RegistroService {

    private RegistroDAO registroDAO;

    public RegistroService() {
        registroDAO = new RegistroDAO();
    }

    public String agregarRegistro(Registro registro) {
        if (registro == null) {
            return "Error: el registro no puede ser nulo.";
        }

        if (registro.getId() <= 0) {
            return "Error: el id debe ser mayor que 0.";
        }

        if (registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            return "Error: el nombre no puede estar vacío.";
        }

        if (registro.getDato1() == null || registro.getDato1().trim().isEmpty()) {
            return "Error: dato1 no puede estar vacío.";
        }

        if (registro.getDato2() == null || registro.getDato2().trim().isEmpty()) {
            return "Error: dato2 no puede estar vacío.";
        }

        Registro existente = registroDAO.buscarRegistroPorId(registro.getId());
        if (existente != null) {
            return "Error: ya existe un registro con ese id.";
        }

        boolean guardado = registroDAO.guardarRegistro(registro);

        if (guardado) {
            return "Registro guardado correctamente.";
        } else {
            return "Error: no se pudo guardar el registro.";
        }
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
        if (registro == null) {
            return "Error: el registro no puede ser nulo.";
        }

        if (registro.getId() <= 0) {
            return "Error: el id debe ser mayor que 0.";
        }

        if (registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            return "Error: el nombre no puede estar vacío.";
        }

        if (registro.getDato1() == null || registro.getDato1().trim().isEmpty()) {
            return "Error: dato1 no puede estar vacío.";
        }

        if (registro.getDato2() == null || registro.getDato2().trim().isEmpty()) {
            return "Error: dato2 no puede estar vacío.";
        }

        Registro existente = registroDAO.buscarRegistroPorId(registro.getId());
        if (existente == null) {
            return "Error: no existe un registro con ese id.";
        }

        boolean actualizado = registroDAO.actualizarRegistro(registro);

        if (actualizado) {
            return "Registro actualizado correctamente.";
        } else {
            return "Error: no se pudo actualizar el registro.";
        }
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
        } else {
            return "Error: no se pudo eliminar el registro.";
        }
    }
}