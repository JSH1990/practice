package com.fastcampus.ch5.controller;

import com.fastcampus.ch5.domain.CommentDto;
import com.fastcampus.ch5.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService service;

    //댓글을 수정하는 메서드
    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto) {
        //ResponseEntity란? Spring Framework에서 제공하는 클래스 중 HttpEntity라는 클래스가 존재한다.
        //이것은 HTTP 요청(Request) 또는 응답(Response)에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스이다.
        //ResponseEntity는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이다. 따라서 HttpStatus, HttpHeaders, HttpBody를 포함한다.

        //@PathVariable 이란? "/comments/{cno}") 에서 {cno} 경로 변수를 표시하기 위해 메서드에 매개변수에 사용된다. 경로 변수는 중괄호 {id}로 둘러싸인 값을 나타낸다.
        //기본적으로 경로 변수는 반드시 값을 가져야 하며, 값이 없는 경우 404 오류가 발생한다.

        //@ResponseBody 어노테이션 이란? 스프링에서 비동기 처리를 하는 경우 @RequestBody , @ResponseBody를 사용한다.
        //이 어노테이션이 붙은 파라미터에는 http요청의 본문(body)이 그대로 전달된다.
        //일반적인 GET/POST의 요청 파라미터라면 @RequestBody를 사용할 일이 없을 것이다.
        //반면에 xml이나 json기반의 메시지를 사용하는 요청의 경우에 이 방법이 매우 유용하다.
        //HTTP 요청의 바디내용을 통째로 자바객체로 변환해서 매핑된 메소드 파라미터로 전달해준다.

        String commenter = "bbb"; //댓글작성자 "bbb" 를 만든다.
        dto.setCommenter(commenter); //댓글작성자를 bbb로 수정한다.
        dto.setCno(cno); //댓글번호도 클라이언트에서 응답받은 댓글dto로 수정한다.
        System.out.println("dto = " + dto);

        try {
            if (service.modify(dto) != 1)
                throw new Exception("Write failed."); //수정 실패하면 실패 예외 던진다.

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //댓글을 등록하는 메서드
    @PostMapping("/comments") // /ch5/comments?bno=1085 POST
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session) {
        String commenter = "bbb";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        System.out.println("dto = " + dto);

        try {
            if (service.write(dto) != 1)
                throw new Exception("Write failed");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}") // comments/1?bno=1085 <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
        String commenter = "bbb";

        try {
            int rowCnt = service.remove(cno, bno, commenter);

            if (rowCnt != 1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }

    }

    //지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list = null;

        try {
            list = service.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
        }
    }
}

