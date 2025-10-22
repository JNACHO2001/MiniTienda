package com.minitienda;



/**
 *
 * @author Coder
 */
public class MiniTienda {

    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        
        inventario.addProducto("masa ", 2005,10);
        
        inventario.mostrarProductos();
        
      
        
    }
}
