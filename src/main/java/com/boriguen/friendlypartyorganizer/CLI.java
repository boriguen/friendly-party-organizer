/**
 * 
 */
package com.boriguen.friendlypartyorganizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.boriguen.friendlypartyorganizer.exception.MissingArgumentException;
import com.boriguen.friendlypartyorganizer.person.Person;

/**
 * @author boriguen
 * 
 */
public class CLI {
	/** Contains the logger. */
	private static final Logger LOGGER = Logger.getLogger(CLI.class);

	/** Contains the key to be used to identify the potential guests argument. */
	public static final String POTENTIAL_GUESTS_KEY = "-g";

	/** Contains the key to be used to identify the connections argument. */
	public static final String CONNECTIONS_KEY = "-c";

	/**
	 * Provides the program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		List<String> arguments = Arrays.asList(args);
		try {
			FriendlyPartyOrganizer fpo = new FriendlyPartyOrganizer(
					extractPotentialGuests(arguments),
					extractConnections(arguments));
			System.out.println(new JSONArray(fpo.listFinalGuests()).toString());
		} catch (JSONException | MissingArgumentException e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Extracts the list of potential guests from the arguments.
	 * 
	 * @param arguments
	 *            - the command-line arguments.
	 * @return the list of potential guests.
	 * @throws JSONException - if the argument value is not a JSON array.
	 * @throws MissingArgumentException - if the potential guests argument is missing.
	 */
	protected static List<Person> extractPotentialGuests(List<String> arguments)
			throws JSONException, MissingArgumentException {
		return generatePeople(new JSONArray(extractArgumentValue(arguments,
				POTENTIAL_GUESTS_KEY)));
	}

	/**
	 * Extracts the map of connections between potential guests from the
	 * arguments.
	 * 
	 * @param arguments
	 *            - the command-line arguments.
	 * @return the pairs of connection between potential guests (may also
	 *         contain people not in the list of potential guests at this point).
	 * @throws JSONException - if the argument value is not a JSON object.
	 * @throws MissingArgumentException - if the connections argument is missing.
	 */
	protected static List<Pair<Person, Person>> extractConnections(
			List<String> arguments) throws JSONException,
			MissingArgumentException {
		return generateConnections(new JSONObject(extractArgumentValue(arguments,
				CONNECTIONS_KEY)));
	}

	/**
	 * Extracts the value of a given argument.
	 * @param arguments - the command-line arguments.
	 * @param argumentKey - the argument key for which to look for the value.
	 * @return the argument value.
	 * @throws MissingArgumentException - if the given argument key is missing.
	 */
	private static String extractArgumentValue(List<String> arguments, String argumentKey)
			throws MissingArgumentException {
		String string = null;
		int index = arguments.indexOf(argumentKey);
		if (index > -1) {
			string = arguments.get(index + 1);
		} else {
			throw new MissingArgumentException("Missing argument: "
					+ argumentKey);
		}
		return string;
	}

	/**
	 * Generates a list of people from a JSON array.
	 * @param array - the JSON array containing people related data.
	 * @return a list of people.
	 */
	private static List<Person> generatePeople(JSONArray array) {
		List<Person> people = new ArrayList<>(20);
		for (Iterator<Object> it = array.iterator(); it.hasNext();) {
			people.add(new Person((String) it.next()));
		}
		return people;
	}

	/**
	 * Generates a map of people from a JSON object.
	 * @param object - the JSON object containing connections related data. 
	 * @return a map of people.
	 */
	private static List<Pair<Person, Person>> generateConnections(JSONObject object) {
		List<Pair<Person, Person>> connections = new ArrayList<>(40);
		for (Iterator<String> it = object.keys(); it.hasNext();) {
			Person person1 = new Person(it.next());
			Person person2 = new Person(object.getString(person1.getName()));
			connections.add(new ImmutablePair<>(person1, person2));
		}
		return connections;
	}

}
