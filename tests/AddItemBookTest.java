package tests;

import opinion.ISocialNetwork;
import opinion.Member;
import opinion.SocialNetwork;


import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

public class AddItemBookTest {

    private static int addBookBadEntryTest(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId,
	String errorMessage) {

int nbBooks = sn.nbBooks();

try {
    sn.addItemBook(login, password, title, kind, author, nbPages); 

    System.out.println("Err " + testId + " : " + errorMessage); 

    return 1; 
} catch (BadEntryException e) { 

    if (sn.nbBooks() != nbBooks) { 

        System.out
                .println("Err "
                        + testId
                        + " : BadEntry was thrown but the number of books was changed"); 

        return 1;
    } else

        return 0; 

} catch (Exception e) { 

    System.out.println("Err " + testId + " : unexpected exception. "
            + e); 
    e.printStackTrace(); 
    return 1; 
}
}

	private static int addItemBookAlreadyExistsException(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId,
	String errorMessage) {

		int nbBooks = sn.nbBooks(); 

		try {
			sn.addItemBook(login, password, title, kind, author, nbPages); 
			System.out.println("Err " + testId + " : " + errorMessage); 
			return 1; 
		} catch (ItemBookAlreadyExistsException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out
						.println("Err "
								+ testId
								+ " : ItemBookAlreadyExistsException was thrown, but the number of books was changed");
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}

    private static int addNotMemberException(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId,
	String errorMessage) {
        
		int nbBooks = sn.nbBooks(); 
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out
						.println("Err "
								+ testId
								+ " : NotMemberException was thrown, but the number of books was changed");
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); 
			e.printStackTrace();
			return 1; 
		}
    }

	private static int addBookOKTest(ISocialNetwork sn,
    String login, String password, String title,
    String kind, String author, int nbPages, String testId) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			if (sn.nbBooks() != nbBooks + 1) { 
				System.out.println("Err " + testId
						+ " : the number of book (" + nbBooks
						+ ") was not incremented");
				return 1; 
			} else
				return 0;
		} catch (Exception e) {
			System.out
					.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1;
		}
	}

	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();

		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();

		int nbTests = 0;
		int nbErrors = 0;
		
		try {
			sn.addMember("Kyrian", "kyrian", "notnull");
			sn.addMember("Tommy", "tommy", "notnull");
			sn.addMember("Marin", "marin", "notnull");
		} catch (BadEntryException | MemberAlreadyExistsException e) {
			e.printStackTrace();
		}

		System.out.println("Testing addItemBook()");

		// <=> test n°1
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
            "Kyrian", 3, "1.5",
                "addItmeBook() doesn't reject null title)");
        nbTests++;
        nbErrors += addBookBadEntryTest(
            sn, "B", "pass", " ", "Aventure",
            "Kyrian", 3, "1.6",
                "addItmeBook() doesn't reject title at least one character other than space)");
        nbTests++;
        nbErrors += addBookBadEntryTest(
            sn, "B", "pass", "title", "Aventure",
            null, 3, "1.7",
                "addItmeBook() doesn't reject null author)");
        nbTests++;
        nbErrors += addBookBadEntryTest(
            sn, "B", "pass", "title", "Aventure",
            "Kyrian", 0, "1.8",
                "addItmeBook() doesn't reject <0 nbPages)");

		// <=> test n°2

		nbTests++;
		nbErrors += addBookOKTest(sn, "Kyrian", "kyrian", "L'aventure", "Aventure", "Marchand", 18, "2.1a");
		nbTests++;
		nbErrors += addBookOKTest(sn, "Tommy", "tommy", "La boulangerie", "Policier", "Girardi", 22, "2.1b");
		nbTests++;
		nbErrors += addBookOKTest(sn, "Marin", "marin", "IMT Atlantique", "Fantastique", "Pigarre", 3, "2.1c");

		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
        "Kyrian", "kyrian",new String("L'aventure"),
        "Aventure", "Marchand",  18, "2.2",
        "The title of the first book was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
        "Marin", "marin",new String("La boulangerie"),
        "Policier", "Pignarre",  22, "2.3",
        "The title of the last book was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
        "Kyrian", "kyrian",new String("L'aVenTure"),
        "Aventure", "Marchand",  18, "2.4",
        "An already registered title, but with different case, was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
        "Kyrian", "kyrian",new String(" L'aventure "),
        "Aventure", "Marchand",  18, "2.5",
        "An already registered title, surrounded by leading/trailing blanks, was accepted as title for a new book");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsException(sn,
        "Kyrian", "kyrian","L'a" + "ven" + "ture",
        "Aventure", "Marchand",  18, "2.6",
        "A String concatenation building an already registered title was accepted as login for a new member");
		nbTests++;

		// <=> test n°3
		// memeber already exist

		nbErrors += addNotMemberException(sn,
        "Antoine", "kyrian",new String("L'aventure"),
        "Aventure", "Marchand",  18, "3.1",
        "The Login of the first book was accepted");
		nbErrors += addNotMemberException(sn,
        "Kyrian", "blabla",new String("L'aventure"),
        "Aventure", "Marchand",  18, "3.2",
        "The password dosn't match");

        // <=> test n°4

		if (nbFilms != sn.nbFilms()) {
			System.out
					.println("Error : the number of films was unexepectedly changed by addMember()");
			nbErrors++;
		}

		System.out.println("Final state of the social network : " + sn);

		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("addItemBookTest : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in addItemBookTest test code - Can't return valuable test results");
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
