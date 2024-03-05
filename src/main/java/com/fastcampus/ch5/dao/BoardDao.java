package com.fastcampus.ch5.dao;

import com.fastcampus.ch5.domain.BoardDto;
import com.fastcampus.ch5.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    //한개 조회는 BoardDto, 전체조회는 list형태로 List<BoardDto>, 수정/삭제는 int로 성공유무만 반환하면 된다고 생각했다.
    //BoardDao는 인터페이스이기때문에 public이 없다. 그리고 { }가 없다. (껍데기 이기때문)


    //count() 게시글 갯수조회(틀림) - 게시글의 갯수 확인은 int형태로 반환된다.
    //매개변수 없다. 전체 조회니까
    int count() throws Exception;

    //게시글 모두 삭제
    //매개변수 없다. 전체 삭제니까
    int deleteAll ();

    //게시글 삭제
    //매개변수는 게시글번호, 작성자가 있어야한다.
    int delete(Integer bno, String writer) throws Exception;

    //게시글 작성
    //매개변수는 게시글주제, 내용, 작성자를 넣는다. x = BoardDto dto
    int insert(BoardDto dto) throws Exception;

    //게시글 모두 조회
    //등록날짜, 게시글 내림차순
    List<BoardDto> selectAll() throws Exception;

    //게시글 조회
    //매개변수는 해당게시글번호이다.
    BoardDto select(Integer bno) throws Exception;

    //페이지 조회
    //x = 매개변수는 Map map 이다. (해결중)
    List<BoardDto> selectPage(Map map) throws Exception;

    //게시글 수정
    //매개변수는 게시글번호와 작성자이다.x = BoardDto dto
    int update(BoardDto dto) throws Exception;

    //게시글 조회 증가
    //해당게시글 조회수니까 해당게시물번호를 매개변수로 했다.
    int increaseViewCnt(Integer bno) throws Exception;

    //검색 페이지 조회
    //x = 매개변수는 SearchCondition sc
    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

    //searchResultCnt 검색결과 갯수 조회 x - 검색결과의 조건이 참인 경우의 갯수가 반환된다.
    //매개변수는 comment 관련인거같다.
    //x = 매개변수는 SearchCondition sc
    int searchResultCnt(SearchCondition sc) throws Exception;

    //댓글 수정 갯수
    //매개변수는 게시글번호와 작성자(작성자가 일치해야 수정), comment 관련일거같다. x = Integer bno, int i
    int updateCommentCnt(Integer bno, int i);
}
