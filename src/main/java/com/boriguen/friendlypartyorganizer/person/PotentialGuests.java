/**
 * 
 */
package com.boriguen.friendlypartyorganizer.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author boriguen
 * 
 */
public class PotentialGuests extends ArrayList<Person> {

	private static final int CONNEXIONS_MIN = 5; // Will be replaced by
													// parameter soon.

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
	public PotentialGuests(List<Person> potentialGuests, List<Pair<Person, Person>> connections) {
		super(potentialGuests);
		processConnections(connections);
	}

	/**
	 * Analyzes the connections of each potential guest and returns a list of
	 * final guests based on the minimum number of connections required.
	 * 
	 * @return the list of final guests.
	 */
	public List<Person> listFinalGuests() {
		List<Person> people = new ArrayList<Person>();
		this.stream().filter(p -> p.getConnectionCount() >= CONNEXIONS_MIN).forEach(people::add);
		return people;
	}

	/**
	 * Processes connections by adding connections to people part of the list of
	 * potential guests.
	 * 
	 * @param connections
	 *            - the map of connections between people.
	 */
	protected void processConnections(List<Pair<Person, Person>> connections) {
		for (Iterator<Pair<Person, Person>> it = connections.iterator(); it.hasNext();) {
			Pair<Person, Person> pair = it.next();
			Person person1 = pair.getLeft();
			Person person2 = pair.getRight();
			// Check if connection people are in the potential guests list
			// before making associations.
			int person1Index = indexOf(person1);
			int person2Index = indexOf(person2);
			if (person1Index > -1 && person2Index > -1) {
				get(person1Index).addConnection(person2);
				get(person2Index).addConnection(person1);
			}
		}
	}

}
