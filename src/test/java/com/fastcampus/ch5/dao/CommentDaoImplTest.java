package com.fastcampus.ch5.dao;

import com.fastcampus.ch5.domain.CommentDto;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentDaoImplTest {
    @Autowired
    CommentDao commentDao;

    @Test
    public void count() throws Exception{
        commentDao.deleteAll(613); //해당 게시물 번호를 입력해 댓글모두 삭제
        assertTrue(commentDao.count(613)==0); //
    }

    @Test
    public void delete() throws Exception{
        commentDao.deleteAll(488); //해당 게시물 번호를 입력해 댓글모두 삭제
        CommentDto commentDto = new CommentDto(488,0,"commenter","bbb"); //해당 게시물에 댓글1개 만들기
        assertTrue(commentDao.insert(commentDto)==1); //만든 댓글 1개 작성
        assertTrue(commentDao.count(488)==1); //댓글 1개 인지 확인
    }

    @Test
    public void insert() throws Exception{
        commentDao.deleteAll(488);
        CommentDto commentDto = new CommentDto(488, 0, "comment", "bbb");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(488)==1);

        commentDto = new CommentDto(488,0,"test2","bbb2"); //게시글한개 더 생성
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(488)==2);
    }

    @Test
    //commentDao.count과 commentDao.selectAll의 차이점은 count는 숫자만나오고, selectAll는 댓글목록리스트가 다 나오는것이다.
    public void selectAll() throws Exception {
        commentDao.deleteAll(488);
        CommentDto commentDto = new CommentDto(488, 0, "comment", "bbb");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(488)==1);

        List<CommentDto> list = commentDao.selectAll(488); //해당 게시물 댓글 조회
        assertTrue(list.size()==1); //댓글 한개있으니까 size도 1개

        commentDto = new CommentDto(488, 0, "comment2", "nnn");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(488)==2);

        list = commentDao.selectAll(488);
        assertTrue(list.size()==2);
    }

    @Test
    public void select() throws Exception {
        commentDao.deleteAll(488);
        CommentDto commentDto = new CommentDto(488,0,"comment","bbb");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(488)==1);

        List<CommentDto> list = commentDao.selectAll(488);
        String comment = list.get(0).getComment(); //리스트에서 첫번째 comment 가져온다.
        String commenter = list.get(0).getCommenter(); //리스트에서 첫번째 commenter 가져온다.
        assertTrue(comment.equals(commentDto.getComment())); //가져온 comment와 commentDto.getComment())가 맞는지 확인한다.
        assertTrue(commenter.equals(commentDto.getCommenter())); //가져온 commenter와 commentDto.getCommenter())가 맞는지 확인한다.
    }

    @Test
    public void update() throws Exception {
        commentDao.deleteAll(488);
        CommentDto commentDto = new CommentDto(488, 0, "comment", "bbb");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(488)==1);

        List<CommentDto> list = commentDao.selectAll(488); //해당 게시물의 댓글을 리스트로 가져온다.
        commentDto.setCno(list.get(0).getCno()); //리스트로 가져온 댓글번호를 setCno로 저장한다. 매개변수가 cno와 comment 이므로
        commentDto.setComment("comment2"); //comment2로 저장한다
        assertTrue(commentDao.update(commentDto)==1); //1이면 성공이다.

        list = commentDao.selectAll(488);
        String comment = list.get(0).getComment();
        String commenter = list.get(0).getCommenter();
        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(commenter.equals(commentDto.getCommenter()));
    }
}