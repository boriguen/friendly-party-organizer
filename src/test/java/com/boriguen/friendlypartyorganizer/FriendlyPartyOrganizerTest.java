package com.boriguen.friendlypartyorganizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import com.boriguen.friendlypartyorganizer.person.Person;

public class FriendlyPartyOrganizerTest {

	private List<Person> potentialGuests;
	private List<Pair<Person, Person>> connections;

	@Before
	public void setUp() throws Exception {
		potentialGuests = Arrays.asList(new Person("Bob"), new Person("Alan"), new Person("Dan"), new Person("Denis"),
				new Person("Kim"), new Person("Kristine"), new Person("Andrea"), new Person("Carl"),
				new Person("Will"));

		connections = new ArrayList<>();
		connections.add(new ImmutablePair<>(potentialGuests.get(0), potentialGuests.get(1)));
		connections.add(new ImmutablePair<>(potentialGuests.get(0), potentialGuests.get(2)));
		connections.add(new ImmutablePair<>(potentialGuests.get(0), potentialGuests.get(3)));
		connections.add(new ImmutablePair<>(potentialGuests.get(0), potentialGuests.get(4)));
		connections.add(new ImmutablePair<>(potentialGuests.get(0), potentialGuests.get(5)));
		connections.add(new ImmutablePair<>(potentialGuests.get(1), potentialGuests.get(2)));
		connections.add(new ImmutablePair<>(potentialGuests.get(1), potentialGuests.get(3)));
		connections.add(new ImmutablePair<>(potentialGuests.get(1), potentialGuests.get(4)));
		connections.add(new ImmutablePair<>(potentialGuests.get(1), potentialGuests.get(5)));
	}

	@Test
	public void listGuestsSuccessTest() {
		List<Person> finalGuests = new FriendlyPartyOrganizer(this.potentialGuests, this.connections,
				CLI.CONNEXIONS_MIN).listFinalGuests();
		assertNotNull(finalGuests);
		assertTrue(finalGuests.contains(new Person("Bob")));
		assertTrue(finalGuests.contains(new Person("Alan")));
		assertFalse(finalGuests.contains(new Person("Dan")));
		assertFalse(finalGuests.contains(new Person("Andrea")));
	}

}
