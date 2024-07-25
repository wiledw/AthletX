package athletX;

import athletX.exception.CredentialException;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.security.MessageDigest;

/**
 * Store class holds store related information such as user login info, and credentials during the session.
 */
public class Store  {

    private static final Map<String, User> users = new HashMap<>();

    private static final Map<String, byte[]> credentials = new HashMap<>();

    public static User loggedIn;

    private static int customerIDIndex = 67000000;
    private static int managerIdIndex = 23000000;

    /**
     * This method is for user login and check if user exists
     * @param username username
     * @param password password
     * @throws CredentialException credential exception
     */
    public static void login(String username, String password) throws CredentialException {
        if (!users.containsKey(username)) {
            throw new CredentialException();
        }

        if (!Arrays.equals(credentials.get(username), hashPassword(password))) {
            throw new CredentialException();
        }

        loggedIn = users.get(username);
    }

    /**
     * This method is to edit username
     * @param username username
     * @param nUsername new username
     */
    public static void editUsername(String username, String nUsername)
    {
        User u = users.get(username);

        users.remove(username);

        byte[] p = credentials.get(username);
        credentials.remove(username);

        u.setUsername(nUsername);
        users.put(nUsername, u);
        credentials.put(nUsername, p);
    }

    /**
     * This method checks if user exists in the hashmap(where we store user)
     * @param username username
     * @return true if exists, false otherwise
     */
    public static boolean userExists(String username)
    {
        return users.containsKey(username);
    }

    /**
     * Add new customer to system
     * @param userName customer username
     * @param firstName customer firstname
     * @param lastName customer lastname
     * @param shippingAddress customer shipping address
     */
    public static void addCustomer(String userName, String firstName, String lastName, String shippingAddress) {
        Customer c = new Customer(userName, firstName, lastName, shippingAddress, customerIDIndex++);

        users.put(userName, c);
    }

    /**
     * Set new password
     * @param username username
     * @param password password
     */
    public static void setPassword(String username, String password)
    {
        credentials.put(username, hashPassword(password));
    }

    /**
     * Add new manager to system
     * @param username Manager username
     * @param firstName Manager firstname
     * @param lastName Manager lastname
     */
    public static void addManager(String username, String firstName, String lastName) {
        Manager m = new Manager(username, firstName, lastName, managerIdIndex++);

        users.put(username, m);
    }

    /**
     * Encrypt the password using SHA-256 algorithm
     * @param password2 password
     * @return hashed value of password
     */
    public static byte[] hashPassword(String password2) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");

        } catch(NoSuchAlgorithmException e)
        {
            // TYPO in SHA-256
        }

        return md.digest(password2.getBytes(StandardCharsets.UTF_8));

    }


}