package biblioteca;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        Recepcion recepcion = new Recepcion(catalogo);

        recepcion.registrarLibro(new Libro("Java Basico", "R. Perez", "978-1-11111-111-1", "NOVEDAD", 5, 300));
        recepcion.registrarLibro(new Libro("Estructuras de Datos", "M. Diaz", "978-2-22222-222-2", "NORMAL", 3, 400));
        System.out.println("Libros registrados: " + recepcion.totalLibros());

        Libro libro = recepcion.consultarLibro("java basico");
        Socio socio = new Socio("Ana Lopez", "Merida", "999", "1234567", 14);

        Date fechaPrestamo = new GregorianCalendar(2026, Calendar.OCTOBER, 1).getTime();
        Date fechaDevolucion = new GregorianCalendar(2026, Calendar.OCTOBER, 22).getTime();
        Prestamo prestamo = new Prestamo(libro, socio, fechaPrestamo, fechaDevolucion);

        System.out.println("-----------------------------");
        prestamo.imprimirResumen();
        System.out.println("-----------------------------");

        // HIDE DELEGATE
        System.out.println("Ciudad del socio: " + prestamo.getSocio().getCiudad());
        System.out.println("Telefono del socio: " + prestamo.getSocio().obtenerTelefonoCompleto());
        System.out.println("Autor del libro: " + prestamo.getLibro().getAutor());

        int retraso = prestamo.calcularDiasRetraso();
        System.out.println("Dias de retraso: " + retraso);
        System.out.println("Multa: $" + prestamo.calcularMulta(retraso));
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Telefono local: " + socio.esTelefonoLocal());
    }
}
