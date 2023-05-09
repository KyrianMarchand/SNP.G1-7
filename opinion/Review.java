package opinion;

public class Review {
    private float mark;
    private String comment;
    private Member memberReview;

    public Review(float mark, String comment, Member memberReview) {
        this.mark = mark;
        this.comment = comment;
        this.memberReview = memberReview;
    }

    public float getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }
    
}