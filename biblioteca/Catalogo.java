package biblioteca;

import java.util.ArrayList;
import java.util.List;

// Tecnica sugerida: REMOVE MIDDLE MAN (6)
// Antes: Main usaba la clase Recepcion, que solo llamaba a los metodos de Catalogo.
// Cambio: se elimino Recepcion y ahora Main usa Catalogo directamente.
public class Catalogo {
    private List<Libro> libros = new ArrayList<>();

    public void agregar(Libro libro) {
        libros.add(libro);
    }

    public Libro buscarPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public int total() {
        return libros.size();
    }
}