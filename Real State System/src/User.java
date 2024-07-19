import java.util.ArrayList;
import java.util.Scanner;

class Customer extends User {
    // constructors
    public Customer() {
    }

    public Customer(String id, String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, double credit, String realEstates) {
        super(id, username, password, firstName, lastName, phoneNumber, emailAddress, credit, realEstates);
    }
}

class Seller extends User {
    // constructors
    public Seller() {
    }

    public Seller(String id, String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, double credit, String realEstates) {
        super(id, username, password, firstName, lastName, phoneNumber, emailAddress, credit, realEstates);
    }
}

public class User extends Person {
    // Scanner
    private Scanner scanner = new Scanner(System.in);

    // inputs
    private String input;

    // constructors
    public User() {
        setId("0", "set");
    }

    public User(String id, String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, double credit, String realEstates) {
        super(id, username, password, firstName, lastName, phoneNumber, emailAddress, credit, realEstates);
    }

    // method to change credential
    public void changeCredentials() {
        System.out.printf("\n(%s) Please select an option below\n", getUsername());

        System.out.print(" 1. Change Username\n 2. Change Password\n 3. Change First Name\n 4. Change Last Name\n 5. Change Email Address\n 6. Change Phone Number\n 7. Back\n> ");
        input = scanner.next();

        switch (input) {
            case "1":
                setUsername("", ""); // Change Username
                changeCredentials();
                break;

            case "2":
                setPassword("", ""); // Change Password
                changeCredentials();
                break;

            case "3":
                setFirstName("", ""); // Change First Name
                changeCredentials();
                break;

            case "4":
                setLastName("", ""); // Change Last Name
                changeCredentials();
                break;

            case "5":
                setEmailAddress("", ""); // Change Email Address
                changeCredentials();
                break;

            case "6":
                setPhoneNumber("", ""); // Change Phone Number
                changeCredentials();
                break;

            case "7": // back
                break;

            default: // wrong command
                System.out.print("You entered wrong command !\n");
                changeCredentials();
        }
    }

    // method to change information of a land
    private void changeLandInfo(Land land) {
        if (land instanceof House) {
            land.setDocument(new Document(), "");
            scanner = new Scanner(System.in);
            land.setValue(0, "");
            land.setTempOwner(new User(), "");
            land.setTransactionType(TransactionType.Free, "");
            scanner = new Scanner(System.in);

            ((House) land).setParkingArea(0, "");
            ((House) land).setRooms(0, "");
            ((House) land).setFurnished(false, "");
            ((House) land).setElevator(false, "");
            ((House) land).setBalcony(false, "");
            ((House) land).setWarehouse(false, "");
            ((House) land).setLobby(false, "");

        } else {
            land.setDocument(new Document(), "");
            scanner = new Scanner(System.in);
            land.setValue(0, "");
            land.setTempOwner(new User(), "");
            land.setTransactionType(TransactionType.Free, "");
            scanner = new Scanner(System.in);
        }
    }

    // add a land
    public void addLand() {
        Land tempLand = new Land();
        Main.lands.add(tempLand);
        changeLandInfo(tempLand);
    }

    // add a house
    public void addHouse() {
        House tempHouse = new House();
        Main.lands.add(tempHouse);
        changeLandInfo(tempHouse);
    }

    // method to show assets of a user
    public void myAssets() {
        // arraylist for land and house
        ArrayList<Land> land = new ArrayList<>();
        ArrayList<Land> house = new ArrayList<>();

        // find the house and lands of the person
        for (Land item : Main.lands)
            if (getRealEstates().contains(item.getDocument().getNumberOfDocument()))
                if (item instanceof House)
                    house.add(item);
                else
                    land.add(item);

        if (land.size() + house.size() < 1)
            System.out.println("You don't have any assets !");

        else {
            // print lands of person
            if (land.size() != 0) {
                System.out.printf("My Lands (%s)\n", land.size());
                for (int i = 0; i < land.size(); i++)
                    System.out.println(i + 1 + ". " + land.get(i));
            }

            // print house of person
            if (house.size() != 0) {
                System.out.printf("My Houses (%s)\n", house.size());
                for (int i = 0; i < house.size(); i++)
                    System.out.println(i + 1 + ". " + house.get(i));
            }
        }
    }

    // method for seller opetions
    public void sellerOptions() {
        System.out.printf("\n^^ Sellers Menu ^^\n(%s) Please select an option below\n", getUsername());

        System.out.print(" 1. Add Land\n 2. Add House\n 3. My Assets\n 4. Back\n> ");
        input = scanner.next();

        switch (input) {
            case "1":
                addLand(); // Add Land
                sellerOptions();
                break;

            case "2":
                addHouse(); // Add House
                sellerOptions();
                break;

            case "3":
                myAssets(); // My Assets
                sellerOptions();
                break;

            case "4": // back
                break;

            default: // wrong command
                System.out.print("You entered wrong command !\n");
                changeCredentials();
        }
    }

