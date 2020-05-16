// package authenticationsystem;
import java.security.MessageDigest;

/**
 * User stores name, hash, password and title.
 * Can be filled in completely when read from file.
 * Can also be partially filled by username and password entry to generate a hash.
 * @author Jason Rideout
 */
public class User {
    private String userName;
    private String userHash;
    private String userPassword;
    private String userTitle;
    
    /**
     * Default constructor.
     * Sets userName, userHash, userPassword, UserTitle to "".
     */
    public User() {
        this("", "", "", "");
    }
    
    /**
     * Full constructor.
     * @param name The user name.
     * @param hash The user hash.
     * @param password The user password.
     * @param title The user title.
     */
    public User(String name, String hash, String password, String title) {
        userName = name;
        userHash = hash;
        userPassword = password;
        userTitle = title;
    }
    /**
     * Partial constructor.
     * Sets userHash to newly generated hash.
     * @param name The user name.
     * @param password The user password.
     * @throws Exception 
     */
    public User(String name, String password) throws Exception {
        userName = name;
        userPassword = password;
        userHash = generateHash();
    }
    
    /**
     * Sets the user name.
     * @param name The new username.
     */
    public void setName(String name) {
        userName = name;
    }
    /**
     * Sets the user password.
     * If there is no userHash, generates hash.
     * @param password The new password.
     * @throws Exception 
     */
    public void setPassword(String password) throws Exception {
        userPassword = password;
        if (userHash.equals("")) {
            userHash = generateHash();
        }
    }
    /**
     * Sets the user title.
     * @param title The new title.
     */
    public void setTitle(String title) {
        userTitle = title;
    }
    /**
     * Sets the user hash.
     * MD5 hash.
     * @param hash The new hash.
     */
    public void setHash(String hash) {
        userHash = hash;
    }
    
    /**
     * Gets the user name.
     * @return The user name.
     */
    public String getName() {
        return userName;
    }
    /**
     * Gets the user password.
     * Not used.
     * @return The user password.
     */
    public String getPassword() {
        return userPassword;
    }
    /**
     * Gets the user hash.
     * @return The user hash.
     */
    public String getHash() {
        return userHash;
    }
    /**
     * Gets the user title.
     * @return The user title.
     */
    public String getTitle() {
        return userTitle;
    }
    
    /**
     * Generates MD5 hash from user password.
     * @return The generated hash.
     * @throws Exception 
     */
    public String generateHash() throws Exception {
        // Get MD5 hash of password
        String original = userPassword;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }

}
