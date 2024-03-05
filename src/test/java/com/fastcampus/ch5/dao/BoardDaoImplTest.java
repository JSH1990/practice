package com.fastcampus.ch5.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void countTest() throws Exception{
        assertNotNull(boardDao);
    }

    @Test
    public void deleteAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void select() {
    }

    @Test
    public void selectPage() {
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