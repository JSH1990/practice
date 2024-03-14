package com.fastcampus.ch5.dao;

import com.fastcampus.ch5.domain.CommentDto;

import java.util.List;

public interface CommentDao {

    //댓글 모두 삭제하기
    int deleteAll(Integer bno);

    //해당 게시글에 댓글 갯수 가져오기
    int count(Integer bno) throws Exception;

    //댓글 1개 삭제
    int delete(Integer cno, String commenter) throws Exception;

    //댓글 작성하기
    int insert(CommentDto dto) throws Exception;

    //댓글 모두 조회하기
    List<CommentDto> selectAll(Integer bno) throws Exception;

    //댓글 하나 조회하기
    CommentDto select(Integer cno) throws Exception;

    int update(CommentDto dto) throws Exception;
}
