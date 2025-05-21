import java.util.Scanner;


public class ConsoleMenu {
    private AppData appData;
    Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(){
        this.appData = DataManager.loadData();
        if(this.appData == null){
            this.appData = new AppData();
        }
    }
    public void mainMenu(){
        while(true){
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                DataManager.saveData(appData);
            }));

            System.out.println("====Главное Меню====\n" + "1 -> Управление складами\n" + "2 -> Управление пунктами продаж\n" +
                    "3 -> Управление работниками\n" + "4 -> Управление товаром\n" + "5 -> Управление покупателями\n" +
                    "0 -> Выход из программы");
            int menuInput = scanner.nextInt();
            switch (menuInput){
                case 1: manageWarehouses(); break;
                case 2: manageSalesPints(); break;
                case 3: manageEmployees();  break;
                case 4: manageProducts();   break;
                case 5: manageCustomers();  break;
                case 0: System.exit(0);
                default: System.out.println("некорректный ввод");
            }
        }
    }

    private void manageWarehouses(){
        while(true){
            System.out.println("====Управление Складами====\n" + "1 -> Открыть новый склад\n" + "2 -> Закрыть склад\n" +
                    "3 -> Посмотреть информацию о складе\n" + "4 -> Посмотреть информацию о товаре на складе\n"
            + "5 -> Посмотреть список всех складов\n" + "0 -> Выход в главное меню");
            int warehouseInput = scanner.nextInt();
            scanner.nextLine();
            switch (warehouseInput){
                case 1: openWarehouse();    break;
                case 2: closeWarehouse();   break;
                case 3: showInfoWH();         break;
                case 4: showProductWH();    break;
                case 5: listWH(); break;
                case 0: return;
                default: System.out.println("некорректный ввод");
            }
        }
    }
    private void openWarehouse(){
        System.out.println("Введите название создаваемого склада");
        String warehouseName = scanner.nextLine();
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(warehouseName)){
                System.out.println("данное имя уже занято, попробуйте другое");
                return;
            }
        }
        if(warehouseName.isBlank()){
            System.out.println("имя склада не может быть пустой строкой");
            return;
        }
        appData.warehouses.add(new Warehouse(warehouseName));
        System.out.println("склад создан");
    }
    private void closeWarehouse(){
        System.out.println("введите имя склада, который необходимо закрыть");
        String warehouseName = scanner.nextLine();
        Warehouse warehouseToClose = null;
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(warehouseName)){
                warehouseToClose = i;
                break;
            }
        }
        if(warehouseToClose != null){
            warehouseToClose.close();
            appData.warehouses.remove(warehouseToClose);
            System.out.println("успешно удалено");
        }
        else{
            System.out.println("склад с таким именем не найден");
        }
    }
    private void showInfoWH(){
        System.out.println("введите имя склада, информацию о котором необходимо вывести");
        String  warehouseName = scanner.nextLine();
        Warehouse warehouseToINfo = null;
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(warehouseName)){
                warehouseToINfo = i;
            }
        }
        if(warehouseToINfo != null){
            warehouseToINfo.info();
        }
        else{
            System.out.println("склад с таким именем не найден");
        }
    }
    private void showProductWH(){
        System.out.println("введите имя склада, информацию о хранилище которого необходимо вывести");
        String warehouseName = scanner.nextLine();
        Warehouse warehouseToInfo = null;
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(warehouseName)){
                warehouseToInfo = i;
            }
        }
        if(warehouseToInfo != null){
            warehouseToInfo.showProducts();
        }
        else{
            System.out.println("склад с таким именем не найден");
        }
    }
    private void listWH(){
        for(Warehouse i: appData.warehouses){
            System.out.print(i.toString() + " | ");
        }
        System.out.println();
    }




    private void manageSalesPints(){
        while (true){
            System.out.println("====Управление Магазинами====\n" + "1 -> Открыть новый магазин\n" + "2 -> Закрыть магазин\n" +
                    "3 -> Посмотреть информацию о магазине\n" + "4 -> Посмотреть информацию о товаре в магазине\n" +
                    "5 -> Посмотреть список всех магазинов\n" + "0 -> Выход в главное меню");
            int salesPointInput = scanner.nextInt();
            scanner.nextLine();
            switch (salesPointInput){
                case 1: openSalesPoint();    break;
                case 2: closeSalesPoint();   break;
                case 3: showInfoSP();         break;
                case 4: showProductSP();    break;
                case 5: listSP(); break;
                case 0: return;
                default: System.out.println("некорректный ввод");
            }
        }
    }
    private void openSalesPoint(){
        System.out.println("Введите название создаваемого магазина");
        String salesPointName = scanner.nextLine();
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(salesPointName)){
                System.out.println("данное имя уже занято, попробуйте другое");
                return;
            }
        }
        if( salesPointName.isBlank()){
            System.out.println("имя магазина не может быть пустой строкой");
            return;
        }
        appData.salesPoints.add(new SalesPoint(salesPointName));
        System.out.println("магазин создан");
    }
    private void closeSalesPoint(){
        System.out.println("введите имя магазина, который необходимо закрыть");
        String salesPointName = scanner.nextLine();
        SalesPoint salesPointToClose = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(salesPointName)){
                salesPointToClose = i;
                break;
            }
        }
        if(salesPointToClose != null){
            salesPointToClose.close();
            appData.salesPoints.remove(salesPointToClose);
            System.out.println("успешно удалено");
        }
        else{
            System.out.println("магазин с таким именем не найден");
        }
    }
    private void showInfoSP(){
        System.out.println("введите имя магазина, информацию о котором необходимо вывести");
        String salesPointName = scanner.nextLine();
        SalesPoint salesPointToINfo = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(salesPointName)){
                salesPointToINfo = i;
            }
        }
        if(salesPointToINfo != null){
            salesPointToINfo.info();
        }
        else{
            System.out.println("магазин с таким именем не найден");
        }
    }
    private void showProductSP(){
        System.out.println("введите имя магазина, информацию о хранилище которого необходимо вывести");
        String salesPointName = scanner.nextLine();
        SalesPoint salesPointToInfo = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(salesPointName)){
                salesPointToInfo = i;
            }
        }
        if(salesPointToInfo != null){
            salesPointToInfo.showProducts();
        }
        else{
            System.out.println("магазин с таким именем не найден");
        }
    }
    private void listSP(){
        for(SalesPoint i: appData.salesPoints){
            System.out.print(i.toString() + " ");
        }
        System.out.println();
    }




    private void manageEmployees(){
        while (true){
            System.out.println("====Управление Сотрудниками====\n" + "1 -> Завести анкету сотрудника\n" + "2 -> Нанять сотрудника на склад\n" +
                    "3 -> Нанять сотрудника в магазин\n" + "4 -> Уволить сотрудника со склада\n" +
                    "5 -> Уволить сотрудника из магазина\n" + "0 -> Выход в главное меню\n");
            int employeeInput = scanner.nextInt();
            scanner.nextLine();
            switch (employeeInput){
                case 1: createEmployee(); break;
                case 2: hireWH(); break;
                case 3: hireSP(); break;
                case 4: fireWH(); break;
                case 5: fireSP(); break;
                case 6: listEP(); break;
                case 0: return;
                default: System.out.println("некорректный ввод");
            }
        }
    }
    private void createEmployee(){
        System.out.println("введите имя сотрудника");
        String employeeName = scanner.nextLine();
        for(Employee i: appData.employees){
            if(i.name.equals(employeeName)){
                System.out.println("данное имя занято, попробуйте другое");
                return;
            }
        }
        if(employeeName.isBlank()){
            System.out.println("имя сотрудника не может быть пустой строкой");
            return;
        }
        appData.employees.add(new Employee(employeeName));
        System.out.println("сотрудник зарегистрирован");
    }
    private void hireWH(){
        System.out.println("выберите сотрудника");
        listEP();
        String choiseEP = scanner.nextLine();
        Employee choiseToHireEP = null;
        for(Employee i: appData.employees){
            if(i.name.equals(choiseEP)){
                choiseToHireEP = i;
            }
        }
        if(choiseToHireEP==null){
            System.out.println("данного сотрудника не существует");
            return;
        }
        System.out.println("выберите склад");
        listWH();
        String choiseWH = scanner.nextLine();
        Warehouse choiseToHireWH = null;
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(choiseWH)){
                choiseToHireWH = i;
            }
        }
        if(choiseToHireWH==null){
            System.out.println("данного склада не существует");
            return;
        }
        choiseToHireEP.hireAnEmployeeOnWH(choiseToHireWH);
    }
    private void fireWH(){
        System.out.println("выберите сотрудника");
        listEP();
        String choiseEP = scanner.nextLine();
        Employee choiseToFireEP = null;
        for(Employee i: appData.employees){
            if(i.name.equals(choiseEP)){
                choiseToFireEP = i;
            }
        }
        if(choiseToFireEP==null){
            System.out.println("данного сотрудника не существует");
            return;
        }
        System.out.println("выберите склад");
        listWH();
        String choiseWH = scanner.nextLine();
        Warehouse choiseToFireWH = null;
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(choiseWH)){
                choiseToFireWH = i;
            }
        }
        if(choiseToFireWH==null){
            System.out.println("данного склада не существует");
            return;
        }
        choiseToFireEP.fireEmployeeWH(choiseToFireWH);
    }
    private void hireSP(){
        System.out.println("выберите сотрудника");
        listEP();
        String choiseEP = scanner.nextLine();
        Employee choiseToHireEP = null;
        for(Employee i: appData.employees){
            if(i.name.equals(choiseEP)){
                choiseToHireEP = i;
            }
        }
        if(choiseToHireEP==null){
            System.out.println("данного сотрудника не существует");
            return;
        }
        System.out.println("выберите магазин");
        listSP();
        String choiseSP = scanner.nextLine();
        SalesPoint choiseToHireSP = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(choiseSP)){
                choiseToHireSP = i;
            }
        }
        if(choiseToHireSP==null){
            System.out.println("данного магазина не существует");
            return;
        }
        choiseToHireEP.hireAnEmployeeOnSP(choiseToHireSP);
    }
    private void fireSP(){
        System.out.println("выберите сотрудника");
        listEP();
        String choiseEP = scanner.nextLine();
        Employee choiseToFireEP = null;
        for(Employee i: appData.employees){
            if(i.name.equals(choiseEP)){
                choiseToFireEP = i;
            }
        }
        if(choiseToFireEP==null){
            System.out.println("данного сотрудника не существует");
            return;
        }
        System.out.println("выберите магазин");
        listSP();
        String choiseSP = scanner.nextLine();
        SalesPoint choiseToFireSP = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(choiseSP)){
                choiseToFireSP = i;
            }
        }
        if(choiseToFireSP==null){
            System.out.println("данного магазина не существует");
            return;
        }
        choiseToFireEP.fireEmployeeSP(choiseToFireSP);
    }
    private void listEP(){
        for(Employee i: appData.employees){
            System.out.println(i.toString() + " ");
        }
    }




    private void manageProducts(){
        while (true){
            System.out.println("====Управление Товаром====\n" + "1 -> Добавить новый товар\n" +
                    "2 -> Привезти товар на склад\n" + "3 -> Доставить товар в магазин\n" +
                    "4 -> Продать товары потребителям\n" + "5 -> Вернуть товар в магазин\n" + "6 -> Показать список всех продуктов\n" +
                    "0 -> Выход в главное меню");
            int productInput = scanner.nextInt();
            scanner.nextLine();
            switch (productInput){
                case 1: createProduct(); break;
                case 2: addToWH(); break;
                case 3: addToSP(); break;
                case 4: sale(); break;
                case 5: returnToSP(); break;
                case 6: listPR(); break;
                case 0: return;
                default: System.out.println("некорректный ввод");
            }
        }
    }
    private void createProduct(){
        System.out.println("Введите сначала наименование продукта, а потом его цену");
        String inputName = scanner.nextLine();
        int inputQuantity = scanner.nextInt();
        scanner.nextLine();
        appData.products.add(new Product(inputName, inputQuantity));
        System.out.println("успешно создано");
    }
    private void listPR(){
        for(Product i: appData.products){
            System.out.print(i.toString() + " ");
            System.out.print("|");
        }
        System.out.println();
    }
    private void addToWH(){
        System.out.println("выберите продукт, введя его ID");
        listPR();
        int inputProduct = scanner.nextInt();
        scanner.nextLine();
        Product addingProduct = null;
        for(Product i: appData.products){
            if(i.ID == inputProduct){
                addingProduct = i;
                break;
            }
        }
        if(addingProduct == null){
            System.out.println("данного товара не существует");
            return;
        }
        System.out.println("выберите склад, введя его имя");
        listWH();
        String inputWarehouse = scanner.nextLine();
        Warehouse addingWarehouse = null;
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(inputWarehouse)){
                addingWarehouse = i;
            }
        }
        if(addingWarehouse == null){
            System.out.println("данного склада не существует");
            return;
        }
        System.out.println("выберите количество продукта");
        int inputQuanity = scanner.nextInt();
        scanner.nextLine();
        addingWarehouse.addProduct(addingProduct, inputQuanity);
        System.out.println("успешно добавлено");
    }
    private void addToSP(){
        System.out.println("выберите склад, с которого необходимо доставить товар, введя его имя");
        listWH();
        String inputWarehouse = scanner.nextLine();
        Warehouse addingWarehouse = null;
        for(Warehouse i: appData.warehouses){
            if(i.name.equals(inputWarehouse)){
                addingWarehouse = i;
            }
        }
        if(addingWarehouse == null){
            System.out.println("данного склада не существует");
            return;
        }
        System.out.println("выберите магазин, введя его имя");
        listSP();
        String choiseSP = scanner.nextLine();
        SalesPoint choiseToHireSP = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(choiseSP)){
                choiseToHireSP = i;
            }
        }
        if(choiseToHireSP==null){
            System.out.println("данного магазина не существует");
            return;
        }
        System.out.println("выберите продукт, введя его ID");
        listPR();
        int inputProduct = scanner.nextInt();
        scanner.nextLine();
        Product addingProduct = null;
        for(Product i: appData.products){
            if(i.ID == inputProduct){
                addingProduct = i;
                break;
            }
        }
        if(addingProduct == null){
            System.out.println("данного товара не существует");
            return;
        }
        System.out.println("выберите количество продукта");
        int inputQuantity = scanner.nextInt();
        scanner.nextLine();
        choiseToHireSP.deliveryRequest(addingWarehouse, addingProduct, inputQuantity);

    }
    private void sale(){
        System.out.println("выберите магазин, введя его имя");
        listSP();
        String choiseSP = scanner.nextLine();
        SalesPoint choiseToHireSP = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(choiseSP)){
                choiseToHireSP = i;
            }
        }
        if(choiseToHireSP==null){
            System.out.println("данного магазина не существует");
            return;
        }
        System.out.println("выберите продукт, введя его ID");
        listPR();
        int inputProduct = scanner.nextInt();
        scanner.nextLine();
        Product addingProduct = null;
        for(Product i: appData.products){
            if(i.ID == inputProduct){
                addingProduct = i;
                break;
            }
        }
        if(addingProduct == null){
            System.out.println("данного товара не существует");
            return;
        }
        System.out.println("выберите количество продукта");
        int inputQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("выберите покупателя, введя его ID");
        listCM();
        int inputCM = scanner.nextInt();
        scanner.nextLine();
        Customer toSale = null;
        for (Customer i: appData.customers){
            if(i.ID == inputCM){
                toSale = i;
            }
        }
        if(toSale == null){
            System.out.println("такого покупателя не существует");
            return;
        }
        choiseToHireSP.saleOfProduct(addingProduct, inputQuantity, toSale);
    }
    private void returnToSP(){
        System.out.println("выберите магазин, введя его имя");
        listSP();
        String choiseSP = scanner.nextLine();
        SalesPoint choiseToHireSP = null;
        for(SalesPoint i: appData.salesPoints){
            if(i.name.equals(choiseSP)){
                choiseToHireSP = i;
            }
        }
        if(choiseToHireSP==null){
            System.out.println("данного магазина не существует");
            return;
        }
        System.out.println("выберите продукт, введя его ID");
        listPR();
        int inputProduct = scanner.nextInt();
        scanner.nextLine();
        Product addingProduct = null;
        for(Product i: appData.products){
            if(i.ID == inputProduct){
                addingProduct = i;
                break;
            }
        }
        if(addingProduct == null){
            System.out.println("данного товара не существует");
            return;
        }
        System.out.println("выберите количество продукта");
        int inputQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("выберите покупателя, введя его ID");
        listCM();
        int inputCM = scanner.nextInt();
        scanner.nextLine();
        Customer toSale = null;
        for (Customer i: appData.customers){
            if(i.ID == inputCM){
                toSale = i;
            }
        }
        if(toSale == null){
            System.out.println("такого покупателя не существует");
            return;
        }
        choiseToHireSP.returnOfProduct(toSale, addingProduct, inputQuantity);
    }



    private void manageCustomers(){
        while (true){
            System.out.println("1 ->Создать анкету покупателя\n" + "2 -> Показать список всех покупателей\n" + "0 -> Вернуться в главное меню");
            int customerInput = scanner.nextInt();
            scanner.nextLine();
            switch (customerInput) {
                case 1:
                    System.out.println("введите имя покупателя");
                    String name = scanner.nextLine();
                    if (!name.isBlank()) {
                        appData.customers.add(new Customer(name));
                        System.out.println("успешно добавлено");
                    } else {
                        System.out.println("имя покупателя не может быть пустой строкой");
                        return;
                    }
                    break;
                case 2:
                    listCM();
                    break;
                case 0:
                    return;
            }
        }
    }
    private void listCM(){
        for(Customer i: appData.customers){
            System.out.println(i.toString() + " | ");
        }
        System.out.println();
    }
}
