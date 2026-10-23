package biblioteca;

//Tecnica Sugerida: INLINE CLASS
//Refactor: al aplicar esta tecnica, todos los parametros de la clase Isbn
//pasaron a formar parte de Libro.
public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria; // "NOVEDAD" o "NORMAL"
    private double multaPorDia;
    private double valorReposicion;

    public Libro(String titulo, String autor, String isbn, String categoria,
            double multaPorDia, double valorReposicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.multaPorDia = multaPorDia;
        this.valorReposicion = valorReposicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getMultaPorDia() {
        return multaPorDia;
    }

    public double getValorReposicion() {
        return valorReposicion;
    }
}
