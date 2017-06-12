
/**
 * Copyright 2017 Dell Inc. or its subsidiaries. All Rights Reserved.
 */

import java.time.LocalDate;

/**
 * Person
 */
public class Person {
    public Person(String name, int age, Sex gender) {
        this.name = name;
        this.age = age;
		this.gender = gender;
	}

    public enum Sex {
		MALE,
		FEMALE
	}

	String name;
    private int age;
    LocalDate birthday;
	Sex gender;
	String emailAddress;

	public int getAge() {
		// ...
		return age;
	}

	public void printPerson() {
		// ...
		System.out.println(name + ", " + getAge() + ", " + gender);
	}

}
