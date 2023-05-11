package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;
import opinion.Member;

import exceptions.BadEntryException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

public class ReviewItemBookTest {

    private static int addReviewBadEntryTest(ISocialNetwork sn, String login, String pwd, String title, float mark,
            String comment, String testId, String errorMessage) {

        int nbMembers = sn.nbMembers(); // Number of members when starting to
                                        // run this method
        try {
            sn.reviewItemBook(login, pwd, title, mark, comment); // Try to add this member
            // Reaching this point means that no exception was thrown by
            // addMember()
            System.out.println("Err " + testId + " : " + errorMessage); // display
                                                                        // the
                                                                        // error
                                                                        // message
            return 1; // and return the "error" value
        } catch (BadEntryException e) { // BadEntry exception was thrown by
                                        // addMember() : this is a good start!
                                        // Let's now check if 'sn' was not
                                        // impacted
            if (sn.nbMembers() != nbMembers) { // The number of members has
                                               // changed : this is an error
                                               // case.
                System.out
                        .println("Err "
                                + testId
                                + " : BadEntry was thrown but the number of members was changed"); // Display
                                                                                                   // a
                                                                                                   // specific
                                                                                                   // error
                                                                                                   // message
                return 1; // return "error" value
            } else
                // The number of members hasn't changed, which is considered a
                // good indicator that 'sn' was not modified
                return 0; // return success value : everything seems OK, nothing
                          // to display
        } catch (Exception e) { // An exception was thrown by addMember(), but
                                // it was not the expected exception BadEntry
            System.out.println("Err " + testId + " : unexpected exception. "
                    + e); // Display a specific error message
            e.printStackTrace(); // Display contextual info about what happened
            return 1; // return error value
        }
    }

