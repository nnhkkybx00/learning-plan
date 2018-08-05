package ahead;

public class Employee implements Comparable<Employee>{
    private Double salary;
    private Integer id;
    private String name;

    public Employee() {
    }

    public Employee(Double salary) {
        this.salary = salary;
    }

    public int compareTo(Employee o) {
        return Double.compare(salary,o.salary);
    }
}
