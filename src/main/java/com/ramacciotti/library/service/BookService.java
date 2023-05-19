package com.ramacciotti.library.service;

import com.ramacciotti.library.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> listAllBooks();

    List<BookDTO> searchBookByKeyword(String keyword);

    void registerBook(BookDTO bookDTO);

}
