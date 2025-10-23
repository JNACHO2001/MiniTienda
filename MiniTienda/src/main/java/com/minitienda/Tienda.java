
import javax.swing.JOptionPane;


public class Tienda {
    List<Categoria> categorias = new ArrayList<>();

    ArrayList<Producto> alimentos = new ArrayList();
    ArrayList<Producto> electrodomesticos = new ArrayList();

    // creamos un  metodo para iniciar cuando estemos en main 
    public void iniciar() {
        int opcion;

        try {

            do {

                String menu = """
                    ===MENU PRINCIPAL===
                    1. Gestionar Alimentos
                    2. Gestionar Electrodomésticos
                    3. Salir
                    """;
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

                // llamamos a cada logica para implementarlas  
                switch (opcion) {
                    case 1 ->
                        logicaAlimentos();

                    case 2 ->
                        logicaElectrodomestico();

                    case 3 ->
                        cerrarPrograma();

                    default ->
                        JOptionPane.showMessageDialog(null, "opcion invalida");
                }

            } while (opcion != 3);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SE PRODUJO UN ERROR");
        }

    }

    // creamos un menu para no duplicar  codigo
    // retornamos  el menu para llamar en otro lado el methodo
    public String menu() {
        String menu = """
                         ---- Menu ---
                         1. Agregar 
                         2. Listar
                         3. Editar 
                         4. Eliminar
                         5. Volver
                         """;

        return menu;

    }

    // creamos la logica de alimentos  y traemos el menu 
    public void logicaAlimentos() {
        int opcion;

        try {
            do {
                menu();

                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu()));

                switch (opcion) {
                    case 1 ->
                        agregarAlimento();
                    case 2 ->
                        listarAlimentos();
                    case 3 ->
                        editarAlimento();
                    case 4 ->
                        eliminarAlimento();
                    case 5 ->
                        salirAlMenu();
                    default ->
                        JOptionPane.showMessageDialog(null, "opcion invalida");
                }

            } while (opcion != 5);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SE PRODUJO UN ERROR");
        }

    }

    public void agregarAlimento() {
        String nombre = JOptionPane.showInputDialog(this, "--Ingresa el nombre del alimento--").trim();
        if (nombre == null) {
            JOptionPane.showMessageDialog(null, "--El campo no puede estar vacio--");
            return;
        }
        // llamamos al metodo y le pasamos el parametro nombre 
        // para que compare con los nombre de los alimentos que ya tiene 
        if (existeProductoNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "--El producto ya existe");
            return;
        }

        String precioInput = JOptionPane.showInputDialog(this, "--Ingresa el precio del alimento--").trim();
        if (precioInput == null) {
            return;
        }
        double precio = Double.parseDouble(precioInput);
        if (precio <= 0) {
            JOptionPane.showMessageDialog(null, "--El valor debe ser mayor a 0--");
            return;
        }

        String fechaVencido = JOptionPane.showInputDialog(this, "--Ingresa la fecha de vencimiento--").trim();
        if (fechaVencido == null) {
            JOptionPane.showMessageDialog(null, "--El campo no puede estar vacio--");
            return;
        }

        String cantidadInput = JOptionPane.showInputDialog(this, "--Ingresa la cantidad del producto--").trim();
        if (cantidadInput == null) {
            return;
        }
        int stock = Integer.parseInt(cantidadInput);
        if (stock <= 0) {
            JOptionPane.showMessageDialog(null, "--El valor debe ser mayor a 0--");
            return;
        }

        // instanciamos un alimento y le pasamos los aprametros que recibe 
        Alimento alimento = new Alimento(fechaVencido, nombre, precio, stock);
        // agregamos un nuevo alimento a la lita de alimentos 

        alimentos.add(alimento);

        System.out.println(alimentos);
        JOptionPane.showMessageDialog(null, "--Producto agregado con exito--");

    }

    public void listarAlimentos() {

        // validar que los campos no entren vacios 
        if (alimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "--Debe agregar productos --");

            return;

        }

        // creamos este string para concatenar objetos y cadenas de texto
        StringBuilder lista = new StringBuilder("Lista de Alimentos:\n");

        // recorremos la lista y se imprime los datos de todo el alimento,el appen es para insertar texto 
        alimentos.forEach((alimento) -> {
            lista.append(alimento.getDescription()).append("\n");

        });

        JOptionPane.showMessageDialog(null, lista);

    }

    public void salirAlMenu() {
        JOptionPane.showMessageDialog(null, "volviendo...");
        return;

    }

    public void cerrarPrograma() {
        JOptionPane.showMessageDialog(null, "Hasta pronto..");
        System.exit(0);
    }

    // validacion si el producto ya esta registrado
    // creamos un metodo boolean para que no sretorne tru o false 
    // le pasamos un argumento que recibe 
    public boolean existeProductoNombre(String nombre) {
        // recorremos el array de alimentos 
        for (Producto producto : alimentos) {
            // si el producto que ingresa el usuario es igual al que esta reccoriendo el array entonces es true
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return true;

            }

        }

        return false;

    }

    public void editarAlimento() {
        if (alimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "--NO hay alimentos para editar--");
            return;
        }

        String nombre = JOptionPane.showInputDialog(this, "--Ingresa el producto a editar--");
        // creamos una variable de control paracerrar el ciclo
        boolean encontrado = false;
