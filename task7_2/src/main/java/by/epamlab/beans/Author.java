package by.epamlab.beans;

import by.epamlab.utils.Constants;
import by.epamlab.utils.ReaderFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static by.epamlab.utils.Constants.*;

public class Author {

    private static Random random = new Random();

    private String name;
    private int age;
    private List<Book> books;

    public Author() {
        this.name = getAuthorName(random.nextInt(ReaderFile.linesFromFile.size()));
        this.age = random.nextInt(PENSIONABLE_AGE) + ADULTHOOD;
        this.books = getBookList(name);
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, int age, List<Book> books) {
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (age != author.age) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        return books != null ? books.equals(author.books) : author.books == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("name='").append(name).append('\'');
        if (age != 0) {
            sb.append(", age=").append(age);
        }
        if (books != null) {
            sb.append(", books=").append(books);
        }
        sb.append('}');
        return sb.toString();
    }

    private static List<Book> getBookList(String authorName){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < ReaderFile.linesFromFile.size(); i += 2) {
            String[] authors = ReaderFile.linesFromFile.get(i).split(Constants.DELIMITER);
            for (String a : authors) {
                if (a.trim().equals(authorName)){
                    String[] lineBooks = ReaderFile.linesFromFile.get(i + 1).split(Constants.DELIMITER);
                    for (String b : lineBooks) {
                        books.add(new Book(b.trim()));
                    }
                }
            }
        }
        return books;
    }

    private static String getAuthorName(int index){
        if (index % 2 != 0){
            index--;
        }
        String lineNames = ReaderFile.linesFromFile.get(index);
        String[] names = lineNames.split(Constants.DELIMITER);
        return names[random.nextInt(names.length)].trim();
    }

}
