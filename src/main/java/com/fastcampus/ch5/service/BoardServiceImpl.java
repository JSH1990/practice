package com.fastcampus.ch5.service;

import com.fastcampus.ch5.dao.BoardDao;
import com.fastcampus.ch5.domain.BoardDto;
import com.fastcampus.ch5.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

    @Override
    //게시글 갯수 확인
    public int getCount() throws Exception{ //controller로 예외 던짐
        return boardDao.count(); //Impl이 아닌, 인터페이스 boardDao를 사용했다.
    }

    @Override
    //게시글 제거
    public int remove(Integer bno, String writer) throws Exception{
        return boardDao.delete(bno, writer);
    }

    @Override
    //게시글 작성
    public int write(BoardDto boardDto) throws Exception{
        return boardDao.insert(boardDto);
    }

    @Override
    //게시글 목록 가져오기
    public List<BoardDto> getList() throws Exception{
        return boardDao.selectAll();
    }

    @Override
    //게시글 읽기
    public BoardDto read(Integer bno) throws Exception{
        BoardDto boardDto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno); //게시글을 읽는 메서드와 조회수를 올리는 메서드 두개를 넣었다.
        //dao는 짬뽕을 만든다. service는 짬뽕을 만들고, 쿠폰한개를 준다라는 행위 그 자체말고, 하나의 프로세스를 만드는 작업이라고 할수있다.

        return boardDto;
    }

    @Override
    //페이지 목록 가져오기
    public List<BoardDto> getPage(Map map) throws Exception{
        return boardDao.selectPage(map);
    }

    @Override
    //게시글 수정하기
    public int modify(BoardDto boardDto) throws Exception{
        return boardDao.update(boardDto);
    }

    @Override
    //게시물 검색
    public List<BoardDto> getSelectResultPage(SearchCondition sc) throws Exception{
        return boardDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception{
        return boardDao.searchResultCnt(sc);
    }

}
