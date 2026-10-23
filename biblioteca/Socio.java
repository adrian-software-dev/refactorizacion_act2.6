package biblioteca;

// EXTRACT CLASS


// MOVE FIELD
public class Socio {
    private String nombre;
    private String ciudad;
    private String telefonoCodigoArea;
    private String telefonoNumero;
    private int diasPlazoPrestamo;

    public Socio(String nombre, String ciudad, String telefonoCodigoArea,
                 String telefonoNumero, int diasPlazoPrestamo) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefonoCodigoArea = telefonoCodigoArea;
        this.telefonoNumero = telefonoNumero;
        this.diasPlazoPrestamo = diasPlazoPrestamo;
    }

    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public int getDiasPlazoPrestamo() { return diasPlazoPrestamo; }

    public String obtenerTelefonoCompleto() {
        return "(" + telefonoCodigoArea + ") " + telefonoNumero;
    }

    public boolean esTelefonoLocal() {
        return telefonoCodigoArea.equals("999");
    }
}
