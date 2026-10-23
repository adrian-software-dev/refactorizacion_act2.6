package biblioteca;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        // REMOVE MIDDLE MAN (6)
        // Se usa Catalogo directamente, ya no existe la clase Recepcion.
        Catalogo catalogo = new Catalogo();

        catalogo.agregar(new Libro("Java Basico", "R. Perez", "978-1-11111-111-1", "NOVEDAD", 5, 300));
        catalogo.agregar(new Libro("Estructuras de Datos", "M. Diaz", "978-2-22222-222-2", "NORMAL", 3, 400));
        System.out.println("Libros registrados: " + catalogo.total());

        Libro libro = catalogo.buscarPorTitulo("java basico");

        // EXTRACT CLASS (3)
        // El telefono ahora se crea aparte y se le pasa al Socio.
        Telefono telefono = new Telefono("999", "1234567");
        Socio socio = new Socio("Ana Lopez", "Merida", telefono);

        Date fechaPrestamo = new GregorianCalendar(2026, Calendar.OCTOBER, 1).getTime();
        Date fechaDevolucion = new GregorianCalendar(2026, Calendar.OCTOBER, 22).getTime();

        // MOVE FIELD (2): los 14 dias de plazo se le pasan al Prestamo, no al Socio.
        Prestamo prestamo = new Prestamo(libro, socio, fechaPrestamo, fechaDevolucion, 14);

        System.out.println("-----------------------------");
        prestamo.imprimirResumen();
        System.out.println("-----------------------------");

        // HIDE DELEGATE (5)
        // Se usan getCiudadSocio() y getTelefonoSocio() de Prestamo en lugar de
        // prestamo.getSocio().getCiudad() y similares.
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