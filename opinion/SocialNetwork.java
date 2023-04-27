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

    @Override
    public void addItemBook(String login, String password, String title,
            String kind, String author, int nbPages) throws BadEntryException,
            NotMemberException, ItemBookAlreadyExistsException {
        // TODO Auto-generated method stub

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

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}