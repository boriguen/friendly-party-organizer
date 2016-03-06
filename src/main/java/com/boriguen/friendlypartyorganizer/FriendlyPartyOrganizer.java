/**
 * 
 */
package com.boriguen.friendlypartyorganizer;

import java.util.List;
import java.util.Map;

import org.json.JSONTokener;

import com.boriguen.friendlypartyorganizer.person.Person;
import com.boriguen.friendlypartyorganizer.person.PotentialGuests;

/**
 * @author boris
 * 
 */
public class FriendlyPartyOrganizer {

	/** Contains the list of potential guests. */
	PotentialGuests potentialGuests = null;

	/**
	 * Instantiates a FriendlyPartyOrganizer object.
	 * 
	 * @param potentialGuests
	 *            - the list of potential guests to invite.
	 * @param connections
	 *            - the pairs of connections between potential guests.
	 */
	public FriendlyPartyOrganizer(List<Person> potentialGuests,
			Map<Person, Person> connections) {
		if (potentialGuests == null || potentialGuests.size() == 0) {
			throw new IllegalArgumentException(
					"The list of potential guests cannot be null nor empty");
		}

		if (connections == null || connections.size() == 0) {
			throw new IllegalArgumentException(
					"The map of connections cannot be null nor empty.");
		}

		this.potentialGuests = new PotentialGuests(potentialGuests, connections);
	}

	public List<Person> listGuests() {
		return this.potentialGuests.listGuests();
	}

}
