package com.mycom.myapp.board.service;

import com.mycom.myapp.board.dao.BoardDao;
import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public BoardResultDto listBoard(BoardParamDto boardParamDto) { // limit, offset
        BoardResultDto boardResultDto = new BoardResultDto();

        // 예외 처리
        // 처리과정 중 오류 발생????
        //  1. 직접 제어 (사용)
        //  2. Spring Framework 처리 의뢰 / error mapping
        try {
            // Controller 는 BoardService 의 listBoard() 1회 호출
            // BoardService 는 BoardDao 의 listBoard() 와 listBoardTotalCount() 2개 호출
            List<BoardDto> list = boardDao.listBoard(boardParamDto);
            int count = boardDao.listBoardTotalCount();
            boardResultDto.setList(list);
            boardResultDto.setCount(count);
            boardResultDto.setResult("success");
        } catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }
        return boardResultDto;
    }

    @Override
    public BoardResultDto listBoardSearchWord(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            // Controller 는 BoardService 의 listBoard() 1회 호출
            // BoardService 는 BoardDao 의 listBoard() 와 listBoardTotalCount() 2개 호출
            List<BoardDto> list = boardDao.listBoardSearchWord(boardParamDto);
            int count = boardDao.listBoardSearchWordTotalCount(boardParamDto); // searchWord
            boardResultDto.setList(list);
            boardResultDto.setCount(count);
            boardResultDto.setResult("success");
        } catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }
        return boardResultDto;
    }
}
