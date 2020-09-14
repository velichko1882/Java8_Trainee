package by.epamlab.beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static by.epamlab.Constants.*;

public class Person {

    private static Random random = new Random();

    private long passportID;
    private Gender gender;
    private String firstName;
    private String lastName;
    private int age;
    private Address address;

    public Person() {
        this.passportID = random.nextInt(MAX_PASSPORT_ID);
        this.gender = Gender.values()[random.nextInt(Gender.values().length)];
        this.firstName = getFirstNameFromFile(gender);
        this.lastName = getRandomLineFromFile(PATH + FILE_LAST_NAMES);
        this.age = random.nextInt(RANGE_OF_YEARS) + ADULTHOOD;
        this.address = new Address();
    }

    public Person(long passportID, Gender gender, String firstName, String lastName, int age, Address address) {
        this.passportID = passportID;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public long getPassportID() {
        return passportID;
    }

    public void setPassportID(long passportID) {
        this.passportID = passportID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("passportID=").append(passportID);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", address=").append(address);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (passportID != person.passportID) return false;
        if (age != person.age) return false;
        if (gender != person.gender) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        return address != null ? address.equals(person.address) : person.address == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (passportID ^ (passportID >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    private String getFirstNameFromFile(Gender gender){
        String fileName;
        if (gender == Gender.MALE){
            fileName = PATH + FILE_MALE_NAMES;
        } else {
            fileName = PATH + FILE_FEMALE_NAMES;
        }
        return getRandomLineFromFile(fileName);
    }

    private String getRandomLineFromFile(String fileName){
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            strings = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings.get(random.nextInt(strings.size()));
    }
}

