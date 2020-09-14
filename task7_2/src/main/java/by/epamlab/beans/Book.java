package by.epamlab.beans;

import by.epamlab.utils.Constants;
import by.epamlab.utils.ReaderFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static by.epamlab.utils.Constants.*;

public class Book {

    private static Random random = new Random();

    private String title;
    private int numberOfPages;
    private List<Author> authors;

    public Book() {
        this.title = getBookTitle(random.nextInt(ReaderFile.linesFromFile.size()));
        this.numberOfPages = random.nextInt(PAGES) + MIN_PAGES;
        this.authors = getAuthorList(title);
    }

    public Book(String title) {
        this.title = title;
        this.numberOfPages = random.nextInt(PAGES) + MIN_PAGES;
    }

    public Book(String title, int numberOfPages, List<Author> authors) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (numberOfPages != book.numberOfPages) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return authors != null ? authors.equals(book.authors) : book.authors == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + numberOfPages;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("title='").append(title).append('\'');
        if (numberOfPages != 0) {
            sb.append(", numberOfPages=").append(numberOfPages);
        }
        if (authors != null) {
            sb.append(", authors=").append(authors);
        }
        sb.append('}');
        return sb.toString();
    }

    public static List<Author> getAuthorList(String bookTitle){
        List<Author> authors = new ArrayList<>();
        for (int i = 1; i < ReaderFile.linesFromFile.size(); i += 2) {
            String[] titles = ReaderFile.linesFromFile.get(i).split(Constants.DELIMITER);
            for (String t : titles) {
                if (t.trim().equals(bookTitle)){
                    String[] lineAuthors = ReaderFile.linesFromFile.get(i - 1).split(Constants.DELIMITER);
                    for (String a : lineAuthors) {
                        authors.add(new Author(a.trim()));
                    }
                }
            }
        }
        return authors;
    }

    private static String getBookTitle(int index){
        if (index % 2 == 0){
            index++;
        }
        String lineTitles = ReaderFile.linesFromFile.get(index);
        String[] titles = lineTitles.split(Constants.DELIMITER);
        return titles[random.nextInt(titles.length)].trim();
    }
}
