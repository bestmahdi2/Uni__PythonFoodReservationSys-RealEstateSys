import java.util.Scanner;

public class Main {
    // Scanner
    Scanner scanner = new Scanner(System.in);

    // variable to count each try
    private int tryIntroCount = 0;

    // main method
    public static void main(String[] args) {
        // Welcome Message
        System.out.printf("%s****************************************\n", Color.PURPLE_BOLD_BRIGHT);
        System.out.println("|        Food Reservation System       |");
        System.out.printf("****************************************%s\n", Color.RESET);

        Main m = new Main(); // Main object
        Utilities.firstAction(); // first actions method
        m.IntroMenu(); // intro menu for main class
    }

    private void IntroMenu() {
        // variables for getting input
        String username, password;
        String enter;

        if (tryIntroCount == 3) { // break if try more than 3 times
            System.out.printf("%s\n*****> Too many tries ! Exiting ... ! <*****\n", Color.RED_BOLD_BRIGHT);
            return;
        }

        System.out.printf("%s\n***> Please select an option below:\n", Color.YELLOW_BOLD_BRIGHT);
        if (Utilities.canSignUp) { // if sign up option is true
            System.out.print(" 1. Login\n 2. Sign Up\n 3. Exit\n> " + Color.RESET);
            enter = scanner.next();

        } else { // if sign up option is false
            System.out.print(" 1. Login\n 2. Exit\n> " + Color.RESET);
            enter = scanner.next();

            if (enter.equals("2")) // fixing exit part
                enter = "3";

            else if (!enter.equals("1")) // to avoid going to "sign up"
                enter = "4";
        }

        switch (enter) {
            case "1": // login
                // get username and password
                System.out.printf("%s*> Enter your username > ", Color.BLUE_BOLD_BRIGHT);
                username = scanner.next();

                System.out.printf("*> Enter your password > %s", Color.RESET);
                password = scanner.next();

                if (Utilities.CredentialCheck("login", username, password)) // check the credentials
                    login(); // login if username and password is correct

                else { // if username or password isn't correct
                    System.out.printf("%s**> Your username or password was wrong !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                    tryIntroCount++; // add to try count
                    IntroMenu(); // call method again
                }
                break;

            case "2": // sign up
                String result = Utilities.signUp(); // sign up method and its result
                System.out.println(result);
                if (!result.equals(String.format("%sSigned up successfully !%s", Color.GREEN_BOLD_BRIGHT, Color.RESET))) // if sign up is not successful
                    IntroMenu();
                Utilities.save();
                break;

            case "3": // exit
                Utilities.save(); // save files
                System.out.printf("%s\n*****> Have a nice day ! Exiting ... ! <*****\n", Color.GREEN_BOLD_BRIGHT);
                break;

            default: // wrong command
                System.out.printf("%s**> You entered wrong command !\n%s", Color.RED_BOLD_BRIGHT, Color.RESET);
                tryIntroCount++;
                IntroMenu();
        }
    }

    private void login() {
        // login message
        System.out.printf("%s\n****> Logged in as %s%s%s <****%n%s", Color.GREEN_BOLD_BRIGHT,
                Color.RED_BRIGHT, Utilities.currentUser.getUsername(), Color.GREEN_BOLD_BRIGHT, Color.RESET);

        // check if the current user is which person and show its menu
        if (Utilities.currentUser instanceof DirectorGeneral)
            Utilities.currentUser.showMenu("Director General");

        else if (Utilities.currentUser instanceof Admin)
            Utilities.currentUser.showMenu(((Admin) Utilities.currentUser).getRole());

        else if (Utilities.currentUser instanceof Supervisor)
            Utilities.currentUser.showMenu("Supervisor");

        if (Utilities.currentUser instanceof Employee)
            Utilities.currentUser.showMenu("Employee");

        else if (Utilities.currentUser instanceof Student)
            Utilities.currentUser.showMenu("Student");
    }
}