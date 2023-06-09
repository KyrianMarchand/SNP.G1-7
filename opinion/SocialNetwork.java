package opinion;

import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.RunElement;

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
public class SocialNetwork implements ISocialNetworkPremium {

    private LinkedList<Member> members;
    private LinkedList<Book> books;
    private LinkedList<Film> films;

    public SocialNetwork() {
        this.members = new LinkedList<Member>();
        this.books = new LinkedList<Book>();
        this.films = new LinkedList<Film>();
    }

    /**
     * @return int
     */
    @Override
    public int nbMembers() {
        return members.size();
    }

    /**
     * @return int
     */
    @Override
    public int nbFilms() {
        return films.size();
    }

    /**
     * @return int
     */
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

    /**
     * Adds a new film to the library.
     * 
     * @param login
     * @param password
     * @param title
     * @param kind
     * @param director
     * @param scenarist
     * @param duration
     * @throws BadEntryException
     * @throws NotMemberException
     * @throws ItemFilmAlreadyExistsException
     */
    @Override
    public void addItemFilm(String login, String password, String title,
            String kind, String director, String scenarist, int duration)
            throws BadEntryException, NotMemberException,
            ItemFilmAlreadyExistsException {
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
        if (director == null) {
            throw new BadEntryException("Invalid director");
        }
        if (scenarist == null) {
            throw new BadEntryException("Invalid scenarist");
        }
        if (duration <= 0) {
            throw new BadEntryException("Invalid duration");
        }
        Member m = this.getMember(login);
        if (m == null || !m.getPassword().equals(password)) {
            throw new NotMemberException("The password does not match with the login of a registered member.");
        }
        String newTitle = title.trim();
        for (Film film : films) {
            if (film.getTitle().trim().equalsIgnoreCase(newTitle)) {
                throw new ItemFilmAlreadyExistsException();
            }
        }
        Film newFilm = new Film(newTitle, kind.trim(), director.trim(), scenarist.trim(), duration);
        films.add(newFilm);
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
     * @param kind
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

    /**
     * @param login
     * @param password
     * @param title
     * @param mark
     * @param comment
     * @throws BadEntryException
     * @throws NotMemberException
     * @throws NotItemException
     * @return mean of the marks for this film
     */
    @Override
    public float reviewItemFilm(String login, String password, String title,
            float mark, String comment) throws BadEntryException,
            NotMemberException, NotItemException {

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
        Film film = this.getFilm(title);
        if (film == null)
            throw new NotItemException("Unknown film");
        Review review = null;
        for (Review r : member.getReviewMemberList()) {
            if (r.getItem().equals(film)) {
                review = r;
                break;
            }
        }
        if (review == null) {
            review = new Review(mark, member, comment, film);
            member.getReviewMemberList().add(review);
            film.getReviewItemList().add(review);
        } else {
            review.setMark(mark);
            review.setComment(comment);
        }
        float avgMark = 0.0f;
        for (Review r : film.getReviewItemList()) {
            avgMark += r.getMark();
        }
        avgMark /= film.getReviewItemList().size();
        return avgMark;
    }

    /**
     * @param title
     * @return the film with the given title, or null if it does not exist
     */
    private Film getFilm(String title) throws NotItemException {
        for (Film film : films) {
            if (film.getTitle().toLowerCase().equalsIgnoreCase(title)) {
                return film;
            }
        }
        throw new NotItemException("Film not found in the social network.");
    }

    /**
     * @param title
     * @return the book with the given title, or null if it does not exist
     */
    private Book getBook(String title) throws NotItemException {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new NotItemException("Book not found in the social network.");
    }

    /**
     * @param login
     * @param password
     * @param title
     * @param mark
     * @param comment
     * @throws BadEntryException
     * @throws NotMemberException
     * @throws NotItemException
     * @return mean of the marks for this book
     */
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
        } else {
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

    /**
     * @param title
     * @throws BadEntryException
     * @return title and score by name
     */
    @Override
    public LinkedList<String> consultItems(String title) throws BadEntryException {
        if (title == null || title.trim().length() == 0) {
            throw new BadEntryException("Invald title");
        }
        LinkedList<String> result = new LinkedList<>();
        try {
            Book BookAjout = getBook(title);
            if (!BookAjout.equals(null))
                result.add(
                        "The book " + BookAjout.getTitle() + " - Kind : " + BookAjout.getKind() + " - has a score of : "
                                + BookAjout.getAverageScore() + "\n");
        } catch (NotItemException e) {
            result.add(title + " Book not found in SocialNetwork");
        }
        try {
            Film filmAjout = getFilm(title);
            if (!filmAjout.equals(null))
                result.add(
                        "The film " + filmAjout.getTitle() + " - Kind : " + filmAjout.getKind() + " - has a score of : "
                                + filmAjout.getAverageScore());
        } catch (NotItemException e) {
            result.add(title + " Film not found in SocialNetwork");
        }

        return result;
    }

    /**
     * @return Members + Books + Films
     */
    public String toString() {
        String result = "";
        result += "Members:\n";
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            result += "- " + member.getLogin() + " a un karma de : " + member.getKarma() + "\n";
        }
        result += "\nBooks:\n";
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            result += "- " + book.getTitle() + " - Score: " + book.getAverageScore() + "\n";
        }
        result += "\nFilms:\n";
        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);
            result += "- " + film.getTitle() + " - Score: " + film.getAverageScore() + "\n";
        }
        return result;
    }

    /**
     * @param reviews
     * @param login
     * @return Review
     * @throws NotItemException
     */
    private Review getReview(LinkedList<Review> reviews, String login) throws NotItemException {
        for (Review review : reviews) {
            if (review.getReviewer().getLogin().equals(login)) {
                return review;
            }
        }
        throw new NotItemException("Reviewer not in SocialNetwork");
    }

    /**
     * @param login
     * @param password
     * @param mark
     * @param category
     * @param title
     * @param reviewer
     * @return float
     * @throws BadEntryException
     * @throws NotMemberException
     * @throws NotItemException
     */
    public float reviewOpinion(String login, String password, float mark, String category, String title,
            String reviewer) throws BadEntryException, NotMemberException, NotItemException {
        float ret = 0;
        if (login == null || login.trim().isEmpty()) {
            throw new BadEntryException("Invalid login");
        }

        if (password == null || password.trim().length() < 4) {
            throw new BadEntryException("Invalid password");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new BadEntryException("Invalid title");
        }

        if (mark < 0.0 || mark > 5.0) {
            throw new BadEntryException("Invalid mark");
        }

        if (category == null
                || (!category.trim().toLowerCase().equals("film") && !category.trim().toLowerCase().equals("book"))) {
            throw new BadEntryException("Invalid category");
        }

        if (reviewer == null || reviewer.trim().isEmpty()) {
            throw new BadEntryException("Invalid reviewer");
        }

        Member member = this.getMember(login);
        if (member == null || !member.getPassword().equals(password)) {
            throw new NotMemberException("The password does not match with the login of a registered member.");
        }

        Member reviewers = this.getMember(reviewer);
        if (reviewers == null) {
            throw new NotMemberException("The reviewer does not exist.");
        }

        LinkedList<Review> reviews = new LinkedList<Review>();

        if (category.trim().toLowerCase().equals("film")) {
            Film film = this.getFilm(title);
            if (film == null)
                throw new NotItemException("Unknown film");
            reviews = film.getReviewItemList();
        } else if (category.trim().toLowerCase().equals("book")) {
            Book book = this.getBook(title);
            if (book == null)
                throw new NotItemException("Unknown book");
            reviews = book.getReviewItemList();
        }

        if (!reviews.isEmpty()) {
            Review reviewToComment = getReview(reviews, reviewer);
            Opinion myOpinion = null;

            try {
                myOpinion = reviewToComment.checkOpinion(member.getLogin());
            } catch (Exception e) {
            }

            if (reviewToComment != null && myOpinion == null) {
                Opinion opinion = new Opinion(mark, member);
                reviewToComment.addOpinion(opinion);
            } else if (reviewToComment != null && myOpinion != null) {
                reviewToComment.removeOpinion(myOpinion);
                Opinion opinion = new Opinion(mark, member);
                reviewToComment.addOpinion(opinion);
            } else {
                throw new BadEntryException("No review exist.");
            }

            reviewToComment.getReviewer().computeKarma();
            ret = reviewToComment.meanOpinion();
        }
        return ret;
    }

    /**
     * @param args
     * @throws BadEntryException
     * @throws ItemFilmAlreadyExistsException
     */
    public static void main(String[] args) throws BadEntryException, ItemFilmAlreadyExistsException {
        SocialNetwork sn = new SocialNetwork();
        try {
            sn.addMember("Kyrian", "kyrian", "null");
            sn.addMember("Tommy", "tommy", "null");
            sn.addMember("Marin", "marin", "null");
            sn.addMember("Theo", "theo", "null");
        } catch (BadEntryException | MemberAlreadyExistsException e) {
            e.printStackTrace();
        }
        try {
            sn.addItemBook("Kyrian", "kyrian", "La boulangerie", "Aventure", "moi", 18);
            sn.addItemBook("Tommy", "tommy", "La police", "Policier", "lui", 12);
            sn.addItemFilm("Kyrian", "kyrian", "FightClub", "Aventure", "moi", "null", 28);
            sn.addItemFilm("Tommy", "tommy", "HitMan", "Policier", "lui", "null", 12);
        } catch (BadEntryException | NotMemberException | ItemBookAlreadyExistsException e) {
            e.printStackTrace();
        }
        try {
            sn.reviewItemBook("Kyrian", "kyrian", "La boulangerie", 5, "Excellent livre !");
            sn.reviewItemBook("Tommy", "tommy", "La boulangerie", 3, "Livre moyen.");
            sn.reviewItemBook("Tommy", "tommy", "La boulangerie", 1, "Vraiment moyen.");
            sn.reviewItemBook("Kyrian", "kyrian", "La boulangerie", 2, "Enfaite moyen...");
            sn.reviewItemFilm("Tommy", "tommy", "HitMan", 3, "Le film etait passable");

            sn.reviewOpinion("Tommy", "tommy", 5, "book", "La boulangerie", "Kyrian");
            sn.reviewOpinion("Marin", "marin", 1, "book", "La boulangerie", "Kyrian");
            sn.reviewOpinion("Theo", "theo", 1, "book", "La boulangerie", "Kyrian");
            sn.reviewOpinion("Theo", "theo", 3, "book", "La boulangerie", "Kyrian");
            sn.reviewOpinion("Theo", "theo", 1, "film", "HitMan", "Tommy");

            System.out.println(sn.toString());

        } catch (NotMemberException | BadEntryException | NotItemException e) {
            e.printStackTrace();
        }
    }
}
