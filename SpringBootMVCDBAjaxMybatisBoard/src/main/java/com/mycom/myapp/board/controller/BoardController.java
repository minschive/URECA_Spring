package com.mycom.myapp.board.controller;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import com.mycom.myapp.board.service.BoardService;
import com.mycom.myapp.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list") // limit, offset, searchWord 모두 한꺼번에 처리 ( service, dao layer 는 분리되어 있다. )
    @ResponseBody
    public BoardResultDto listBoard(BoardParamDto boardParamDto) {

        BoardResultDto boardResultDto = null;

        if(Strings.isEmpty(boardParamDto.getSearchWord())) { // 검색어가 없는 경우 (null, "")
            boardResultDto = boardService.listBoard(boardParamDto); // limit, offset
        } else {
            boardResultDto = boardService.listBoardSearchWord(boardParamDto); // limit, offset, searchWord
        }

        // 예외 처리 테스트
//        String s = null;
//        s.length();

        return boardResultDto;
    }

    // Controller 에서 session 에 담긴 현재 조회 사용자의 userSeq 를 Service 에 전달
    @GetMapping("/detail/{boardId}")
    @ResponseBody
    public BoardResultDto detailBoard(@PathVariable("boardId") Integer boardId, HttpSession session) { // boardId 자동으로 mapping 안되면 null

        BoardParamDto boardParamDto = new BoardParamDto();
        boardParamDto.setBoardId(boardId);
        int userSeq = ((UserDto) session.getAttribute("userDto")).getUserSeq(); // session 이 invalidate 된 상황 고려해봐야 함
        boardParamDto.setUserSeq(userSeq);

        return boardService.detailBoard(boardParamDto);
    }

    @PostMapping("/insert")
    @ResponseBody
    public BoardResultDto insertBoard(BoardDto boardDto, HttpSession session) { // client 에서 boardId, userSeq 전송 X
        int userSeq = ((UserDto) session.getAttribute("userDto")).getUserSeq(); // session 에서 현재 글 작성자 userSeq
        boardDto.setUserSeq(userSeq);
        return boardService.insertBoard(boardDto);
    }

    @PostMapping("/update")
    @ResponseBody
    public BoardResultDto updateBoard(BoardDto boardDto) { // client 에서 boardId 전송 O, userSeq 수정 대상 X
        return boardService.updateBoard(boardDto);
    }

    @GetMapping("/delete/{boardId}")
    @ResponseBody
    // client 에서 boardId 전송 O, boardId 자동으로 mapping 안되면 null 처리시도, primitive type 으로 오류 발생
    public BoardResultDto deleteBoard(@PathVariable("boardId") int boardId) {
        return boardService.deleteBoard(boardId);
    }
}
