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

    /**
     * @return String
     */
    public String getDirector() {
        return director;
    }

    /**
     * @return String
     */
    public String getScenarist() {
        return scenarist;
    }

    /**
     * @return int
     */
    public int getDuration() {
        return duration;
    }

}
