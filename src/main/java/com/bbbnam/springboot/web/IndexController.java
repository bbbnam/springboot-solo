package com.bbbnam.springboot.web;

import com.bbbnam.springboot.config.auth.LoginUser;
import com.bbbnam.springboot.config.auth.dto.SessionUser;
import com.bbbnam.springboot.domain.posts.PostsResponseDto;
import com.bbbnam.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        /*어노테이션 기반으로 변경
        SessionUser user = (SessionUser) httpSession.getAttribute("user");*/

        if(user != null) {
            model.addAttribute("socialUserName", user.getName());
            //윈도우 OS(windows10) 의 경우 userName이 환경변수로 컴퓨터 이름을 지칭하게끔 처리되어 있는데,
            //mustache가 이를 인식하여 컴퓨터 이름이 나옴;; 하여 다른것으로 변경
            //참고 : https://github.com/jojoldu/freelec-springboot2-webservice/issues/293
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
