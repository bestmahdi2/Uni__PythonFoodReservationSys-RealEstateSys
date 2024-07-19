import java.util.*;

public class Person {
    // Scanner
    protected Scanner scanner = new Scanner(System.in);

    // essential variables
    private String username;
    private String password;

    // other variables
    private String firstName = "---";
    private String lastName = "---";
    private String phoneNumber = "---";
    private String emailAddress = "---";
    private int credit = 0;

    // arraylists for user's meal of this and next month
    private ArrayList<Integer> mealThisMonth = new ArrayList<>();
    private ArrayList<Integer> mealNextMonth = new ArrayList<>();

    // constructors
    public Person() {
    }

    public Person(String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, String credit, String mealThisMonth, String mealNextMonth) {
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setCredit(Integer.parseInt(credit));
        setMealThisMonth(mealThisMonth);
        setMealNextMonth(mealNextMonth);
    }

    // getter and setter methods
    public ArrayList<Integer> getMealThisMonth() {
        return mealThisMonth;
    }

    public void setMealThisMonth(String mealThisMonth) {
        this.mealThisMonth.clear();

        // clean the input string
        mealThisMonth = mealThisMonth.replace("[", "").replace("]", "").replaceAll(" ", "");
        if (mealThisMonth.equals("")) return;

        // separate numbers with "," and add them to arraylist
        String[] temp = mealThisMonth.split(",", -1);
        for (String num : temp)
            this.mealThisMonth.add(Integer.parseInt(num));
    }

    public void setMealThisMonth(ArrayList<Integer> mealThisMonth){
        this.mealThisMonth = mealThisMonth;
    }

    public ArrayList<Integer> getMealNextMonth() {
        return mealNextMonth;
    }

