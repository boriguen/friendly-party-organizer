package com.boriguen.friendlypartyorganizer.person;

import java.util.HashSet;
import java.util.Set;

public class Person {
	/** The name of the person. */
	private String name;

	/** The people connected to this Person. */
	private Set<Person> connections;

	/**
	 * Instantiates a Person object.
	 * 
	 * @param name
	 *            - name representing the full name of the person, e.g. John B.
	 *            Smith.
	 */
	public Person(final String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this.
	 * 
	 * @return the name of this.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Adds a connection to this.
	 * 
	 * @param person
	 *            - person to connect with this.
	 */
	public void addConnection(final Person person) {
		if (this.connections == null) {
			this.connections = new HashSet<>();
		}
		this.connections.add(person);
	}

	/**
	 * Returns back the number of connections.
	 * 
	 * @return the number of connections.
	 */
	public int getConnectionCount() {
		return this.connections == null ? 0 : this.connections.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", connections=" + connections + "]";
	}
}
