public class Employee {
    private final int ID;
    private final String firstName;
    private final String lastName;
    private final int age;

    public Employee(int initialID, String initialFirstName, String initialLastName, int initialAge){
        this.ID = initialID;
        this.firstName = initialFirstName;
        this.lastName = initialLastName;
        this.age = initialAge;
    }

    public int getID() {
        return this.ID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }
    public void setID(int initialID){
        int id = this.ID;

    }

}
