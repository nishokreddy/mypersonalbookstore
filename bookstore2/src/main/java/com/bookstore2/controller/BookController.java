package com.bookstore2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore2.entity.Book;
import com.bookstore2.entity.MyBookList;
import com.bookstore2.repository.BookRepository;
import com.bookstore2.service.BookService;
import com.bookstore2.service.MyBookListService;


import java.util.List;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister(Model model) {
		
		Book book = new Book();
		model.addAttribute("book", book);
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=service.getAllBook();
		ModelAndView m=new ModelAndView();
		m.setViewName("bookList");
		m.addObject("book",list);
		return m;
//		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/book/add")
	public String addBook(@ModelAttribute Book b) {
		
		service.save(b);		
		return "redirect:/available_books";
		
	}
	
	@PostMapping("/book/update")
	public String addBook1(@ModelAttribute Book book) {
		
		bookRepository.save(book);
		return "redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
	
}