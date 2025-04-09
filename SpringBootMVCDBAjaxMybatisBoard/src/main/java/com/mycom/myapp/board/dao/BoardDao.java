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

    // 상세
    BoardDto detailBoard(BoardParamDto boardParamDto); // boardId

    // 등록, 수정
    int insertBoard(BoardDto boardDto);
    int updateBoard(BoardDto boardDto);
    int deleteBoard(int boardId);
}
