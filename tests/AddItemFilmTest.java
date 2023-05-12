// Import necessary packages
package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;
import exceptions.BadEntryException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

// A class for testing the addItemFilm method of the SocialNetwork class
public class AddItemFilmTest {

    // Test for a bad entry (null login or password, login with only space
    // characters)
    private static int addFilmBadEntryTest(ISocialNetwork sn, String login, String password, String title,
            String kind, String director, String scenarist, int duration, String testId, String errorMessage) {
        // Record the number of Films in the network
        int nbFilms = sn.nbFilms();

        try {
            // Try to add a Film with the given information
            sn.addItemFilm(login, password, title, kind, director, scenarist, duration);

            // If there's no exception, print an error message
            System.out.println("Err " + testId + " : " + errorMessage);

            return 1; // Return an error code
        }
        // Catch a BadEntryException if thrown
        catch (BadEntryException e) {
            // Check if the number of Films in the network has changed
            if (sn.nbFilms() != nbFilms) {
                // If the number of Films has changed, print an error message
                System.out.println("Err " + testId + " : BadEntry was thrown but the number of Films was changed");
                return 1; // Return an error code
            }
            // If the number of Films hasn't changed, return no error
            else
                return 0;
        }
        // Catch any other exception that might be thrown
        catch (Exception e) {
            // Print an error message and stack trace
            System.out.println("Err " + testId + " : unexpected exception. " + e);
            e.printStackTrace();
            return 1; // Return an error code
        }
    }

