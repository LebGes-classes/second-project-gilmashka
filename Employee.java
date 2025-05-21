import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    //поля и конструктор
    @JsonProperty
    int ID;
    @JsonProperty
    String name;
    @JsonProperty
    private static int quantityOfEmployees = 0;

    public Employee(String name) {
        this.name = name;
        this.ID = quantityOfEmployees++;
    }
    public void hireAnEmployeeOnWH(Warehouse warehouse){
        if(warehouse.isEmpty()){
            warehouse.workingEmployee = this;
            System.out.println("сотрудник по имени " + this.name + " успешно нанят");
        }
        else{
            System.out.println("свободных вакансий нет");
        }
    }
    public void hireAnEmployeeOnSP(SalesPoint salesPoint){
        if(salesPoint.isEmpty()){
            salesPoint.workingEmployee = this;
            System.out.println("сотрудник по имени " + this.name + " успешно нанят");
        }
        else{
            System.out.println("свободных вакансий нет");
        }
    }
    //метод увольнения сотрудников
    public void fireEmployeeWH(Warehouse warehouse) {
        if(!warehouse.isEmpty()){
            System.out.println("Сотрудник " + warehouse.workingEmployee.name + " уволен");
            warehouse.workingEmployee = null;
        }
    }
    public void fireEmployeeSP(SalesPoint salesPoint) {
        if(!salesPoint.isEmpty()){
            System.out.println("Сотрудник " + salesPoint.workingEmployee.name + " уволен");
            salesPoint.workingEmployee = null;
        }
    }

    @Override
    public String toString() {
        return name;
    }
    public Employee(){}
}
