package opinion;

import java.util.LinkedList;

public class Member {
    private String login;
    private String password;
    private String profile;
    private float karma;
    private LinkedList<Review> reviewMemberList;

    public Member(String login, String password, String profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
        reviewMemberList = new LinkedList<Review>();
        karma = 1;
    }

    /**
     * @return String
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return String
     */
    public String getProfile() {
        return this.profile;
    }

    /**
     * @return LinkedList<Review>
     */
    public LinkedList<Review> getReviewMemberList() {
        return reviewMemberList;
    }

    
    /** 
     * @return 
     */
    public float getKarma() {
        return karma;
    }

    
    /** 
     * @return 
     */
    public void computeKarma() {
        float mean = 0;
        Boolean look = false;
        for (int i = 0; i < reviewMemberList.size(); i++) {
            if (reviewMemberList.get(i).getOpinions().size() > 0) {
                look = true;
                mean += reviewMemberList.get(i).meanOpinion();
            }
            if (look) {
                karma = 1 + ((mean - 2.5f) / 5);
            }
        }
    }
}
