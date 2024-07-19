import java.util.Scanner;

enum TransactionType {
    Mortgage, Rent, Free
}

// Solar Calendar class for Persian calendar
class House extends Land {
    private Scanner scanner = new Scanner(System.in);
    private String input;

    // variables
    private double parkingArea;
    private int rooms;
    private boolean furnished, elevator, balcony, warehouse, lobby;

    // constructor
    House() {
    }

    House(Document document, User tempOwner, TransactionType transactionType, double value,
          double parkingArea, int rooms, boolean furnished, boolean elevator, boolean balcony, boolean warehouse, boolean lobby) {
        super(document, tempOwner, transactionType, value);
        setParkingArea(parkingArea, "set");
        setRooms(rooms, "set");
        setFurnished(furnished, "set");
        setElevator(elevator, "set");
        setBalcony(balcony, "set");
        setWarehouse(warehouse, "set");
        setLobby(lobby, "set");
    }

    House(Document document, User tempOwner, String transactionType, double value,
          double parkingArea, int rooms, boolean furnished, boolean elevator, boolean balcony, boolean warehouse, boolean lobby) {
        super(document, tempOwner, transactionType, value);
        setParkingArea(parkingArea, "set");
        setRooms(rooms, "set");
        setFurnished(furnished, "set");
        setElevator(elevator, "set");
        setBalcony(balcony, "set");
        setWarehouse(warehouse, "set");
        setLobby(lobby, "set");
    }

    // getter and setter methods
    public double getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(double parkingArea, String type) {
        double input;
        if (type.equals("set"))
            this.parkingArea = parkingArea;

        else {
            System.out.printf("Enter new parking area [%s] > ", getParkingArea());

            if (scanner.hasNextDouble())  // check if input is number
                input = scanner.nextDouble();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setParkingArea(input, "set");
            System.out.print("Parking area changed successfully !\n");
        }
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms, String type) {
        int input;

        if (type.equals("set"))
            this.rooms = rooms;

        else {
            System.out.printf("Enter rooms [%s] > ", getRooms());

            if (scanner.hasNextInt())  // check if input is number
                input = scanner.nextInt();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            if (rooms > 0) {
                setRooms(input, "set");
                System.out.print("Rooms changed successfully !\n");
            } else
                System.out.println("Rooms number should be more than 0");
        }
    }

    public boolean hasFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished, String type) {
        if (type.equals("set"))
            this.furnished = furnished;

        else {
            System.out.printf("Is this house furnished (yes / no) [%s] > ", hasFurnished());

            input = scanner.next();

            if (input.equalsIgnoreCase("yes"))
                setFurnished(true, "set");

            else if (input.equalsIgnoreCase("no"))
                setFurnished(false, "set");

            else {
                System.out.print("Wrong command !\n");
                return;
            }

            System.out.print("Successful !\n");
        }
    }

    public boolean hasElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator, String type) {
        if (type.equals("set"))
            this.elevator = elevator;

        else {
            System.out.printf("Is this house elevator (yes / no) [%s] > ", hasElevator());

            input = scanner.next();

            if (input.equalsIgnoreCase("yes"))
                setElevator(true, "set");

            else if (input.equalsIgnoreCase("no"))
                setElevator(false, "set");

            else {
                System.out.print("Wrong command !\n");
                return;
            }
            System.out.print("Successful !\n");
        }
    }

    public boolean hasBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony, String type) {
        if (type.equals("set"))
            this.balcony = balcony;

        else {
            System.out.printf("Is this house balcony (yes / no) [%s] > ", hasBalcony());

            input = scanner.next();

            if (input.equalsIgnoreCase("yes"))
                setBalcony(true, "set");

            else if (input.equalsIgnoreCase("no"))
                setBalcony(false, "set");

            else {
                System.out.print("Wrong command !\n");
                return;
            }
            System.out.print("Successful !\n");
        }
    }

    public boolean hasWarehouse() {
        return warehouse;
    }

    public void setWarehouse(boolean warehouse, String type) {
        if (type.equals("set"))
            this.warehouse = warehouse;

        else {
            System.out.printf("Is this house warehouse (yes / no) [%s] > ", hasWarehouse());

            input = scanner.next();

            if (input.equalsIgnoreCase("yes"))
                setWarehouse(true, "set");

            else if (input.equalsIgnoreCase("no"))
                setWarehouse(false, "set");

            else {
                System.out.print("Wrong command !\n");
                return;
            }
            System.out.print("Successful !\n");
        }
    }

    public boolean hasLobby() {
        return lobby;
    }

    public void setLobby(boolean lobby, String type) {
        if (type.equals("set"))
            this.lobby = lobby;

        else {
            System.out.printf("Is this house lobby (yes / no) [%s] > ", hasLobby());

            input = scanner.next();

            if (input.equalsIgnoreCase("yes"))
                setLobby(true, "set");

            else if (input.equalsIgnoreCase("no"))
                setLobby(false, "set");

            else {
                System.out.print("Wrong command !\n");
                return;
            }
            System.out.print("Successful !\n");
        }
    }

    @Override
    public String toString() {
        return String.format("House > [Document: %s, Temp Owner: %s, Transaction Type: %s, Value: %s T, Rent For Month: %s T, Mortgage For Year: %s T]," +
                        " [Rooms: %s, Parking Area: %s, Furnished: %s, Elevator: %s, Balcony: %s, Warehouse: %s, Lobby: %s]",
                getDocument(), getTempOwner().getFullName(), getTransactionType(), getValue(), getRentPrice(), getMortgagePrice(),
                getParkingArea(), getRooms(), hasFurnished(), hasElevator(), hasBalcony(), hasWarehouse(), hasLobby());
    }
}

