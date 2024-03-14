package com.fastcampus.ch5.service;

import com.fastcampus.ch5.domain.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    //해당 게시글에 댓글 갯수 가져오기
    int getCount(Integer bno) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    //해당 게시글 댓글 삭제
    int remove(Integer cno, Integer bno, String commenter) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    //해당 게시글 댓글 작성
    int write(CommentDto commentDto) throws Exception;

    //게시글 댓글 모두 가져오기
    List<CommentDto> getList(Integer bno) throws Exception;

    //해당 게시글 댓글 조회
    CommentDto read(Integer cno) throws Exception;

    //해당 게시글 댓글 수정
    int modify(CommentDto commentDto) throws Exception;
}
