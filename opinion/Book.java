package opinion;

public class Book {
    private String title;
    private String kind;
    private String author;
    private int nbPages;

    public Book(String title, String kind, String author, int nbPages){
        this.title = title;
        this.author = author;
        this.kind = kind;
        this.author = author;
        this.nbPages = nbPages;
    }

    public String getTitle(){
        return this.title;
    }
}
