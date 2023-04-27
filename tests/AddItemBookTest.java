package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;


import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

public class AddItemBookTest {

    private static int addBookBadEntryTest(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId,
	String errorMessage) {

int nbBooks = sn.nbBooks(); // Number of members when starting to
                            // run this method
try {
    sn.addItemBook(login, password, title, kind, author, nbPages); // Try to add this member
    // Reaching this point means that no exception was thrown by
    System.out.println("Err " + testId + " : " + errorMessage); // display
                                                                // the
                                                                // error
                                                                // message
    return 1; // and return the "error" value
} catch (BadEntryException e) { // BadEntry exception was thrown by
                                // addMember() : this is a good start!
                                // Let's now check if 'sn' was not
                                // impacted
    if (sn.nbBooks() != nbBooks) { // The number of members has
                                   // changed : this is an error
                                   // case.
        System.out
                .println("Err "
                        + testId
                        + " : BadEntry was thrown but the number of books was changed"); // Display
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

	private static int addItemBookAlreadyExistsException(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId,
	String errorMessage) {

		int nbBooks = sn.nbBooks(); // Number of members when starting to
										// process this method
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages); // Try to add this member
			// Reaching this point means that no exception was thrown by
			// addMember()
			System.out.println("Err " + testId + " : " + errorMessage); // display
																		// the
																		// error
																		// message
			return 1; // and return the "error" value
		} catch (ItemBookAlreadyExistsException e) {// AlreadyExists exception was
													// thrown by addMember() :
													// this is a good start!
													// Let's now check if 'sn'
													// was not impacted
			if (sn.nbBooks() != nbBooks) {
				System.out
						.println("Err "
								+ testId
								+ " : ItemBookAlreadyExistsException was thrown, but the number of books was changed"); // Display
																												// a
																												// specific
																												// error
																												// message
				return 1;// and return the "error" value
			} else
				return 0; // return success value : everything is OK, nothing to
							// display
		} catch (Exception e) { // An exception was thrown by addMember(), but
								// it was not the expected exception
								// AlreadyExists
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
	}

    private static int addNotMemberException(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId,
	String errorMessage) {
        
		int nbBooks = sn.nbBooks(); // Number of members when starting to
										// process this method
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages); // Try to add this member
			// Reaching this point means that no exception was thrown by
			// addMember()
			System.out.println("Err " + testId + " : " + errorMessage); // display
																		// the
																		// error
																		// message
			return 1; // and return the "error" value
		} catch (NotMemberException e) {// AlreadyExists exception was
													// thrown by addMember() :
													// this is a good start!
													// Let's now check if 'sn'
													// was not impacted
			if (sn.nbBooks() != nbBooks) {
				System.out
						.println("Err "
								+ testId
								+ " : NotMemberException was thrown, but the number of books was changed"); // Display
																												// a
																												// specific
																												// error
																												// message
				return 1;// and return the "error" value
			} else
				return 0; // return success value : everything is OK, nothing to
							// display
		} catch (Exception e) { // An exception was thrown by addMember(), but
								// it was not the expected exception
								// AlreadyExists
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
    }

	private static int addBookOKTest(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId) {
		int nbBooks = sn.nbBooks(); // Number of members when starting to
										// process this method
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages); // Try to add this member
			// No exception was thrown. That's a good start !
			if (sn.nbBooks() != nbBooks + 1) { // But the number of members
													// hasn't changed
													// accordingly
				System.out.println("Err " + testId
						+ " : the number of book (" + nbBooks
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
	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();

		int nbBooks = sn.nbBooks(); // number of books in 'sn' (should be 0
									// here)
		int nbFilms = sn.nbFilms(); // number of films in 'sn' (should be 0
									// here)

		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing addItemBook()");

		// <=> test n°1

		// check if incorrect parameters cause addMember() to throw BadEntry
		// exception

		nbTests++;
		nbErrors += addBookBadEntryTest(sn, null, "pass", "Titre", "Aventure",
				"Kyrian", 3, "1.1", "addItmeBook() doesn't reject null passwords");
		nbTests++;
		nbErrors += addBookBadEntryTest(
            sn, " ", "pass", "Titre", "Aventure",
            "Kyrian", 3, "1.2",
				"addItmeBook() doesn't reject logins that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addBookBadEntryTest(sn, "B ", null, "Titre", "Aventure",
        "Kyrian", 3, "1.3",
				"addItmeBook() doesn't reject null passwords");
		nbTests++;
		nbErrors += addBookBadEntryTest(
            sn, "B", "  qwd ", "Titre", "Aventure",
            "Kyrian", 3, "1.4",
				"addItmeBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
        nbTests++;
        nbErrors += addBookBadEntryTest(
            sn, "B", "pass", null, "Aventure",
            "Kyrian", 3, "1.4",
                "addItmeBook() doesn't reject null title)");
        nbTests++;
        nbErrors += addBookBadEntryTest(
            sn, "B", "pass", " ", "Aventure",
            "Kyrian", 3, "1.4",
                "addItmeBook() doesn't reject title at least one character other than space)");
        nbTests++;
        nbErrors += addBookBadEntryTest(
            sn, "B", "pass", "title", "Aventure",
            null, 3, "1.4",
                "addItmeBook() doesn't reject null author)");
        nbTests++;
        nbErrors += addBookBadEntryTest(
            sn, "B", "pass", "title", "Aventure",
            "Kyrian", 0, "1.4",
                "addItmeBook() doesn't reject <0 nbPages)");
		// <=> test n°2

		// populate 'sn' with 3 books

		nbTests++;
		nbErrors += addBookOKTest(sn, "Kyrian", "password", "L'aventure", "Aventure", "Marchand", 18, "2.1a");
		nbTests++;
		nbErrors += addBookOKTest(sn, "Tommy", "password2", "L'aventure 2.0", "Policier", "Girardi", 22, "2.1b");
		nbTests++;
		nbErrors += addBookOKTest(sn, "Marin", "password3", "L'aventure 3.0", "Fantastique", "Pigarre", 3, "2.1c");

		// try to add already registered members

		nbTests++;
		nbErrors += addMemberAlreadyExistsTest(sn, new String("Paul"),
				"abcdefghij", "", "2.2",
				"The login of the first member was accepted as login for a new member");
		nbTests++;
		nbErrors += addMemberAlreadyExistsTest(sn, new String("Alice"),
				"abcdefghij", "", "2.3",
				"The login of the last member was accepted as login for a new member");
		nbTests++;
		nbErrors += addMemberAlreadyExistsTest(
				sn,
				new String("anToine"),
				"abcdefghij",
				"",
				"2.4",
				"An already registered login, but with different case, was accepted as login for a new member");
		nbTests++;
		nbErrors += addMemberAlreadyExistsTest(
				sn,
				new String(" Antoine "),
				"abcdefghij",
				"",
				"2.5",
				"An already registered login, surrounded by leading/trailing blanks, was accepted as login for a new member");
		nbTests++;
		nbErrors += addMemberAlreadyExistsTest(
				sn,
				"An" + "toi" + "ne",
				"abcdefghij",
				"",
				"2.6",
				"A String concatenation building an already registered login was accepted as login for a new member");

		nbTests++;
		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out
					.println("Error : the number of films was unexepectedly changed by addMember()");
			nbErrors++;
		}
		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out
					.println("Error : the number of books was unexepectedly changed by addMember()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		// Print a summary of the tests and return test results
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("AddMemberTest : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in AddMemberTest test code - Can't return valuable test results");
			return null;
			}
		}
 
	
	
	/**
	 * Launches test()
	 * @param args not used
	 */
	public static void main(String[] args) {
		test();
	}
}
