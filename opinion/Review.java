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

    public Opinion checkOpinion(String reviewer) {
        for (Opinion opinion : opinions) {
            if (opinion.getReviewer().equals(reviewer)) {
                return opinion;
            }
        }
        return null;
    }

    public void addOpinion(Opinion o) {
        opinions.add(o);
    }

    public float meanOpinion() {
        float mean = 0;
        for (int i = 0; i < opinions.size(); i++) {
            mean += opinions.get(i).getMark();
        }
        return mean / opinions.size();
    }
}
