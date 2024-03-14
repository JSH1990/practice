package com.fastcampus.ch5.controller;

import com.fastcampus.ch5.domain.BoardDto;
import com.fastcampus.ch5.domain.PageHandler;
import com.fastcampus.ch5.domain.SearchCondition;
import com.fastcampus.ch5.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");

        try { //다른 계층에서 던진 예외를 controller에서 예외처리
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int regCnt = boardService.remove(bno, writer);

            if(regCnt!=1){
                throw new Exception("board remove error"); //사용자 정의 예외처리
            }

            rattr.addFlashAttribute("msg", "DELETE_OK");//삭제 성공하면 msg값으로 DELETE_OK 메세지 나옴

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DELETE_ERR"); //삭제 실패하면 msg값으로 DELETE_ERR 메세지 나옴
        }
        return "redirect:/board/list"; //동작 수행하면 게시판 목록으로 돌아감
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer); //session에서 id가져와 setWriter로 넣음

        try {
            int rowCnt = boardService.write(boardDto);

            if(rowCnt != 1){
                throw new Exception("Write failed");
            }

            rattr.addFlashAttribute("msg", "MRT_OK");//기존 attribute사용하면 url에 주소가 남아있어 1회만 사용하는 addFlashAttribute사용

            return "redirect:/board/list";

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto); //게시글 작성 실패시, 작성중이던 글을 그대로 가져오기위해서 m에 넣음.
            m.addAttribute("msg", "MRT_ERR");
            return "board";
        }
    }

    @GetMapping("/write") //해당 게시물을 작성하려면 작성버튼을 클릭해서 작성할 게시물 폼이 나와야 하기때문에 필요
    public String write(Model m){
        m.addAttribute("mode", "new"); //mode를 통해 게시물 작성과, 수정을 구별하기 위해 담는다.
        return "board";
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno); //해당게시물번호 가져와 boardDto에 담음
            //해당 게시물을 읽으려면, 해당번호의 게시물,page,pageSize가 필요하다. 그래서 m에 넣는다.
            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "board"; //해당 번호의 게시물로 이동
    }
    
    private boolean loginCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
    
    @GetMapping("/list")
    public String list(SearchCondition sc, Model m, HttpServletRequest request){
        if(!loginCheck(request)) //로그인 id 값이 null 이라면
            return "redirect:/login/login?toUrl=" + request.getRequestURL(); //로그인창으로 이동시킴

        try {
            int totalCnt = boardService.getSearchResultCnt(sc); //검색결과 가져와 totalCnt에 넣는다.
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler= new PageHandler(totalCnt, sc); //페이지 핸들러 객체 생성

            List<BoardDto> list = boardService.getSelectResultPage(sc); //검색결과 가져와서 list에 넣음.
            m.addAttribute("list", list); //서버에서 가져온 검색결과를 m에 넣음
            m.addAttribute("ph", pageHandler); //pageHandler를 m에 넣음

            Instant starOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", starOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "boardList";
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.modify(boardDto);

            if(rowCnt != 1){
                throw new Exception("Modify failed");
            }

            rattr.addFlashAttribute("msg", "MOD_OK"); //글 수정 성공하면 메세지 보여줌
            return "redirect:/board/list"; //글목록으로 돌아감

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto); //수정 실패시 작성중이던 글 두기
            m.addAttribute("msg", "MOD_REE");
            return "board";
        }
    }

    
}
