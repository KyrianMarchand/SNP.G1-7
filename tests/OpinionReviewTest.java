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
    
        private static int addOpinionReviewTest(ISocialNetworkPremium sn, String login, String password, float mark, String category, String title, 
                String reviewer,String testId, String errorMessage) {
            
            try {
                sn.reviewOpinion(login, password, mark, category, title, reviewer);
                System.out.println("Err " + testId + " : " + errorMessage);
                                                    
                return 1;
            
            } catch (BadEntryException e) { 
                System.out.println("Test "+ testId+ " : BadEntryExeption was thrown " +e);
                return 0;
                
            } catch (NotMemberException e) { 
                System.out.println("Test "+ testId+ " : NotMemberException was thrown " +e);
                return 0;

            } catch (NotItemException e) {
                System.out.println("Test "+ testId+ " : NotItemException was thrown " +e);
                return 0;
                
            } catch (Exception e) {
            
                System.out.println("Test " + testId + " : unexpected exception. "+ e); 
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
                    System.out.println("Err 1.5: add opinion with bad login (category Book)");
                    testErr[1]++;
                }
            } catch (NotMemberException e) {
                System.out.println("OK 1.5: NotMemberException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }

            try {
                aver = sn.reviewOpinion("Marin", "kyrian", 3, "Film", "FightClub", "Tommy");
                if (aver == 3) {
                    System.out.println("Err 1.5: add opinion with bad login (category Film)");
                    testErr[1]++;
                }
            } catch (NotMemberException e) {
                System.out.println("OK 1.5: NotMemberException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }

            try {
                aver = sn.reviewOpinion("Kyrian", "blabla", 3, "Book", "La Boulangerie", "Tommy");
                if (aver == 3) {
                    System.out.println("Err 1.5: add opinion with bad password (category Book)");
                    testErr[1]++;
                }
            } catch (NotMemberException e) {
                System.out.println("OK 1.5: NotMemberException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }

            try {
                aver = sn.reviewOpinion("Kyrian", "blabla", 3, "Film", "FightClub", "Tommy");
                if (aver == 3) {
                    System.out.println("Err 1.5: add opinion with bad password (category Film)");
                    testErr[1]++;
                }
            } catch (NotMemberException e) {
                System.out.println("OK 1.5: NotMemberException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }

            try {
                aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Book", "La Boulangerie", "Marin");
                if (aver == 3) {
                    System.out.println("Err 1.5: add opinion with bad reviewer (category Book)");
                    testErr[1]++;
                }
            } catch (NotMemberException e) {
                System.out.println("OK 1.5: NotMemberException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }

            try {
                aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Film", "FightClub", "Marin");
                if (aver == 3) {
                    System.out.println("Err 1.5: add opinion with bad reviewer (category Film)");
                    testErr[1]++;
                }
            } catch (NotMemberException e) {
                System.out.println("OK 1.5: NotMemberException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }
    
            System.out.println("Testing NotItemException()"); 

            try {
                aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Book", "Le Livre", "Tommy");
                if (aver == 3) {
                    System.out.println("Err 1.5: add opinion with book that doesn't exist.");
                    testErr[1]++;
                }
            } catch (NotItemException e) {
                System.out.println("OK 1.5: NotItemException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }

            try {
                aver = sn.reviewOpinion("Kyrian", "kyrian", 3, "Film", "Le Film", "Tommy");
                if (aver == 3) {
                    System.out.println("Err 1.5: add opinion with film that doesn't exist.");
                    testErr[1]++;
                }
            } catch (NotItemException e) {
                System.out.println("OK 1.5: NotItemException has been successfully catched");
                testErr[0]++;
            } catch (Exception e) {
                System.out.println(e);
                testErr[1]++;
            }
        }

        public static TestReport test() {

            System.out.println("Testing opinionReview()");
    
            ISocialNetworkPremium sn = new SocialNetwork();
    
            int nbTests = 0;
            int nbErrors = 0;

        try {
            sn.addMember("Kyrian", "kyrian", "profile");
            sn.addMember("Tommy", "tommy", "profile");
            sn.addItemBook("Kyrian", "kyrian", "La Boulangerie", "Aventure", "moi", 28);
            sn.addItemBook("Tommy", "tommy", "La police", "Policier", "lui", 12);
            sn.addItemFilm("Kyrian", "kyrian", "FightClub", "Aventure", "moi", null, 28);
            sn.addItemFilm("Tommy", "tommy", "HitMan", "Policier", "lui", null, 12);
            sn.reviewItemBook("Kyrian", "kyrian", "La Boulangerie", 5, "Très bon livre");
            sn.reviewItemFilm("Tommy", "tommy", "HitMan", 3, "Le film etait passable");
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
        nbErrors += addOpinionReviewTest(sn, " ", "pass",  5, "film", "titre", "Kyrian", "1.2",
                "reviewItemFilm() doesn't reject logins that don't contain at least one character other than space");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B ", null,  5, "film", "titre", "Kyrian", "1.3",
                "reviewItemFilm() doesn't reject null passwords");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "  qwd ",  5, "film", "titre", "Kyrian",, "1.4",
                "reviewItemFilm() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass",  5, "film", null, "Kyrian", "1.5",
                "reviewItemFilm() doesn't reject null title)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass",  5, "film", " ", "Kyrian", "1.6",
                "reviewItemFilm() doesn't reject title at least one character other than space)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass",  -2, "film", "titre", "Kyrian", "1.7",
                "reviewItemFilm() doesn't reject <0 mark)");
        nbTests++;
        nbErrors += addOpinionReviewTest(sn, "B", "pass",  6, "film", "titre", "Kyrian", "1.7",
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
}}
