package com.boriguen.friendlypartyorganizer;

import java.util.HashSet;
import java.util.Set;

public class Person {
	/** The name of the person. */
	String name = null;
	
	/** The people connected to this Person. */
	Set<Person> connections = null;
	
	/**
	 * Instantiates a Person object.
	 * @param name - name representing the full name of the person, e.g. John B. Smith.
	 */
	public Person(String name) {
		this.name = name;
	}
	
	/**
	 * Adds a connection to this.
	 * @param person - person to connect with this.
	 */
	public void addConnection(Person person) {
		if (this.connections == null) {
			this.connections = new HashSet<Person>();
		}
		this.connections.add(person);
	}
	
	/**
	 * Returns back the number of connections.
	 * @return the number of connections.
	 */
	public int getConnectionCount() {
		return this.connections == null ? 0 : this.connections.size();
	}
}
