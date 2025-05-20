import java.util.HashMap;

public class SalesPoint extends WorkPlace{
    //поля класса и конструкторы
    int ID;
    String name;
    HashMap<Product, Integer> salesProducts = new HashMap<>();
    int totalRevenue;
    private static int quantityOfSalesPoint;

    public SalesPoint(String name) {
        this.ID = ++quantityOfSalesPoint;
        this.name = name;
        this.totalRevenue = 0;
    }
    //методы
    //вспомогательный метод для добавления товара на склад. используется в методе доставки со склада, а также в методе возврата в магазин
    public void add(Product product, int quantity){
        if(quantity >= 0 && !isEmpty()){
            salesProducts.merge(product, quantity, Integer::sum);
            System.out.println("успешно добавлено");
        }
        else if(isEmpty()){
            System.out.println("невозможно выполнить: нет ответственного лица");
        }
        else{System.out.println("некорректно введено количество");}
    }
    //вспомогательный метод проверки возможности извлечения товара со склада магазина.
    public boolean extract(Product product, int quantity){
        if(quantity >= 0 && !isEmpty()){
            if(!salesProducts.containsKey(product) || salesProducts.get(product) < quantity){
                return false;
            }
            salesProducts.merge(product, -quantity, Integer::sum);
            if(salesProducts.get(product) == 0){
                salesProducts.remove(product);
            }
            return true;
        } else if (isEmpty()) {
            return false;
        }
        return false;
    }
    //метод запроса доставки в магазин. если успешно - доставит товар на склад магазина
    public void deliveryRequest(Warehouse warehouse, Product product, int quantity) {
        if(quantity >= 0 && !isEmpty()){
            if (warehouse.auxiliaryExtracting(product, quantity)) {
                this.add(product, quantity);  // <-- Используем this
                System.out.println("успешно доставлен товар " + product.name + " в количестве " + quantity);
            } else {
                System.out.println("ошибка: товар отсутствует на складе");
            }
        }
        else if(isEmpty()){System.out.println("невозможно выполнить: нет ответственного лица");}
        else{System.out.println("некорректно введено количество");}
    }
    //метод продажи товара
    public void saleOfProduct(Product product, int quantity, Customer customer) {
        if(quantity >= 0 && !isEmpty()){
            if (this.extract(product, quantity) && quantity > 0) {  // Проверяем и удаляем товар
                this.totalRevenue += product.price * quantity;
                customer.buy(product, quantity);
                System.out.println("успешно продано " + quantity + " единиц товара: " + product.name +
                        " за общую стоимость " + (product.price * quantity) + " покупателю по имени " + customer.name);
            } else {
                System.out.println("магазин не располагает таким количеством товара");
            }
        }
        else if(isEmpty()){
            System.out.println("невозможно выполнить: нет ответственного лица");
        }
        else{System.out.println("некорректно введено количество");}
    }
    //метод возврата товара
    public void returnOfProduct(Customer customer, Product product, int quantity){
        if(quantity >= 0 && !isEmpty()){
            if(customer.isBuy(product, quantity)){
                customer.listOfProduct.merge(product, -quantity, Integer::sum);
                if(customer.listOfProduct.get(product) == 0){
                    customer.listOfProduct.remove(product);
                }
                this.add(product, quantity);
                this.totalRevenue -= product.price * quantity;
                System.out.println("в магазин возвращено " + quantity + "единиц товара " + product.name + ". " + "Магазин выплатил покупателю " + product.price * quantity);
            }
            else{
                System.out.println("покупатель не приобретал этот товар");
            }
        }
        else if(isEmpty()){
            System.out.println("невозможно выполнить: нет ответственного лица");
        }
        else{System.out.println("введено некорректное количество товара");}
    }
    public void info(){
        System.out.println("информация о пункте продаж " + this.name + ", " + this.ID + "\n"
        + "ответственное лицо " + this.workingEmployee.name + "\n"
        + "количество различных товаров " + this.salesProducts.size() + "\n"
        + "общая прибыль " + this.totalRevenue);
    }
    public void showProducts() {
        if (isEmpty()) {
            System.out.println("Нет ответственного лица для просмотра содержимого.");
            return;
        }
        System.out.println("Товары в магазине '" + this.name + "':");
        for (HashMap.Entry<Product, Integer> entry : salesProducts.entrySet()) {
            System.out.println("- " + entry.getKey().name + " (ID: " + entry.getKey().ID + "): " + entry.getValue() + " шт.");
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
