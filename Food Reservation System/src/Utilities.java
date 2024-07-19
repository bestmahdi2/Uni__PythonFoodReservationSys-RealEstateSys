import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

// Solar Calendar class for Persian calendar
class SolarCalendar {
    public String strWeekDay = "";
    public String strMonth = "";

    public int WeekDay;

    int date;
    int month;
    int year;

    // constructors
    public SolarCalendar() {
        Date MiladiDate = new Date();
        calcSolarCalendar(MiladiDate);
    }

    public SolarCalendar(Date MiladiDate) {
        calcSolarCalendar(MiladiDate);
    }

    private void calcSolarCalendar(Date MiladiDate) {
        int ld;

        int miladiYear = MiladiDate.getYear() + 1900;
        int miladiMonth = MiladiDate.getMonth() + 1;
        int miladiDate = MiladiDate.getDate();
        WeekDay = MiladiDate.getDay();

        int[] buf1 = new int[12];
        int[] buf2 = new int[12];

        buf1[0] = 0;
        buf1[1] = 31;
        buf1[2] = 59;
        buf1[3] = 90;
        buf1[4] = 120;
        buf1[5] = 151;
        buf1[6] = 181;
        buf1[7] = 212;
        buf1[8] = 243;
        buf1[9] = 273;
        buf1[10] = 304;
        buf1[11] = 334;

        buf2[0] = 0;
        buf2[1] = 31;
        buf2[2] = 60;
        buf2[3] = 91;
        buf2[4] = 121;
        buf2[5] = 152;
        buf2[6] = 182;
        buf2[7] = 213;
        buf2[8] = 244;
        buf2[9] = 274;
        buf2[10] = 305;
        buf2[11] = 335;

        if ((miladiYear % 4) != 0) {
            date = buf1[miladiMonth - 1] + miladiDate;

            if (date > 79) {
                date = date - 79;
                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = date / 31;
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            } else {
                if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                    ld = 11;
                } else {
                    ld = 10;
                }
                date = date + ld;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }
        } else {
            date = buf2[miladiMonth - 1] + miladiDate;

            if (miladiYear >= 1996) {
                ld = 79;
            } else {
                ld = 80;
            }
            if (date > ld) {
                date = date - ld;

                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = (date / 31);
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            } else {
                date = date + 10;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }
        }

        switch (month) {
            case 1:
                strMonth = "فروردين";
                break;
            case 2:
                strMonth = "ارديبهشت";
                break;
            case 3:
                strMonth = "خرداد";
                break;
            case 4:
                strMonth = "تير";
                break;
            case 5:
                strMonth = "مرداد";
                break;
            case 6:
                strMonth = "شهريور";
                break;
            case 7:
                strMonth = "مهر";
                break;
            case 8:
                strMonth = "آبان";
                break;
            case 9:
                strMonth = "آذر";
                break;
            case 10:
                strMonth = "دي";
                break;
            case 11:
                strMonth = "بهمن";
                break;
            case 12:
                strMonth = "اسفند";
                break;
        }

        switch (WeekDay) {
            case 0:
                strWeekDay = "يکشنبه";
                break;
            case 1:
                strWeekDay = "دوشنبه";
                break;
            case 2:
                strWeekDay = "سه شنبه";
                break;
            case 3:
                strWeekDay = "چهارشنبه";
                break;
            case 4:
                strWeekDay = "پنج شنبه";
                break;
            case 5:
                strWeekDay = "جمعه";
                break;
            case 6:
                strWeekDay = "شنبه";
                break;
        }
    }

    public String strToday() {
        Locale loc = new Locale("en_US");
        return this.year + "/" + String.format(loc, "%02d", this.month) + "/" + String.format(loc, "%02d", this.date);
    }
}

public class Utilities {
    // sign up option
    public static boolean canSignUp = true;

    // have today in English and Persian
    public static Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    public static SolarCalendar today = new SolarCalendar(date);

    // user
    public static Person currentUser;
    public static ArrayList<Person> allUsers = new ArrayList<>();