    // This method attempts to add a book to the social network, but it expects a
    // NotMemberException to be thrown
    // because the login and password provided are not associated with a member
    // account. It returns 1 if the test fails
    // and 0 if it passes.
    private static int addNotMemberException(ISocialNetwork sn, String login, String password, String title, float mark,
            String comment, String testId, String errorMessage) {

        try {
            // Try to add a book using the invalid login and password.
            sn.reviewItemBook(login, password, title, mark, comment);

            // If no exception is thrown, the test has failed. Print an error message and
            // return 1.
            System.out.println("Err " + testId + " : " + errorMessage);
            return 1;

        } catch (NotMemberException e) {
            // If a NotMemberException is thrown, check if the number of books in the social
            // network has changed.
            if (sn.nbBooks() != nbBooks) {
                // If the number of books has changed, the test has failed. Print an error
                // message and return 1.
                System.out.println(
                        "Err " + testId + " : NotMemberException was thrown, but the number of books was changed");
                return 1;
            } else {
                // If the number of books has not changed, the test has passed. Return 0.
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

    private static int addNotItemException(ISocialNetwork sn, String login, String password, String title, float mark,
            String comment, String testId, String errorMessage) {

        try {
            // Try to add a book using the invalid login and password.
            sn.reviewItemBook(login, password, title, mark, comment);

            // If no exception is thrown, the test has failed. Print an error message and
            // return 1.
            System.out.println("Err " + testId + " : " + errorMessage);
            return 1;

        } catch (NotItemException e) {
            // If a NotMemberException is thrown, check if the number of books in the social
            // network has changed.
            if (sn.nbBooks() != nbBooks) {
                // If the number of books has changed, the test has failed. Print an error
                // message and return 1.
                System.out.println(
                        "Err " + testId + " : NotMemberException was thrown, but the number of books was changed");
                return 1;
            } else {
                // If the number of books has not changed, the test has passed. Return 0.
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

    /**
     * Check that this new member (login, pwd, profile) can be (and <i>is</i>)
     * added to the <i>ISocialNetwork</i>.</br>
     * If OK, the method just returns 0
     * : the new member was added.</br>
     * If not OK, an error message is displayed
     * and 1 is returned ; the new member was not correctly added.
     * 
     * @param sn
     *                - the <i>ISocialNetwork</i>
     * @param login
     *                - new member's login
     * @param pwd
     *                - new member's password
     * @param profile
     *                - new member's profile
     * @param testId
     *                - the test ID that will prefix any error message displayed by
     *                this method
     * @return 0 if the test is OK, 1 if not
     */
    private static int addReviewOKTest(ISocialNetwork sn, String login, String pwd, String title,
    float mark, String comment, String testId) {
        int nbMembers = sn.nbMembers(); // Number of members when starting to
                                        // process this method
        try {
            sn.reviewItemBook(login, pwd, titlecomment, mark, ); // Try to add this member
            // No exception was thrown. That's a good start !
            if (sn.nbMembers() != nbMembers + 1) { // But the number of members
                                                   // hasn't changed
                                                   // accordingly
                System.out.println("Err " + testId
                        + " : the number of members (" + nbMembers
                        + ") was not incremented"); // Error message displayed
                return 1; // return error code
            } else
                return 0; // return success code : everything is OK, nothing to
                          // display
        } catch (Exception e) {// An exception was thrown by addMember() : this
                               // is an error case
            System.out
                    .println("Err " + testId + " : unexpected exception " + e); // Error
                                                                                // message
                                                                                // displayed
            e.printStackTrace(); // Display contextual info about what happened
            return 1; // return error code
        }
    }

    /**
     * <i>addMember()</i> main test :
     * <ul>
     * <li>check if members can be added</li>
     * <li>check if incorrect parameters cause addMember() to throw BadEntry
     * exception</li>
     * <li>check if adding already registered members cause addMember() to throw
     * AlreadyExists exception</li>
     * </ul>
     * 
     * @return a summary of the performed tests
     */
    public static TestReport test() {

        // Create a new instance of ISocialNetwork
        ISocialNetwork sn = new SocialNetwork();

        // Get the current number of books and films in the social network
        int nbBooks = sn.nbBooks();
        int nbFilms = sn.nbFilms();

        // Initialize variables to track number of tests and errors
        int nbTests = 0;
        int nbErrors = 0;

        try {
            // Add some members to the social network
            sn.addMember("Kyrian", "kyrian", "profile");
            sn.addMember("Tommy", "tommy", "profile");
            sn.addMember("Marin", "marin", "profile");
            sn.addItemBook("Kyrian", "kyrian", "L'aventure", "Aventure", "Kyrian",20);
            sn.addItemBook("Tommy", "tommy", "La boulangerie", "Boulangerie", "Tommy", 30);
            sn.addItemBook("Marin", "marin", "IMT Atlantique", "IMT", "Marin", 22);
            sn.reviewItemBook("Kyrian", "kyrian", "notnull", 20, "notnull");
            sn.reviewItemBook("Tommy", "tommy", "notnull", 20, "notnull");
            sn.reviewItemBook("Marin", "marin", "notnull", 20, "notnull");
        } catch (BadEntryException | MemberAlreadyExistsException e) {
            e.printStackTrace();
        }

        System.out.println("Testing addItemBook()");

        // Test #1: addBookBadEntryTest()
        nbTests++;
        nbErrors += addReviewBadEntryTest(sn, null, "pass", "Titre", 20,
                "comment", "1.1", "reviewItemBook() doesn't reject null login");
        nbTests++;
        nbErrors += addReviewBadEntryTest(
                sn, " ", "pass", "Titre", 20,
                "comment", "1.2",
                "reviewItemBook() doesn't reject logins that don't contain at least one character other than space");
        nbTests++;
        nbErrors += addReviewBadEntryTest(n, "B ", null, "Titre", 20,
                "comment", "1.3",
                "reviewItemBook() doesn't reject null passwords");
        nbTests++;
        nbErrors += addReviewBadEntryTest(
                sn, "B", "  qwd ", "Titre", 20,
                "Comment", "1.4",
                "reviewItemBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
        nbTests++;
        nbErrors += addReviewBadEntryTest(
                sn, "B", "pass", null, 20,
                "Comment", "1.5",
                "reviewItemBook() doesn't reject null title)");
        nbTests++;
        nbErrors += addReviewBadEntryTest(
                sn, "B", "pass", " ", 20,
                "Comment", "1.6",
                "reviewItemBook() doesn't reject title at least one character other than space)");
        nbTests++;
        nbErrors += addReviewBadEntryTest(
                sn, "B", "pass", "title", -1,
                "Comment", "1.7",
                "reviewItemBook() doesn't reject <0 mark)");
        nbTests++;
        nbErrors += addReviewBadEntryTest(
                sn, "B", "pass", "title", 6,
                "Comment", "1.7",
                "reviewItemBook() doesn't reject >5 mark)");
        nbTests++;
        nbErrors += addReviewBadEntryTest(
                sn, "B", "pass", "title", 6,
                null, "1.8",
                "reviewItemBook() doesn't reject null comment)");

        // <=> test n°2

        nbTests++;
        nbErrors += addReviewOKTest(sn, "Kyrian", "kyrian", "L'aventure", 20, "Comment", "2.1a");
        nbTests++;
        nbErrors += addReviewOKTest(sn, "Tommy", "tommy", "La boulangerie", 16, "Good book", "2.1b");
        nbTests++;
        nbErrors += addReviewOKTest(sn, "Marin", "marin", "IMT Atlantique", 12, "Very good Book", "2.1c");

        // <=> test n°3
        // memeber already exist
        nbTests++;
        nbErrors += addNotMemberException(sn,
                "Antoine", "kyrian", new String("L'aventure"),
                ,20,"Comment", "3.1",
                "The Login of the first review was accepted");
        nbTests++;
        nbErrors += addNotMemberException(sn,
                "Kyrian", "blabla", new String("L'aventure"),
                20, "Comment", "3.2",
                "The password dosn't match");

        // <=> test n°4
        nbTests++;
        nbErrors += addNotItemException(sn, "Tommy", "tommy", "BadTitle",20, "Good book", "4.1", "The title does not match with any existing book");

        // Check if the number of films has changed unexpectedly
        if (nbFilms != sn.nbFilms()) {
            System.out
                    .println("Error : the number of films was unexepectedly changed by addMember()");
            nbErrors++;
        }

        // Display the final state of the social network
        System.out.println("Final state of the social network : " + sn);

        try {
            // Create a TestReport object to summarize the test results
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("ReviewItemBookTest : " + tr);
            return tr;
        } catch (NotTestReportException e) {
            // This shouldn't happen, but handle it just in case
            System.out.println("Unexpected error in ReviewItemBookTest test code - Can't return valuable test results");
            return null;
        }
    }

    /**
     * Launches test()
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        test();
    }
}
