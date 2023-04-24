package opinion;

public class Member {
    private String login;
    private String password;
    private String profile;

    public Member(String login, String password, String profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    public String getLogin(){
        return this.login;
    }
}
