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

    
    /** 
     * @return float
     */
    public float getMark() {
        return mark;
    }

    
    /** 
     * @return 
     */
    public String getComment() {
        return comment;
    }

    
    /** 
     * @return 
     */
    public Member getReviewer() {
        return reviewer;
    }

    
    /** 
     * @return 
     */
    public Item getItem() {
        return item;
    }

    
    /** 
     * @param comment
     * @return 
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    
    /** 
     * @param mark
     * @return 
     */
    public void setMark(float mark) {
        this.mark = mark;
    }

    
    /** 
     * @return LinkedList<Opinion>
     */
    public LinkedList<Opinion> getOpinions() {
        return opinions;
    }

    
    /** 
     * @param o
     * @return 
     */
    public void addOpinions(Opinion o) {
        opinions.add(o);
    }

    
    /** 
     * @param reviewer
     * @return Opinion
     */
    public Opinion checkOpinion(String reviewer) {
        for (Opinion opinion : opinions) {
            if (opinion.getReviewer().equals(reviewer)) {
                return opinion;
            }
        }
        return null;
    }

    
    /** 
     * @param o
     * @return 
     */
    public void addOpinion(Opinion o) {
        opinions.add(o);
    }

    
    /** 
     * @return float
     */
    public float meanOpinion() {
        float mean = 0;
        for (int i = 0; i < opinions.size(); i++) {
            mean += opinions.get(i).getMark();
        }
        return mean / opinions.size();
    }
}
