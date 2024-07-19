import java.util.ArrayList;

public class Supervisor extends Person {
    // constructors
    public Supervisor() {
    }

    public Supervisor(String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, String credit, String mealThisMonth, String mealNextMonth) {
        super(username, password, firstName, lastName, phoneNumber, emailAddress, credit, mealThisMonth, mealNextMonth);
    }

    public Supervisor(String[] array) {
        super(array[0], array[5], array[1], array[2], array[3], array[4], array[6], array[7], array[8]);
    }

    // method to show number of users
    private void statisticsUsers() {
        int students = 0, employees = 0, admins = 0;

        for (Person person : Utilities.allUsers) { // separate each user
            if (person instanceof Admin)
                admins++;

            else if (person instanceof Employee)
                employees++;

            else if (person instanceof Student)
                students++;
        }

        System.out.printf("%s%-20s | %-20s | %-20s%s\n", Color.CYAN_BOLD_BRIGHT, "Students", "Employees", "Admins", Color.RESET);
        System.out.printf("%s%-20d | %-20d | %-20d%s\n\n", Color.PURPLE_BOLD_BRIGHT, students, employees, admins, Color.RESET);
    }

    // method to show number of food in selected day
    private void statisticsFoodsCount() {
        int enter; // find the day
        System.out.printf("%s*> Enter a day to see statistics > ", Color.BLUE_BOLD_BRIGHT);
        if (scanner.hasNextInt())
            enter = scanner.nextInt();
        else {
            System.out.printf("%s**> Number input expected !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        int count = 0; // count all users which ordered this food on that day
        for (Person person : Utilities.allUsers) {
            if (person.getMealThisMonth().contains(enter))
                count++;
        }

        System.out.printf("%s%-20s | %-20s | %-20s\n", Color.CYAN_BOLD_BRIGHT, "Day", "Main Dish", "Count");
        System.out.printf("%s%-20d | %-20s | %-20d%s\n\n", Color.PURPLE_BOLD_BRIGHT, enter, Utilities.allFoods.get(enter).getMainDish(), count, Color.RESET);
    }

    // method to show price of all foods
    private void statisticsFoodsPrice() {
        // find all kind of foods
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Food> foods = new ArrayList<>();
        for (Food food : Utilities.allFoods)
            if (!temp.contains(food.getMainDish())) {
                temp.add(food.getMainDish());
                foods.add(food);
            }

        System.out.printf("%s   %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.CYAN_BOLD_BRIGHT, "Main Dish", "Price", "Appetizer", "Side Dish", "Dessert", "Drink");

        int i = 0;
        for (Food food : foods) // print all foods with its price
            System.out.printf("%s" + ++i + ". %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n%s", Color.PURPLE_BOLD_BRIGHT,
                    food.getMainDish(), food.getCost(), food.getAppetizer(), food.getSideDish(), food.getDessert(), food.getDrink(), Color.RESET);
        System.out.println();
    }

    // method to show statics deciding with role of user
    public void statistics(String role) {
        switch (role) {
            case "Director General":
            case "Supervisor":
                System.out.printf("%s1) Number of Foods For Selected Day:\n", Color.GREEN_BOLD_BRIGHT);
                statisticsFoodsCount();
                System.out.printf("%s2) Foods Price List:\n", Color.GREEN_BOLD_BRIGHT);
                statisticsFoodsPrice();
                System.out.printf("%s3) Number of Users:\n", Color.GREEN_BOLD_BRIGHT);
                statisticsUsers();
                break;

            case "Office Admin":
                System.out.printf("%sNumber of Users:\n", Color.CYAN_BOLD_BRIGHT);
                statisticsUsers();
                break;

            case "Pricing Manager":
                System.out.printf("%sFoods Price List:\n", Color.CYAN_BOLD_BRIGHT);
                statisticsFoodsPrice();
                break;

            case "Kitchen Manager":
                System.out.printf("%sNumber of Foods For Selected Day:\n", Color.CYAN_BOLD_BRIGHT);
                statisticsFoodsCount();
                break;
        }
    }

    // method to show lists of selected information
    public void lists(String role) {
        String enter;
        System.out.printf("%s\n***> Please select an option below\n", Color.YELLOW_BOLD_BRIGHT);
        System.out.printf(" 1. This Month Food Schedule\n 2. Next Month Food Schedule\n 3. Students\n 4. Employees\n 5. Admins\n 6. Supervisor\n 7. Director General\n 8. Back\n> %s", Color.RESET);
        enter = scanner.next();

        switch (enter) {
            case "1": // This Month Food Schedule
                System.out.println(Food.toString(new ArrayList<>(Utilities.allFoods.subList(0, 31))));
                lists(role);
                break;

            case "2": // Next Month Food Schedule
                System.out.println(Food.toString(new ArrayList<>(Utilities.allFoods.subList(31, 62))));
                lists(role);
                break;

            case "3": // Students
                Student st = new Student();
                System.out.println(st.toString(Utilities.allUsers));
                lists(role);
                break;

            case "4": // Employees
                Employee em = new Employee();
                System.out.println(em.toString(Utilities.allUsers));
                lists(role);
                break;

            case "5": // Admins
                Admin ad = new Admin();
                System.out.println(ad.toString(Utilities.allUsers));
                lists(role);
                break;

            case "6": // Supervisor
                Supervisor su = new Supervisor();
                System.out.println(su.toString(Utilities.allUsers));
                lists(role);
                break;

            case "7": // Director General
                if (!role.equals("Office Admin")) {
                    DirectorGeneral di = new DirectorGeneral();
                    System.out.println(di.toString(Utilities.allUsers));
                } else
                    System.out.printf("%s**> You don't have permission to access this section !\n", Color.RED_BOLD_BRIGHT);

                lists(role);
                break;

            case "8": // Back
                break;

            default: // wrong command
                System.out.printf("%s**> You entered wrong command !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                lists(role);
        }
    }

    // method to reserve a meal
    private void reserveMealAUser(int index) {
        showCalendarMeal(index);

        int enter;
        System.out.printf("%s*> Enter a day to reserve meal > ", Color.BLUE_BOLD_BRIGHT);
        if (scanner.hasNextInt()) // check input is number
            enter = scanner.nextInt();
        else {
            System.out.printf("%s**> Number input expected !\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        if (enter > 0 && enter < 32) { // input is betwen 1 and 31
            if (enter < Utilities.today.date) { // check if entered is not before of today
                System.out.printf("%s**> Reservation deadline has passed !\n", Color.RED_BOLD_BRIGHT);
                return;
            }

            Food food;
            food = Utilities.allFoods.get(enter - 1); // find the food

            if (Utilities.allUsers.get(index).getMealThisMonth().contains(enter)) // if day is already in user's information
                System.out.printf("%s**> This meal was reserved already !\n", Color.GREEN_BOLD_BRIGHT);

            else { // if meal is not reserved yet
                if (food.getCost() > getCredit()) { // check for supervisor credit
                    System.out.printf("%s**> You don't have enough credit !\n", Color.RED_BOLD_BRIGHT);
                    return;
                }

                spendCredit(food.getCost()); // spend from supervisor credit
                ArrayList<Integer> temp = Utilities.allUsers.get(index).getMealThisMonth();
                temp.add(enter);
                Utilities.allUsers.get(index).setMealThisMonth(temp); // set the meal to selected user

                System.out.printf("%s**> The meal reserved for user %s%s%s !\n", Color.GREEN_BOLD_BRIGHT, Color.RED_BOLD_BRIGHT, Utilities.allUsers.get(index).getUsername(), Color.GREEN_BOLD_BRIGHT);
            }

        } else
            System.out.printf("%s**> Day should be between 1 and 31 !\n", Color.RED_BOLD_BRIGHT);
    }

    // method for reserving a meal for selected user with supervisor credit
    public void reserveMealForUser(String role) {
        // get the username of user
        System.out.printf("%s***> \n", Color.YELLOW_BOLD_BRIGHT);
        System.out.printf("%s*> Enter Student/Employee Username > ", Color.BLUE_BOLD_BRIGHT);
        String username = scanner.next();

        boolean found = false; // change the user information
        for (int i = 0; i < Utilities.allUsers.size(); i++)
            if (Utilities.allUsers.get(i).getUsername().equals(username)) {
                System.out.println(Utilities.allUsers.get(i).toString("with header"));
                reserveMealAUser(i);
                found = true;
                break;
            }

        if (!found)
            System.out.printf("%s**> There is no user with this username !\n", Color.RED_BOLD_BRIGHT);
    }

    // menu method for Supervisor
    @Override
    public void showMenu(String role) {
        String enter;

        String userS = String.format("%s%s%s", Color.RED_BOLD_BRIGHT, getUsername(), Color.RESET);
        String roleS = String.format("%s%s%s", Color.GREEN_BOLD_BRIGHT, role, Color.RESET);

        System.out.printf("%s\n***> Please select an option below [%s%s - %s%s]\n", Color.YELLOW_BOLD_BRIGHT,
                userS, Color.YELLOW_BOLD_BRIGHT, roleS, Color.YELLOW_BOLD_BRIGHT);

        System.out.printf(" 1. Display Credit\n 2. Charge Credit\n 3. Reserve Meal\n 4. Change Password\n 5. Lists\n 6. Statistics\n 7. Reserve Meal For User\n 8. Exit\n> %s", Color.RESET);
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
                lists(role);
                showMenu(role);
                break;

            case "6": // Statistics
                statistics(role);
                showMenu(role);
                break;

            case "7": // Reserve Meal For User
                reserveMealForUser(role);
                showMenu(role);
                break;

            case "8": // exit
                Utilities.save();
                System.out.printf("%s\n*****> Have a nice day ! Exiting ... ! <*****\n", Color.GREEN_BOLD_BRIGHT);
                break;

            default: // wrong command
                System.out.printf("%s**> You entered wrong command !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                showMenu(role);
        }
    }
}