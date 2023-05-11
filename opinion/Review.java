package opinion;

public class Review {
    private String comment;
    private float mark;
    private Member member;
    private Item item;

    public Review(float mark, Member member, String comment, Item item) {
        this.mark = mark;
        this.member = member;
        this.comment = comment;
        this.item = item;
    }

    public float getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public Member getMember() {
        return member;
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
    
}
