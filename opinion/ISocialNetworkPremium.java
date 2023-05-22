package opinion;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

public interface ISocialNetworkPremium extends ISocialNetwork{
	
	
	/**
     * @param login
     *            the login of the member
     * @param password
     *            the password of the member
     * @param mark
     *            the mark you want to give to the review
     * @param category
     *            the category of the item to review (film or book)
     * @param title
     *            the title the item where the review is left
     * @param reviewer
     *            the reviewer of the review you want to mark
     * @return the mean of the review
     *  
     * @throws BadEntryException
	 *                            <ul>
	 *                            <li>if login is not instantiated or contains less
	 *                            than one
	 *                            non-space character</li>
	 *                            <li>if password is not instantiated or contains
	 *                            less than
	 *                            four characters (not taking into account leading
	 *                            or trailing
	 *                            blanks)</li>
	 *                            <li>if title is not instantiated or contains less
	 *                            than one
	 *                            non-space character</li>
	 *                            <li>if mark is not greater or equals to 0.0 and
	 *                            lesser or
	 *                            equals to 5.0.</li>
	 *                            <li>if comment is not instantiated</li>
	 *                            </ul>
	 *                            <br>
	 * @throws NotMemberException
     *                            <ul>
     * 	                          <li>if login does not match with the login of a
	 *                            registered member or if password does not
	 *                            correspond to </li>
     *                            <li>if reviewer does not exist</li>
     *                            </ul>
	 * @throws NotItemException
	 *                            if title is not registered as a book's title or a film's title in
	 *                            the <i>SocialNetwork</i>
    */	
	public float reviewOpinion(String login, String password, float mark, String category, String title, String reviewer) throws BadEntryException,NotMemberException, NotItemException ;
	
}
