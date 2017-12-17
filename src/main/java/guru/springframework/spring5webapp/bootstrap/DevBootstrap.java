package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        List<Publisher> publishers = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Publisher work = new Publisher("Work", "sacvetn");
        Publisher harperCollins = new Publisher("Harper Collins", "sdsafg");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "23444", work);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        Book book = new Book("Our book", "123456", harperCollins);
        book.getAuthors().add(eric);
        book.getAuthors().add(rod);
        rod.getBooks().add(book);
        eric.getBooks().add(book);

        publishers.add(work);
        publishers.add(harperCollins);

        authors.add(eric);
        authors.add(rod);

        books.add(ddd);
        books.add(noEJB);
        books.add(book);

        publisherRepository.saveAll(publishers);
        authorRepository.saveAll(authors);
        bookRepository.saveAll(books);
    }

}
