/**
 * 
 */
package com.boriguen.friendlypartyorganizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;

import com.boriguen.friendlypartyorganizer.exception.MissingArgumentException;
import com.boriguen.friendlypartyorganizer.person.Person;

/**
 * @author boriguen
 * 
 */
public class CLI {
	/** Contains the logger. */
	private static final Logger LOGGER = Logger.getLogger(CLI.class);

	/** Contains the number of minimum connections needed.*/
	public static final int CONNEXIONS_MIN = 5;
	
	/**
	 * Contains the key to be used to identify the potential guests argument.
	 */
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
			FriendlyPartyOrganizer fpo = new FriendlyPartyOrganizer(extractPotentialGuests(arguments),
					extractConnections(arguments), CONNEXIONS_MIN);
			LOGGER.info(new JSONArray(fpo.listFinalGuests()).toString());
		} catch (JSONException | MissingArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Extracts the list of potential guests from the arguments.
	 * 
	 * @param arguments
	 *            - the command-line arguments.
	 * @return the list of potential guests.
	 * @throws JSONException
	 *             - if the argument value is not a JSON array.
	 * @throws MissingArgumentException
	 *             - if the potential guests argument is missing.
	 */
	protected static List<Person> extractPotentialGuests(final List<String> arguments)
			throws JSONException, MissingArgumentException {
		return generatePeople(new JSONArray(extractArgumentValue(arguments, POTENTIAL_GUESTS_KEY)));
	}

	/**
	 * Extracts the map of connections between potential guests from the
	 * arguments.
	 * 
	 * @param arguments
	 *            - the command-line arguments.
	 * @return the pairs of connection between potential guests (may also
	 *         contain people not in the list of potential guests at this
	 *         point).
	 * @throws JSONException
	 *             - if the argument value is not a JSON object.
	 * @throws MissingArgumentException
	 *             - if the connections argument is missing.
	 */
	protected static List<Pair<Person, Person>> extractConnections(final List<String> arguments)
			throws JSONException, MissingArgumentException {
		return generateConnections(new JSONArray(extractArgumentValue(arguments, CONNECTIONS_KEY)));
	}

	/**
	 * Extracts the value of a given argument.
	 * 
	 * @param arguments
	 *            - the command-line arguments.
	 * @param argumentKey
	 *            - the argument key for which to look for the value.
	 * @return the argument value.
	 * @throws MissingArgumentException
	 *             - if the given argument key is missing.
	 */
	private static String extractArgumentValue(final List<String> arguments, final String argumentKey)
			throws MissingArgumentException {
		String string;
		int index = arguments.indexOf(argumentKey);
		if (index > -1) {
			string = arguments.get(index + 1);
		} else {
			throw new MissingArgumentException("Missing argument: " + argumentKey);
		}
		return string;
	}

	/**
	 * Generates a list of people from a JSON array.
	 * 
	 * @param array
	 *            - the JSON array containing people related data.
	 * @return a list of people.
	 */
	private static List<Person> generatePeople(final JSONArray array) {
		List<Person> people = new ArrayList<>(20);
		array.forEach(object -> people.add(new Person((String) object)));
		return people;
	}

	/**
	 * Generates a map of people from a JSON object.
	 * 
	 * @param object
	 *            - the JSON object containing connections related data.
	 * @return a map of people.
	 */
	private static List<Pair<Person, Person>> generateConnections(final JSONArray array) {
		List<Pair<Person, Person>> connections = new ArrayList<>(40);
		array.forEach(object -> {
			String[] split =  ((String) object).split(":");
			connections.add(new ImmutablePair<>(new Person(split[0]), new Person(split[1])));
		});
		return connections;
	}

}
