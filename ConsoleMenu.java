import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleMenu {
    private ArrayList<Warehouse> warehouses = new ArrayList<>();
    private ArrayList<SalesPoint> salesPoints = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (choice) {
                case 1:
                    manageWarehouses();
                    break;
                case 2:
                    //manageSalesPoints();
                    break;
                case 3:
                    //manageEmployees();
                    break;
                case 4:
                    //manageProducts();
                    break;
                case 5:
                    System.out.println("Выход из системы.");
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Управление складами");
        System.out.println("2. Управление магазинами");
        System.out.println("3. Управление сотрудниками");
        System.out.println("4. Управление товарами");
        System.out.println("5. Выход");
        System.out.print("Выберите действие: ");
    }

    private void manageWarehouses() {
        System.out.println("\n=== Управление складами ===");
        System.out.println("1. Создать склад");
        System.out.println("2. Закрыть склад");
        System.out.println("3. Показать все склады");
        System.out.println("4. Вернуться");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Введите название склада: ");
                int name = scanner.nextInt();
                warehouses.add(new Warehouse(name));
                System.out.println("Склад создан!");
                break;
            case 2:
                System.out.print("Введите ID склада: ");
                int id = scanner.nextInt();
                if(warehouses.contains(id)){
                    warehouses.get(id).close();
                    break;
                }
                else{
                    System.out.println("Такого склада не существует");
                }

            case 3:
                for (Warehouse w : warehouses) {
                    w.info();
                }
                break;
        }
    }

    // Аналогичные методы для manageSalesPoints(), manageEmployees(), manageProducts()
}