    // method for finding available land and houses
    public void available(String type) {
        ArrayList<Land> land = new ArrayList<>();
        ArrayList<Land> house = new ArrayList<>();
        ArrayList<Land> both = new ArrayList<>();

        // find free land and houses
        for (Land item : Main.lands)
            if (item.getTransactionType().equals(TransactionType.Free))
                if (item instanceof House)
                    house.add(item);
                else
                    land.add(item);

        if (land.size() + house.size() < 1) {
            System.out.println("No available lands and houses !");
            return;
        }

        // print houses
        if (type.equals("houses")) {
            System.out.printf("Available Houses (%d)\n", house.size());
            for (int i = 0; i < house.size(); i++)
                System.out.println(i + 1 + ". " + house.get(i));

        } else if (type.equals("lands")) { // print lands
            System.out.printf("Available Lands (%d)\n", land.size());
            for (int i = 0; i < land.size(); i++)
                System.out.println(i + 1 + ". " + land.get(i));

        } else { // print both
            for (int i = 0; i < Main.lands.size(); i++)
                if (Main.lands.get(i).getTransactionType().equals(TransactionType.Free))
                    both.add(Main.lands.get(i));

            System.out.printf("Available Lands - Houses (%d)\n", both.size());
            for (int i = 0; i < both.size(); i++)
                System.out.println(i + 1 + ". " + both.get(i));
        }
    }

    // method to rent a land or house
    private void rent(Land tempLand) {
        int input;

        System.out.printf("How many months you want to rent [%f] > ", getCredit());
        if (scanner.hasNextInt())  // check if input is number
            input = scanner.nextInt();

        else {
            System.out.println("Number input expected !\n");
            return;
        }

        double price = tempLand.getRentPrice() * input;

        if (price <= getCredit()) {
            shopping(price);
            tempLand.setTempOwner(this, "set");
            tempLand.setTransactionType(TransactionType.Rent, "set");
            getRealEstates().add(tempLand.getDocument().getNumberOfDocument());
            System.out.println("Successful !");

        } else
            System.out.println("You don't have enough credit !");
    }

    // method to mortgage a land or house
    private void mortgage(Land tempLand) {
        int input;

        System.out.printf("How many years you want to mortgage [%f] > ", getCredit());
        if (scanner.hasNextInt())  // check if input is number
            input = scanner.nextInt();

        else {
            System.out.println("Number input expected !\n");
            return;
        }

        double price = tempLand.getMortgagePrice() * input;

        if (price <= getCredit()) {
            shopping(price);
            tempLand.setTempOwner(this, "set");
            tempLand.setTransactionType(TransactionType.Mortgage, "set");
            getRealEstates().add(tempLand.getDocument().getNumberOfDocument());
            System.out.println("Successful !");

        } else
            System.out.println("You don't have enough credit !");
    }

    // method for buying a land or house
    private void buy(Land tempLand) {
        if (tempLand.getValue() <= getCredit()) {
            shopping(tempLand.getValue());
            tempLand.getDocument().setOwner(this, "set");
            tempLand.setTransactionType(TransactionType.Free, "set");
            getRealEstates().add(tempLand.getDocument().getNumberOfDocument());
            System.out.println("Successful !");

        } else
            System.out.println("You don't have enough credit !");
    }

    // method for rent, mortgage and buy for a user
    public void customerAction(String type) {
        ArrayList<Land> both = new ArrayList<>();
        for (int i = 0; i < Main.lands.size(); i++)
            if (Main.lands.get(i).getTransactionType().equals(TransactionType.Free))
                both.add(Main.lands.get(i));

        int input;

        System.out.printf("Select from one land or house below [%f]\n\n", getCredit());
        available("both");

        System.out.print("\nYour selection > ");
        if (scanner.hasNextInt())  // check if input is number
            input = scanner.nextInt();

        else {
            System.out.println("Number input expected !\n");
            return;
        }

        if (input > 0 && input <= both.size()) {
            switch (type) {
                case "rent":
                    rent(both.get(input - 1));
                    break;

                case "mortgage":
                    mortgage(both.get(input - 1));
                    break;

                case "buy":
                    buy(both.get(input - 1));
                    break;
            }
            System.out.println(getRealEstates());

        } else
            System.out.println("Wrong input number !\n");
    }

    // method for customers
    public void customerOptions() {
        System.out.printf("\n^^ Customer Menu ^^\n(%s) Please select an option below\n", getUsername());

        System.out.print(" 1. Available Lands\n 2. Available Houses\n 3. Rent\n 4. Mortgage\n 5. Buy\n 6. Back\n> ");
        input = scanner.next();

        switch (input) {
            case "1":
                available("lands");
                customerOptions();
                break;

            case "2":
                available("houses");
                customerOptions();
                break;

            case "3":
                customerAction("rent");
                customerOptions();
                break;

            case "4":
                customerAction("mortgage");
                customerOptions();
                break;

            case "5":
                customerAction("buy");
                customerOptions();
                break;

            case "6": // back
                break;

            default: // wrong command
                System.out.print("You entered wrong command !\n");
                customerOptions();
        }
    }

    // menu method for User
    public void showMenu(String type) {
        System.out.printf("\n(%s) Please select an option below [%s T]\n", getUsername(), getCredit());

        System.out.print(" 1. Change Credentials\n 2. Charge Credit\n 3. Seller Options\n 4. Customer Options\n 5. My Assets\n 6. Exit\n> ");
        input = scanner.next();

        switch (input) {
            case "1":
                changeCredentials();
                showMenu(type);
                break;

            case "2": // Charge Credit
                setCredit(0, "");
                showMenu(type);
                break;

            case "3":
                if (type.equals("Seller"))
                    sellerOptions();
                else
                    System.out.println("You don't have permission to use this option !");
                showMenu(type);
                break;

            case "4":
                if (type.equals("Customer"))
                    customerOptions();
                else
                    System.out.println("You don't have permission to use this option !");
                showMenu(type);
                break;

            case "5":
                myAssets();
                showMenu(type);
                break;

            case "6": // exit
                Main.save();
                System.out.print("\nHave a nice day ! Exiting ... !\n");
                break;

            default: // wrong command
                System.out.print("You entered wrong command !\n");
                showMenu(type);
        }
    }
}