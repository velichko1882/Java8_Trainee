package by.epamlab;

import by.epamlab.beans.Author;
import by.epamlab.beans.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Runner {

    private static Logger logger = Logger.getLogger(Runner.class.getName());
    private static List<Author> authors = new ArrayList<>();

    public static void main(String[] args) {

        Stream.generate(Author::new)
                .limit(10)
                .forEach(Runner::addAuthor);
        List<Book> books = authors.stream()
                .flatMap(a -> a.getBooks().stream())
                .peek(b -> b.setAuthors(Book.getAuthorList(b.getTitle())))
                .collect(Collectors.toList());

//1
        Predicate<Book> predicatePages = book -> book.getNumberOfPages() > 200;
        boolean one = books.stream()
                .anyMatch(predicatePages);
        logger.info("Books with more than 200 pages: " + one);

//2
        boolean all = books.stream()
                .allMatch(predicatePages);
        logger.info("All books have over 200 pages: " + all);

//3
        int maxNumberPages = books.stream()
                .mapToInt(Book::getNumberOfPages)
                .max()
                .getAsInt();
        logger.info("Books with maximum pages:");
        books.stream()
                .filter(book -> book.getNumberOfPages() == maxNumberPages)
                .forEach(book -> logger.info(book.toString()));

        int minNumberPages = books.stream()
                .mapToInt(Book::getNumberOfPages)
                .min()
                .getAsInt();
        logger.info("Books with minimum pages:");
        books.stream()
                .filter(book -> book.getNumberOfPages() == minNumberPages)
                .forEach(book -> logger.info(book.toString()));

//4
        logger.info("Single-author books:");
        books.stream()
                .filter(book -> book.getAuthors().size() == 1)
                .forEach(book -> logger.info(book.toString()));

//5
        logger.info("Sort books by number of pages and then by title:");
        books.stream()
                .sorted(Comparator.comparing(Book::getNumberOfPages).thenComparing(Book::getTitle))
                .forEach(book -> logger.info(book.toString()));

//6
        logger.info("Unique book titles:");
        books.stream()
                .map(Book::getTitle)
                .sorted()
                .distinct()
                .forEach(s -> logger.info(s));

//7
        logger.info("Unique list of book authors with less than 200 pages:");
        books.stream()
                .filter(book -> book.getNumberOfPages() < 200)
                .flatMap(book -> book.getAuthors().stream())
                .map(Author::getName)
                .sorted()
                .distinct()
                .forEach(s -> logger.info(s));

    }

    private static void addAuthor(Author author){
        long match = authors.stream()
                .filter(a -> a.getName().equals(author.getName()))
                .count();
        if (match == 0){
            authors.add(author);
        }
    }
}
