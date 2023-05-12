package tests;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class ConsultItemTest {

    public static int ConsultItemBadEntry(ISocialNetwork sn, String title, String testId, String errorMessage) {
        try {
            sn.consultItems(title);
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

    public static TestReport test() {

        System.out.println("Testing consultItem()");

        ISocialNetwork sn = new SocialNetwork();

        int nbBooks = sn.nbBooks();
        // int nbFilms = sn.nbFilms();

        int nbTests = 0;
        int nbErrors = 0;

        try {

            sn.addMember("Kyrian", "kyrian", "nononon");
            sn.addMember("Tommy", "tommy", "ouiouioui");

            // sn.addItemFilm("Kyrian", "kyrian", "FightClub", "Action", "aaa", "bbb", 5);
            // sn.addItemFilm("Tommy", "tommy", "BadBoy", "Aventure", "aaa", "bbb", 5);

            sn.addItemBook("Kyrian", "Kyrian", "La Boulangerie", "Aventure", "moi", 12);
            sn.addItemBook("Tommy", "tommy", "La police", "Policier", "lui", 99);

        } catch (Exception e) {
        }

        // Test avec mauvaise syntax
        System.out.println("BadEntryExcpeption()");
        nbTests++;
        nbErrors += ConsultItemBadEntry(sn, null, "1.1", "consultItem() doesn't reject null title");

        nbTests++;
        nbErrors += ConsultItemBadEntry(sn, "  ", "1.2",
                "consultItem() doesn't reject title at least one character other than space"); // title vide

        System.out.println("Check Items");

        try {
            nbTests++;
            int nbItem = 1;
            if (sn.consultItems("La Boulangerie").size() == nbItem) {
                System.out.println("1.3 OK -> good number");
            }

            else {
                System.out.println("Err 1.3");
                nbErrors++;
            }

        } catch (Exception e) {
        }

        try {
            nbTests++;
            int nbItem = 1;
            if (sn.consultItems("La Police").size() == nbItem) {
                System.out.println("1.4 OK -> good number");
            }

            else {
                System.out.println("Err 1.4");
                nbErrors++;
            }

        } catch (Exception e) {
        }

        try {
            nbTests++;
            int nbItem = 1;
            if (sn.consultItems("La BouLangerie").size() == nbItem) {
                System.out.println("1.5 OK -> with case sensitivity");
            }

            else {
                System.out.println("Err 1.5");
                nbErrors++;
            }

        } catch (Exception e) {
        }
        try {
            nbTests++;
            int nbItem = 1;
            if (sn.consultItems("         La BouLangerie            ").size() == nbItem) {
                System.out.println("1.6 OK -> with space");
            }

            else {
                System.out.println("Err 1.6");
                nbErrors++;
            }

        } catch (Exception e) {
        }

        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("ConsultItemTest : " + tr);
            return tr;
        } catch (NotTestReportException e) {
            System.out.println("Unexpected error in ConsultItemTest test code - Can't return valuable test results");
            return null;
        }
    }

    public static void main(String[] args) {
        test();
    }

}