class Land {
    private Scanner scanner = new Scanner(System.in);
    private String input;

    // variables
    private Document document;
    private User tempOwner;
    private TransactionType transactionType;
    private double value;

    // constructors
    public Land() {
    }

    public Land(Document document, User tempOwner, TransactionType transactionType, double value) {
        setDocument(document, "set");
        scanner = new Scanner(System.in);
        setTempOwner(tempOwner, "set");
        setTransactionType(transactionType, "set");
        setValue(value, "set");
    }

    public Land(Document document, User tempOwner, String transactionType, double value) {
        setDocument(document, "set");
        scanner = new Scanner(System.in);
        setTempOwner(tempOwner, "set");
        setTransactionType(transactionType);
        setValue(value, "set");
    }

    // setter and getter methods
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document, String type) {
        if (type.equals("set"))
            this.document = document;

        else {
            Document temp = new Document();
            temp.setNumberOfDocument(0, "");
            temp.setFloor(0, "");
            temp.setOwner(new User(), "");
            temp.setCity("", "");
            scanner = new Scanner(System.in);
            temp.setAddress("", "");
            temp.setConstructionYear(0, "");
            temp.setPostalCode("", "");
            temp.setArea(0, "");
            scanner = new Scanner(System.in);
        }
    }

    public User getTempOwner() {
        return tempOwner;
    }

    public void setTempOwner(User tempOwner, String type) {
        if (type.equals("set"))
            this.tempOwner = tempOwner;

        else {
            System.out.print("Enter the new temporary owner's id > ");
            input = scanner.next();

            boolean found = false;
            for (User person : Main.users)
                if (person.getId().equals(input)) {
                    found = true;
                    setTempOwner(person, "set");
                }

            if (found)
                System.out.print("Temporary owner changed successfully !\n");

            else
                System.out.print("No user with this id !\n");
        }
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType, String type) {
        if (type.equals("set"))
            this.transactionType = transactionType;

        else {
            System.out.printf("Enter the new transaction type (mortgage, rent, purchase) [%s] > ", getTransactionType());
            input = scanner.next();

            switch (input.toLowerCase()) {
                case "mortgage":
                    setTransactionType(TransactionType.Mortgage, "set");
                    break;

                case "rent":
                    setTransactionType(TransactionType.Rent, "set");
                    break;

                case "purchase":
                    setTransactionType(TransactionType.Free, "set");
                    break;

                default:
                    System.out.println("wrong transaction type !");
                    return;
            }
            System.out.print("Temporary owner changed successfully !\n");
        }
    }

    public void setTransactionType(String transactionType) {
        if (TransactionType.Free.toString().equals(transactionType))
            this.transactionType = TransactionType.Free;

        else if (TransactionType.Mortgage.toString().equals(transactionType))
            this.transactionType = TransactionType.Mortgage;

        else
            this.transactionType = TransactionType.Rent;
    }

    public double getValue() {
        return value;
    }

    public double getRentPrice() {
        return value * 0.5 / 100;
    }

    public double getMortgagePrice() {
        return value * 15 / 100;
    }

    public void setValue(double value, String type) {
        double input;
        if (type.equals("set"))
            this.value = value;

        else {
            System.out.printf("Enter the new value [%s] > ", getValue());

            if (scanner.hasNextDouble())  // check if input is number
                input = scanner.nextDouble();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setValue(input, "set");
            System.out.print("Value changed successfully !\n");
        }
    }

    @Override
    public String toString() {
        return String.format("Land > [Document: %s, Temp Owner: %s, Transaction Type: %s, Value: %s T, Rent For Month: %s, Mortgage For Year: %s]",
                getDocument(), getTempOwner().getFullName(), getTransactionType(), getValue(), getRentPrice(), getMortgagePrice());
    }
}

