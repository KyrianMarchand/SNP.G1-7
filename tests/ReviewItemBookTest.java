package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

public class ReviewItemBookTest {

    private static int addReviewBadEntryTest(ISocialNetwork sn, String login, String password, String title,
            float mark, String comment, String testId, String errorMessage) {

        try {
            sn.reviewItemBook(login, password, title, mark, comment);
            System.out.println("Test " + testId + " : " + errorMessage);

            return 1;
        } catch (BadEntryException e) {
            System.out.println("Test " + testId + " : BadEntry was thrown");
            return 0;

        } catch (Exception e) {

            System.out.println("Test " + testId + " : unexpected exception. " + e);
            return 1;
        }
    }

    private static int[] reviewItemBookMoy(ISocialNetwork sn) {

        float aver = 0;
        int testErr[] = { 0, 0 };

        System.out.println("add ReviewItemBook()");
        try {
            aver = sn.reviewItemBook("Kyrian", "kyrian", "La Boulangerie", 5, "Très bon livre");
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
            aver = sn.reviewItemBook("Kyrian", "kyrian", "La Boulangerie", 2, "Moyen...");
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
            aver = sn.reviewItemBook("Tommy", "tommy", "La Boulangerie", 4, "Bien !");
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
            aver = sn.reviewItemBook("Tommy", "tommy", "La Boulangerie", 0, "Nul...");
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
            aver = sn.reviewItemBook("Marin", "kyrian", "La Boulangerie", 4, "Bien");
            if (aver == 2) {
                System.out.println("Err 1.5: add Book with bad login");
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
            aver = sn.reviewItemBook("Kyrian", "kyrian", "IMT Atlantique", 4, "Bien");
            System.out.println("Err 1.6: add bad Book");

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

    public static TestReport test() {

        ISocialNetwork sn = new SocialNetwork();
        int nbTests = 0, nbErrors = 0;

        System.out.println("Testing reviewItemBook()");

        try {
            sn.addMember("Kyrian", "kyrian", "nononon");
            sn.addMember("Tommy", "tommy", "ouiouioui");
            sn.addItemBook("Kyrian", "kyrian", "La Boulangerie", "Aventure", "moi", 28);
            sn.addItemBook("Tommy", "tommy", "La police", "Policier", "lui", 12);
        } catch (BadEntryException e1) {
            e1.printStackTrace();
        } catch (NotMemberException e1) {
            e1.printStackTrace();
        } catch (ItemBookAlreadyExistsException e1) {
            e1.printStackTrace();
        } catch (MemberAlreadyExistsException e) {
            e.printStackTrace();
        }

        System.out.println("BadEntryException()");
        nbTests++;
        nbErrors += addReviewBadEntryTest(sn, null, "pass", "Titre", 20, "comment", "1.1",
                "reviewItemBook() doesn't reject null login");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, " ", "pass", "Titre", 20, "comment", "1.2",
                "reviewItemBook() doesn't reject logins that don't contain at least one character other than space");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B ", null, "Titre", 20, "comment", "1.3",
                "reviewItemBook() doesn't reject null passwords");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "  qwd ", "Titre", 20, "Comment", "1.4",
                "reviewItemBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", null, 20, "Comment", "1.5",
                "reviewItemBook() doesn't reject null title)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", " ", 20, "Comment", "1.6",
                "reviewItemBook() doesn't reject title at least one character other than space)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", "title", -1, "Comment", "1.7",
                "reviewItemBook() doesn't reject <0 mark)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", "title", 6, "Comment", "1.7",
                "reviewItemBook() doesn't reject >5 mark)");
        nbTests++;

        nbErrors += addReviewBadEntryTest(sn, "B", "pass", "title", 6, null, "1.8",
                "reviewItemBook() doesn't reject null comment)");

        int testErr[] = reviewItemBookMoy(sn);
        nbTests += testErr[0];
        nbErrors += testErr[1];

        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("\nreviewItemBook : " + tr);
            return tr;
        } catch (NotTestReportException e) { // This shouldn't happen
            System.out.println("Unexpected error in reviewItemBook test code - Can't return valuable test results");
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