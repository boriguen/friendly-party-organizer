package com.boriguen.friendlypartyorganizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.boriguen.friendlypartyorganizer.person.Person;

public class FriendlyPartyOrganizerTest {

	List<Person> potentialGuests = null;
	Map<Person, Person> connections = null;

	@Before
	public void setUp() throws Exception {
		potentialGuests = Arrays.asList(new Person("Bob"), new Person("Alan"),
				new Person("Dan"), new Person("Denis"), new Person("Kim"),
				new Person("Kristine"), new Person("Andrea"),
				new Person("Carl"), new Person("Will"));

		connections = new HashMap<Person, Person>();
		connections.put(potentialGuests.get(0), potentialGuests.get(1));
		connections.put(potentialGuests.get(0), potentialGuests.get(2));
		connections.put(potentialGuests.get(0), potentialGuests.get(3));
		connections.put(potentialGuests.get(0), potentialGuests.get(4));
		connections.put(potentialGuests.get(0), potentialGuests.get(5));
		connections.put(potentialGuests.get(1), potentialGuests.get(2));
		connections.put(potentialGuests.get(1), potentialGuests.get(3));
		connections.put(potentialGuests.get(1), potentialGuests.get(4));
		connections.put(potentialGuests.get(1), potentialGuests.get(5));
	}

	@Test
	public void listGuestsSuccessTest() {
		List<Person> finalGuests = new FriendlyPartyOrganizer(
				this.potentialGuests, this.connections).listGuests();
		Assert.assertTrue(finalGuests.contains(new Person("Bob")));
		Assert.assertTrue(finalGuests.contains(new Person("Alan")));
		Assert.assertFalse(finalGuests.contains(new Person("Dan")));
		Assert.assertFalse(finalGuests.contains(new Person("Andrea")));
	}

}
