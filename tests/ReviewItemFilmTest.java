package tests;

import opinion.ISocialNetwork;
import opinion.ISocialNetworkPremium;
import opinion.SocialNetwork;

import exceptions.BadEntryException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

public class ReviewItemFilmTest {

    private static int addReviewBadEntryTest(ISocialNetwork sn, String login, String password, String title,
            float mark, String comment, String testId, String errorMessage) {

        try {
            sn.reviewItemFilm(login, password, title, mark, comment);
            System.out.println("OK " + testId + " : " + errorMessage);

            return 1;
        } catch (BadEntryException e) {
            System.out.println("OK " + testId + " : BadEntry was thrown");
            return 0;

        } catch (Exception e) {

            System.out.println("OK " + testId + " : unexpected exception. " + e);
            return 1;
        }
    }

    private static int[] reviewItemFilmMoy(ISocialNetwork sn) {

        float aver = 0;
        int testErr[] = { 0, 0 };

        System.out.println("add reviewItemFilm()");
        try {
            aver = sn.reviewItemFilm("Kyrian", "kyrian", "FightClub", 5, "Très bon film");
            if (aver == 5) {
                System.out.println("1.1 OK -> the login add a mark");
                testErr[0]++;
            } else {
                System.out.println("Err 1.1 : err add review");
                testErr[1]++;

            }

        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewItemFilm("Kyrian", "kyrian", "FightClub", 2, "Moyen...");
            if (aver == 2) {
                System.out.println("1.2 OK -> the login change his mark and the average mark has been changed");
                testErr[0]++;
            } else {
                System.out.println("Err 10.2: err add review");
                testErr[1]++;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            aver = sn.reviewItemFilm("Tommy", "tommy", "FightClub", 4, "Bien !");
            if (aver == 3) {
                System.out.println("1.3 OK -> other login add a mark and the average mark has been changed");
                testErr[0]++;
            } else {
                System.out.println("Err 1.3:  err add review");
                testErr[1]++;

            }

        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewItemFilm("Tommy", "tommy", "FightClub", 0, "Nul...");
            if (aver == 1) {
                System.out.println("1.4 OK -> other login change his mark and the average mark has been changed");
                testErr[0]++;
            } else {
                System.out.println("Err 10.4: err add review");
                testErr[1]++;

            }

        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        System.out.println("NotMemberException()");
        try {
            aver = sn.reviewItemFilm("Marin", "kyrian", "FightClub", 4, "Bien");
            if (aver == 2) {
                System.out.println("Err 1.5: add Film with bad login");
                testErr[1]++;
            }

        } catch (NotMemberException e) {
            System.out.println("1.5 OK -> NotMemberException has been successfully catched");
            testErr[0]++;

        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        System.out.println("NotItemException()");
        try {
            aver = sn.reviewItemFilm("Kyrian", "kyrian", "IMT Atlantique", 4, "Bien");
            System.out.println("Err 1.6: add bad Film");

        } catch (NotMemberException e) {
            System.out.println("1.6 OK -> NotMemberException has been successfully catched");
            testErr[1]++;

        } catch (NotItemException e) {
            System.out.println("1.6 OK -> NotItemException has been successfully catched");
            testErr[0]++;

        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }
        return testErr;
    }

    private static void nominalTestReviewItemFilm(ISocialNetwork sn) {
        float aver = 0f;

        try {
            System.out.println("Tommy added a review with a mark of 5, the average is now : "
                    + sn.reviewItemFilm("Tommy", "tommy", "La police", 5, "Good book"));
            System.out.println("Tommy changed his mark one the review with a mark of 2, the average is now : "
                    + sn.reviewItemFilm("Tommy", "tommy", "La police", 2, "Excellent"));
            System.out.println("Kyrian added a review with a mark of 4, the average is now : "
                    + sn.reviewItemFilm("Kyrian", "kyrian", "La police", 4, "Great"));
            System.out.println("Kyrian changed his mark one the review with a mark of 3, the average is now : "
                    + sn.reviewItemFilm("Kyrian", "kyrian", "La police", 3, "Excellent"));
        } catch (NotMemberException | BadEntryException | NotItemException e) {
            e.printStackTrace();
        }

        System.out.println("");

    }

    public static TestReport test() {

        ISocialNetwork sn = new SocialNetwork();
        int nbTests = 0, nbErrors = 0;

        System.out.println("Testing reviewItemFilm()");

        try {
            sn.addMember("Kyrian", "kyrian", "nononon");
            sn.addMember("Tommy", "tommy", "ouiouioui");
            sn.addItemFilm("Kyrian", "kyrian", "FightClub", "Aventure", "moi", "scenarist1", 28);
            sn.addItemFilm("Tommy", "tommy", "La police", "Policier", "lui", "scenarist2", 12);
        } catch (BadEntryException e1) {
            e1.printStackTrace();
        } catch (NotMemberException e1) {
            e1.printStackTrace();
        } catch (ItemFilmAlreadyExistsException e1) {
            e1.printStackTrace();
        } catch (MemberAlreadyExistsException e) {
            e.printStackTrace();
        }

        System.out.println("\nNominal Test; \n");
        nominalTestReviewItemFilm(sn);

        System.out.println("BadEntryException()");
        nbTests++;
        nbErrors += addReviewBadEntryTest(sn, null, "pass", "Titre", 20, "comment", "1.1",
                "reviewItemFilm() doesn't reject null login");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, " ", "pass", "Titre", 20, "comment", "1.2",
                "reviewItemFilm() doesn't reject logins that don't contain at least one character other than space");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B ", null, "Titre", 20, "comment", "1.3",
                "reviewItemFilm() doesn't reject null passwords");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "  qwd ", "Titre", 20, "Comment", "1.4",
                "reviewItemFilm() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", null, 20, "Comment", "1.5",
                "reviewItemFilm() doesn't reject null title)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", " ", 20, "Comment", "1.6",
                "reviewItemFilm() doesn't reject title at least one character other than space)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", "title", -1, "Comment", "1.7",
                "reviewItemFilm() doesn't reject <0 mark)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", "title", 6, "Comment", "1.7",
                "reviewItemFilm() doesn't reject >5 mark)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", "title", 6, null, "1.8",
                "reviewItemFilm() doesn't reject null comment)");

        int testErr[] = reviewItemFilmMoy(sn);
        nbTests += testErr[0];
        nbErrors += testErr[1];

        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("\nreviewItemFilm : " + tr);
            return tr;
        } catch (NotTestReportException e) { // This shouldn't happen
            System.out.println("Unexpected error in reviewItemFilm test code - Can't return valuable test results");
            return null;
        }
    }

    /**
     * Launches test()
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        test();
    }
}