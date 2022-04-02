package com.sjs.exam.jpaBoard.user.controller;

import com.sjs.exam.jpaBoard.article.dao.ArticleRepository;
import com.sjs.exam.jpaBoard.article.domain.Article;
import com.sjs.exam.jpaBoard.user.dao.UserRepository;
import com.sjs.exam.jpaBoard.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/user")
public class UserController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("doJoin")
    @ResponseBody
    public String doJoin(String name, String email,String password) {
        if(name == null || name.trim().length() == 0){
            return "이름을 입력해주세요.";
        }
        name = name.trim();
        if(email == null || email.trim().length() == 0){
            return "이메일을 입력해주세요.";
        }
        email = email.trim();
        if(password == null || password.trim().length() == 0){
            return "이메일을 입력해주세요.";
        }
        password = password.trim();
        User user = new User();
        user.setRegDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "%d번 회원이 생성되었습니다.".formatted(user.getId());
    }
    @RequestMapping("doDelete")
    @ResponseBody
    public String doDelete(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }
        return "회원이 탈퇴하셨습니다.";
    }



}
