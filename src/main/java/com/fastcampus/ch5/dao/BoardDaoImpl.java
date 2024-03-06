package com.fastcampus.ch5.dao;

import com.fastcampus.ch5.domain.*;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.fastcampus.ch5.dao.BoardMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno); //매개변수에 bno 들어있어서 빨간불 안들어옴 (mapper와 상관없음)
        map.put("writer", writer);
        return session.delete(namespace+"delete",map); //delete(string, object)로 위에 생성한 map객체를 의미한다.
    }

    @Override
    public int insert(BoardDto dto) throws Exception {
        session.insert(namespace+"insert", dto);
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public BoardDto select(Integer bno) throws Exception {
        return session.selectOne(namespace+"select",bno);
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return null;
    }

    @Override
    public int update(BoardDto dto) throws Exception {
        return 0;
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return 0;
    }

    @Override
    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return null;
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return 0;
    }

    @Override
    public int updateCommentCnt(Integer bno, int i) {
        return 0;
    }


}