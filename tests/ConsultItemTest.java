package tests;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class ConsultItemTest {

    // This method tests whether an exception is thrown when calling the
    // consultItems() method with a bad title
    public static int ConsultItemBadEntry(ISocialNetwork sn, String title, String testId, String errorMessage) {
        try {
            // Call consultItems() method with the given title
            sn.consultItems(title);
            System.out.println("Test " + testId + " : " + errorMessage);

            // If no exception was thrown, return 1 to indicate that the test failed
            return 1;
        } catch (BadEntryException e) {
            // If a BadEntryException was thrown, print a message and return 0 to indicate
            // that the test passed
            System.out.println("Test " + testId + " : BadEntry was thrown");
            return 0;

        } catch (Exception e) {
            // If any other exception was thrown, print an error message and return 1 to
            // indicate that the test failed
            System.out.println("Test " + testId + " : unexpected exception. " + e);
            return 1;
        }
    }

    // This method runs a series of tests on the consultItems() method and returns a
    // TestReport object
    public static TestReport test() {

        System.out.println("Testing consultItem()");

        ISocialNetwork sn = new SocialNetwork();

        int nbTests = 0;
        int nbErrors = 0;

        System.out.println("BadEntryExcpeption()");

        // Test with null title
        nbTests++;
        nbErrors += ConsultItemBadEntry(sn, null, "1.1", "consultItem() doesn't reject null title");

        // Test with empty title
        nbTests++;
        nbErrors += ConsultItemBadEntry(sn, "  ", "1.2",
                "consultItem() doesn't reject title at least one character other than space");

        try {
            // Create a TestReport object with the number of tests and errors
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("ConsultItemTest : " + tr);
            return tr;
        } catch (NotTestReportException e) {
            // If an exception was thrown when creating the TestReport object, print an
            // error message and return null
            System.out.println("Unexpected error in ConsultItemTest test code - Can't return valuable test results");
            return null;
        }
    }

    // The main method runs the test() method
    public static void main(String[] args) {
        test();
    }
}