    // Test for adding a Film that already exists
    private static int addItemFilmAlreadyExistsException(ISocialNetwork sn, String login, String password, String title,
            String kind, String director, String scenarist, int duration, String testId, String errorMessage) {
        // Record the number of Films in the network
        int nbFilms = sn.nbFilms();

        try {
            // Try to add a Film with the given information
            sn.addItemFilm(login, password, title, kind, director, scenarist, duration);
            // If there's no exception, print an error message
            System.out.println("Err " + testId + " : " + errorMessage);
            return 1; // Return an error code
        }
        // Catch an ItemFilmAlreadyExistsException if thrown
        catch (ItemFilmAlreadyExistsException e) {
            // Check if the number of Films in the network has changed
            if (sn.nbFilms() != nbFilms) {
                // If the number of Films has changed, print an error message
                System.out.println("Err " + testId
                        + " : ItemFilmAlreadyExistsException was thrown, but the number of Films was changed");
                return 1; // Return an error code
            }
            // If the number of Films hasn't changed, return no error
            else
                return 0;
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. "
                    + e);
            e.printStackTrace();
            return 1;
        }
    }

    // This method attempts to add a Film to the social network, but it expects a
    // NotMemberException to be thrown
    // because the login and password provided are not associated with a member
    // account. It returns 1 if the test fails
    // and 0 if it passes.
    private static int addNotMemberException(ISocialNetwork sn, String login, String password, String title,
            String kind, String director, String scenarist, int duration, String testId, String errorMessage) {

        int nbFilms = sn.nbFilms();

        try {
            // Try to add a Film using the invalid login and password.
            sn.addItemFilm(login, password, title, kind, director, scenarist, duration);

            // If no exception is thrown, the test has failed. Print an error message and
            // return 1.
            System.out.println("Err " + testId + " : " + errorMessage);
            return 1;

        } catch (NotMemberException e) {
            // If a NotMemberException is thrown, check if the number of Films in the social
            // network has changed.
            if (sn.nbFilms() != nbFilms) {
                // If the number of Films has changed, the test has failed. Print an error
                // message and return 1.
                System.out.println(
                        "Err " + testId + " : NotMemberException was thrown, but the number of Films was changed");
                return 1;
            } else {
                // If the number of Films has not changed, the test has passed. Return 0.
                return 0;
            }

        } catch (Exception e) {
            // If an unexpected exception is thrown, print an error message and stack trace,
            // and return 1.
            System.out.println("Err " + testId + " : unexpected exception. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    // This is a Java method for testing the addition of a Film in a social network
    // platform
    // The method takes in several parameters: the social network object, user login
    // and password, Film title, kind, author, number of pages, and a test ID
    // It returns an integer value: 0 if the test passes and 1 if it fails

    private static int addFilmOKTest(ISocialNetwork sn, String login, String password, String title,
            String kind, String director, String scenarist, int duration, String testId) {
        // Get the current number of Films in the social network
        int nbFilms = sn.nbFilms();

        try {
            // Attempt to add a new Film to the social network using the provided
            // information
            sn.addItemFilm(login, password, title, kind, director, scenarist, duration);

            // Check if the number of Films in the social network has been incremented by 1
            if (sn.nbFilms() != nbFilms + 1) {
                System.out.println("Err " + testId + " : the number of Film (" + nbFilms + ") was not incremented");
                return 1; // If the number of Films was not incremented, the test has failed
            } else {
                return 0; // If the number of Films was incremented, the test has passed
            }
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception " + e);
            e.printStackTrace();
            return 1; // If an unexpected exception occurs, the test has failed
        }
    }

    public static TestReport test() {

        // Create a new instance of ISocialNetwork
        ISocialNetwork sn = new SocialNetwork();

        // Get the current number of Films
        int nbFilms = sn.nbFilms();

        // Initialize variables to track number of tests and errors
        int nbTests = 0;
        int nbErrors = 0;

        try {
            // Add some members to the social network
            sn.addMember("Kyrian", "kyrian", "notnull");
            sn.addMember("Tommy", "tommy", "notnull");
            sn.addMember("Marin", "marin", "notnull");
        } catch (BadEntryException | MemberAlreadyExistsException e) {
            e.printStackTrace();
        }

        System.out.println("Testing addItemFilm()");

        // Test #1: addFilmBadEntryTest()
        nbTests++;
        nbErrors += addFilmBadEntryTest(sn, null, "pass", "Titre", "Aventure",
                "Kyrian", "scen", 3, "1.1", "addItemFilm() doesn't reject null login");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, " ", "pass", "Titre", "Aventure",
                "Kyrian", "scen", 3, "1.2",
                "addItemFilm() doesn't reject logins that don't contain at least one character other than space");
        nbTests++;
        nbErrors += addFilmBadEntryTest(sn, "B ", null, "Titre", "Aventure",
                "Kyrian", "scen", 3, "1.3",
                "addItemFilm() doesn't reject null passwords");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, "B", "  qwd ", "Titre", "Aventure",
                "Kyrian", "scen", 3, "1.4",
                "addItemFilm() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, "B", "pass", null, "Aventure",
                "Kyrian", "scen", 3, "1.5",
                "addItemFilm() doesn't reject null title)");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, "B", "pass", " ", "Aventure",
                "Kyrian", "scen", 3, "1.6",
                "addItemFilm() doesn't reject title at least one character other than space)");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, "B", "pass", "title", null,
                "direc", "scen", 3, "1.7",
                "addItemFilm() doesn't reject null kind)");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, "B", "pass", "title", "Aventure",
                null, "scen", 3, "1.8",
                "addItemFilm() doesn't reject null director)");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, "B", "pass", "title", "Aventure",
                "null", null, 3, "1.9",
                "addItemFilm() doesn't reject null scenarist)");
        nbTests++;
        nbErrors += addFilmBadEntryTest(
                sn, "B", "pass", "title", "Aventure",
                "null", "null", 0, "1.10",
                "addItemFilm() doesn't reject duration < 0)");

        // <=> test n°2

        nbTests++;
        nbErrors += addFilmOKTest(sn, "Kyrian", "kyrian", "L'aventure", "Aventure", "Marchand", "scenarist", 18,
                "2.1a");
        nbFilms++;
        nbTests++;
        nbErrors += addFilmOKTest(sn, "Tommy", "tommy", "La boulangerie", "Policier", "Girardi", "scenarist", 22,
                "2.1b");
        nbFilms++;
        nbTests++;
        nbErrors += addFilmOKTest(sn, "Marin", "marin", "IMT Atlantique", "Fantastique", "Pigarre", "scenarist", 3,
                "2.1c");
        nbFilms++;
        nbTests++;

        nbErrors += addItemFilmAlreadyExistsException(sn,
                "Kyrian", "kyrian", new String("L'aventure"),
                "Aventure", "Marchand", "scenarist", 18, "2.2",
                "The title of the first Film was accepted as title for a new Film");
        nbTests++;
        nbErrors += addItemFilmAlreadyExistsException(sn,
                "Marin", "marin", new String("La boulangerie"),
                "Policier", "Pignarre", "scenarist", 22, "2.3",
                "The title of the last Film was accepted as title for a new Film");
        nbTests++;
        nbErrors += addItemFilmAlreadyExistsException(sn,
                "Kyrian", "kyrian", new String("L'aVenTure"),
                "Aventure", "Marchand", "scenarist", 18, "2.4",
                "An already registered title, but with different case, was accepted as title for a new Film");
        nbTests++;
        nbErrors += addItemFilmAlreadyExistsException(sn,
                "Kyrian", "kyrian", new String(" L'aventure "),
                "Aventure", "Marchand", "scenarist", 18, "2.5",
                "An already registered title, surrounded by leading/trailing blanks, was accepted as title for a new Film");
        nbTests++;
        nbErrors += addItemFilmAlreadyExistsException(sn,
                "Kyrian", "kyrian", "L'a" + "ven" + "ture",
                "Aventure", "Marchand", "scenarist", 18, "2.6",
                "A String concatenation building an already registered title was accepted as login for a new member");
        nbTests++;

        // <=> test n°3
        // memeber already exist

        nbErrors += addNotMemberException(sn,
                "Antoine", "kyrian", new String("L'aventure"),
                "Aventure", "Marchand", "scenarist", 18, "3.1",
                "The Login of the first Film was accepted");
        nbErrors += addNotMemberException(sn,
                "Kyrian", "blabla", new String("L'aventure"),
                "Aventure", "Marchand", "scenarist", 18, "3.2",
                "The password dosn't match");

        // <=> test n°4

        // Check if the number of films has changed unexpectedly
        if (nbFilms != sn.nbFilms()) {
            System.out
                    .println("Error : the number of films was unexepectedly changed by addFilm()");
            nbErrors++;
        }

        // Display the final state of the social network
        System.out.println("Final state of the social network : " + sn);

        try {
            // Create a TestReport object to summarize the test results
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("addItemFilmTest : " + tr);
            return tr;
        } catch (NotTestReportException e) {
            // This shouldn't happen, but handle it just in case
            System.out.println("Unexpected error in addItemFilmTest test code - Can't return valuable test results");
            return null;
        }
    }

    /**
     * 
     * Launches test()
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        test();
    }
}
// end of class and method definitions