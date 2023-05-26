package opinion;

public class Opinion {

    private float mark;
    private Member reviewer;

    public Opinion(float mark, Member reviewer) {
        this.mark = mark;
        this.reviewer = reviewer;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public Member getReviewer() {
        return reviewer;
    }

    public void setReviewer(Member reviewer) {
        this.reviewer = reviewer;
    }

    public void modifyOpinion(float mark) {
        this.mark = mark;
    }

}