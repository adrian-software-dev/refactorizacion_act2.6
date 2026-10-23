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

    // Calcula los días de retraso utilizando la extensión local de fechas
     public int calcularDiasRetraso() {
        // Usamos FechaExtension y su método diasHasta
        return new FechaExtension(calcularFechaVencimiento()).diasHasta(fechaDevolucion);
    }
    // Se creo este metodo despues de refactorizarlo con el Move method, y como Libro ahora tiene calcularMulta()
    // ahora la clase Prestamo no sabe cómo calcularlo a menos que delegue la llamada al libro que tiene asociado.
    //y para eso agregamos el metodo para que llame al libro.

    public double calcularMulta(int diasRetraso) {
        // Delega el cálculo al objeto Libro
        return this.libro.calcularMulta(diasRetraso);
    }


    // ACA SE APLICO INTRODUCE LOCAL EXTENSION: Clase interna envoltorio para encapsular operaciones sobre Date
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
        
        // Calcula los días transcurridos desde esta fecha hasta otra fecha límite
        public int diasHasta(Date hasta) {
            if (fecha == null || hasta == null) return 0;
            double milisPorDia = 24.0 * 60 * 60 * 1000;
            return (int) Math.round((hasta.getTime() - this.fecha.getTime()) / milisPorDia);
        }
        // Esto es para obtener la fecha original si se requiere
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
