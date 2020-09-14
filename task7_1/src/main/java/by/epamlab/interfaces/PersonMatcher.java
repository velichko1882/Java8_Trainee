package by.epamlab.interfaces;

import by.epamlab.beans.Gender;
import by.epamlab.beans.Person;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface PersonMatcher<T> {

    List<T> match (List<Person> personList);

    default List<Person> getPensioners (List<Person> personList){
        List<Person> pensionerList = new ArrayList<>();
        for (Person person : personList){
            if (person.getGender() == Gender.FEMALE) {
                if (person.getAge() > 57) {
                    pensionerList.add(person);
                }
            } else {
                if (person.getAge() > 62){
                    pensionerList.add(person);
                }
            }
        }
        return pensionerList;
    }

    static List<Person> getPopulation (List<Person>  personList, String town){
        List<Person> population = new ArrayList<>();
        for (Person person : personList) {
            if (person.getAddress().getTown().equals(town)){
                population.add(person);
            }
        }
        return population;
    }

}
