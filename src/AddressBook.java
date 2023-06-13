import java.io.*;
import java.util.*;

public class AddressBook {
    private HashMap<String, String> contacts; //HashMap para almacenar los contactos
    private String filename; //nombre del archivo de contactos

    public AddressBook(String filename) {
        this.contacts = new HashMap<>();
        this.filename = filename;
    }

    public void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String number = parts[0].trim();
                String name = parts[1].trim();
                contacts.put(number, name); //Le agrega el contacto al HashMap
            }
            reader.close();
            System.out.println("Contactos cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar los contactos: " + e.getMessage());
        }
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            writer.close();
            System.out.println("Contactos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos: " + e.getMessage());
        }
    }

    public void list() {
        System.out.println("Contactos:");
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
        } else {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    public void create(String number, String name) {
        contacts.put(number, name); //agrega nuevo contacto al HashMap
        System.out.println("Contacto creado exitosamente.");
    }

    public void delete(String number) {
        if (contacts.containsKey(number)) {
            contacts.remove(number); //Elimina contacto del HashMap
            System.out.println("Contacto eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el número de contacto especificado.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //se crea instancia de la agenda
        AddressBook addressBook = new AddressBook("C:\\Users\\adri-\\IdeaProjects\\contacts.csv");
        addressBook.load(); //Carga contactos existentes desde el archivo CSV

        int choice = 0;
        while (choice != 4) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.print("Ingrese su elección: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de la entrada del número

            switch (choice) {
                case 1:
                    addressBook.list(); //muestra la lista de contactos
                    break;
                case 2:
                    System.out.print("Ingrese el número de contacto: ");
                    String number = scanner.nextLine();
                    System.out.print("Ingrese el nombre de contacto: ");
                    String name = scanner.nextLine();
                    addressBook.create(number, name); //Crea nuevo contacto
                    break;
                case 3:
                    System.out.print("Ingrese el número de contacto a eliminar: ");
                    String numberToDelete = scanner.nextLine();
                    addressBook.delete(numberToDelete); //Elimina el contacto existente
                    break;
                case 4:
                    addressBook.save(); //Guarda cambios en el archivo CSV
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        addressBook.list();
        scanner.close();
    }
}
