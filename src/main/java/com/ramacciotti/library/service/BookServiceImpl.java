package com.ramacciotti.library.service;

import com.ramacciotti.library.dto.BookDTO;
import com.ramacciotti.library.entity.Book;
import com.ramacciotti.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookDTO> listAllBooks() {

        log.info(">> service: listAllBooks()");

        List<BookDTO> resultDTOList = new ArrayList<>();

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {

            BookDTO bookDTO = new BookDTO()
                    .withTitle(book.getTitle())
                    .withAuthor(book.getAuthor())
                    .withSynopsis(book.getSynopsis())
                    .withStatus(book.getStatus())
                    .withShelf(book.getShelf());

            resultDTOList.add(bookDTO);

        }

        return resultDTOList;

    }

    @Override
    public List<BookDTO> searchBookByKeyword(String keyword) {

        log.info(">> service: searchBookByKeyword()");

        List<BookDTO> resultDTOList = new ArrayList<>();

        List<Book> books = bookRepository.findBookByKeyword(keyword);

        for (Book book : books) {

            BookDTO bookDTO = new BookDTO()
                    .withTitle(book.getTitle())
                    .withAuthor(book.getAuthor())
                    .withSynopsis(book.getSynopsis())
                    .withStatus(book.getStatus())
                    .withShelf(book.getShelf());

            resultDTOList.add(bookDTO);

        }

        return resultDTOList;

    }

    @Override
    @Transactional
    public void registerBook(BookDTO bookDTO) {

        log.info(">> service: registerBook()");

        Book existingBook = bookRepository.findByTitleAndAuthor(bookDTO.getTitle(), bookDTO.getAuthor());

        if (existingBook != null) {
            throw new RuntimeException("Livro com o mesmo título e autor já existe");
        }

        Book book = new Book()
                .withAuthor(bookDTO.getAuthor())
                .withTitle(bookDTO.getTitle())
                .withSynopsis(bookDTO.getSynopsis())
                .withShelf(bookDTO.getShelf())
                .withStatus("available");

        bookRepository.save(book);

    }

}
