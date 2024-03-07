package com.fastcampus.ch5.dao;

import com.fastcampus.ch5.domain.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})


public class BoardDaoImplTest {

    //왜 BoardDaoImpl이 있어야 BoardDao가 주입이 될까?
    //=BoardDao를 구현화 시킨게 BoardDaoImpl이기때문이다.
    //BoardDaoImpl를 안만들면, test를 할때 sqlmapper가 연결이 안되있어 테스트 자체가 불가능하다.
    @Autowired
    private BoardDao boardDao;

    @Test
    public void countTest() throws Exception{ //dao와 daoImpl 모두 예외를 던졌기 때문에 던져야 한다.
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("no title", "no content", "bbb");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==1);

        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==2);
    }

    @Test
    public void deleteAll() throws Exception{
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("bbb","bbb","bbb"); //boardDto자체가 글을 작성한 순간이고, boardDao.insert()가 글작성 버튼을 누른거다.
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==1);

        boardDto = new BoardDto("aaa", "aaa", "aaa"); //다른글을 작성하기위해 새로운 생성자를 만들어, boardDto객체에 넣음
    }

    @Test
    public void deleteTest() throws Exception{
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("bbb", "bbb", "bbb");
        assertTrue(boardDao.insert(boardDto)==1);
        Integer bno = boardDao.selectAll().get(0).getBno(); //게시글을 모두 조회해서, 첫번째 게시글에 해당하는 게시글번호를 가져와 bno변수에 넣는다.
        assertTrue(boardDao.delete(bno, boardDto.getWriter())==1); //가져온 게시글번호와 작성자를 매개변수로 가져와 삭제한다.
        assertTrue(boardDao.count()==0);

        assertTrue(boardDao.insert(boardDto)==1);
        bno = boardDao.selectAll().get(0).getBno(); //위에서 만들어놓은 한개의 bno 변수로 다양하게 사용하기 위함이다.
        assertTrue(boardDao.delete(bno, boardDto.getWriter()+"222")==0); //가져온 getWriter 아이디의 222를 추가한다는 의미이다.
        assertTrue(boardDao.count()==1);
    }

    @Test
    public void insertTest() {
        boardDao.deleteAll();

    }

    @Test
    public void selectAll() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        List<BoardDto> list = boardDao.selectAll();
        assertTrue(list.size() == 0);

        BoardDto boardDto = new BoardDto("aaa", "bbb", "ccc");
        assertTrue(boardDao.insert(boardDto)==1);

        list = boardDao.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(boardDao.insert(boardDto) == 1);
        list = boardDao.selectAll();
        assertTrue(list.size()== 2);
    }

    @Test
    public void selectTest() throws Exception{
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("aaa", "bbb", "ccc");
        assertTrue(boardDao.insert(boardDto)==1);

        Integer bno = boardDao.selectAll().get(0).getBno();
        boardDto.setBno(bno);
        BoardDto boardDto2 = boardDao.select(bno);
        System.out.println("boardDto" + boardDto);
        System.out.println("boardDto2" + boardDto2);

        assertTrue(boardDto.equals(boardDto2)); //BoardDto클래스에서 equals메서드를 만든 이유이다.
    }

    @Test
    public void selectPageTest() throws Exception{
        boardDao.deleteAll();

        for (int i=1; i <= 10; i++){ //게시글 10개 만드는 메서드
            BoardDto boardDto = new BoardDto(""+i, "aaa"+i, "bbb"); //123..10 까지 번호를 붙여 게시글을 만들기위해 i사용
            boardDao.insert(boardDto);
        }

        assertTrue(boardDao.count()==10);

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<BoardDto> list = boardDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = boardDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));

        
    }

    @Test
    public void update() {
    }

    @Test
    public void increaseViewCnt() {
    }

    @Test
    public void searchSelectPage() {
    }

    @Test
    public void searchResultCnt() {
    }

    @Test
    public void updateCommentCnt() {
    }
}