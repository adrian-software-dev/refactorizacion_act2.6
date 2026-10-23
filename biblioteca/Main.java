package biblioteca;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        // Tecnica sugerida: REMOVE MIDDLE MAN
        // Refactor: Se elimino la clase Recepcion pues su existencia era puramente para
        // como intermediario para catalogo.
        Catalogo catalogo = new Catalogo();

        catalogo.agregar(new Libro("Java Basico", "R. Perez", "978-1-11111-111-1", "NOVEDAD", 5, 300));
        catalogo.agregar(new Libro("Estructuras de Datos", "M. Diaz", "978-2-22222-222-2", "NORMAL", 3, 400));
        System.out.println("Libros registrados: " + catalogo.total());

        Libro libro = catalogo.buscarPorTitulo("java basico");
        Telefono telefono = new Telefono("999", "1234567");
        Socio socio = new Socio("Ana Lopez", "Merida");

        Date fechaPrestamo = new GregorianCalendar(2026, Calendar.OCTOBER, 1).getTime();
        Date fechaDevolucion = new GregorianCalendar(2026, Calendar.OCTOBER, 22).getTime();
        Prestamo prestamo = new Prestamo(libro, socio, fechaPrestamo, fechaDevolucion, telefono, 14);

        System.out.println("-----------------------------");
        prestamo.imprimirResumen();
        System.out.println("-----------------------------");

        System.out.println("Ciudad del socio: " + prestamo.getCiudadSocio());
        System.out.println("Telefono del socio: " + prestamo.getTelefonoSocio());
        System.out.println("Autor del libro: " + prestamo.getLibro().getAutor());

        int retraso = prestamo.calcularDiasRetraso();
        System.out.println("Dias de retraso: " + retraso);
        System.out.println("Multa: $" + prestamo.calcularMulta(retraso));
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Telefono local: " + telefono.esTelefonoLocal());
    }
}
