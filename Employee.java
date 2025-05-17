public class Employee {
    //поля и конструктор
    int ID;
    String name;
    private static int quantityOfEmployees = 0;

    public Employee(String name) {
        this.name = name;
        this.ID = quantityOfEmployees++;
    }
    //метод найма сотрудника на склад
    public void hireAnEmployee(WorkPlace workPlace){
        if(workPlace.isEmpty()){
            workPlace.workingEmployee = this;
            System.out.println("сотрудник по имени " + this.name + " успешно нанят");
        }
        else{
            System.out.println("свободных вакансий нет");
        }
    }
    //метод увольнения сотрудников
    public void fireEmployee(WorkPlace workPlace) {
        if(!workPlace.isEmpty()){
            System.out.println("Сотрудник " + workPlace.workingEmployee.name + " уволен");
            workPlace.workingEmployee = null;
        }
    }
}