    public void setMealNextMonth(String mealNextMonth) {
        this.mealNextMonth.clear();

        // clean the input string
        mealNextMonth = mealNextMonth.replace("[", "").replace("]", "").replaceAll(" ", "");
        if (mealNextMonth.equals("")) return;

        // separate numbers with "," and add them to arraylist
        String[] temp = mealNextMonth.split(",", -1);
        for (String num : temp)
            this.mealNextMonth.add(Integer.parseInt(num));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void addCredit(int credit) { // method to add credit to this credit
        if (credit > 1000000) // can't add more than 1 million to credit
            System.out.printf("%s**> Can't add more than 1 million Tomaan !", Color.RED_BOLD_BRIGHT);

        else {
            this.credit += credit;
            System.out.printf("%s**> Charging was successful ! Credit: %d\n", Color.GREEN_BOLD_BRIGHT, getCredit());
        }
    }

    public void spendCredit(int credit) { // spent credit when buying a meal
        this.credit -= credit;
    }

    // method to charge credit
    public void chargeCredit() {
        System.out.printf("%sYour Current credit: %d\n", Color.GREEN_BOLD_BRIGHT, getCredit());
        System.out.printf("%s*> Enter amount of credit > ", Color.BLUE_BOLD_BRIGHT);
        if (scanner.hasNextInt()) { // check if input is number
            addCredit(scanner.nextInt());
        } else
            System.out.printf("%s**> Number input expected !", Color.RED_BOLD_BRIGHT);
    }

    // method to change password of user
    public void changePassword() {
        String enter, enter2;

        System.out.printf("%s*> Enter your current password > ", Color.CYAN_BOLD_BRIGHT);
        enter = scanner.next();

        if (getPassword().equals(enter)) { // if current password entered correctly
            System.out.printf("%s*> Enter your new password > ", Color.BLUE_BOLD_BRIGHT);
            enter = scanner.next();

            System.out.print("*> Repeat your new password > ");
            enter2 = scanner.next();

            if (enter.equals(enter2)) { // to check if two password is matching
                setPassword(enter);
                System.out.printf("%s**> Your password changed successfully !\n%s", Color.GREEN_BOLD_BRIGHT, Color.RESET);
            } else
                System.out.printf("%s**> Passwords don't match !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
        } else
            System.out.printf("%s**> You entered your password wrong !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
    }

    // show calendar of this month
    public void showCalendarMeal(int userIndex) {
        System.out.printf("\n%s----------------------------\n", Color.CYAN_BOLD_BRIGHT); // seprators
        if (userIndex == -1)
            Utilities.printCalendar("this", Utilities.today.date, mealThisMonth);
        else {
            System.out.printf("%s%s%s Calendar > ", Color.RED_BOLD_BRIGHT, Utilities.allUsers.get(userIndex).getUsername(), Color.YELLOW_BOLD_BRIGHT);
            Utilities.printCalendar("this", Utilities.today.date, Utilities.allUsers.get(userIndex).mealThisMonth);
        }

        System.out.printf("%s----------------------------\n%s", Color.CYAN_BOLD_BRIGHT, Color.RESET); // seprators
    }

    // method to reserve a day in this or next month
    private void reserveADay(String mode) {
        int enter;
        System.out.printf("%s*> Enter a day to reserve meal > ", Color.BLUE_BOLD_BRIGHT);
        if (scanner.hasNextInt()) // check input is number
            enter = scanner.nextInt();
        else {
            System.out.printf("%s**> Number input expected !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        if (enter > 0 && enter < 32) { // input is betwen 1 and 31
            if (mode.equals("this") && enter < Utilities.today.date) { // check if entered is not before of today
                System.out.printf("%s**> Reservation deadline has passed !\n", Color.RED_BOLD_BRIGHT);
                return;
            }

            Food food;
            if (mode.equals("this")) { // this month
                food = Utilities.allFoods.get(enter - 1); // find the food

                if (mealThisMonth.contains(enter)) { // if day is already in user's information, remove it and bring back the price to credit of user
                    mealThisMonth.remove((Integer) enter);
                    addCredit(food.getCost());
                    System.out.printf("%s**> This meal canceled successfully !\n", Color.GREEN_BOLD_BRIGHT);

                } else { // if meal is not reserved yet
                    if (food.getCost() > getCredit()) {
                        System.out.printf("%s**> You don't have enough credit !\n", Color.RED_BOLD_BRIGHT);
                        return;
                    }

                    spendCredit(food.getCost());
                    mealThisMonth.add(enter);
                    System.out.printf("%s**> The meal reserved !\n", Color.GREEN_BOLD_BRIGHT);
                }
            } else { // next month
                food = Utilities.allFoods.get(enter + 31 - 1);

                if (mealNextMonth.contains(enter)) { // if day is already in user's information, remove it and bring back the price to credit of user
                    mealNextMonth.remove((Integer) enter);
                    addCredit(food.getCost());
                    System.out.printf("%s**> This meal canceled successfully !\n", Color.RED_BOLD_BRIGHT);

                } else {
                    if (food.getCost() > getCredit()) {
                        System.out.printf("%s**> You don't have enough credit !\n", Color.RED_BOLD_BRIGHT);
                        return;
                    }

                    spendCredit(food.getCost());
                    mealNextMonth.add(enter);
                    System.out.printf("%s**> The meal reserved !\n", Color.GREEN_BOLD_BRIGHT);
                }
            }
        } else
            System.out.printf("%s**> Day should be between 1 and 31 !\n", Color.RED_BOLD_BRIGHT);
    }

    // method for reserve menu
    public void reserveMeal() {
        showCalendarMeal(-1); // show this month calendar

        String enter;
        System.out.printf("%s\n***> Please select an option below [Credit: %s%d%s]\n", Color.YELLOW_BOLD_BRIGHT, Color.GREEN_BOLD_BRIGHT, getCredit(), Color.YELLOW_BOLD_BRIGHT);
        System.out.printf(" 1. This Month Food Schedule\n 2. Next Month Food Schedule\n 3. Reserve/Cancel This Month\n 4. Reserve/Cancel Next Month\n 5. Charge Credit\n 6. Show Next Month Calendar\n 7. Back\n> %s", Color.RESET);
        enter = scanner.next();

        switch (enter) {
            case "1": // This Month Food Schedule
                System.out.println(Food.toString(new ArrayList<>(Utilities.allFoods.subList(0, 31))));
                reserveMeal();
                break;

            case "2": // Next Month Food Schedule
                System.out.println(Food.toString(new ArrayList<>(Utilities.allFoods.subList(31, 62))));
                reserveMeal();
                break;

            case "3": // Reserve/Cancel This Month
                reserveADay("this");
                reserveMeal();
                break;

            case "4": // Reserve/Cancel Next Month
                reserveADay("next");
                reserveMeal();
                break;

            case "5": // Charge Credit
                chargeCredit();
                reserveMeal();
                break;

            case "6": // Show Next Month Calendar
                System.out.printf("%s----------------------------\n", Color.PURPLE_BOLD_BRIGHT);
                Utilities.printCalendar("next", Utilities.today.date, mealNextMonth);
                System.out.printf("%s^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n%s", Color.PURPLE_BOLD_BRIGHT, Color.RESET);
                reserveMeal();
                break;

            case "7": // Back
                break;

            default: // wrong command
                System.out.printf("%s**> You entered wrong command !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                reserveMeal();
        }
    }

    // menu method for Person
    public void showMenu(String role) {
        String enter;

        String userS = String.format("%s%s%s", Color.RED_BOLD_BRIGHT, getUsername(), Color.RESET);
        String roleS = String.format("%s%s%s", Color.GREEN_BOLD_BRIGHT, role, Color.RESET);

        System.out.printf("%s\n***> Please select an option below [%s%s - %s%s]\n", Color.YELLOW_BOLD_BRIGHT,
                userS, Color.YELLOW_BOLD_BRIGHT, roleS, Color.YELLOW_BOLD_BRIGHT);

        System.out.printf(" 1. Display Credit\n 2. Charge Credit\n 3. Reserve Meal\n 4. Change Password\n 5. Exit\n> %s", Color.RESET);
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

            case "5": // exit
                Utilities.save();
                System.out.printf("%s\n*****> Have a nice day ! Exiting ... ! <*****\n", Color.GREEN_BOLD_BRIGHT);
                break;

            default: // wrong command
                System.out.printf("%s**> You entered wrong command !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                showMenu(role);
        }
    }

    // toString method with color
    public String toString(String mode) {
        String header = "";
        if (mode.equals("with header")) // if needs to print head
            header = String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.YELLOW_BOLD_BRIGHT,
                    "Username", "First Name", "Last Name", "Phone Number", "Email Address", "This Month Meal", "Next Month Meal");

        return header + String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n%s", Color.CYAN_BOLD_BRIGHT,
                getUsername(), getFirstName(), getLastName(), getPhoneNumber(), getEmailAddress(), getMealThisMonth(), getMealNextMonth(), Color.RESET);
    }

    // toString method with color, if have an arraylist of Person objects
    public String toString(ArrayList<Person> allUsers) {
        StringBuilder header;
        if (!this.getClass().getName().equals("Admin")) // other person than admin
            header = new StringBuilder(String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.YELLOW_BOLD_BRIGHT,
                    "Username", "First Name", "Last Name", "Phone Number", "Email Address", "This Month Meal", "Next Month Meal"));

        else // admin (include Role part)
            header = new StringBuilder(String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.YELLOW_BOLD_BRIGHT,
                    "Role", "Username", "First Name", "Last Name", "Phone Number", "Email Address", "This Month Meal", "Next Month Meal"));

        for (Person person : allUsers) // add every person in arraylist to string
            if (person.getClass().getName().equals(this.getClass().getName()))
                header.append(person.toString(""));

        return header.toString();
    }
}