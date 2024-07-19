public class Student extends Person {
    // constructors
    public Student() {
    }

    public Student(String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, String credit, String mealThisMonth, String mealNextMonth) {
        super(username, password, firstName, lastName, phoneNumber, emailAddress, credit, mealThisMonth, mealNextMonth);
    }

    public Student(String[] array) {
        super(array[0], array[5], array[1], array[2], array[3], array[4], array[6], array[7], array[8]);
    }
}