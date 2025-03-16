package com.sisimpur.library.service.impl;

import com.sisimpur.library.dto.BookCreateDto;
import com.sisimpur.library.dto.BookDto;
import com.sisimpur.library.exception.ResourceNotFoundException;
import com.sisimpur.library.mapper.BaseMapper;
import com.sisimpur.library.mapper.BookMapper;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.Book;
import com.sisimpur.library.model.User;
import com.sisimpur.library.repository.AuthorRepository;
import com.sisimpur.library.repository.BookRepository;
import com.sisimpur.library.repository.SearchableRepository;
import com.sisimpur.library.repository.UserRepository;
import com.sisimpur.library.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends AbstractCrudService<Book, BookDto, Long> implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           UserRepository userRepository,
                           BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    protected SearchableRepository<Book, Long> getRepository() {
       return bookRepository;
    }

    @Override
    protected BaseMapper<Book, BookDto> getMapper() {
        return bookMapper;
    }

    @Override
    public BookDto createBook(BookCreateDto bookCreateDto) {
        // Map the create DTO to a Book entity
        Book book = Book.builder()
                .title(bookCreateDto.getTitle())
                .genre(bookCreateDto.getGenre())
                .publishedYear(bookCreateDto.getPublishedYear())
                .active(bookCreateDto.getActive())
                .build();

        // Find the author by id and set it; throw an exception if not found.
        Author author = authorRepository.findById(bookCreateDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookCreateDto.getAuthorId()));
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public BookDto assignBookToUser(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Maintain the bidirectional relationship
        book.getUsers().add(user);
        user.getBooks().add(book);

        // Save changes (you can save either side; here we update book)
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public BookDto unassignBookFromUser(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Remove the relationship from both sides
        book.getUsers().remove(user);
        user.getBooks().remove(book);

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }
}
