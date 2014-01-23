package managementsystem;

import users.Borrower;
import utils.Period;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import equipment.Equipment;

/**
 * Loan class, contains all informations about a loan. A HashMap contains the
 * equipment borrowed as values and the models matching the following values as
 * keys. It also contains the period corresponding to the loan, the borrower and
 * booleans value in relation to the acceptance and the return of the loan.
 * 
 * initial code by : Marc Karassev; modified by : Marc Karassev
 * 
 * @author Marc Karassev
 * 
 */
public class Loan implements Serializable {
	// TODO tests

	// Fields

	private HashMap<String, ArrayList<Equipment>> stuff;
	private Period period;
	private Borrower borrower;
	private boolean accepted;
	private boolean givenBack;

	// Constructors
	/**
	 * Default constructor, constructs a loan with an empty HashMap, a default
	 * period and a default Borrower.
	 */
	public Loan() {
		this(new HashMap<String, Integer>(), new Period(), new Borrower());
	}

	/**
	 * Constructor used to make a reservation of a model. Constructs a loan with
	 * a HashMap where keys are identical to those of the HashMap given but
	 * where values are ArrayLists of Equipments of size equal to the matching
	 * integer value of the given HashMap.
	 * 
	 * @param stuffAsked
	 *            HashMap where keys are the asked models and values the
	 *            matching wanted number
	 * @param p
	 *            the period of the loan
	 * @param borrower
	 *            the borrower who asks for the loan
	 */
	public Loan(HashMap<String, Integer> stuffAsked, Period p, Borrower borrower) {
		Set<String> keys = stuffAsked.keySet();
		Iterator<String> it = keys.iterator();
		String key;

		stuff = new HashMap<String, ArrayList<Equipment>>(stuffAsked.size());
		while (it.hasNext()) {
			key = it.next();
			stuff.put(key, new ArrayList<Equipment>(stuffAsked.get(key)));
		}
		period = p;
		this.borrower = borrower;
		accepted = false;
		givenBack = false;
	}

	// Methods

	/**
	 * Returns a description of the current status of the loan.
	 * 
	 * @return a string description of its status
	 */
	public String status() {
		if (!accepted)
			return "not accepted";
		if (period.today())
			return "ongoing";
		if (period.daysFromNow() < 0)
			return "not begun";
		if (givenBack)
			return "returned";
		return "not returned yet";
	}

	/**
	 * Returns a string representation of the loan and its fields.
	 */
	@Override
	public String toString() {
		return "stuff: " + stuff + "\nperiod: " + period + "\nborrower: "
				+ borrower + "\naccepted: " + accepted + "\ngivenBack: "
				+ givenBack;
	}

	// Getters and setters

	/**
	 * Returns the HashMap stuff fields containing the lists of equipment booked
	 * sorted by models.
	 * 
	 * @return the HashMap stuff field
	 */
	public HashMap<String, ArrayList<Equipment>> getStuff() {
		return stuff;
	}

	/**
	 * Returns the period during which the stuff is supposed to be borrowed
	 * according to the loan.
	 * 
	 * @return the period field
	 */
	public Period getPeriod() {
		return period;
	}

	/**
	 * Returns the borrower of the stuff.
	 * 
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * Returns the loan's acceptance. If he was approved by a manager or not.
	 * 
	 * @return true if yes, false otherwise
	 */
	public boolean isAccepted() {
		return accepted;
	}

	/**
	 * Sets the acceptance of the loan.
	 * 
	 * @param accepted
	 *            the new value of the accepted field
	 */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	/**
	 * Returns the return of the loan. If the borrower gave the borrowed stuff
	 * back or not.
	 * 
	 * @return true if it is, false otherwise
	 */
	public boolean getGivenBack() {
		return givenBack;
	}

	/**
	 * Sets the return of the loan.
	 * 
	 * @param b
	 *            the new value for the givenBack field
	 */
	public void setGivenBack(Boolean b) {
		givenBack = b;
	}
}
