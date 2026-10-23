package biblioteca;

public class Libro {
    private String titulo;
    private String autor;
    private Isbn isbn;
    private String categoria;       // "NOVEDAD" o "NORMAL"
    private double multaPorDia;
    private double valorReposicion;

    public Libro(String titulo, String autor, String isbn, String categoria,
                 double multaPorDia, double valorReposicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = new Isbn(isbn);
        this.categoria = categoria;
        this.multaPorDia = multaPorDia;
        this.valorReposicion = valorReposicion;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn.getValor(); }
    public String getCategoria() { return categoria; }
    public double getMultaPorDia() { return multaPorDia; }
    public double getValorReposicion() { return valorReposicion; }
    
    // Pase el metodo calcularMulta que usaba Solo usa datos de Libro (multa por día, categoría, valor dereposición), 
    // así que lo movi a Libro o sea aca.
    //
    public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0;
        }
        double multa = diasRetraso * this.multaPorDia;
        
        if (this.categoria.equals("NOVEDAD")) {
            multa = multa * 2;
        }
        
        if (multa > this.valorReposicion) {
            multa = this.valorReposicion;
        }
        
        return multa;
    }
}
