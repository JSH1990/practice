package com.fastcampus.ch5.service;

import com.fastcampus.ch5.domain.BoardDto;
import com.fastcampus.ch5.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardService {
    //게시글 갯수 확인
    int getCount() throws Exception;

    //게시글 제거
    int remove(Integer bno, String writer) throws Exception;

    //게시글 작성
    int write(BoardDto boardDto) throws Exception;

    //게시글 목록 가져오기
    List<BoardDto> getList() throws Exception;

    //게시글 읽기
    BoardDto read(Integer bno) throws Exception;

    //페이지 목록 가져오기
    List<BoardDto> getPage(Map map) throws Exception;

    //게시글 수정하기
    int modify(BoardDto boardDto) throws Exception;

    List<BoardDto> getSelectResultPage(SearchCondition sc) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;
}
