package biblioteca;

// Tecnica sugerida: EXTRACT CLASS (3)
// Antes: Socio mezclaba sus datos con el codigo de area, el numero y los
// metodos del telefono.
// Cambio: se creo la clase Telefono con esos datos y metodos, y Socio ahora
// solo guarda un objeto Telefono.
public class Telefono {
    private String telefonoCodigoArea;
    private String telefonoNumero;

    public Telefono(String telefonoCodigoArea, String telefonoNumero) {
        this.telefonoCodigoArea = telefonoCodigoArea;
        this.telefonoNumero = telefonoNumero;
    }

    public String obtenerTelefonoCompleto() {
        return "(" + telefonoCodigoArea + ") " + telefonoNumero;
    }

    public boolean esTelefonoLocal() {
        return telefonoCodigoArea.equals("999");
    }
}