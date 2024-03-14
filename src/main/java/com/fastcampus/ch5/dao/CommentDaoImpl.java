package com.fastcampus.ch5.dao;

import com.fastcampus.ch5.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //dao는 db저장소이기때문에 @Repository 애노테이션 적어줘야함.
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.fastcampus.ch5.dao.CommentMapper."; //마지막에 .(점) 꼭 찍어야함

    @Override
    //해당 게시글에 댓글 갯수 가져오기
    public int count(Integer bno) throws Exception{
        return session.selectOne(namespace+"count", bno);
    }

    @Override
    //댓글 모두 삭제하기
    public int deleteAll(Integer bno){
        return session.delete(namespace+"deleteAll", bno);
    }

    @Override
    //댓글 1개 삭제
    public int delete(Integer cno, String commenter) throws Exception{
        Map map = new HashMap(); //삭제하기위해 2가지 매개변수를 담으려고 map객체 생성.
        map.put("cno", cno);
        map.put("commenter", commenter);
        return session.delete(namespace+"delete", map);
    }

    @Override
    //댓글 작성하기
    public int insert(CommentDto dto) throws Exception{
        return session.insert(namespace+"insert", dto);
    }

    @Override
    //댓글 모두 조회하기
    public List<CommentDto> selectAll(Integer bno) throws Exception{
        return session.selectList(namespace+"selectAll", bno);
    }

    @Override
    //댓글 하나 조회하기
    public CommentDto select(Integer cno) throws Exception{
        return session.selectOne(namespace + "select", cno);
    }

    @Override
    public int update(CommentDto dto) throws Exception{ //기존의 댓글을 수정해야하므로 매개변수를 CommentDto로 했음.
        return session.update(namespace+"update", dto);
    }

}
