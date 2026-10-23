package biblioteca;

public class Socio {
    private String nombre;
    private String ciudad;

    // Tecnica sugerida: EXTRACT CLASS (3)
    // Antes: Socio guardaba el codigo de area, el numero y los metodos del telefono.
    // Cambio: esos datos y metodos pasaron a la clase Telefono y Socio solo
    // tiene un atributo de ese tipo.
    private Telefono telefono;

    // Tecnica sugerida: MOVE FIELD (2)
    // Cambio: se elimino diasPlazoPrestamo de Socio (ahora esta en Prestamo).
    public Socio(String nombre, String ciudad, Telefono telefono) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Telefono getTelefono() {
        return telefono;
    }
}