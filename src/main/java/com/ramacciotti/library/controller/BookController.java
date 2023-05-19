package com.ramacciotti.library.controller;

import com.ramacciotti.library.dto.BookDTO;
import com.ramacciotti.library.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookstore")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @GetMapping("/list")
    public ModelAndView listAllBooks() {
        log.info(">> controller: listAllBooks()");
        List<BookDTO> books = bookServiceImpl.listAllBooks();
        ModelAndView modelAndView = new ModelAndView("list.html");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/search")
    public List<BookDTO> searchBookByKeyword(@RequestParam("keyword") String keyword) {
        return bookServiceImpl.searchBookByKeyword(keyword);
    }

}
