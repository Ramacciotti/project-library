package com.ramacciotti.library.controller;

import com.ramacciotti.library.dto.BookDTO;
import com.ramacciotti.library.service.BookServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        log.info(">> controller: searchBookByKeyword()");
        return bookServiceImpl.searchBookByKeyword(keyword);
    }

    @GetMapping("/register")
    public ModelAndView registerForm() {
        log.info(">> controller: registerForm()");
        return new ModelAndView("register.html");
    }

    @PostMapping("/submit")
    public ModelAndView submitBook(@Valid BookDTO bookDTO) {
        log.info(">> controller: submitBook()");

        try {
            bookServiceImpl.registerBook(bookDTO);
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("errorMessage", "Livro com o mesmo título e autor já existe");
            return modelAndView;
        }

        return new ModelAndView("redirect:/bookstore/list");
    }

    @GetMapping("/loan")
    public ModelAndView loanBook(@RequestParam("title") String title, @RequestParam("author") String author) {
        log.info(">> controller: loanBook()");
        ModelAndView modelAndView = new ModelAndView("loan.html");
        modelAndView.addObject("title", title);
        modelAndView.addObject("author", author);
        return modelAndView;
    }

}
