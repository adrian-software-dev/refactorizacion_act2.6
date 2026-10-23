package biblioteca;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Tecnica sugerida: INTRODUCE LOCAL EXTENSION
// Antes: Prestamo tenia los metodos diasEntre, formatearFecha y esFinDeSemana,
// pero solo trabajaban con un Date y no con datos del prestamo.
// Cambio: se creo esta clase que extiende de Date y se movieron esos tres
// metodos aqui. Prestamo ahora usa FechaBiblioteca en vez de Date.
public class FechaBiblioteca extends Date {

    // Constructor que copia un Date normal a una FechaBiblioteca
    public FechaBiblioteca(Date fecha) {
        super(fecha.getTime());
    }

    // Dias entre esta fecha y la fecha "hasta" (antes recibia desde y hasta,
    // ahora "desde" es la propia fecha)
    public int diasEntre(Date hasta) {
        double milisPorDia = 24.0 * 60 * 60 * 1000;
        return (int) Math.round((hasta.getTime() - this.getTime()) / milisPorDia);
    }

    // Regresa la fecha con formato dd/MM/yyyy
    public String formatearFecha() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this);
    }

    // Indica si la fecha cae en sabado o domingo
    public boolean esFinDeSemana() {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(this);
        int dia = calendario.get(Calendar.DAY_OF_WEEK);
        return dia == Calendar.SATURDAY || dia == Calendar.SUNDAY;
    }
}