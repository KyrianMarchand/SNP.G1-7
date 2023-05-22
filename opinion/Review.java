package opinion;

import java.util.LinkedList;

public class Review {
    private String comment;
    private float mark;
    private Member reviewer;
    private Item item;
    private LinkedList<Opinion> opinions;


    public Review(float mark, Member reviewer, String comment, Item item) {
        this.mark = mark;
        this.reviewer = reviewer;
        this.comment = comment;
        this.item = item;
        this.opinions = new LinkedList<Opinion>();
    }

    public float getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public Member getReviewer() {
        return reviewer;
    }

    public Item getItem() {
        return item;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public LinkedList<Opinion> getOpinions() {
        return opinions;
    }

    public void addOpinions(Opinion o) {
        opinions.add(o);
    }
}
