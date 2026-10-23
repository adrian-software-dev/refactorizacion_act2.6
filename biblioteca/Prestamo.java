package biblioteca;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Prestamo {
    private Libro libro;
    private Socio socio;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(Libro libro, Socio socio, Date fechaPrestamo, Date fechaDevolucion) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() { return libro; }
    public Socio getSocio() { return socio; }

    // INTRODUCE FOREIGN METHOD
    public Date calcularFechaVencimiento() {
        return new Date(fechaPrestamo.getTime() + socio.getDiasPlazoPrestamo() * 24L * 60 * 60 * 1000);
    }

    public int calcularDiasRetraso() {
        return diasEntre(calcularFechaVencimiento(), fechaDevolucion);
    }

    // MOVE METHOD
    public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0;
        }
        double multa = diasRetraso * libro.getMultaPorDia();
        if (libro.getCategoria().equals("NOVEDAD")) {
            multa = multa * 2;
        }
        if (multa > libro.getValorReposicion()) {
            multa = libro.getValorReposicion();
        }
        return multa;
    }

    // INTRODUCE LOCAL EXTENSION
    private int diasEntre(Date desde, Date hasta) {
        double milisPorDia = 24.0 * 60 * 60 * 1000;
        return (int) Math.round((hasta.getTime() - desde.getTime()) / milisPorDia);
    }

    private String formatearFecha(Date fecha) {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    private boolean esFinDeSemana(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int dia = calendario.get(Calendar.DAY_OF_WEEK);
        return dia == Calendar.SATURDAY || dia == Calendar.SUNDAY;
    }

    public void imprimirResumen() {
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Socio: " + socio.getNombre());
        System.out.println("Prestado: " + formatearFecha(fechaPrestamo));
        System.out.println("Vence: " + formatearFecha(calcularFechaVencimiento()));
        System.out.println("Vence en fin de semana: " + esFinDeSemana(calcularFechaVencimiento()));
        System.out.println("Devuelto: " + formatearFecha(fechaDevolucion));
    }
}
