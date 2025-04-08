package com.mycom.myapp.board.dao;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    // 목록
    List<BoardDto> listBoard(BoardParamDto boardParamDto);
    int listBoardTotalCount();

    List<BoardDto> listBoardSearchWord(BoardParamDto boardParamDto); // limit, offset, searchWord
    int listBoardSearchWordTotalCount(BoardParamDto boardParamDto); // searchWord
}
