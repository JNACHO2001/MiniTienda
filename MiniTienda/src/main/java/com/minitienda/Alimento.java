/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Coder
 */
public class Alimento extends Producto{
       private String fechaVencido;

    public Alimento(String fechaVencido, String nombre, Double precio, int stock) {
        super(nombre, precio, stock);
        this.fechaVencido = fechaVencido;
    }

    public String getFechaVencido() {
        return fechaVencido;
    }

    public void setFechaVencido(String fechaVencido) {
        this.fechaVencido = fechaVencido;
    }

    @Override
    protected String getDescription() {
        return "Alimento: " + getNombre() + " - Precio: $" + getPrecio() +" - Stock: "+ getStock() + " - Vence: " + fechaVencido;
    }
    
}
