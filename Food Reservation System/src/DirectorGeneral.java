public class DirectorGeneral extends Admin {
    // constructors
    public DirectorGeneral() {
    }

    public DirectorGeneral(String username, String password, String firstName, String lastName, String phoneNumber, String emailAddress, String credit, String mealThisMonth, String mealNextMonth) {
        super(username, password, firstName, lastName, phoneNumber, emailAddress, credit, mealThisMonth, mealNextMonth);
    }

    public DirectorGeneral(String[] array) {
        super(array[0], array[5], array[1], array[2], array[3], array[4], array[6], array[7], array[8]);
    }

    // toString method with color
    @Override
    public String toString(String mode) {
        String header = "";
        if (mode.equals("with header"))
            header = String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n", Color.YELLOW_BOLD_BRIGHT,
                    "Username", "First Name", "Last Name", "Phone Number", "Email Address", "This Month Meal", "Next Month Meal");

        return header + String.format("%s%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s\n%s", Color.CYAN_BOLD_BRIGHT,
                getUsername(), getFirstName(), getLastName(), getPhoneNumber(), getEmailAddress(), getMealThisMonth(), getMealNextMonth(), Color.RESET);
    }
}
