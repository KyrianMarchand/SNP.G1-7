package opinion;

public class Opinion {

    private float mark;
    private Member reviewer;

    public Opinion(float mark, Member reviewer) {
        this.mark = mark;
        this.reviewer = reviewer;
    }
    
    /** 
     * @return float
     */
    public float getMark() {
        return mark;
    }

    /** 
     * @param mark
     * @return 
     */
    public void setMark(float mark) {
        this.mark = mark;
    }
    
    /** 
     * @return 
     */
    public Member getReviewer() {
        return reviewer;
    }

    /** 
     * @param reviewer
     * @return 
     */
    public void setReviewer(Member reviewer) {
        this.reviewer = reviewer;
    }
    /** 
     * @param mark
     * @return 
     */
    public void modifyOpinion(float mark) {
        this.mark = mark;
    }

}