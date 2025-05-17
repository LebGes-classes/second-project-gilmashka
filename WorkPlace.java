public class WorkPlace {
    Employee workingEmployee = null;

    //
    public boolean isEmpty() {
        return this.workingEmployee == null;
    }

    public void info(){}
    public void showProducts(){}

    public void close() {
        if (!isEmpty()) {
            System.out.println("уволен сторудник: " + workingEmployee.name);
            workingEmployee = null;
        }
        System.out.println(this.getClass().getSimpleName() + " закрыт.");
    }
}

