import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");



        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person("John", 51, Person.Sex.MALE));
        people.add(new Person("Mary", 3, Person.Sex.FEMALE));
        people.add(new Person("Joe", 61, Person.Sex.MALE));
      //  printPersonsWithinAgeRange(people, 40,55);

        final Consumer<Person> printPersonDetails = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                person.printPerson();
            }
        };
        CheckAgeLimit checkAgeLimit = new CheckAgeLimit(40, 55);
        doSomethingOnPersonsBasedOnSomeCriteria(people, checkAgeLimit, printPersonDetails);

        doSomethingOnPersonsBasedOnSomeCriteria(people, person -> person.gender.equals(Person.Sex.FEMALE), person -> person.printPerson());

        doSomethingOnPersonsBasedOnSomeCriteria(people, person -> person.gender.equals(Person.Sex.FEMALE), person -> person.printPerson());
        CheckPerson matchNoPerson = person -> false;

        doSomethingOnPersonsBasedOnSomeCriteria(people, matchNoPerson,printPersonDetails);
        Function<Person,Person> fatherOf = new Function<Person, Person>() {
            @Override
            public Person apply(Person person) {
                return new Person("father", 1, Person.Sex.MALE);
            }
        };

    }


    private static void printPersonsWithinAgeRange(ArrayList<Person> people, int low, int high) {
        for (Person person : people) {

            int age = person.getAge();
            if (low <= age && age <=high )
            {
                person.printPerson();
            }
            else
            {
                System.out.println("Ignoring " + age);
            }
        }
    }


    private static void doSomethingOnPersonsBasedOnSomeCriteria(ArrayList<Person> people, Predicate<Person> checkPerson, Consumer<Person> consumer) {
        for (Person person : people) {
            if (checkPerson.test(person) )
            {
                consumer.accept(person);
            }
        }
    }

    private static class CheckAgeLimit implements CheckPerson {

        final int low;
        final int high;

        public CheckAgeLimit(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        public boolean test(Person person) {
            return low <= person.getAge() && person.getAge() <= high;
        }
    }

    private interface CheckPerson extends Predicate<Person>{
    }
}
