package biblioteca;

public class Libro {
    private String titulo;
    private String autor;

    // Tecnica sugerida: INLINE CLASS (4)
    // Antes: el ISBN estaba en la clase Isbn, que solo guardaba un String.
    // Cambio: se elimino la clase Isbn y ahora isbn es un String dentro de Libro.
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

    // Tecnica sugerida: MOVE METHOD (1)
    // Antes: este metodo estaba en Prestamo, pero solo usa datos de Libro
    // (multa por dia, categoria y valor de reposicion).
    // Cambio: se movio aqui. Prestamo.calcularMulta() ahora solo llama a este.
    public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0;
        }

        double multa = diasRetraso * this.multaPorDia;

        // Los libros de novedad pagan el doble
        if (this.categoria.equals("NOVEDAD")) {
            multa = multa * 2;
        }

        // La multa nunca pasa del valor de reposicion del libro
        if (multa > this.valorReposicion) {
            multa = this.valorReposicion;
        }

        return multa;
    }
}