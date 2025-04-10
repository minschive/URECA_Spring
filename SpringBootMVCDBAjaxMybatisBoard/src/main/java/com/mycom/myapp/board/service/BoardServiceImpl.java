package com.mycom.myapp.board.service;

import com.mycom.myapp.board.dao.BoardDao;
import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    // 게시글 상세 정보 + 조회수 처리
    // transaction test
    //  1. @Transactional 사용 X 경우 (Spring Transaction 관리 AOP 관여 X)
    //     insert O, update X
    //  2. @Transactional 사용 O 경우 (Spring Transaction 관리 AOP 관여 O, PointCut 에 추가)
    //      1. RuntimeException 계열 객체 throw => Transaction 관리 AOP 에 전달 => rollback
    //      2. 예외 발생 X => Transaction 관리 AOP 가 commit
    //      3. RuntimeException 계열 객체 throw 가 되어도 try-catch 로 묶어버리면 Transaction 관리 AOP 에 전달 X
    //          catch block에서 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 을 통해 rollback 처리
    @Override
    @Transactional
    public BoardResultDto detailBoard(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            // 조회수 처리
            // 현재 게시글에 대한 현재 사용자의 조회 여부 확인
            int userReadCnt = boardDao.countBoardUserRead(boardParamDto);

            System.out.println("boardId : " + boardParamDto.getBoardId());
            System.out.println("userSeq : " + boardParamDto.getUserSeq());
            System.out.println("userReadCnt : " + userReadCnt);

            if(userReadCnt == 0) { // 현재 게시글을 처음 읽는 상황
                boardDao.insertBoardUserRead(boardParamDto); // 현재 게시글을 현재 사용자가 읽었다. 표시 등록

                // transaction test
                String s = null;
                s.length();

                boardDao.updateBoardReadCount(boardParamDto.getBoardId()); // 현재 게시글 조회수 증가 처리
            }

            // 게시글 상세 정보
            BoardDto boardDto = boardDao.detailBoard(boardParamDto);
            // sameUser
            if(boardDto.getUserSeq() == boardParamDto.getUserSeq()) {
                boardDto.setSameUser(true);
            } else {
                boardDto.setSameUser(false);
            }
            boardResultDto.setDto(boardDto);
            boardResultDto.setResult("success");
        } catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult("fail");

            // Spring 제안 방법
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            // RuntimeException 객체 생성 & throw
//            throw new RuntimeException("~~~~");
//            throw new IllegalStateException("~~~~~");
        }
        return boardResultDto;
    }

    @Override
    public BoardResultDto insertBoard(BoardDto boardDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            int ret = boardDao.insertBoard(boardDto);

            if(ret == 1) boardResultDto.setResult("success");
            else boardResultDto.setResult("fail");

        } catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }
        return boardResultDto;
    }

    @Override
    public BoardResultDto updateBoard(BoardDto boardDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            int ret = boardDao.updateBoard(boardDto);

            if(ret == 1) boardResultDto.setResult("success");
            else boardResultDto.setResult("fail");

        } catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }
        return boardResultDto;
    }

    @Override
    public BoardResultDto deleteBoard(int boardId) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            int ret = boardDao.deleteBoard(boardId);

            if(ret == 1) boardResultDto.setResult("success");
            else boardResultDto.setResult("fail");

        } catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }
        return boardResultDto;
    }


}
