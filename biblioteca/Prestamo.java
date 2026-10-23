package biblioteca;

import java.util.Date;

public class Prestamo {
    private Libro libro;
    private Socio socio;
    private FechaBiblioteca fechaPrestamo;
    private FechaBiblioteca fechaDevolucion;

    // Tecnica sugerida: MOVE FIELD (2)
    // Antes: diasPlazoPrestamo estaba en Socio, pero Socio nunca lo usaba.
    // Cambio: el atributo se movio a Prestamo, que es la unica clase que lo usa.
    private int diasPlazoPrestamo;

    // INTRODUCE LOCAL EXTENSION (8): las fechas se guardan como FechaBiblioteca.
    public Prestamo(Libro libro, Socio socio, Date fechaPrestamo, Date fechaDevolucion,
            int diasPlazoPrestamo) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = new FechaBiblioteca(fechaPrestamo);
        this.fechaDevolucion = new FechaBiblioteca(fechaDevolucion);
        this.diasPlazoPrestamo = diasPlazoPrestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public Socio getSocio() {
        return socio;
    }

    public int getDiasPlazoPrestamo() {
        return diasPlazoPrestamo;
    }

    // Tecnica sugerida: INTRODUCE FOREIGN METHOD (7)
    // Antes: la suma de dias se hacia con aritmetica de milisegundos directo
    // dentro de calcularFechaVencimiento().
    // Cambio: se creo sumarDias(Date, int) y calcularFechaVencimiento() lo usa.
    private Date sumarDias(Date fecha, int dias) {
        return new Date(fecha.getTime() + dias * 24L * 60 * 60 * 1000);
    }

    public FechaBiblioteca calcularFechaVencimiento() {
        return new FechaBiblioteca(sumarDias(fechaPrestamo, diasPlazoPrestamo));
    }

    // INTRODUCE LOCAL EXTENSION (8): el calculo de dias ahora lo hace FechaBiblioteca.
    public int calcularDiasRetraso() {
        return calcularFechaVencimiento().diasEntre(fechaDevolucion);
    }

    // Tecnica sugerida: MOVE METHOD (1)
    // Antes: calcularMulta() estaba aqui, pero solo usaba datos de Libro
    // (multa por dia, categoria y valor de reposicion).
    // Cambio: el calculo se movio a Libro.calcularMulta(). Aqui solo queda este
    // metodo que le pasa la llamada al libro, para que Main siga usando Prestamo.
    public double calcularMulta(int diasRetraso) {
        return libro.calcularMulta(diasRetraso);
    }

    // INTRODUCE LOCAL EXTENSION (7): formatearFecha y esFinDeSemana ahora estan en
    // FechaBiblioteca.
    public void imprimirResumen() {
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Socio: " + socio.getNombre());
        System.out.println("Prestado: " + fechaPrestamo.formatearFecha());
        System.out.println("Vence: " + calcularFechaVencimiento().formatearFecha());
        System.out.println("Vence en fin de semana: " + calcularFechaVencimiento().esFinDeSemana());
        System.out.println("Devuelto: " + fechaDevolucion.formatearFecha());
    }

    // Tecnica sugerida: HIDE DELEGATE (5)
    // Antes: Main usaba prestamo.getSocio().getCiudad() y similares.
    // Cambio: se agregaron getCiudadSocio() y getTelefonoSocio() para que Main
    // solo hable con Prestamo y no conozca la estructura interna de Socio.
    public String getCiudadSocio() {
        return socio.getCiudad();
    }

    public String getTelefonoSocio() {
        return socio.getTelefono().obtenerTelefonoCompleto();
    }
}