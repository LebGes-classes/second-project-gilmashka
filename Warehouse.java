import java.util.ArrayList;

public class Warehouse extends WorkPlace{
    int ID;
    ArrayList<WarehouseCell> Cells = new ArrayList<>();

    public Warehouse(int ID) {
        this.ID = ID;
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
                    return;  // <-- Важно: выход после обработки
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
    public void info(){
        System.out.println("информация о складе " + this.ID + "\n"
                + "ответственное лицо " + this.workingEmployee.name + "\n"
                + "количество ячеек " + this.Cells.size());
    }
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
    @Override
    public void close() {
        super.close();  // Увольняем сотрудника
        Cells.clear();
        System.out.println("товары со склада удалены, склад закрыт");
    }
}