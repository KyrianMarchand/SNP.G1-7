package opinion;

import java.util.LinkedList;

public class Member {
    private String login;
    private String password;
    private String profile;
    private LinkedList<Review> reviewMemberList;

    public Member(String login, String password, String profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
        reviewMemberList = new LinkedList<Review>();
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
}
