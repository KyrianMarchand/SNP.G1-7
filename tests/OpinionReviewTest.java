package tests;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.ISocialNetworkPremium;
import opinion.SocialNetwork;

public class OpinionReviewTest {

    private static int addOpinionReviewTest(ISocialNetworkPremium sn, String login, String password, float mark,
            String category, String title,
            String reviewer, String testId, String errorMessage) {

        try {
            sn.reviewOpinion(login, password, mark, category, title, reviewer);
            System.out.println("Err " + testId + " : " + errorMessage);

            return 1;

        } catch (BadEntryException e) {
            System.out.println("Test " + testId + " : OK BadEntryExeption was thrown " + e);
            return 0;

        } catch (NotMemberException e) {
            System.out.println("Test " + testId + " : OK NotMemberException was thrown " + e);
            return 0;

        } catch (NotItemException e) {
            System.out.println("Test " + testId + " : OK NotItemException was thrown " + e);
            return 0;

        } catch (Exception e) {

            System.out.println("Test " + testId + " : unexpected exception. " + e);
            return 1;
        }

    }

    private static int[] MeanOpinionReview(ISocialNetworkPremium sn) {

        float aver = 0;
        int testErr[] = { 0, 0 };

        System.out.println("Testing NotMemberException()");

        try {
            aver = sn.reviewOpinion("Marin", "kyrian", 3, "Book", "La Boulangerie", "Tommy");
            if (aver == 3) {
                System.out.println("Err 2.1: add opinion with bad login (category Book)");
                testErr[1]++;
            }
        } catch (NotMemberException e) {
            System.out.println("OK 2.1: NotMemberException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Marin", "kyrian", 3, "Film", "FightClub", "Tommy");
            if (aver == 3) {
                System.out.println("Err 2.2: add opinion with bad login (category Film)");
                testErr[1]++;
            }
        } catch (NotMemberException e) {
            System.out.println("OK 2.2: NotMemberException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Kyrian", "blabla", 3, "Book", "La Boulangerie", "Tommy");
            if (aver == 3) {
                System.out.println("Err 2.3: add opinion with bad password (category Book)");
                testErr[1]++;
            }
        } catch (NotMemberException e) {
            System.out.println("OK 2.3: NotMemberException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Kyrian", "blabla", 3, "Film", "FightClub", "Tommy");
            if (aver == 3) {
                System.out.println("Err 2.4: add opinion with bad password (category Film)");
                testErr[1]++;
            }
        } catch (NotMemberException e) {
            System.out.println("OK 2.4: NotMemberException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Book", "La Boulangerie", "Marin");
            if (aver == 3) {
                System.out.println("Err 2.5: add opinion with bad reviewer (category Book)");
                testErr[1]++;
            }
        } catch (NotMemberException e) {
            System.out.println("OK 2.5: NotMemberException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Film", "FightClub", "Marin");
            if (aver == 3) {
                System.out.println("Err 2.6: add opinion with bad reviewer (category Film)");
                testErr[1]++;
            }
        } catch (NotMemberException e) {
            System.out.println("OK 2.6: NotMemberException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        System.out.println("Testing NotItemException()");

        try {
            aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Book", "Le Livre", "Tommy");
            if (aver == 3) {
                System.out.println("Err 3.1: add opinion with book that doesn't exist.");
                testErr[1]++;
            }
        } catch (NotItemException e) {
            System.out.println("OK 3.1: NotItemException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Film", "Le Film", "Tommy");
            if (aver == 3) {
                System.out.println("Err 3.2: add opinion with film that doesn't exist.");
                testErr[1]++;
            }
        } catch (NotItemException e) {
            System.out.println("OK 3.2: NotItemException has been successfully catched");
            testErr[0]++;
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        System.out.println("Testing Average()");

        try {
            aver = sn.reviewOpinion("Tommy", "tommy", 5, "Book", "La boulangerie", "Kyrian");
            if (aver == 5) {
                System.out.println("OK 4.1: The mean is correct.");
                testErr[0]++;
            } else {
                System.out.println(aver);
                System.out.println("Err 4.1: err mean is uncorrect.");
                testErr[1]++;
            }
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Tommy", "tommy", 1, "Book", "La boulangerie", "Kyrian");
            if (aver == 1) {
                System.out.println("OK 4.2: the login change his mark and the average mark has been changed.");
                testErr[0]++;
            } else {
                System.out.println(aver);
                System.out.println("Err 4.2: err mean is uncorrect.");
                testErr[1]++;
            }
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Paul", "paul", 5, "Book", "La boulangerie", "Kyrian");
            if (aver == 3) {
                System.out.println("OK 4.3: The mean is correct.");
                testErr[0]++;
            } else {
                System.out.println("Err 4.3: err mean is uncorrect.");
                testErr[1]++;
            }
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            aver = sn.reviewOpinion("Paul", "paul", 3, "Book", "la boulangerie", "Kyrian");
            if (aver == 2) {
                System.out.println("OK 4.4: other login change his mark and the average mark has been changed.");
                testErr[0]++;
            } else {
                System.out.println("Err 4.4: err mean is uncorrect.");
                testErr[1]++;
            }
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        System.out.println("Testing nominal()");

        try {
            sn.addMember("Enzo", "enzo", "profile");
            if (sn.nbMembers() == 4) {
                System.out.println("OK 5.1: Member have been added.");
                testErr[0]++;
            } else {
                System.out.println("Err 5.1: err addMember().");
                testErr[1]++;
            }
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            sn.addItemBook("Enzo", "enzo", "Livre", "null", "null", 2);
            if (sn.nbBooks() == 3) {
                System.out.println("OK 5.2: Book have been added.");
                testErr[0]++;
            } else {
                System.out.println("Err 5.2: err addItemBook().");
                testErr[1]++;
            }
        } catch (Exception e) {
            System.out.println(e);
            testErr[1]++;
        }

        try {
            sn.reviewItemBook("Kyrian", "kyrian", "Livre", 5, "null");
            System.out.println("OK 5.3: Book have been reviewed.");
        } catch (Exception e) {
            System.out.println("Err 5.2: err reviewItemBook()." + e);
            testErr[1]++;
        }

        try {
            sn.reviewOpinion("Paul", "paul", 3, "Book", "Livre", "Kyrian");
            System.out.println("OK 5.4: Opinion have been added.");
        } catch (Exception e) {
            System.out.println("Err 5.4: err reviewOpinion()." + e);
            testErr[1]++;
        }

        return testErr;

    }

    public static TestReport test() {

        System.out.println("Testing opinionReview()");

        ISocialNetworkPremium sn = new SocialNetwork();

        int nbTests = 0;
        int nbErrors = 0;

        try {
            sn.addMember("Kyrian", "kyrian", "profile");
            sn.addMember("Tommy", "tommy", "profile");
            sn.addMember("Paul", "paul", "profile");
            sn.addItemBook("Kyrian", "kyrian", "La Boulangerie", "Aventure", "moi", 28);
            sn.addItemBook("Tommy", "tommy", "La police", "Policier", "lui", 12);
            sn.addItemFilm("Kyrian", "kyrian", "FightClub", "Aventure", "moi", "null", 28);
            sn.addItemFilm("Tommy", "tommy", "HitMan", "Policier", "lui", "null", 12);
            sn.reviewItemBook("Kyrian", "kyrian", "La boulangerie", 5, "Excellent livre !");
            sn.reviewItemBook("Tommy", "tommy", "La boulangerie", 3, "Livre moyen.");
            sn.reviewItemFilm("Tommy", "tommy", "HitMan", 5, "Le film etait passable");

        } catch (BadEntryException e1) {
            e1.printStackTrace();
        } catch (NotMemberException e1) {
            e1.printStackTrace();
        } catch (ItemBookAlreadyExistsException e1) {
            e1.printStackTrace();
        } catch (ItemFilmAlreadyExistsException e1) {
            e1.printStackTrace();
        } catch (NotItemException e1) {
            e1.printStackTrace();
        } catch (MemberAlreadyExistsException e) {
            e.printStackTrace();
        }

        System.out.println("Testing BadEntryException()");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, null, "pass", 5, "film", "titre", "Kyrian", "1.1",
                "opinionReview() doesn't reject null login");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, " ", "pass", 5, "film", "titre", "Kyrian", "1.2",
                "reviewItemFilm() doesn't reject logins that don't contain at least one character other than space");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B ", null, 5, "film", "titre", "Kyrian", "1.3",
                "reviewItemFilm() doesn't reject null passwords");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "  qwd ", 5, "film", "titre", "Kyrian", "1.4",
                "reviewItemFilm() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass", 5, "film", null, "Kyrian", "1.5",
                "reviewItemFilm() doesn't reject null title)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass", 5, "film", " ", "Kyrian", "1.6",
                "reviewItemFilm() doesn't reject title at least one character other than space)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass", -2, "film", "titre", "Kyrian", "1.7",
                "reviewItemFilm() doesn't reject <0 mark)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass", 6, "film", "titre", "Kyrian", "1.8",
                "reviewItemFilm() doesn't reject >5 mark)");

        int testErr[] = MeanOpinionReview(sn);
        nbTests += testErr[0];
        nbErrors += testErr[1];

        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("\n opinionReview : " + tr);
            return tr;
        } catch (NotTestReportException e) { // This shouldn't happen
            System.out.println("Unexpected error in opinionReview test code - Can't return valuable test results");
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