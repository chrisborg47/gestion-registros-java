package entidades;

public class Registro {
    private int id;
    private String nombre;
    private String dato1;
    private String dato2;

    public Registro() {
    }

    public Registro(int id, String nombre, String dato1, String dato2) {
        this.id = id;
        this.nombre = nombre;
        this.dato1 = dato1;
        this.dato2 = dato2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dato1='" + dato1 + '\'' +
                ", dato2='" + dato2 + '\'' +
                '}';
    }
}