    // food
    public static ArrayList<Food> allFoods = new ArrayList<>();

    // method for first actions and reading from files
    public static void firstAction() {
        String[] temp;
        allUsers.clear();
        allFoods.clear();

        // raed users from files
        String[] users = read("src/users.txt");

        for (int i = 1; i < users.length; i++) {
            // clean string and separate them with "-"
            users[i] = users[i].replace("\n", "").replaceAll(" +", " ").replaceAll(" \\| ", "-").replaceAll(" \\|", "-").replaceAll("\\| ", "-").replaceAll(" +", " ").trim();
            if (users[i].equals("")) continue;
            temp = users[i].split("-", -1);

            // create user with its role
            switch (temp[0]) {
                case "Student":
                    allUsers.add(new Student(Arrays.copyOfRange(temp, 1, temp.length)));
                    break;

                case "Employee":
                    allUsers.add(new Employee(Arrays.copyOfRange(temp, 1, temp.length)));
                    break;

                case "Supervisor":
                    allUsers.add(new Supervisor(Arrays.copyOfRange(temp, 1, temp.length)));
                    break;

                case "DirectorGeneral":
                    allUsers.add(new DirectorGeneral(Arrays.copyOfRange(temp, 1, temp.length)));
                    break;

                default:
                    allUsers.add(new Admin(temp));
                    break;
            }
        }

        // foods
        String[] foods = read("src/foods.txt");

        for (int i = 1; i < foods.length; i++) {
            // clean string and separate them with "-"
            foods[i] = foods[i].replace("\n", "").replaceAll(" +", " ").replaceAll(" \\| ", "-").replaceAll(" \\|", "-").replaceAll("\\| ", "-").replaceAll(" +", " ").trim();
            if (foods[i].equals("")) continue;
            temp = foods[i].split("-", -1);

            allFoods.add(new Food(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], Integer.parseInt(temp[6])));
        }
    }

    // method for saving users and foods
    public static void save() {
        // header
        StringBuilder users = new StringBuilder(String.format("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n",
                "Role", "Username", "First Name", "Last Name", "Phone Number", "Email Address", "Password", "Credit", "This Month Meal", "Next Month Meal"));

        for (Person person : allUsers) // add string user to main string deciding if it's admin or not
            if (person instanceof Admin && !(person instanceof DirectorGeneral))
                users.append(String.format("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n",
                        ((Admin) person).getRole(), person.getUsername(), person.getFirstName(), person.getLastName(), person.getPhoneNumber(), person.getEmailAddress(), person.getPassword(), person.getCredit(), person.getMealThisMonth(), person.getMealNextMonth()));
            else
                users.append(String.format("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n",
                        person.getClass().getName(), person.getUsername(), person.getFirstName(), person.getLastName(), person.getPhoneNumber(), person.getEmailAddress(), person.getPassword(), person.getCredit(), person.getMealThisMonth(), person.getMealNextMonth()));

        write("src/users.txt", String.valueOf(users)); // save users


        // header
        StringBuilder foods = new StringBuilder(String.format("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n",
                "Day", "Appetizer", "Main Dish", "Side Dish", "Dessert", "Drink", "Cost"));

        for (Food food : allFoods) // add string food to main string
            foods.append(String.format("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n",
                    food.getDay(), food.getAppetizer(), food.getMainDish(), food.getSideDish(), food.getDessert(), food.getDrink(), food.getCost()));

        write("src/foods.txt", String.valueOf(foods)); // save foods
    }

    // method to check credential of user for login
    public static boolean CredentialCheck(String mode, String username, String password) {
        firstAction(); // recheck the file

        if (mode.equals("login")) { // login
            for (Person user : allUsers) // if user's username and password is correct
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUser = user;
                    return true;
                }
        } else // to check if username is not duplicated
            for (Person user : allUsers)
                if (user.getUsername().equals(username))
                    return true;

        return false;
    }

    // method for signing up a new user
    public static String signUp() {
        Scanner scanner = new Scanner(System.in);
        String enter;
        Person user;

        System.out.printf("%s***> Please select a role below:\n", Color.YELLOW_BOLD_BRIGHT);
        System.out.printf(" 1. Employee\n 2. Student\n> %s", Color.RESET);
        enter = scanner.next();

        if (!enter.equals("1") && !enter.equals("2")) // select 1 and 2
            return String.format("%s**> Wrong command !", Color.RED_BOLD_BRIGHT);

        System.out.printf("%s*> Enter new username > ", Color.BLUE_BOLD_BRIGHT);
        String username = scanner.next();

        if (CredentialCheck("signup", username, "---")) // duplicated username
            return String.format("%s**> This username is already taken ! ", Color.RED_BOLD_BRIGHT);

        else {
            System.out.print("*> Enter new password > ");
            String password = scanner.next();

            System.out.print("*> Repeat new password > ");
            String password2 = scanner.next();

            if (!password.equals(password2))
                return String.format("%s**> Passwords don't match !", Color.RED_BOLD_BRIGHT);

            System.out.print("*> Enter first name > ");
            String firstName = scanner.next();

            System.out.print("*> Enter last name > ");
            String lastName = scanner.next();

            System.out.print("*> Enter phone number > ");
            String phoneNumber = scanner.next();

            System.out.print("*> Enter email address > ");
            String emailAddress = scanner.next();

            if (enter.equals("1"))
                user = new Employee(username, password, firstName, lastName, phoneNumber, emailAddress, "0", "", "");

            else
                user = new Student(username, password, firstName, lastName, phoneNumber, emailAddress, "0", "", "");
        }

        allUsers.add(user);
        return String.format("%sSigned up successfully !%s", Color.GREEN_BOLD_BRIGHT, Color.RESET);
    }

    // read the file and return string array
    public static String[] read(String file) {
        ArrayList<String> data = new ArrayList<>();

        try {
            File myObj = new File(file);

            if (myObj.createNewFile())  // if not exists
                System.out.printf("%sDatabase has been cleared !%s\n", Color.RED_BRIGHT, Color.RESET);

            else { // if file exists
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine())
                    data.add(myReader.nextLine());
            }

        } catch (IOException e) {
            System.out.printf("%sAn error occurred !%s\n", Color.RED_BRIGHT, Color.RESET);
            e.printStackTrace();
        }

        return data.toArray(new String[0]);
    }

    // write the data to file
    public static void write(String file, String data) {
        try {
            File myObj = new File(file);

            if (myObj.createNewFile()) // if not exists
                System.out.printf("%sDatabase has been cleared !%s\n", Color.RED_BRIGHT, Color.RESET);

            FileWriter myWriter = new FileWriter(file);
            myWriter.write(data);
            myWriter.close();
            System.out.printf("%s%s saved successfully !%s\n", Color.GREEN_BRIGHT, file, Color.RESET);

        } catch (IOException e) {
            System.out.printf("%sAn error occurred !%s\n", Color.RED_BRIGHT, Color.RESET);
            e.printStackTrace();
        }
    }

    // method to print the calendar with color
    public static void printCalendar(String mode, int startDay, ArrayList<Integer> reservedDays) {
        LocalDate today = LocalDate.now(); // today date
        LocalDate nextMonth = today.plusDays(31);
        Date date;

        if (mode.equals("next")) { // next month
            date = Date.from(nextMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

            SolarCalendar sc = new SolarCalendar(date); // create a solar calendar of next month
            System.out.println(Color.YELLOW_BOLD_BRIGHT + "Next Month: ");

            int dayOfWeek = sc.WeekDay;
            int daysInMonth = sc.month >= 6 ? 30 : 31;

            //print month name and year
            System.out.println(Color.CYAN_BOLD_BRIGHT + " Sh  Ye  Do  Se  Ch  Pa  Jo" + Color.RESET);

            //print initial spaces
            StringBuilder initialSpace = new StringBuilder();
            for (int i = 0; i < dayOfWeek; i++)
                initialSpace.append("    ");
            System.out.print(initialSpace);

            //print the days of the month starting from 1
            for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {
                for (int j = ((i == 0) ? dayOfWeek : 0); j < 7 && (dayOfMonth <= daysInMonth); j++) {
                    System.out.print(Color.RESET);
                    if (reservedDays.contains(dayOfMonth)) // if this day is reserved
                        System.out.printf(Color.GREEN + "%3d " + Color.GREEN, dayOfMonth);
                    else
                        System.out.printf(Color.RED + "%3d " + Color.RED, dayOfMonth);
                    dayOfMonth++;
                }
                System.out.println(Color.RESET);
            }

        } else { // this month
            date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

            SolarCalendar sc = new SolarCalendar(date);
            System.out.println(Color.YELLOW_BOLD_BRIGHT + "Today: " + sc.strToday() + Color.RESET);

            int dayOfWeek = sc.WeekDay;
            int daysInMonth = sc.month >= 6 ? 30 : 31;

            //print month name and year

            System.out.println(Color.CYAN_BOLD_BRIGHT + " Sh  Ye  Do  Se  Ch  Pa  Jo" + Color.RESET);

            //print initial spaces
            StringBuilder initialSpace = new StringBuilder();
            for (int i = 0; i < dayOfWeek; i++)
                initialSpace.append("    ");
            System.out.print(initialSpace);

            //print the days of the month starting from 1
            for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {
                for (int j = ((i == 0) ? dayOfWeek : 0); j < 7 && (dayOfMonth <= daysInMonth); j++) {
                    if (dayOfMonth < startDay) // underline the passed days
                        System.out.printf(Color.WHITE_UNDERLINED + "%3d " + Color.WHITE_UNDERLINED, dayOfMonth);

                    else {
                        System.out.print(Color.RESET);
                        if (reservedDays.contains(dayOfMonth))
                            System.out.printf(Color.GREEN + "%3d " + Color.GREEN, dayOfMonth);
                        else
                            System.out.printf(Color.RED + "%3d " + Color.RED, dayOfMonth);
                    }
                    dayOfMonth++;
                }
                System.out.println(Color.RESET);
            }
        }
    }
}

