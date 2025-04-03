package com.mycom.myapp.dao;

import com.mycom.myapp.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDao {

    List<BookDto> listBook();
    BookDto detailBook(int bookid);
    int insertBook(BookDto book);
    int updateBook(BookDto book);
    int deleteBook(int bookId);

}
