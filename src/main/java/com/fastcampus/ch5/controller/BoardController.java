package com.fastcampus.ch5.controller;

import com.fastcampus.ch5.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");

        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int regCnt = boardService.remove(bno, writer);

            if(regCnt!=1){
                throw new Exception("board remove error"); //사용자 정의 예외처리

                rattr.addFlashAttribute("msg", "DELETE_OK"); //삭제 성공하면 msg값으로 DELETE_OK 메세지 나옴
            }

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DELETE_ERR"); //삭제 실패하면 msg값으로 DELETE_ERR 메세지 나옴
        }
        return "redirect:/board/list"; //동작 수행하면 게시판 목록으로 돌아감
    }
}