// recorremos los alimentos 
        for (Producto alimento : alimentos) {
            // validamos que se a al que el usario ingresa 
            if (alimento.getNombre().equalsIgnoreCase(nombre)) {
                // cambiamos la variable de control 
                encontrado = true;
                JOptionPane.showMessageDialog(null, "--Producto encontrado: " + nombre);

                try {
                    // pedimos los nuevos precios 
                    String precioInput = JOptionPane.showInputDialog(this, "--Ingresa el nuevo precio--");
                    String cantidadInput = JOptionPane.showInputDialog(this, "--Ingresa la nueva cantidad--");
                    // parseamos todo
                    double nuevoPrecio = Double.parseDouble(precioInput);
                    int nuevaCantidad = Integer.parseInt(cantidadInput);
                    // actualizamos con los sets
                    alimento.setPrecio(nuevoPrecio);
                    alimento.setStock(nuevaCantidad);

                    JOptionPane.showMessageDialog(null, "--Producto actualizado--");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "--Error: Debes ingresar números válidos--");
                }

                break; // Salimos del ciclo una vez actualizado
            }
        }
        // si no lo encuentra salta aca

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "--Producto no encontrado--");
        }
    }

    public void eliminarAlimento() {
        if (alimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "--no hay nada que eliminar");
            return;

        }

        String nombre = JOptionPane.showInputDialog(this, "--Ingresa el producto a eliminar--");
        // variable de control
        boolean encontrado = false;

        // rrcorremos todo los espacios  del array
        for (int i = 0; i < alimentos.size(); i++) {
            // le decimos que vamos a obtener todo el objeto de el indicr donde nos encontramos
            Producto alimento = alimentos.get(i);
            //comparamos 
            if (alimento.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = true;
                // borramos ese indice con su objeto
                alimentos.remove(i);
                JOptionPane.showMessageDialog(null, "--Producto eliminado--");

            }
            break;

        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "--Producto no encontrado--");

        }

    }

    public void logicaElectrodomestico() {

        int opcion;
        menu();

        do {

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu()));

            try {

                switch (opcion) {
                    case 1 ->
                        agregarElectrodomestico();

                    case 2 ->
                        listarElectrodomestico();

                    case 3 ->
                        editarElectrodomestico();

                    case 4 ->
                        eliminarElectrodomestico();

                    default ->
                        JOptionPane.showMessageDialog(null, "opcion invalida");
                }

            } catch (Exception e) {
            }

        } while (opcion != 5);

    }

    public void agregarElectrodomestico() {
        // Suponiendo que 'categorias' está declarado como atributo de clase
        if (categorias.isEmpty()) {
            categorias.add(new Categoria("Hogar"));
            categorias.add(new Categoria("Electronica"));
            categorias.add(new Categoria("Juguetes"));
        }

        String nombre = JOptionPane.showInputDialog("-- Ingresa el nombre del electrodomestico --");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "-- El nombre no puede estar vacío --");
            return;
        }

        if (existeProductoElectro(nombre)) {
            JOptionPane.showMessageDialog(null, "-- El producto ya existe --");
            return;
        }

        double precio = 0;
        try {
            String precioInput = JOptionPane.showInputDialog("-- Ingresa el precio --").trim();
            precio = Double.parseDouble(precioInput);
            if (precio <= 0) {
                JOptionPane.showMessageDialog(null, "-- El precio debe ser mayor que cero --");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "-- Precio inválido, ingresa un número válido --");
            return;
        }

        int stock = 0;
        try {
            String cantidadInput = JOptionPane.showInputDialog("-- Ingresa la cantidad --").trim();
            stock = Integer.parseInt(cantidadInput);
            if (stock <= 0) {
                JOptionPane.showMessageDialog(null, "-- La cantidad debe ser mayor que cero --");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "-- Cantidad inválida, ingresa un número válido --");
            return;
        }

        // Mostrar opciones de categoría
        StringBuilder opciones = new StringBuilder("-- Categorías disponibles --\n");
        for (int i = 0; i < categorias.size(); i++) {
            opciones.append((i + 1)).append(". ").append(categorias.get(i).getNombre()).append("\n");
        }

        String opCate = JOptionPane.showInputDialog(opciones.toString());
        if (opCate == null || opCate.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "-- No seleccionaste ninguna opción --");
            return;
        }

        int opcion = 0;
        try {
            opcion = Integer.parseInt(opCate.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "-- Opción inválida, ingresa un número válido --");
            return;
        }

        if (opcion < 1 || opcion > categorias.size()) {
            JOptionPane.showMessageDialog(null, "-- Opción fuera de rango --");
            return;
        }

        Categoria categoriaSeleccionada = categorias.get(opcion - 1);
        Electrodomestico electro = new Electrodomestico(nombre, precio, stock, categoriaSeleccionada);
        categoriaSeleccionada.agregarProducto(nombre);
        electrodomesticos.add(electro);
        JOptionPane.showMessageDialog(null, "---- Producto agregado ---");
        System.out.println(electro.getDescription());
        System.out.println(categoriaSeleccionada.getNombre());
    }

    public void listarElectrodomestico() {

        if (electrodomesticos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "no hay electrodomesticos que mostrar");
            return;

        }

        StringBuilder lista = new StringBuilder("Lista de Electrodomesticos:\n");
        electrodomesticos.forEach((electro) -> {
            lista.append(electro.getDescription()).append("\n");

        });

        JOptionPane.showMessageDialog(null, lista);

    }

    public void editarElectrodomestico() {
        if (electrodomesticos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "--NO hay Electrodomestico para editar--");
            return;

        }

        String nombre = JOptionPane.showInputDialog(this, "--Ingresa el producto a editar--");
        boolean encontrado = false;
        for (Producto electro : electrodomesticos) {
            if (electro.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = true;
                try {

                    String precioInput = JOptionPane.showInputDialog(this, "--Ingresa el nuevo precio--");
                    String cantidadInput = JOptionPane.showInputDialog(this, "--Ingresa la nueva cantidad--");
                    // parseamos todo
                    double nuevoPrecio = Double.parseDouble(precioInput);
                    int nuevaCantidad = Integer.parseInt(cantidadInput);

                    electro.setPrecio(nuevoPrecio);
                    electro.setStock(nuevaCantidad);
                    JOptionPane.showMessageDialog(null, "--Producto actualizado--");

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "--Error: Debes ingresar numeros validos--");
                }
                break;

            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "--Producto no encontrado--");

            }

        }

    }

    public void eliminarElectrodomestico() {

        if (electrodomesticos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "--no hay nada que eliminar");
            return;

        }
        String nombre = JOptionPane.showInputDialog(this, "--Ingresa el producto a eliminar--");
        // variable de control
        boolean encontrado = false;

        for (int i = 0; i < electrodomesticos.size(); i++) {
            Producto electro = electrodomesticos.get(i);

            if (electro.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = true;
                electrodomesticos.remove(i);
                JOptionPane.showMessageDialog(null, "--Producto eliminado--");

            }
            break;

        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");

        }

    }

    public boolean existeProductoElectro(String nombre) {
        // recorremos el array de alimentos 
        for (Producto producto : electrodomesticos) {
            // si el producto que ingresa el usuario es igual al que esta reccoriendo el array entonces es true
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return true;

            }

        }

        return false;

    }

}
    
    

