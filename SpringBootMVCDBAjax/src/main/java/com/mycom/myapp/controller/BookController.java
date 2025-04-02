package com.mycom.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mycom.myapp.dto.BookDto;
import com.mycom.myapp.service.BookService;

// Controller 는 항상 front 와 back 연결
// url mapping 은 front 와 약속
// client 의 ajax 요청에 대해 BookController 는 자바 객체를 json 으로 응답해 줘야 한다.
// Spring 은 이를 자동화 해 준다. @ResponseBody
@Controller
@RequestMapping("/books")
// @ResponseBody bookMain() 는 jsp 로 분기되어야 한다.
public class BookController {
   private final BookService bookService;

   public BookController(BookService bookService) {
      this.bookService = bookService;
   }

   // index.html 의 도서 관리 링크 대응
   // books.jsp 로 이동
   @GetMapping(value="/")
   public String bookMain() {
      return "books";
   }

   // 목록 : /books/list,   get,  X,       list.jsp
   @GetMapping(value="/list")
   @ResponseBody
   public List<BookDto> listBook() {
      List<BookDto> bookList = bookService.listBook();
      return bookList;
   }

   // 상세 : /books/detail, get,  bookId,  detailForm.jsp  // /books/detail/9 path variable
   @GetMapping(value="/detail/{bookId}")
   @ResponseBody
   public BookDto detailBook(@PathVariable int bookId) {
      System.out.println(bookId);
      BookDto bookDto = bookService.detailBook(bookId);
      return bookDto;
   }

   // 등록 : /books/insert, post, BookDto, insertResult.jsp
   @PostMapping(value="/insert")
   @ResponseBody
   public Map<String, String> insertBook(BookDto bookDto) {
      System.out.println(bookDto);
      int ret = bookService.insertBook(bookDto);
      System.out.println(ret);
      Map<String, String> map = new HashMap<>();
      if (ret == 1) {
         map.put("result", "success");
      } else {
         map.put("result", "fail");
      }

      return map;
   }

   // 수정 : /books/update, post, BookDto, updateResult.jsp
   @PostMapping(value="/update")
   @ResponseBody
   public Map<String, String> updateBook(BookDto bookDto) {
      System.out.println(bookDto);
      int ret = bookService.updateBook(bookDto);

      Map<String, String> map = new HashMap<>();
      if (ret == 1) {
         map.put("result", "success");
      } else {
         map.put("result", "fail");
      }

      return map;
   }

   // 삭제 : /books/delete, get,  bookId,  deleteResult.jsp
   @GetMapping(value="/delete/{bookId}")
   @ResponseBody
   public Map<String, String> deleteBook(@PathVariable int bookId) {
      System.out.println(bookId);
      int ret = bookService.deleteBook(bookId);

      Map<String, String> map = new HashMap<>();
      if (ret == 1) {
         map.put("result", "success");
      } else {
         map.put("result", "fail");
      }

      return map;
   }

   // insertForm.jsp 는 삭제
}