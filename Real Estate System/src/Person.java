import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    // Scanner
    private Scanner scanner = new Scanner(System.in);

    // inputs
    private String input, input2;

    // a person variables
    private String id, username, password, firstName, lastName, phoneNumber, emailAddress;
    private double credit = 0;

    // to keep property of person with number of its document
    private ArrayList<Integer> realEstates = new ArrayList<>();

    // constructors
    public Person() {
    }

    public Person(String id, String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, double credit, String realEstates) {
        setId(id, "set");
        setUsername(username, "set");
        setPassword(password, "set");
        setFirstName(firstName, "set");
        setLastName(lastName, "set");
        setPhoneNumber(phoneNumber, "set");
        setEmailAddress(emailAddress, "set");
        setCredit(credit, "set");
        setRealEstates(realEstates, "set");
    }

    // getter and setter methods
    public String getId() {
        return id;
    }

    public void setId(String id, String type) {
        if (type.equals("set"))
            this.id = id;

        else {
            System.out.printf("Enter new ID [%s] > ", getId());

            if (scanner.hasNextInt())  // check if input is number
                input = scanner.next();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            if (id.length() == 10) {
                setId(input, "set");
                System.out.print("ID changed successfully !\n");

            } else
                System.out.print("ID must be 10 numbers !\n");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username, String type) {
        if (type.equals("set"))
            this.username = username;

        else {
            System.out.printf("Enter new username [%s] > ", getUsername());

            input = scanner.next();
            setUsername(input, "set");
            System.out.print("Username changed successfully !\n");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password, String type) {
        if (type.equals("set"))
            this.password = password;

        else {
            System.out.print("Enter current password > ");
            input = scanner.next();

            if (getPassword().equals(input)) { // if current password inputed correctly
                System.out.print("Enter new password > ");
                input = scanner.next();

                System.out.print("Repeat new password > ");
                input2 = scanner.next();

                if (input.equals(input2)) { // to check if two password is matching
                    setPassword(input, "set");
                    System.out.print("Password changed successfully !\n");

                } else
                    System.out.print("Passwords don't match !\n");

            } else
                System.out.print("You entered the password wrong !\n");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName, String type) {
        if (type.equals("set"))
            this.firstName = firstName;

        else {
            System.out.printf("Enter new first name [%s] > ", getFirstName());

            input = scanner.next();
            String captilize = input.substring(0, 1).toUpperCase() + input.substring(1);
            setFirstName(captilize, "set");
            System.out.print("First name changed successfully !\n");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName, String type) {
        if (type.equals("set"))
            this.lastName = lastName;

        else {
            System.out.printf("Enter new first name [%s] > ", getLastName());

            input = scanner.next();
            String captilize = input.substring(0, 1).toUpperCase() + input.substring(1);
            setFirstName(captilize, "set");
            System.out.print("First name changed successfully !\n");
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber, String type) {
        if (type.equals("set"))
            this.phoneNumber = phoneNumber;

        else {
            System.out.printf("Enter new phone number [%s] > ", getPhoneNumber());
            input = scanner.next();

            if (phoneNumber.length() == 11) {
                setPhoneNumber(input, "set");
                System.out.print("Phone number changed successfully !\n");

            } else
                System.out.print("Phone number numbers !\n");
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress, String type) {
        if (type.equals("set"))
            this.emailAddress = emailAddress;

        else {
            System.out.printf("Enter new email address [%s] > ", getEmailAddress());

            input = scanner.next();
            setEmailAddress(input, "set");
            System.out.print("Email address changed successfully !\n");
        }
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit, String type) {
        if (type.equals("set"))
            this.credit += credit;

        else {
            double input;

            System.out.printf("Enter amount of credit [%s] > ", getCredit());
            if (scanner.hasNextDouble())  // check if input is number
                input = scanner.nextDouble();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setCredit(input, "set");
            System.out.print("Credit added successfully !\n");
        }
    }

    public void shopping(double cost) {
        this.credit -= cost;
    }

    public void setRealEstates(String realEstates, String type) {
        this.realEstates.clear();

        // clean the input string
        realEstates = realEstates.replace("[", "").replace("]", "").replaceAll(" ", "");
        if (realEstates.equals("")) return;

        // separate numbers with "," and add them to arraylist
        String[] temp = realEstates.split(",", -1);
        for (String num : temp)
            this.realEstates.add(Integer.parseInt(num));
    }

    public ArrayList<Integer> getRealEstates() {
        return realEstates;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("{ID: %s, Username: %s, Full Name: %s, Phone: %s, Email: %s, Credit: %s, Real Estates = %s}",
                getId(), getUsername(), getFullName(), getPhoneNumber(), getEmailAddress(), getCredit(), getRealEstates());
    }
}