class Document {
    private Scanner scanner = new Scanner(System.in);
    private String input;

    // variables
    private int numberOfDocument, floor, constructionYear;
    private User owner;
    private String city = "-", address = "-", postalCode = "-";
    private double area;

    // constructors
    Document() {
    }

    Document(int numberOfDocument, String city, User owner, String address, int constructionYear, String postalCode, int floor, double area) {
        setNumberOfDocument(numberOfDocument, "set");
        setFloor(floor, "set");
        setOwner(owner, "set");
        setCity(city, "set");
        setAddress(address, "set");
        setConstructionYear(constructionYear, "set");
        setPostalCode(postalCode, "set");
        setArea(area, "set");
    }

    // getter and setter methods
    public int getNumberOfDocument() {
        return numberOfDocument;
    }

    public void setNumberOfDocument(int numberOfDocument, String type) {
        if (type.equals("set"))
            this.numberOfDocument = numberOfDocument;

        else {
            System.out.printf("Enter new document number [%d] > ", getNumberOfDocument());

            if (scanner.hasNextInt())  // check if input is number
                input = scanner.next();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setNumberOfDocument(numberOfDocument, "set");
            System.out.print("Number of document changed successfully !\n");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city, String type) {
        if (type.equals("set"))
            this.city = city;

        else {
            System.out.printf("Enter new city name [%s] > ", getCity());

            input = scanner.next();
            setCity(input, "set");
            System.out.print("City changed successfully !\n");
        }
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner, String type) {
        if (type.equals("set"))
            this.owner = owner;

        else {
            System.out.printf("Enter the new owner's id [%s] > ", getOwner());
            input = scanner.next();

            boolean found = false;
            for (User person : Main.users)
                if (person.getId().equals(input)) {
                    found = true;
                    setOwner(person, "set");
                }

            if (found)
                System.out.print("Owner changed successfully !\n");

            else
                System.out.print("No user with this id !\n");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address, String type) {
        if (type.equals("set"))
            this.address = address;

        else {
            System.out.printf("Enter new address [%s] > ", getAddress());

            input = scanner.next();
            setAddress(input, "set");
            System.out.print("Address changed successfully !\n");
        }
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear, String type) {
        if (type.equals("set"))
            this.constructionYear = constructionYear;

        else {
            int input;
            System.out.printf("Enter new year [%d] > ", getConstructionYear());

            if (scanner.hasNextInt())  // check if input is number
                input = scanner.nextInt();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setConstructionYear(input, "set");
            System.out.print("Construction year changed successfully !\n");
        }
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode, String type) {
        if (type.equals("set"))
            this.postalCode = postalCode;

        else {
            System.out.printf("Enter new postal code [%s] > ", getPostalCode());

            if (scanner.hasNextInt())  // check if input is number
                input = scanner.next();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setPostalCode(input, "set");
            System.out.print("Postal code changed successfully !\n");
        }
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor, String type) {
        int input;
        if (type.equals("set"))
            this.floor = floor;

        else {
            System.out.printf("Enter floors [%d] > ", getFloor());

            if (scanner.hasNextInt())  // check if input is number
                input = scanner.nextInt();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setFloor(input, "set");
            System.out.print("Floors changed successfully !\n");
        }
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area, String type) {
        if (type.equals("set"))
            this.area = area;

        else {
            double input;
            System.out.printf("Enter new area [%f] > ", getArea());

            if (scanner.hasNextDouble())  // check if input is number
                input = scanner.nextDouble();

            else {
                System.out.println("Number input expected !\n");
                return;
            }

            setArea(input, "set");
            System.out.print("Area changed successfully !\n");
        }
    }

    @Override
    public String toString() {
        return String.format("{Number Of Document: %s, Floor: %s, Owner: %s, City: %s, Address: %s, Construction Year: %s, Postal Code: %s, area: %s}",
                getNumberOfDocument(), getFloor(), getOwner().getFullName(), getCity(), getAddress(), getConstructionYear(), getPostalCode(), getArea());
    }
}