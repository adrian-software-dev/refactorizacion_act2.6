package biblioteca;

//Tecnica sugerida: EXTRACT CLASS
//Refactor: se creo la clase nueva de Telefono pues Socio se hacia cargo de sus
//responsabilidades de manera innecesaria
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
