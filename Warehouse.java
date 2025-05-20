import java.util.ArrayList;

public class Warehouse extends WorkPlace{
    // поля и конструкторы
    int ID;
    String name;
    ArrayList<WarehouseCell> Cells = new ArrayList<>();
    private static int quantityOfWarehouses = 0;

    public Warehouse(String name) {
        this.name = name;
        this.ID = quantityOfWarehouses++;
    }

    //метод с отметками в консоли о возможности доставки товара на склад
    public void addProduct(Product product, int quantity){
        if(quantity>=0 && !isEmpty()){
            for(WarehouseCell cell : Cells){
                if(cell.product.equals(product)){
                    cell.quantity += quantity;
                    System.out.println("товар успешно добавлен");
                    return;
                }
            }
            Cells.add(new WarehouseCell(product, quantity));
            System.out.println("ячейка создана, товар успешно добавлен в нее");
        }
        else if(isEmpty()){
            System.out.println("невозможно выполнить: нет ответственного лица");
        }
        else{System.out.println("некорректное значение количества");}
    }
    //метод с отметками в консоли о возможности извлечения товара со склада
    public void extractProduct(Product product, int quantity) {
        if(quantity >=0 && !isEmpty()){
            for (WarehouseCell cell : Cells) {
                if (cell.product.equals(product)) {
                    if (cell.quantity < quantity) {
                        System.out.println("ошибка: недостаточно товара");
                        return;
                    }
                    cell.quantity -= quantity;
                    System.out.println("товар успешно извлечён");
                    if (cell.quantity == 0) {
                        Cells.remove(cell);
                        System.out.println("ячейка освобождена");
                    }
                    return;
                }
            }
            System.out.println("товар не найден");
        }
        else if(isEmpty()){System.out.println("невозможно выполнить: нет ответственного лица");}
        else{System.out.println("некорректно введено количество");}
    }
    //вспомогательный метод без отметок в консоли о возможности извлечения товара со склада, используется в методе доставки до пункта продаж
    public boolean auxiliaryExtracting(Product product, int quantity) {
        if(quantity >=0 && !isEmpty()){
            for (WarehouseCell cell : Cells) {
                if (cell.product.equals(product)) {
                    if (cell.quantity < quantity) {
                        return false;
                    }
                    cell.quantity -= quantity;
                    if (cell.quantity == 0) {
                        Cells.remove(cell);
                    }
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    @Override
    public void info(){
        System.out.println("информация о складе " + this.ID + "\n"
                + "ответственное лицо " + this.workingEmployee.name + "\n"
                + "количество ячеек " + this.Cells.size());
    }
    @Override
    public void showProducts() {
        if (isEmpty()) {
            System.out.println("Нет ответственного лица для просмотра содержимого.");
            return;
        }
        System.out.println("Содержимое склада (ID: " + this.ID + "):");
        for (WarehouseCell cell : Cells) {
            System.out.println("- " + cell.product.name + " (ID: " + cell.product.ID + "): " + cell.quantity + " шт.");
        }
    }
    public void close() {
        if (!isEmpty()) {
            System.out.println("уволен сотрудник: " + workingEmployee.name);
            workingEmployee = null;
        }
        System.out.println(this.getClass().getSimpleName() + " закрыт.");
    }
    @Override
    public String toString() {
        return name;
    }
    Employee workingEmployee = null;
    public boolean isEmpty() {
        return this.workingEmployee == null;
    }

}