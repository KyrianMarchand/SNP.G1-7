package opinion;

public class Film extends Item {
    private String director;
    private String scenarist;
    private int duration;

    public Film(String title, String kind, String director, String scenarist, int duration) {
        super(title, kind);
        this.director = director;
        this.scenarist = scenarist;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public String getScenarist() {
        return scenarist;
    }

    public int getDuration() {
        return duration;
    }

}
