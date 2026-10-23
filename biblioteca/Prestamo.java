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
        // Usamos FechaExtension y su método diasHasta
        return new FechaExtension(calcularFechaVencimiento()).diasHasta(fechaDevolucion);
    }
    // MOVE METHOD
    public double calcularMulta(int diasRetraso) {
        // Delega el cálculo al objeto Libro
        return this.libro.calcularMulta(diasRetraso);
    }

   public class FechaExtension {
        private final Date fecha;
    
        public FechaExtension(Date fecha) {
            this.fecha = fecha;
        }
    
        public String formatear() {
            if (fecha == null) return "N/A";
            return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        }
    
        public boolean esFinDeSemana() {
            if (fecha == null) return false;
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fecha);
            int dia = calendario.get(Calendar.DAY_OF_WEEK);
            return dia == Calendar.SATURDAY || dia == Calendar.SUNDAY;
        }
    
        public int diasHasta(Date hasta) {
            if (fecha == null || hasta == null) return 0;
            double milisPorDia = 24.0 * 60 * 60 * 1000;
            return (int) Math.round((hasta.getTime() - this.fecha.getTime()) / milisPorDia);
        }
    
        public Date getFecha() {
            return fecha;
        }
    }
    public void imprimirResumen() {
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Socio: " + socio.getNombre());
        System.out.println("Prestado: " + new FechaExtension(fechaPrestamo).formatear());
        
        // Almacenamos la extensión en una variable para evitar recalcular el vencimiento dos veces
        FechaExtension fechaVencimiento = new FechaExtension(calcularFechaVencimiento());
        System.out.println("Vence: " + fechaVencimiento.formatear());
        System.out.println("Vence en fin de semana: " + fechaVencimiento.esFinDeSemana());
        
        System.out.println("Devuelto: " + new FechaExtension(fechaDevolucion).formatear());
    }
}
