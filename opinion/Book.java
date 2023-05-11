package opinion;

public class Book extends Item{
    private String author;
    private int nbPages;

    public Book(String title, String kind, String author, int nbPages){
        super(title,kind);
        this.author = author;
        this.author = author;
        this.nbPages = nbPages;
    }

    public String getAuthor() {
        return author;
    }

    public int getNbPages() {
        return nbPages;
    }

}
