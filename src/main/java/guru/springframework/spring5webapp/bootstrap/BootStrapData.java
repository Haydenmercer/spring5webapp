package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    //Spring will inject the dependencies automatically at runtime
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepo) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        //Bootstrap the data here, using the repositories injected.

        Book book1 = new Book("Book one", "123123");
        Author eric = new Author("Hayden", "Mercer");
        Publisher publisher1 = new Publisher("Publisher1", "Publish Tolldene Close", "Woking", "GU212NX");

        publisherRepository.save(publisher1);

        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        book1.setPublisher(publisher1);
        publisher1.getBooks().add(book1);

        authorRepository.save(eric);
        bookRepository.save(book1);
        publisherRepository.save(publisher1);

        System.out.println("Inside bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
