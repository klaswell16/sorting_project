class Employee {
    private final String empId;
    private String firstName;
    private String lastName;
    private int age;

    public Employee(String empId, String firstName, String lastName, int age) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getEmpId() {
        return empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return empId + "," + firstName + "," + lastName + "," + age;
    }
}
