/**
 * 
 */
package com.boriguen.friendlypartyorganizer.person;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author boriguen
 * 
 */
public class PotentialGuests extends ArrayList<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9121121464424366449L;

	private int connectionsMin;
	
	/**
	 * Instantiates a PotentialGuests object.
	 * 
	 * @param potentialGuests
	 *            - the list of potential guests.
	 */
	public PotentialGuests(final List<Person> potentialGuests, final List<Pair<Person, Person>> connections, int connectionsMin) {
		super(potentialGuests);
		processConnections(connections);
		this.connectionsMin = connectionsMin;
	}

	/**
	 * Analyzes the connections of each potential guest and returns a list of
	 * final guests based on the minimum number of connections required.
	 * 
	 * @return the list of final guests.
	 */
	public List<Person> listFinalGuests() {
		final List<Person> people = new ArrayList<>();
		this.stream().filter(p -> p.getConnectionCount() >= this.connectionsMin).forEach(people::add);
		return people;
	}

	/**
	 * Processes connections by adding connections to people part of the list of
	 * potential guests.
	 * 
	 * @param connections
	 *            - the map of connections between people.
	 */
	protected void processConnections(final List<Pair<Person, Person>> connections) {
		connections.stream().forEach(pair -> {
			final Person person1 = pair.getLeft();
			final Person person2 = pair.getRight();
			// Check if connection people are in the potential guests list
			// before making associations.
			int person1Index = indexOf(person1);
			int person2Index = indexOf(person2);
			if (person1Index > -1 && person2Index > -1) {
				get(person1Index).addConnection(person2);
				get(person2Index).addConnection(person1);
			}
		});
	}

}
