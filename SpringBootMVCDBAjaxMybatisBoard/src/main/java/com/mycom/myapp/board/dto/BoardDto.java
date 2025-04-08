package com.mycom.myapp.board.dto;

import com.mycom.myapp.board.dao.BoardDao;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

// board table 과 user table 의 조인 결과를 표현
// java 8 LocalDateTime ( client 에게 일시(일자, 시각) 관련 데이터를 년월일 시분초 로 쉽게 내려줄 수 있다. )
public class BoardDto {
    private int boardId;
    private int userSeq;
    private String userName;
    private String userProfileImage;
    private String title;
    private String content;
    private LocalDateTime regDt; // jackson 대신 gson 사용 ( 년원일 시분초 json 구성이 서로 다른데 gson 이 더 쉽다. )
    private int readCount;

    private boolean sameUser; // 현재 로그인한 사용자 (게시글을 보는 사용자) 와 글 작성자가 동일인 여부

    public BoardDto() {
    }

    public BoardDto(int userSeq, String title, String content) {
        super();
        this.userSeq = userSeq;
        this.title = title;
        this.content = content;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    // mybatis Date Mapping 처리 시 LocalDateTime 으로 바로 처리 X -> 변경 필요 (LocalDateTime -> Date)
//    public void setRegDt(LocalDateTime regDt) {
//        this.regDt = regDt;
//    }
    public void setRegDtt(Date regDt) {
        this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public boolean isSameUser() {
        return sameUser;
    }

    public void setSameUser(boolean sameUser) {
        this.sameUser = sameUser;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", userSeq=" + userSeq +
                ", userName='" + userName + '\'' +
                ", userProfileImage='" + userProfileImage + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regDt=" + regDt +
                ", readCount=" + readCount +
                ", sameUser=" + sameUser +
                '}';
    }
}
