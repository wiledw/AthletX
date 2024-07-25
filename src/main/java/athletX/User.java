package athletX;

/**
 * Represents a user
 */
public class User {
    private String firstName;
    private String lastName;
    private Actors actor;
    private String username;

    /**
     * Makes a new user
     * @param firstName User's first name
     * @param lastName User's last name
     * @param username User's username
     * @param actor Their status
     */
    public User(String firstName, String lastName, String username, Actors actor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.actor = actor;

    }

    /**
     * @return First name of user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the user's first name
     * @param firstName The first name to be set to
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The user's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * This method sets the user's last name
     * @param lastName The last name to be set to
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the value of the user's new username
     * @param username The new username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

}