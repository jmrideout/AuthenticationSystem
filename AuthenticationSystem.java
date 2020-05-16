// package authenticationsystem;
import java.util.Scanner;
import java.io.FileInputStream;


/**
 * Zoo Authentication System.
 * Allow user to login. If they are verified,
 * they gain access to the file corresponding to their title.
 * Uses User, Credentials classes.
 * @author Jason Rideout
 */
public class AuthenticationSystem {
    /**
     * Zoo authentication system.
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Definitions
        Credentials storedCredentials = new Credentials(); // Stores credentials from credentials.txt
        int numberOfTries = 0; // Used to keep track of login attempts
        final int MAX_TRIES = 3; // Login attempt limit
        User theUser = new User(); // stores user entered username and password
        Scanner scnr = new Scanner(System.in); // Reads user input
        boolean verified = false; // Boolean flag to confirm that credentials are verified
        
        // Get credentials from file
        storedCredentials.loadList("credentials.txt");
        
        // Display login screen
        System.out.println("Welcome to the SNHU Zoological Park");
        System.out.println("Enter q to quit");
        
        // Verify loop
        while ((numberOfTries < MAX_TRIES) && !verified) {
            // Get Username and check for quit
            String tempString;
            System.out.println("Username: ");
            tempString = scnr.nextLine();
            if (tempString.equals("q")) {
                // quit program
                System.out.println("Quitting...");
                return;
            }
            else {
                theUser.setName(tempString);
            }
            
            // Get Password and check for quit
            System.out.println("Password: ");
            tempString = scnr.nextLine();
            if (tempString.equals("q")) {
                // quit program
                System.out.println("Quitting...");
                return;
            }
            else {
                theUser.setPassword(tempString);
            }
            
            // Verify username and hash
            int index = storedCredentials.findUser(theUser);
            if (index != -1) { // User found. Verified.
                verified = true;
                // Set the title to gain access to correct file.
                theUser.setTitle(storedCredentials.get(index).getTitle());
            }
            else { //not verified
                System.out.println("Incorrect username and password.");
                System.out.println();
            }
            
            numberOfTries++;
        }
        
        // Display corresponding file if verified.
        if (verified) {
            // System.out.println("Verified!");
            System.out.println(readFile(theUser.getTitle()));
        }
        else { // max attempts reached and unverified
            System.out.println("Login failed. Please contact the administrator to reset your password.");
        }
        
        System.out.println("Enter q to quit: ");
        String input = scnr.nextLine();
        while (!input.equals("q")) {
            input = scnr.nextLine();
        }
    }
    
    /**
     * Reads the file corresponding to title.
     * @param title Also the filename without ".txt".
     * @return String of the entire file's contents.
     * @throws Exception 
     */
    public static String readFile(String title) throws Exception {
        Scanner inFS = null; // reads user file
        String fullPath = title + ".txt";
        String contents = "";
        
        // Open file
        FileInputStream fileByteStream = new FileInputStream(fullPath);
        inFS = new Scanner(fileByteStream);
        
        // Copy file contents line by line and add newline between
        while (inFS.hasNext()) {
            contents += inFS.nextLine() + "\n";
        }
        
        return contents;
    }

}
