/**
 * 
 */
package com.boriguen.friendlypartyorganizer;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.boriguen.friendlypartyorganizer.person.Person;
import com.boriguen.friendlypartyorganizer.person.PotentialGuests;

/**
 * @author boris
 * 
 */
public class FriendlyPartyOrganizer {

	/** Contains the list of potential guests. */
	private PotentialGuests potentialGuests;

	/**
	 * Instantiates a FriendlyPartyOrganizer object.
	 * 
	 * @param potentialGuests
	 *            - the list of potential guests to invite.
	 * @param connections
	 *            - the pairs of connections between potential guests.
	 */
	public FriendlyPartyOrganizer(final List<Person> potentialGuests, final List<Pair<Person, Person>> connections) {
		if (potentialGuests == null || potentialGuests.size() == 0) {
			throw new IllegalArgumentException("The list of potential guests cannot be null nor empty");
		}

		if (connections == null || connections.size() == 0) {
			throw new IllegalArgumentException("The map of connections cannot be null nor empty.");
		}

		this.potentialGuests = new PotentialGuests(potentialGuests, connections);
	}

	public List<Person> listFinalGuests() {
		return this.potentialGuests.listFinalGuests();
	}

}
