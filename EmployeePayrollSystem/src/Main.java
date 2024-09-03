import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " has been removed.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPayroll System Menu:");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String fullName = scanner.next();
                    System.out.print("Enter ID: ");
                    int fullId = scanner.nextInt();
                    System.out.print("Enter monthly salary: ");
                    double salary = scanner.nextDouble();
                    payrollSystem.addEmployee(new FullTimeEmployee(fullName, fullId, salary));
                    System.out.println("Full-Time Employee added.");
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String partName = scanner.next();
                    System.out.print("Enter ID: ");
                    int partId = scanner.nextInt();
                    System.out.print("Enter hours worked: ");
                    int hours = scanner.nextInt();
                    System.out.print("Enter hourly rate: ");
                    double rate = scanner.nextDouble();
                    payrollSystem.addEmployee(new PartTimeEmployee(partName, partId, hours, rate));
                    System.out.println("Part-Time Employee added.");
                    break;
                case 3:
                    System.out.print("Enter employee ID to remove: ");
                    int removeId = scanner.nextInt();
                    payrollSystem.removeEmployee(removeId);
                    break;
                case 4:
                    System.out.println("\nEmployee Details:");
                    payrollSystem.displayEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
