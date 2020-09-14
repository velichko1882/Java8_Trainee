package by.epamlab;

import by.epamlab.beans.Address;
import by.epamlab.beans.Gender;
import by.epamlab.beans.Person;
import by.epamlab.interfaces.PersonMatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {

    private static Logger logger = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {

        Consumer<Person> personConsumer = person -> logger.info(person.toString());

//2
        List<Person> personList = getPersonList(100);

//3-4
        Comparator<Person> simpleComparator = Comparator.comparing(person -> person.getGender());
        Comparator<Person> complexComparator = Comparator.comparing((Person person) -> person.getAddress().getTown())
                                                    .thenComparing(person -> person.getAge());
        personList.stream()
                .sorted(simpleComparator)
                .peek(personConsumer)
                .sorted(complexComparator)
                .forEach(personConsumer);
        logger.info(Constants.LINE_DELIMITER);
//5
        Predicate<Person> personPredicate = person -> person.getAddress().getTown().equals("Gomel") ? true : false;

        Supplier<Address> addressSupplier = () -> {
            Address address = new Address();
            address.setTown("Brest");
            return address;
        };

        Function<Person, String> function = person -> person.getAddress().getTown();

        UnaryOperator<Person> personUnaryOperator = person -> {
            Address address = addressSupplier.get();
            person.setAddress(address);
            return person;
        };
        Runnable runnable = () -> {
            List<Person> personListInThread = getPersonList(100);
            personList.addAll(personListInThread);
            long count = personList.stream()
                    .sorted(complexComparator)
                    .filter(personPredicate)
                    .peek(personConsumer)
                    .count();
            try {
                Thread.sleep(200);
                logger.info("Number of people living in Gomel = " + count);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(Constants.LINE_DELIMITER);

        personList.stream()
                .map(personUnaryOperator)
                .map(function)
                .forEach(s -> logger.info(s));

//6
        PersonMatcher<Person> personMatcher = pList -> {
            List<Person> matchPersonList = new ArrayList<>();
            for (Person person : pList) {
                if (person.getFirstName().equals("ADAM") || person.getFirstName().equals("EVA")){
                    matchPersonList.add(person);
                }
            }
            return matchPersonList;
        };

        List<Person> people = personMatcher.match(personList);
        people.forEach(person -> logger.info(person.toString()));

        PersonMatcher<Address> addressPersonMatcher = pList -> {
            List<Address> addressList = new ArrayList<>();
            for (Person person : pList) {
                if (person.getGender() == Gender.FEMALE && person.getLastName().startsWith("A")){
                    addressList.add(person.getAddress());
                }
            }
            return addressList;
        };

        List<Address> addresses = addressPersonMatcher.match(personList);
        addresses.forEach(address -> logger.info(address.toString()));

        PersonMatcher<String> stringPersonMatcher = pList -> {
            List<String> stringList = new ArrayList<>();
            for (Person person : pList) {
                if (person.getGender() == Gender.FEMALE &&
                        person.getAddress().getTown().equals("Gomel") &&
                        person.getAge() < 40){
                    stringList.add(person.getFirstName() + " " + person.getLastName());
                }
            }
            return stringList;
        };

        List<String> names = stringPersonMatcher.match(personList);
        names.forEach(s -> logger.info(s));

//7
        List<Person> pensioners = personMatcher.getPensioners(personList);
        pensioners.forEach(person -> logger.info(person.toString()));

        List<Person> populationTown = PersonMatcher.getPopulation(personList, "Minsk");
        populationTown.forEach(person -> logger.info(person.toString()));
    }


    private static List<Person> getPersonList(int numberOfPerson){
        return Stream.generate(Person::new)
                .limit(numberOfPerson)
                .collect(Collectors.toList());
    }
}
