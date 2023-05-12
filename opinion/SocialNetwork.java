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
     * Adds a new member to the system.
     * 
     * @param login
     * @param password
     * @param profile
     * @throws BadEntryException
     * @throws MemberAlreadyExistsException
     */

    @Override
    public void addMember(String login, String password, String profile)
            throws BadEntryException, MemberAlreadyExistsException {
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
     * Adds a new book to the library.
     * 
     * @param login
     * @param password
     * @param title
     * @param kind     the kind of the book to add
     * @param author
     * @param nbPages
     * @throws BadEntryException
     * @throws NotMemberException
     * @throws ItemBookAlreadyExistsException
     */
    @Override
    public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
            throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
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
        String newTitle = title.trim();
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

    /**
     * @param title
     * @return the book with the given title, or null if it does not exist
     */
    public Book getBook(String title) throws NotItemException {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new NotItemException("Book not found in the social network.");
    }

    @Override
    public float reviewItemBook(String login, String password, String title, float mark, String comment)
            throws BadEntryException, NotMemberException, NotItemException {

        if (login == null || login.trim().length() < 1)
            throw new BadEntryException("Invalid login");
        if (password == null || password.trim().length() < 4)
            throw new BadEntryException("Invalid password");
        if (title == null || title.trim().length() < 1)
            throw new BadEntryException("Invalid book title");
        if (mark < 0.0f || mark > 5.0f)
            throw new BadEntryException("Invalid mark");
        if (comment == null)
            throw new BadEntryException("Invalid comment");

        Member member = this.getMember(login);
        if (member == null || !member.getPassword().equals(password)) {
            throw new NotMemberException("The password does not match with the login of a registered member.");
        }

        Book book = this.getBook(title);
        if (book == null)
            throw new NotItemException("Unknown book");

        Review review = null;
        for (Review r : member.getReviewMemberList()) {
            if (r.getItem().equals(book)) {
                review = r;
                break;
            }
        }

        if (review == null) {
            review = new Review(mark, member, comment, book);
            member.getReviewMemberList().add(review);
            book.getReviewItemList().add(review);
        }

        else {
            review.setMark(mark);
            review.setComment(comment);
        }

        float avgMark = 0.0f;
        for (Review r : book.getReviewItemList()) {
            avgMark += r.getMark();
        }
        avgMark /= book.getReviewItemList().size();

        return avgMark;
    }

    @Override
    public LinkedList<String> consultItems(String title) throws BadEntryException {
        if (title == null || title.trim().length() == 0) {
            throw new BadEntryException("Invald title");
        }
        LinkedList<String> result = new LinkedList<>();
        try {
            // if a Book match with the title, he has been added in list
            Book BookAjout = getBook(title);
            if (!BookAjout.equals(null))
                result.add(BookAjout.getTitle() + " - " + BookAjout.getKind() + " - Score: "
                        + BookAjout.getAverageScore());
        } catch (NotItemException e) {
            result.add(title + " Book not found in SocialNetwork.");
            e.printStackTrace();
        }
        return result;
    }

    public String toString() {
        String result = "";
        result += "Members: ";
        for (Member member : members) {
            result += member.getLogin() + "\n";
        }
        result += "Books: ";
        for (Book book : books) {
            result += book.getTitle() + "\n";
        }
        return result;
    }

    /**
     * @param args
     * @throws BadEntryException
     */
    public static void main(String[] args) throws BadEntryException {
        SocialNetwork sn = new SocialNetwork();
        try {
            sn.addMember("Kyrian", "kyrian", "null");
            sn.addMember("Tommy", "tommy", "null");
        } catch (BadEntryException | MemberAlreadyExistsException e) {
            e.printStackTrace();
        }
        try {
            sn.addItemBook("Kyrian", "kyrian", "La boulangerie", "Aventure", "moi", 18);
            sn.addItemBook("Kyrian", "kyrian", "La police", "Policier", "moi", 32);
        } catch (BadEntryException | NotMemberException | ItemBookAlreadyExistsException e) {
            e.printStackTrace();
        }
        try {
            sn.reviewItemBook("Kyrian", "kyrian", "La boulangerie", 5, "Excellent livre !");
            sn.reviewItemBook("Tommy", "tommy", "La boulangerie", 3, "Livre moyen.");
            System.out.println(sn.consultItems("la"));
            sn.reviewItemBook("Kyrian", "kyrian", "La boulangerie", 2, "Enfaite moyen...");
            System.out.println(sn.consultItems("La boulangerie"));
        } catch (NotMemberException | BadEntryException | NotItemException e) {
            e.printStackTrace();
        }
    }
}
