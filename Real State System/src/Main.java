import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Scanner
    Scanner scanner = new Scanner(System.in);

    // Arraylists
    static ArrayList<Land> lands = new ArrayList<>();
    static ArrayList<Document> documents = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();

    static User currentUser;

    // main method
    public static void main(String[] args) {
        // Welcome Message
        System.out.println("Welcome to Real Estate Agency !");

        Main m = new Main(); // Main object
        loadDatabase(); // first actions method
        m.Menu(); // intro menu for main class
    }

    private void Menu() {
        // variables for getting input
        String username, password;
        String enter;

        System.out.println("\nPlease select an option below:");

        System.out.print(" 1. Login\n 2. Exit\n> ");
        enter = scanner.next();

        switch (enter) {
            case "1": // login
                // get username and password
                System.out.print("Enter your username > ");
                username = scanner.next();

                System.out.print("Enter your password > ");
                password = scanner.next();

                if (CredentialCheck("login", username, password)) // check the credentials
                    login(); // login if username and password is correct

                else { // if username or password isn't correct
                    System.out.print("Your username or password was wrong !\n");
                    Menu(); // call method again
                }
                break;

            case "2": // exit
                Main.save(); // save files
                System.out.print("\nHave a nice day ! Exiting ... !\n");
                break;

            default: // wrong command
                System.out.print("You entered wrong command !\n");
                Menu();
        }
    }

    private void login() {
        // login message
        System.out.printf("\nWelcome back [ %s ] !\n", currentUser.getUsername());

        // check if the current user is which person and show its menu
        if (currentUser instanceof Manager)
            currentUser.showMenu("Manager");

        else if (currentUser instanceof Agent)
            currentUser.showMenu("Agent");

        else if (currentUser instanceof Customer)
            currentUser.showMenu("Customer");

        else if (currentUser instanceof Seller)
            currentUser.showMenu("Seller");
    }

    // method for first actions and reading from files
    public static void loadDatabase() {
        String[] temp;
        users.clear();
        users.add(new User());

        documents.clear();
        lands.clear();

        // raed users from files
        String[] users = read("src/Users.txt");

        for (int i = 1; i < users.length; i++) {
            // clean string and separate them with "-"
            users[i] = users[i].replace("\n", "").replaceAll(" +", " ").trim();
            if (users[i].equals("")) continue;
            temp = users[i].split(" - ", -1);

            // create user with its role
            switch (temp[0]) {
                case "Manager":
                    Main.users.add(new Manager(temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], Double.parseDouble(temp[8]), temp[9]));
                    break;

                case "Agent":
                    Main.users.add(new Agent(temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], Double.parseDouble(temp[8]), temp[9]));
                    break;

                case "Seller":
                    Main.users.add(new Seller(temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], Double.parseDouble(temp[8]), temp[9]));
                    break;

                case "Customer":
                    Main.users.add(new Customer(temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], Double.parseDouble(temp[8]), temp[9]));
                    break;
            }
        }

        // Documents
        String[] documents = read("src/Documents.txt");

        for (int i = 1; i < documents.length; i++) {
            // clean string and separate them with "-"
            documents[i] = documents[i].replace("\n", "").replaceAll(" +", " ");
            if (documents[i].equals("")) continue;
            temp = documents[i].split(" - ", -1);

            int userIndex = findUser(temp[2]);

            if (userIndex > -1) {
                User owner = Main.users.get(userIndex);
                Main.documents.add(new Document(Integer.parseInt(temp[0]), temp[1], owner, temp[3], Integer.parseInt(temp[4]), temp[5], Integer.parseInt(temp[6]), Double.parseDouble(temp[7])));
            }
        }

        // lands
        String[] lands = read("src/RealEstates.txt");

        for (int i = 1; i < lands.length; i++) {
            // clean string and separate them with "-"
            documents[i] = documents[i].replace("\n", "").replaceAll(" +", " ").replaceAll(" +", " ").trim();
            if (lands[i].equals("")) continue;
            temp = lands[i].split(" - ", -1);

            int DocIndex = findDocument(temp[1]);
            int userIndex = findUser(temp[2]);

            if (DocIndex > -1 && userIndex > -1) {
                Document doc = Main.documents.get(DocIndex);
                User tempOwner = Main.users.get(userIndex);

                if (temp[0].equals("Land"))
                    Main.lands.add(new Land(doc, tempOwner, temp[3], Double.parseDouble(temp[4])));

                else
                    Main.lands.add(new House(doc, tempOwner, temp[3], Double.parseDouble(temp[4]),
                            Double.parseDouble(temp[5]), Integer.parseInt(temp[6]), Boolean.parseBoolean(temp[7]),
                            Boolean.parseBoolean(temp[8]), Boolean.parseBoolean(temp[9]), Boolean.parseBoolean(temp[10]),
                            Boolean.parseBoolean(temp[11])));
            }
        }
    }

    // method for saving users and foods
    public static void save() {
        StringBuilder user = new StringBuilder("User - ID - Username - Password - First Name - Last Name - Phone Number - Email Address - Credit - Real Estates\n");

        users.remove(0);

        for (User person : users) {// add string user to main string deciding if it's admin or not
            if (person instanceof Manager)
                user.append(String.format("Manager - %s - %s - %s - %s - %s - %s - %s - %s - %s\n",
                        person.getId(), person.getUsername(), person.getPassword(), person.getFirstName(), person.getLastName(), person.getPhoneNumber(), person.getEmailAddress(), person.getCredit(), person.getRealEstates()));

            else if (person instanceof Agent)
                user.append(String.format("Agent - %s - %s - %s - %s - %s - %s - %s - %s - %s\n",
                        person.getId(), person.getUsername(), person.getPassword(), person.getFirstName(), person.getLastName(), person.getPhoneNumber(), person.getEmailAddress(), person.getCredit(), person.getRealEstates()));

            else if (person instanceof Customer)
                user.append(String.format("Customer - %s - %s - %s - %s - %s - %s - %s - %s - %s\n",
                        person.getId(), person.getUsername(), person.getPassword(), person.getFirstName(), person.getLastName(), person.getPhoneNumber(), person.getEmailAddress(), person.getCredit(), person.getRealEstates()));

            else
                user.append(String.format("Seller - %s - %s - %s - %s - %s - %s - %s - %s - %s\n",
                        person.getId(), person.getUsername(), person.getPassword(), person.getFirstName(), person.getLastName(), person.getPhoneNumber(), person.getEmailAddress(), person.getCredit(), person.getRealEstates()));
        }

        write("src/Users.txt", String.valueOf(user)); // save users


        StringBuilder document = new StringBuilder("Number Of Document - City - Owner - Address - Construction Year - Postal Code - Floor - Area\n");

        for (Document doc : documents) {// add string user to main string deciding if it's admin or not
            document.append(String.format("%s - %s - %s - %s - %s - %s - %s - %s\n",
                    doc.getNumberOfDocument(), doc.getCity(), doc.getOwner().getId(), doc.getAddress(), doc.getConstructionYear(), doc.getPostalCode(), doc.getFloor(), doc.getArea()));
        }

        write("src/Documents.txt", String.valueOf(document)); // save documents

        StringBuilder land = new StringBuilder("Type - Document - Temp Owner - Transaction Type - Value - Parking Area - Rooms - Furnished - Elevator - Balcony - Warehouse - Lobby\n");

        for (Land real : lands) {// add string user to main string deciding if it's admin or not
            if (real instanceof House) {
                House nw = (House) real;
                land.append(String.format("House - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s\n",
                        nw.getDocument().getNumberOfDocument(), nw.getTempOwner().getId(), nw.getTransactionType(), nw.getValue(),
                        nw.getParkingArea(), nw.getRooms(), nw.hasFurnished(), nw.hasElevator(), nw.hasBalcony(), nw.hasWarehouse(), nw.hasLobby()));

            } else
                land.append(String.format("Land - %s - %s - %s - %s\n",
                        real.getDocument().getNumberOfDocument(), real.getTempOwner().getId(), real.getTransactionType(), real.getValue()));
        }

        write("src/RealEstates.txt", String.valueOf(land)); // save documents
    }

    // method to check credential of user for login
    public static boolean CredentialCheck(String mode, String username, String password) {
        loadDatabase(); // recheck the file

        if (mode.equals("login")) { // login
            for (User user : users) // if user's username and password is correct
                if (!(user.getUsername() == null) && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUser = user;
                    return true;
                }
        } else // to check if username is not duplicated
            for (User user : users)
                if (!(user.getUsername() == null) && user.getUsername().equals(username))
                    return true;

        return false;
    }

    // method for signing up a new user
    public static String signUp() {
        Scanner scanner = new Scanner(System.in);
        String enter;
        User user;

        System.out.print("Please select a person below:\n");
        System.out.print(" 1. Customer\n 2. Seller\n> ");
        enter = scanner.next();

        if (!enter.equals("1") && !enter.equals("2")) // select 1 and 2
            return "Wrong command !";

        System.out.print("Enter new username > ");
        String username = scanner.next();

        if (CredentialCheck("signup", username, "---")) // duplicated username
            return "This username is already taken ! ";

        else {
            System.out.print("Enter new ID > ");
            String id = scanner.next();

            System.out.print("Enter new password > ");
            String password = scanner.next();

            System.out.print("Repeat new password > ");
            String password2 = scanner.next();

            if (!password.equals(password2))
                return "Passwords don't match !";

            System.out.print("Enter first name > ");
            String firstName = scanner.next();

            System.out.print("Enter last name > ");
            String lastName = scanner.next();

            System.out.print("Enter phone number > ");
            String phoneNumber = scanner.next();

            System.out.print("Enter email address > ");
            String emailAddress = scanner.next();

            if (enter.equals("1"))
                user = new Customer(id, username, password, firstName, lastName, phoneNumber, emailAddress, 0, "");

            else
                user = new Seller(id, username, password, firstName, lastName, phoneNumber, emailAddress, 0, "");
        }

        users.add(user);
        return "Signed up successfully !";
    }

    // read the file and return string array
    public static String[] read(String file) {
        ArrayList<String> data = new ArrayList<>();

        try {
            File myObj = new File(file);

            if (myObj.createNewFile())  // if not exists
                System.out.print("Database has been cleared !\n");

            else { // if file exists
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine())
                    data.add(myReader.nextLine());
            }

        } catch (IOException e) {
            System.out.print("An error occurred !\n");
            e.printStackTrace();
        }

        return data.toArray(new String[0]);
    }

    // write the data to file
    public static void write(String file, String data) {
        try {
            File myObj = new File(file);

            if (myObj.createNewFile()) // if not exists
                System.out.printf("Database has been cleared !\n");

            FileWriter myWriter = new FileWriter(file);
            myWriter.write(data);
            myWriter.close();
            System.out.printf("%s saved successfully !\n", file);

        } catch (IOException e) {
            System.out.print("An error occurred !\n");
            e.printStackTrace();
        }
    }

    public static int findUser(String input) {
        String id;

        if (!input.equals(""))
            id = input;

        else {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter user id > ");
            id = scanner.next();
        }

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getId().equals(id)) {
                return i;
            }

        System.out.print("There is no user with this id !\n");
        return -1;
    }

    public static int findDocument(String input) {
        int document;

        if (!input.equals(""))
            document = Integer.parseInt(input);

        else {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter document number > ");
            document = scanner.nextInt();
        }

        for (int i = 0; i < documents.size(); i++)
            if (documents.get(i).getNumberOfDocument() == document)
                return i;

        System.out.print("There is no document with this document !\n");
        return -1;
    }
}