class Color {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
}

class Food {
    // variables
    private String day;
    private String appetizer;
    private String mainDish;
    private String sideDish;
    private String dessert;
    private String drink;
    private int cost;

    // constructors
    public Food(String day, String appetizer, String mainDish, String sideDish, String dessert, String drink, int cost) {
        setDay(day);
        setAppetizer(appetizer);
        setMainDish(mainDish);
        setSideDish(sideDish);
        setDessert(dessert);
        setDrink(drink);
        setCost(cost);
    }

    // getter and setter methods
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAppetizer() {
        return appetizer;
    }

    public void setAppetizer(String appetizer) {
        this.appetizer = appetizer;
    }

    public String getMainDish() {
        return mainDish;
    }

    public void setMainDish(String mainDish) {
        this.mainDish = mainDish;
    }

    public String getSideDish() {
        return sideDish;
    }

    public void setSideDish(String sideDish) {
        this.sideDish = sideDish;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    // toString method with color
    public String toString(String mode) {
        String header = "";
        if (mode.equals("with header"))
            header = String.format("%s%-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.YELLOW_BOLD_BRIGHT,
                    "Day", "Appetizer", "Main Dish", "Side Dish", "Dessert", "Drink", "Cost");

        return header + String.format("%s%-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n%s", Color.CYAN_BOLD_BRIGHT,
                getDay(), getAppetizer(), getMainDish(), getSideDish(), getDessert(), getDrink(), getCost(), Color.RESET);
    }

    // toString method with color, if have an arraylist of Food objects
    public static String toString(ArrayList<Food> foods) {
        StringBuilder header = new StringBuilder(String.format("%s%-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.YELLOW_BOLD_BRIGHT,
                "Day", "Appetizer", "Main Dish", "Side Dish", "Dessert", "Drink", "Cost"));

        for (Food food : foods)
            header.append(food.toString(""));

        return header.toString();
    }
}