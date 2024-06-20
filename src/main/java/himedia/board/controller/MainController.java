package himedia.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.board.model.Post;
import himedia.board.model.Pagination;

@Controller
public class MainController {

    @GetMapping("/board")
    public String board(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        // 예시 데이터
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            posts.add(new Post(i, "제목" + i, "작성자" + i, "2024-06-20", 100 + i));
        }
        
        // 페이징 데이터
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(page);
        pagination.setStartPage(1);
        pagination.setEndPage(5);

        model.addAttribute("posts", posts);
        model.addAttribute("pagination", pagination);

        return "board/board"; // JSP 파일의 경로를 올바르게 반환
    }
}
