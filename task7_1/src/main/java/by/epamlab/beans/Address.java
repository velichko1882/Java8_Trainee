package by.epamlab.beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.epamlab.Constants.*;


public class Address {

    private static Random random = new Random();
    private static List<String> stringAddresses =
            getStringAddresses(PATH + FILE_ADDRESS);

    private String town;
    private String street;
    private int houseNumber;

    public Address() {
        int index = random.nextInt(stringAddresses.size());
        String csvAddress = stringAddresses.get(index);
        String[] address = csvAddress.split(DELIMITER);
        this.town = address[INDEX_TOWN];
        this.street = address[INDEX_STREET];
        this.houseNumber = Integer.parseInt(address[INDEX_HOUSE_NUMBER]);
    }

    public Address(String town, String street, int houseNumber) {
        this.town = town;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("town='").append(town).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNumber=").append(houseNumber);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (houseNumber != address.houseNumber) return false;
        if (town != null ? !town.equals(address.town) : address.town != null) return false;
        return street != null ? street.equals(address.street) : address.street == null;
    }

    @Override
    public int hashCode() {
        int result = town != null ? town.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + houseNumber;
        return result;
    }

    private static List<String> getStringAddresses(String fileName) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            Stream<String> stringStream = reader.lines();
            strings = stringStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

}