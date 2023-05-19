package com.ramacciotti.library.service;

import com.ramacciotti.library.dto.BookDTO;
import com.ramacciotti.library.entity.Book;
import com.ramacciotti.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                    .withSynopsis(book.getSynopsis());

            resultDTOList.add(bookDTO);

        }

        return resultDTOList;

    }

}
