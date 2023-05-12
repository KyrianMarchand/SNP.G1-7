// Import necessary packages
package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;
import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

// A class for testing the addItemBook method of the SocialNetwork class
public class AddItemBookTest {

	// Test for a bad entry (null login or password, login with only space
	// characters)
	private static int addBookBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		// Record the number of books in the network
		int nbBooks = sn.nbBooks();

		try {
			// Try to add a book with the given information
			sn.addItemBook(login, password, title, kind, author, nbPages);

			// If there's no exception, print an error message
			System.out.println("Err " + testId + " : " + errorMessage);

			return 1; // Return an error code
		}
		// Catch a BadEntryException if thrown
		catch (BadEntryException e) {
			// Check if the number of books in the network has changed
			if (sn.nbBooks() != nbBooks) {
				// If the number of books has changed, print an error message
				System.out.println("Err " + testId + " : BadEntry was thrown but the number of books was changed");
				return 1; // Return an error code
			}
			// If the number of books hasn't changed, return no error
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

	// Test for adding a book that already exists
	private static int addItemBookAlreadyExistsException(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		// Record the number of books in the network
		int nbBooks = sn.nbBooks();

		try {
			// Try to add a book with the given information
			sn.addItemBook(login, password, title, kind, author, nbPages);
			// If there's no exception, print an error message
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1; // Return an error code
		}
		// Catch an ItemBookAlreadyExistsException if thrown
		catch (ItemBookAlreadyExistsException e) {
			// Check if the number of books in the network has changed
			if (sn.nbBooks() != nbBooks) {
				// If the number of books has changed, print an error message
				System.out.println("Err " + testId
						+ " : ItemBookAlreadyExistsException was thrown, but the number of books was changed");
				return 1; // Return an error code
			}
			// If the number of books hasn't changed, return no error
			else
				return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}

	// This method attempts to add a book to the social network, but it expects a
	// NotMemberException to be thrown
	// because the login and password provided are not associated with a member
	// account. It returns 1 if the test fails
	// and 0 if it passes.
	private static int addNotMemberException(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {

		int nbBooks = sn.nbBooks();

		try {
			// Try to add a book using the invalid login and password.
			sn.addItemBook(login, password, title, kind, author, nbPages);

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

	// This is a Java method for testing the addition of a book in a social network
	// platform
	// The method takes in several parameters: the social network object, user login
	// and password, book title, kind, author, number of pages, and a test ID
	// It returns an integer value: 0 if the test passes and 1 if it fails

	private static int addBookOKTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId) {
		// Get the current number of books in the social network
		int nbBooks = sn.nbBooks();

		try {
			// Attempt to add a new book to the social network using the provided
			// information
			sn.addItemBook(login, password, title, kind, author, nbPages);

			// Check if the number of books in the social network has been incremented by 1
			if (sn.nbBooks() != nbBooks + 1) {
				System.out.println("Err " + testId + " : the number of book (" + nbBooks + ") was not incremented");
				return 1; // If the number of books was not incremented, the test has failed
			} else {
				return 0; // If the number of books was incremented, the test has passed
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

		// Get the current number of books and films in the social network
		int nbBooks = sn.nbBooks();

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

		System.out.println("Testing addItemBook()");

		// Test #1: addBookBadEntryTest()
		nbTests++;
		nbErrors += addBookBadEntryTest(sn, null, "pass", "Titre", "Aventure",
				"Kyrian", 3, "1.1", "addItemBook() doesn't reject null login");
		nbTests++;
		nbErrors += addBookBadEntryTest(
				sn, " ", "pass", "Titre", "Aventure",
				"Kyrian", 3, "1.2",
				"addItemBook() doesn't reject logins that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addBookBadEntryTest(sn, "B ", null, "Titre", "Aventure",
				"Kyrian", 3, "1.3",
				"addItemBook() doesn't reject null passwords");
		nbTests++;
		nbErrors += addBookBadEntryTest(
				sn, "B", "  qwd ", "Titre", "Aventure",
				"Kyrian", 3, "1.4",
				"addItemBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		nbTests++;
		nbErrors += addBookBadEntryTest(
				sn, "B", "pass", null, "Aventure",
				"Kyrian", 3, "1.5",
				"addItemBook() doesn't reject null title)");
		nbTests++;
		nbErrors += addBookBadEntryTest(
				sn, "B", "pass", " ", "Aventure",
				"Kyrian", 3, "1.6",
				"addItemBook() doesn't reject title at least one character other than space)");
		nbTests++;
		nbErrors += addBookBadEntryTest(
				sn, "B", "pass", "title", "Aventure",
				null, 3, "1.7",
				"addItemBook() doesn't reject null author)");
		nbTests++;
		nbErrors += addBookBadEntryTest(
				sn, "B", "pass", "title", "Aventure",
				"Kyrian", 0, "1.8",
				"addItemBook() doesn't reject <0 nbPages)");
		nbTests++;
		nbErrors += addBookBadEntryTest(
				sn, "B", "pass", "title", null,
				"Kyrian", 3, "1.9",
				"addItemBook() doesn't reject null kind)");

		// <=> test n°2

		nbTests++;
		nbErrors += addBookOKTest(sn, "Kyrian", "kyrian", "L'aventure", "Aventure", "Marchand", 18, "2.1a");
		nbBooks++;
		nbTests++;
		nbErrors += addBookOKTest(sn, "Tommy", "tommy", "La boulangerie", "Policier", "Girardi", 22, "2.1b");
		nbBooks++;
		nbTests++;
		nbErrors += addBookOKTest(sn, "Marin", "marin", "IMT Atlantique", "Fantastique", "Pigarre", 3, "2.1c");
		nbBooks++;

		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
				"Kyrian", "kyrian", new String("L'aventure"),
				"Aventure", "Marchand", 18, "2.2",
				"The title of the first book was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
				"Marin", "marin", new String("La boulangerie"),
				"Policier", "Pignarre", 22, "2.3",
				"The title of the last book was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
				"Kyrian", "kyrian", new String("L'aVenTure"),
				"Aventure", "Marchand", 18, "2.4",
				"An already registered title, but with different case, was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
				"Kyrian", "kyrian", new String(" L'aventure "),
				"Aventure", "Marchand", 18, "2.5",
				"An already registered title, surrounded by leading/trailing blanks, was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
				"Kyrian", "kyrian", "L'a" + "ven" + "ture",
				"Aventure", "Marchand", 18, "2.6",
				"A String concatenation building an already registered title was accepted as login for a new member");
		nbTests++;

		// <=> test n°3
		// memeber already exist

		nbErrors += addNotMemberException(sn,
				"Antoine", "kyrian", new String("L'aventure"),
				"Aventure", "Marchand", 18, "3.1",
				"The Login of the first book was accepted");
		nbErrors += addNotMemberException(sn,
				"Kyrian", "blabla", new String("L'aventure"),
				"Aventure", "Marchand", 18, "3.2",
				"The password dosn't match");

		// <=> test n°4

		// Check if the number of films has changed unexpectedly
		if (nbBooks != sn.nbBooks()) {
			System.out
					.println("Error : the number of films was unexepectedly changed by addBook()");
			nbErrors++;
		}

		// Display the final state of the social network
		System.out.println("Final state of the social network : " + sn);

		try {
			// Create a TestReport object to summarize the test results
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("addItemBookTest : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			// This shouldn't happen, but handle it just in case
			System.out.println("Unexpected error in addItemBookTest test code - Can't return valuable test results");
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