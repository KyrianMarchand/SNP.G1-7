package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

/**
 * Skeleton for the SocialNetwork
 * 
 */
public class SocialNetwork implements ISocialNetwork {

    private LinkedList<Member> members;
	private LinkedList<Book> books;

    public SocialNetwork() {
        this.members = new LinkedList<Member>();
		this.books = new LinkedList<Book>();
    }

    @Override
    public int nbMembers() {
        return members.size();
    }

    @Override
    public int nbFilms() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int nbBooks() {
		return books.size();
    }

/**
Adds a new member to the system.
@param login
@param password
@param profile 
@throws BadEntryException
@throws MemberAlreadyExistsException
*/

    @Override
    public void addMember(String login, String password, String profile) throws BadEntryException, MemberAlreadyExistsException {
        if (login == null || login.trim().isEmpty()) {
            throw new BadEntryException("Invalid login");
        }
        if (password == null || password.trim().isEmpty() || password.trim().length() < 4) {
            throw new BadEntryException("Invalid password");
        }
        if (profile == null) {
            throw new BadEntryException("Invalid profile");
        }
    
        String trimmedLogin = login.trim();
        for (Member member : members) {
            if (member.getLogin().trim().equalsIgnoreCase(trimmedLogin)) {
                throw new MemberAlreadyExistsException();
            }
        }
    
        Member newMember = new Member(trimmedLogin, password.trim(), profile.trim());
        members.add(newMember);
    }
    
    

    @Override
    public void addItemFilm(String login, String password, String title,
            String kind, String director, String scriptwriter, int duration)
            throws BadEntryException, NotMemberException,
            ItemFilmAlreadyExistsException {
        // TODO Auto-generated method stub

    }

    /**
     * @param login
     * @return
     * @throws NotMemberException
     */
    private Member getMember(String login) throws NotMemberException {
        for (Member member : members) {
            if (member.getLogin().toLowerCase().equalsIgnoreCase(login)) {
                return member;
            }
        }
        throw new NotMemberException("Member not found in the social network.");
    }

/**
Adds a new book to the library.
@param login 
@param password
@param title
@param kind the kind of the book to add
@param author
@param nbPages
@throws BadEntryException 
@throws NotMemberException 
@throws ItemBookAlreadyExistsException 
*/
@Override
public void addItemBook(String login, String password, String title, String kind, String author, int nbPages) throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
    if (login == null || login.trim().isEmpty()) {
        throw new BadEntryException("Invalid login");
    }
    if (password == null || password.trim().length() < 4) {
        throw new BadEntryException("Invalid password");
    }
    if (title == null || title.trim().isEmpty()) {
        throw new BadEntryException("Invalid title");
    }
    if (kind == null) {
        throw new BadEntryException("Invalid kind");
    }
    if (author == null) {
        throw new BadEntryException("Invalid author");
    }
    if (nbPages <= 0) {
        throw new BadEntryException("Invalid nbPages");
    }

    Member m = this.getMember(login);
    if (m == null || !m.getPassword().equals(password)) {
        throw new NotMemberException("The password does not match with the login of a registered member.");
    }

    String newTitle = title.trim().toLowerCase();
    for (Book book : books) {
        if (book.getTitle().trim().equalsIgnoreCase(newTitle)) {
            throw new ItemBookAlreadyExistsException();
        }
    }

    Book newBook = new Book(newTitle, kind.trim(), author.trim(), nbPages);
    books.add(newBook);
}

    @Override
    public float reviewItemFilm(String login, String password, String title,
            float mark, String comment) throws BadEntryException,
            NotMemberException, NotItemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float reviewItemBook(String login, String password, String title,
            float mark, String comment) throws BadEntryException,
            NotMemberException, NotItemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public LinkedList<String> consultItems(String title)
            throws BadEntryException {
        return new LinkedList<String>();
    }

    public String toString() {
        String result = "";
        result += "Members: ";
        for (Member member : members) {
            result += member.getLogin() + "\n";
        }
        result += "Books: ";
        for (Book book : books) {
            result += book.getTitle();
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
    SocialNetwork sn = new SocialNetwork();
    try {
        sn.addMember("Kyrian", "kyrian", "null");
    } catch (BadEntryException | MemberAlreadyExistsException e) {
        e.printStackTrace();
    }
    try {
        sn.addItemBook("Kyrian", "kyrian", "L'aventure", "Aventure", "moi", 18);
    } catch (BadEntryException | NotMemberException | ItemBookAlreadyExistsException e) {
        e.printStackTrace();
    }
    System.out.println(sn.toString());

    }

}