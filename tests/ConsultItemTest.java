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

        int nbTests = 0;
        int nbErrors = 0;

        System.out.println("BadEntryExcpeption()");
        nbTests++;
        nbErrors += ConsultItemBadEntry(sn, null, "1.1", "consultItem() doesn't reject null title");

        nbTests++;
        nbErrors += ConsultItemBadEntry(sn, "  ", "1.2",
                "consultItem() doesn't reject title at least one character other than space"); // title vide

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
