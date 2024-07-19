import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Admin extends Supervisor {
    String role; // role of each admin

    // constructors
    public Admin() {
    }

    public Admin(String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, String credit, String mealThisMonth, String mealNextMonth) {
        super(username, password, firstName, lastName, phoneNumber, emailAddress, credit, mealThisMonth, mealNextMonth);
        setRole(role);
    }

    public Admin(String[] array) {
        super(array[1], array[6], array[2], array[3], array[4], array[5], array[7], array[8], array[9]);
        setRole(array[0]);
    }

    // getter method
    public String getRole() {
        return role;
    }

    // setter method
    public void setRole(String role) {
        this.role = role;
    }

    // change food schedule method for changing the change selected day's food
    public void changeFoodSchedule(String role) {
        if (!(Utilities.currentUser instanceof DirectorGeneral) && !role.equals("Kitchen Manager")) { // if user is not director or kitchen manager
            System.out.printf("%s**> You don't have permission to access this section !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        // choose a month to change its days' food
        System.out.printf("%s***> Please select a month below:\n", Color.YELLOW_BOLD_BRIGHT);
        System.out.printf(" 1. This Month\n 2. Next Month\n> %s", Color.RESET);
        String input = scanner.next();

        if (!input.equals("1") && !input.equals("2")) { // if input is not 1 or 2
            System.out.printf("%s**> Wrong command !%n", Color.RED_BOLD_BRIGHT);
            return;
        }

        // get the day which want to change its food
        int enter;
        System.out.printf("%s*> Enter a day to change food > ", Color.BLUE_BOLD_BRIGHT);
        if (scanner.hasNextInt())
            enter = scanner.nextInt();
        else {
            System.out.printf("%s**> Number input expected !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        if (enter > 0 && enter < 32) { // day should be between 1 and 31
            if (input.equals("1") && enter < Utilities.today.date) { // if selected day is passed
                System.out.printf("%s**> Reservation deadline has passed !\n", Color.RED_BOLD_BRIGHT);
                return;
            }

            // find the food if it's in this month or next
            Food food;
            if (input.equals("1"))
                food = Utilities.allFoods.get(enter - 1);
            else
                food = Utilities.allFoods.get(enter + 31 - 1);

            System.out.println(food.toString("with header"));

            try { // to user BufferedReader we need try catch
                // change the information of selected day
                BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

                System.out.printf("%s*> Enter this day appetizer > ", Color.BLUE_BOLD_BRIGHT);
                food.setAppetizer(bi.readLine());
                System.out.printf("%s*> Enter this day main dish > ", Color.BLUE_BOLD_BRIGHT);
                food.setMainDish(bi.readLine());
                System.out.printf("%s*> Enter this day side dish > ", Color.BLUE_BOLD_BRIGHT);
                food.setSideDish(bi.readLine());
                System.out.printf("%s*> Enter this day dessert > ", Color.BLUE_BOLD_BRIGHT);
                food.setDessert(bi.readLine());
                System.out.printf("%s*> Enter this day drink > ", Color.BLUE_BOLD_BRIGHT);
                food.setDrink(bi.readLine());
            } catch (IOException e) {
                System.out.println(e);
            }

            // change the day
            Utilities.allFoods.set(enter - 1, food);

            System.out.printf("%sDay food changed successfully !\n%s", Color.GREEN_BOLD_BRIGHT, Color.RESET);

        } else
            System.out.printf("%s**> Day should be between 1 and 31 !\n", Color.RED_BOLD_BRIGHT);
    }

    // change the selected food price
    public void changeFoodPrice(String role) {
        if (!(Utilities.currentUser instanceof DirectorGeneral) && !role.equals("Pricing Manager")) { // if user is not director or pricing manager
            System.out.printf("%s**> You don't have permission to access this section !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        System.out.printf("%s***> Please select a food below:\n", Color.YELLOW_BOLD_BRIGHT);

        ArrayList<String> temp = new ArrayList<>(); // find all types of foods
        for (Food food : Utilities.allFoods)
            if (!temp.contains(food.getMainDish()))
                temp.add(food.getMainDish());

        for (int i = 0; i < temp.size(); i++) // print all types of foods
            System.out.printf(" %d. %s\n", 1 + i, temp.get(i));
        System.out.print(Color.RESET);

        int enter; // get the food to change its price
        System.out.printf("%s*> Enter a number to change food price > ", Color.BLUE_BOLD_BRIGHT);
        if (scanner.hasNextInt())
            enter = scanner.nextInt();
        else {
            System.out.printf("%s**> Number input expected !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        if (enter > 0 && enter <= temp.size()) { // if index entered is not more than all kinds of foods
            String foodName = temp.get(enter - 1);

            // get the new price
            System.out.printf("%s*> Enter %s%s%s price > ", Color.BLUE_BOLD_BRIGHT, Color.GREEN_BOLD_BRIGHT, foodName, Color.BLUE_BOLD_BRIGHT);
            int price;
            if (scanner.hasNextInt())
                price = scanner.nextInt();
            else {
                System.out.printf("%s**> Number input expected !\n", Color.RED_BOLD_BRIGHT);
                return;
            }

            for (int i = 0; i < Utilities.allFoods.size(); i++) // find all days which this food is in, and change the price
                if (Utilities.allFoods.get(i).getMainDish().equals(foodName))
                    Utilities.allFoods.get(i).setCost(price);

            System.out.printf("%sPrice of food changed successfully !\n%s", Color.GREEN_BOLD_BRIGHT, Color.RESET);

        } else
            System.out.printf("%s**> Number should be between 1 and %d !\n", Color.RED_BOLD_BRIGHT, temp.size());
    }

    // method to charge credit of a user
    private void chargeCredit(int index) {
        if (!(Utilities.currentUser instanceof DirectorGeneral) && !(Utilities.allUsers.get(index) instanceof Student) && !(Utilities.allUsers.get(index) instanceof Employee)) {
            System.out.printf("%s**> You don't have permission to change this user information !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        System.out.printf("%sUser Current credit: %d\n", Color.GREEN_BOLD_BRIGHT, Utilities.allUsers.get(index).getCredit());
        System.out.printf("%s*> Enter amount of credit > ", Color.BLUE_BOLD_BRIGHT);
        if (scanner.hasNextInt()) { // check if input is number
            Utilities.allUsers.get(index).addCredit(scanner.nextInt());
        } else
            System.out.printf("%s**> Number input expected !", Color.RED_BOLD_BRIGHT);
    }

    // charge the selected user's credit
    public void chargeUserCredit(String role) {
        if (!(Utilities.currentUser instanceof DirectorGeneral) && !role.equals("Pricing Manager")) { // if user is not director or pricing manager
            System.out.printf("%s**> You don't have permission to access this section !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        // get the username of user
        System.out.printf("%s***> \n", Color.YELLOW_BOLD_BRIGHT);
        System.out.printf("%s*> Enter Student/Employee Username > ", Color.BLUE_BOLD_BRIGHT);
        String username = scanner.next();

        boolean found = false; // change the user information
        for (int i = 0; i < Utilities.allUsers.size(); i++)
            if (Utilities.allUsers.get(i).getUsername().equals(username)) {
                System.out.println(Utilities.allUsers.get(i).toString("with header"));
                chargeCredit(i);
                found = true;
                break;
            }

        if (!found)
            System.out.printf("%s**> There is no user with this username !\n", Color.RED_BOLD_BRIGHT);
    }

    // add student or employee
    public void addStudentEmployee(String role) { // if user is not director or office admin
        if (!(Utilities.currentUser instanceof DirectorGeneral) && !role.equals("Office Admin")) {
            System.out.printf("%s**> You don't have permission to access this section !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        String result = Utilities.signUp(); // sign up new student or employee
        System.out.println(result); // print the result
    }

    // get the index of user in allUsers and change its information
    private void changeUser(int index) { // if user is not director or student and employee
        if (!(Utilities.currentUser instanceof DirectorGeneral) && !(Utilities.allUsers.get(index) instanceof Student) && !(Utilities.allUsers.get(index) instanceof Employee)) {
            System.out.printf("%s**> You don't have permission to change this user information !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        // get the new information of user
        System.out.printf("%s*> Enter new username > ", Color.BLUE_BOLD_BRIGHT);
        Utilities.allUsers.get(index).setUsername(scanner.next());

        System.out.print("*> Enter new password > ");
        String password = scanner.next();

        System.out.print("*> Repeat new password > ");
        String password2 = scanner.next();

        if (!password.equals(password2)) { // check matching password
            System.out.printf("%s**> Passwords don't match !", Color.RED_BOLD_BRIGHT);
            return;
        }
        Utilities.allUsers.get(index).setPassword(password);

        System.out.print("*> Enter first name > ");
        Utilities.allUsers.get(index).setFirstName(scanner.next());

        System.out.print("*> Enter last name > ");
        Utilities.allUsers.get(index).setLastName(scanner.next());

        System.out.print("*> Enter phone number > ");
        Utilities.allUsers.get(index).setPhoneNumber(scanner.next());

        System.out.print("*> Enter email address > ");
        Utilities.allUsers.get(index).setEmailAddress(scanner.next());

        System.out.print("*> Enter Credit > ");
        Utilities.allUsers.get(index).setCredit(scanner.nextInt());

        System.out.printf("%sUser information changed successfully !%s\n", Color.GREEN_BOLD_BRIGHT, Color.RESET);
    }

    // change the student or employee information
    public void changeStudentEmployee(String role) { // if user is not director or office admin
        if (!(Utilities.currentUser instanceof DirectorGeneral) && !role.equals("Office Admin")) {
            System.out.printf("%s**> You don't have permission to access this section !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        // get the username of user
        System.out.printf("%s***> \n", Color.YELLOW_BOLD_BRIGHT);
        System.out.printf("%s*> Enter Student/Employee Username > ", Color.BLUE_BOLD_BRIGHT);
        String username = scanner.next();

        boolean found = false; // change the user information
        for (int i = 0; i < Utilities.allUsers.size(); i++)
            if (Utilities.allUsers.get(i).getUsername().equals(username)) {
                System.out.println(Utilities.allUsers.get(i).toString("with header"));
                changeUser(i);
                found = true;
                break;
            }

        if (!found)
            System.out.printf("%s**> There is no user with this username !\n", Color.RED_BOLD_BRIGHT);
    }

    // option method
    public void options(String role) {
        String enter;
        System.out.printf("%s\n***> Please select an appropriate option below\n", Color.YELLOW_BOLD_BRIGHT);

        System.out.printf(" 1. Change Food Schedule    (%sKitchen Manager%s)\n 2. Change Food Price       (%sPricing Manager%s)\n 3. Charge User Credit      (%sPricing Manager%s)\n 4. Add Student/Employee    (%sOffice Admin%s)\n 5. Change Student/Employee (%sOffice Admin%s)\n 6. Statistics\n 7. Back\n> %s",
                Color.GREEN_BOLD_BRIGHT, Color.YELLOW_BOLD_BRIGHT, Color.GREEN_BOLD_BRIGHT, Color.YELLOW_BOLD_BRIGHT, Color.GREEN_BOLD_BRIGHT, Color.YELLOW_BOLD_BRIGHT, Color.GREEN_BOLD_BRIGHT, Color.YELLOW_BOLD_BRIGHT, Color.GREEN_BOLD_BRIGHT, Color.YELLOW_BOLD_BRIGHT, Color.RESET);
        enter = scanner.next();

        switch (enter) {
            case "1": // Change Food Schedule
                changeFoodSchedule(getRole());
                options(role);
                break;

            case "2": // Change Food Price
                changeFoodPrice(getRole());
                options(role);
                break;

            case "3": // Charge User Credit
                chargeUserCredit(getRole());
                options(role);
                break;

            case "4": // Add Student/Employee
                addStudentEmployee(getRole());
                options(role);
                break;

            case "5": // Change Student/Employee
                changeStudentEmployee(getRole());
                options(role);
                break;

            case "6": // Statistics
                if (!(Utilities.currentUser instanceof DirectorGeneral)) // get it its role if user is admin
                    statistics(getRole());
                else
                    statistics(role);
                options(role);
                break;

            case "7": // back
                break;

            default: // wrong command
                System.out.printf("%s**> You entered wrong command !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                options(role);
        }
    }

    // menu method for Admin and Director General
    @Override
    public void showMenu(String role) { // override the show menu method
        String enter, roleS;

        // find user's username and its role and make them colorful string
        String userS = String.format("%s%s%s", Color.RED_BOLD_BRIGHT, getUsername(), Color.RESET);
        if (this.role != null)
            roleS = String.format("%s%s%s", Color.GREEN_BOLD_BRIGHT, this.role, Color.RESET);
        else
            roleS = String.format("%s%s%s", Color.GREEN_BOLD_BRIGHT, role, Color.RESET);

        System.out.printf("%s\n***> Please select an option below [%s%s - %s%s]\n", Color.YELLOW_BOLD_BRIGHT,
                userS, Color.YELLOW_BOLD_BRIGHT, roleS, Color.YELLOW_BOLD_BRIGHT);

        System.out.printf(" 1. Display Credit\n 2. Charge Credit\n 3. Reserve Meal\n 4. Change Password\n 5. Lists\n 6. Options\n 7. Exit\n> %s", Color.RESET);
        enter = scanner.next();

        switch (enter) {
            case "1": // Display Credit
                System.out.printf("%sYour Current credit: %d\n", Color.GREEN_BOLD_BRIGHT, getCredit());
                showMenu(role);
                break;

            case "2": // Charge Credit
                chargeCredit();
                showMenu(role);
                break;

            case "3": // Reserve Meal
                reserveMeal();
                showMenu(role);
                break;

            case "4": // Change Password
                changePassword();
                showMenu(role);
                break;

            case "5": // Lists
                if (!(Utilities.currentUser instanceof DirectorGeneral) && !role.equals("Office Admin")) // check if user is Director or office admin
                    System.out.printf("%s**> You don't have permission to access this section !\n", Color.RED_BOLD_BRIGHT);

                else
                    lists(role);

                showMenu(role);
                break;

            case "6": // Options
                options(role);
                showMenu(role);
                break;

            case "7": // exit
                Utilities.save(); // save the files
                System.out.printf("%s\n*****> Have a nice day ! Exiting ... ! <*****\n", Color.GREEN_BOLD_BRIGHT);
                break;

            default: // wrong command
                System.out.printf("%s**> You entered wrong command !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                showMenu(role);
        }
    }

    // toString method with color
    @Override
    public String toString(String mode) {
        String header = "";
        if (mode.equals("with header")) // if needs to print head
            header = String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.YELLOW_BOLD_BRIGHT,
                    "Role", "Username", "First Name", "Last Name", "Phone Number", "Email Address", "This Month Meal", "Next Month Meal");

        return header + String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n%s", Color.CYAN_BOLD_BRIGHT,
                role, getUsername(), getFirstName(), getLastName(), getPhoneNumber(), getEmailAddress(), getMealThisMonth(), getMealNextMonth(), Color.RESET);
    }
}