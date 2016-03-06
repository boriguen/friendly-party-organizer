/**
 * 
 */
package com.boriguen.friendlypartyorganizer.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author boriguen
 * 
 */
public class PotentialGuests extends ArrayList<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9121121464424366449L;

	/**
	 * Instantiates a PotentialGuests object.
	 * 
	 * @param potentialGuests
	 *            - the list of potential guests.
	 */
	public PotentialGuests(List<Person> potentialGuests,
			Map<Person, Person> connections) {
		super(potentialGuests);
		processConnections(connections);
	}

	public List<Person> listGuests() {
		return this;
	}
	
	/**
	 * Processes connections by adding connections to people part of the list
	 * of potential guests.
	 * @param connections - the map of connections between people.
	 */
	protected void processConnections(Map<Person, Person> connections) {
		for (Iterator<Person> it = connections.keySet().iterator(); it
				.hasNext();) {
			Person person1 = it.next();
			Person person2 = connections.get(person1);
			// Check if connection people are in the potential guests list
			// before making associations.
			if (contains(person1) && contains(person2)) {
				person1.addConnection(person2);
				person2.addConnection(person1);
			}
		}
	}

}
