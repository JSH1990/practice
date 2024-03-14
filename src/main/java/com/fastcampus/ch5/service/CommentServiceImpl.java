package com.fastcampus.ch5.service;

import com.fastcampus.ch5.dao.BoardDao;
import com.fastcampus.ch5.dao.CommentDao;
import com.fastcampus.ch5.domain.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    BoardDao boardDao;
    CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao){
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }

    @Override
    //해당 게시글에 댓글 갯수 가져오기
    public int getCount(Integer bno) throws Exception{
        return commentDao.count(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    //해당 게시글 댓글 삭제
    public int remove(Integer cno, Integer bno, String commenter) throws Exception{ //댓글번호, 게시글번호, 댓글작성자
        int rowCnt = boardDao.updateCommentCnt(bno, -1); //boardDao.updateCommentCnt(bno, cnt) 해당 번호의 게시판에 댓글갯수 늘리기.
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt); // 댓글겟수 늘리기 출력(댓글이 몇개있는지 확인하기위해-삭제기능확인위해서)

        rowCnt = commentDao.delete(cno, commenter);//갯수 삭제 확인
        System.out.println("rowCnt= "  +rowCnt); //삭제되었는지 확인위해
        return rowCnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    //해당 게시글 댓글 작성
    public int write(CommentDto commentDto) throws Exception {
        boardDao.updateCommentCnt(commentDto.getBno(), 1); //해당 게시물 번호 가져와 댓글 1개 생성
        return commentDao.insert(commentDto);
    }

    @Override
    //게시글 댓글 모두 가져오기
    public List<CommentDto> getList(Integer bno) throws Exception{
        return commentDao.selectAll(bno);
    }

    @Override
    //해당 게시글 댓글 조회
    public CommentDto read(Integer cno) throws Exception{
        return commentDao.select(cno);
    }

    @Override
    //해당 게시글 댓글 수정
    public int modify(CommentDto commentDto) throws Exception{
        return commentDao.update(commentDto);
    }


}
