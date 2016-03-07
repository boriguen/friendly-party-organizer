package com.boriguen.friendlypartyorganizer;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.boriguen.friendlypartyorganizer.exception.MissingArgumentException;
import com.boriguen.friendlypartyorganizer.person.Person;

public class CLITest {

	private static List<String> successArguments = null;

	@Before
	public void setUp() {
		successArguments = Arrays.asList(CLI.CONNECTIONS_KEY,
				"[\"Bob:Alan\", \"Bob:Dan\", \"Bob:Carl\", \"Bob:Kristine\", \"Bob:Andrea\"]", CLI.POTENTIAL_GUESTS_KEY,
				"[\"Bob\", \"Alan\", \"Dan\", \"Carl\", \"Kristine\", \"Andrea\"]");
	}

	@Test
	public void extractPotentialGuestsSuccessTest() throws JSONException, MissingArgumentException {
		List<Person> people = CLI.extractPotentialGuests(CLITest.successArguments);
		assertNotNull(people);
		assertEquals(new Person("Bob"), people.get(0));
		assertEquals(new Person("Alan"), people.get(1));
	}

	@Test(expected = MissingArgumentException.class)
	public void extractPotentialGuestsMissingArgumentExceptionTest() throws JSONException, MissingArgumentException {
		List<String> arguments = Arrays.asList("-e", "failure");
		CLI.extractPotentialGuests(arguments);
	}

	@Test(expected = JSONException.class)
	public void extractPotentialGuestsJSONExceptionTest() throws JSONException, MissingArgumentException {
		List<String> arguments = Arrays.asList(CLI.POTENTIAL_GUESTS_KEY, "[\"Bob, Alan]");
		CLI.extractPotentialGuests(arguments);
	}

	@Test
	public void extractConnectionsSuccessTest() throws JSONException, MissingArgumentException {
		List<Pair<Person, Person>> connections = CLI.extractConnections(CLITest.successArguments);
		assertNotNull(connections);
		assertEquals(new Person("Alan"), connections.get(0).getRight());
		assertEquals(new Person("Dan"), connections.get(1).getRight());
	}

	@Test(expected = MissingArgumentException.class)
	public void extractConnectionsMissingArgumentExceptionTest() throws JSONException, MissingArgumentException {
		List<String> arguments = Arrays.asList("-e", "failure");
		CLI.extractConnections(arguments);
	}

	@Test(expected = JSONException.class)
	public void extractConnectionsJSONExceptionTest() throws JSONException, MissingArgumentException {
		List<String> arguments = Arrays.asList(CLI.CONNECTIONS_KEY, "{Bob; Alan}");
		CLI.extractConnections(arguments);
	}

}
