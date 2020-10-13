package Personnel;

public interface EmployeePower {
    void fire(String employee);

    void hire(String employee);

    void layoff(String employee);

    void transfer(String employee);

    void promote(String employee);
}
