// package authenticationsystem;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores credentials from file into an array of Users.
 * Credential file must have the following format without spaces:
 * name \t hash \t password \t title
 * @author Jason Rideout
 */
public class Credentials {
    private User[] credentialList;
    
    /**
     * Default constructor.
     * Sets credentialList to null.
     */
    Credentials() {
        credentialList = null;
    }
    
    /**Populates credentialList from credentials.txt file at filepath.
     * @param filepath The filepath to the credentials.txt file
     * @throws Exception
     */
    public void loadList(String filepath) throws Exception {
        Scanner inFS = null; // reads credential file
        Scanner lineScanner = null; // reads lines
        ArrayList list = new ArrayList(0); // Used to initially get data and length
        
        // Try to open file
        FileInputStream fileByteStream = new FileInputStream(filepath);
        inFS = new Scanner(fileByteStream);
        
        // Fill array
        while (inFS.hasNext()) {
            list.add(inFS.nextLine());
        }
        
        // Close the file
        fileByteStream.close();
        
        credentialList = new User[list.size()]; // Make the array with correct length
        // Initialize list
        for (int i = 0; i < credentialList.length; i++) {
            credentialList[i] = new User();
        }
        
        // fill User credentials list
        for (int i = 0; i < credentialList.length; i++) {
            lineScanner = new Scanner(list.get(i).toString()); // read the current line
            lineScanner.useDelimiter("\t"); // Tab delimiter
            credentialList[i].setName(lineScanner.next()); // Set User name
            credentialList[i].setHash(lineScanner.next());
            credentialList[i].setPassword(lineScanner.next());
            credentialList[i].setTitle(lineScanner.next());
        }
        
        // Test if loadList works correctly
        // displayList();
        
        return;
    }
    
    /**
     * Finds matching username and password of unknownUser in credential list. 
     * @param theUser User with filled username and hash.
     * @return Index number of User if verified. -1 if not found.
     */
    public int findUser(User theUser) {
        // checks unknownUser against list of credentials
        if (credentialList == null) {
            System.out.println("CredentialsError: Empty credentials list.");
            return -1;
        }
        for (int i = 0; i < credentialList.length; i++) {
            // Check each userName
            String unknownName = theUser.getName();
            String knownName = credentialList[i].getName();
            if (unknownName.equals(knownName)) {
                // Matching userName. Check password.
                String unknownHash = theUser.getHash();
                String knownHash = credentialList[i].getHash();
                if (unknownHash.equals(knownHash)) {
                    // verified
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * Prints list to System.out.
     * Test function only
     */
    private void displayList() {
        for (int i = 0; i < credentialList.length; i++) {
            System.out.println("User " + (i+1));
            System.out.println("Username: " + credentialList[i].getName());
            System.out.println("Password: " + credentialList[i].getPassword());
            System.out.println("Hash: " + credentialList[i].getHash());
            System.out.println("Title: " + credentialList[i].getTitle());
            System.out.println();
        }
        return;
    }
    
    /**
     * Gets the user at index in list.
     * @param index The index number in credentialList.
     * @return User at index. null if index is out of bounds.
     */
    public User get(int index) {
        if ((index >= credentialList.length) || (index < 0)) {
            return null;
        }
        else {
            return credentialList[index];
        }
    }
}
