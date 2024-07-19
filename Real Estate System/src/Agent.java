import java.util.Scanner;

public class Agent extends User {
    // Scanner
    private Scanner scanner = new Scanner(System.in);

    // constructors
    public Agent() {
    }

    public Agent(String id, String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, double credit, String realEstates) {
        super(id, username, password, firstName, lastName, phoneNumber, emailAddress, credit, realEstates);
    }

    // method for showing menu for agent and manager
    public void showMenu(String type) {
        System.out.printf("\n(%s) Please select an option below [%s T]\n", getUsername(), getCredit());
        System.out.print(" 1. Add User\n 2. Change Credentials\n 3. Change User's Credentials\n 4. Charge Credit\n 5. Charge User's Credit\n 6. Seller Options\n 7. Seller User's Options\n 8. Customer Options\n 9. Customer User's Options\n 10. My Assets\n 11. User's Assets\n 12. Exit\n> ");

        int index;
        String input = scanner.next();

        switch (input) {
            case "1":
                String result = Main.signUp(); // Add User
                System.out.println(result);
                showMenu(type);
                break;

            case "2":
                changeCredentials(); // Change Credentials
                showMenu(type);
                break;

            case "3":
                if (type.equals("Manager")) {// Change User's Credentials
                    index = Main.findUser(""); // Seller User's Options
                    if (index > -1)
                        Main.users.get(index).changeCredentials();
                } else
                    System.out.println("You don't have permission to change this user information !");
                showMenu(type);
                break;

            case "4":
                setCredit(0, ""); // Charge Credit
                showMenu(type);
                break;

            case "5":
                if (type.equals("Manager")) {// Charge User's Credit
                    index = Main.findUser(""); // Seller User's Options
                    if (index > -1)
                        Main.users.get(index).setCredit(0, "");
                } else
                    System.out.println("You don't have permission to change this user information !");
                showMenu(type);
                break;

            case "6":
                sellerOptions(); // Seller Options
                showMenu(type);
                break;

            case "7":
                index = Main.findUser(""); // Seller User's Options
                if (index > -1)
                    Main.users.get(index).sellerOptions();
                showMenu(type);
                break;

            case "8":
                customerOptions(); // Customer Options
                showMenu(type);
                break;

            case "9":
                Main.users.get(Main.findUser("")).customerOptions(); // Customer User's Options
                showMenu(type);
                break;

            case "10":
                myAssets(); // My Assets
                showMenu(type);
                break;

            case "11":
                index = Main.findUser(""); // User's Assets
                if (index > -1)
                    Main.users.get(index).myAssets();
                showMenu(type);
                break;

            case "12": // exit
                Main.save(); // save the files
                System.out.print("\nHave a nice day ! Exiting ... !\n");
                break;

            default: // wrong command
                System.out.print("You entered wrong command !\n");
                showMenu(type);
        }
    }
}