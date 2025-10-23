
public class Electrodomestico extends Producto {

    private  Categoria categoria; 

    // Constructor corregido
    public Electrodomestico(String nombre, Double precio, int stock, Categoria categoriaSeleccionada) {
        super(nombre, precio, stock);
        this.categoria = categoriaSeleccionada;
    }
    
     


    // Getter para categoría
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    protected String getDescription() {
        return "Electrodoméstico: " + getNombre() + " - Precio: $" + getPrecio()
                + " - Stock: " + getStock() + " - Categoría: " + categoria.getNombre();
    }
}
