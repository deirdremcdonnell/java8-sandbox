import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");


        doLambdaChecks();

    }

    private static void doLambdaChecks() {
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person("John", 51, Person.Sex.MALE));
        people.add(new Person("Mary", 3, Person.Sex.FEMALE));
        people.add(new Person("Joe", 61, Person.Sex.MALE));
        people.add(new Person("Molly", 2, Person.Sex.FEMALE));
        //  printPersonsWithinAgeRange(people, 40,55);

        final Consumer<Person> printPersonDetails = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                person.printPerson();
            }
        };
        CheckAgeLimit checkAgeLimit = new CheckAgeLimit(40, 55);
        doSomethingOnPersonsBasedOnSomeCriteria(people, checkAgeLimit, person->"<40 to 55>  " + person.name + " " + person.getAge(), detail -> System.out.println(detail));

        System.out.println("Females: ");
        doSomethingOnPersonsBasedOnSomeCriteria(people, person -> person.gender.equals(Person.Sex.FEMALE), person -> person.name, detail -> System.out.println(detail));

        System.out.println("Males: ");
        doSomethingOnPersonsBasedOnSomeCriteria(people, person -> person.gender.equals(Person.Sex.MALE), person -> person.name,detail -> System.out.println(detail));
        CheckPerson matchNoPerson = person -> false;

        System.out.println("NoMatch: ");
        doSomethingOnPersonsBasedOnSomeCriteria(people, matchNoPerson, null, null);

        System.out.println("Children: ");
        doSomethingOnListIfPredicateApplies(people, person -> person.getAge() < 18, person -> person.name, detail -> System.out.println(detail));
        System.out.println("Children (using lambdas and Aggregate operations on Stream) :");
        people.stream().filter(person -> person.getAge() < 18).map(person -> person.name).forEach( detail -> System.out.println(detail));
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


    private static void doSomethingOnPersonsBasedOnSomeCriteria(ArrayList<Person> people, Predicate<Person> checkPerson, Function<Person, String> mapper, Consumer<String> consumer) {
        for (Person person : people) {
            if (checkPerson.test(person) )
            {
                final String result = mapper.apply(person);
                consumer.accept(result);
            }
        }
    }

    private static <X, Y> void doSomethingOnListIfPredicateApplies(ArrayList<X> people, Predicate<X> validation, Function<X, Y> mapper, Consumer<Y> consumer) {
        for (X person : people) {
            if (validation.test(person) )
            {
                final Y result = mapper.apply(person);
                consumer.accept(result);
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
