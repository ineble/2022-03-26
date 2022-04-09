package com.sjs.exam.jpaBoard.user.controller;

import com.sjs.exam.jpaBoard.article.dao.ArticleRepository;
import com.sjs.exam.jpaBoard.article.domain.Article;
import com.sjs.exam.jpaBoard.user.dao.UserRepository;
import com.sjs.exam.jpaBoard.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/user")
public class UserController {

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
        if(userRepository.existsByEmail(email) == true){
            return "중복된 이메일입니다.";
        }
        if(password == null || password.trim().length() == 0){
            return "비민번호를 입력해주세요.";
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
    @RequestMapping("doLogin")
    @ResponseBody
    public String doLogin(String email, String password, HttpServletRequest req, HttpServletResponse resp){
        if(email == null || email.trim().length() == 0){
            return "이메일을 입력해주세요.";
        }
        email = email.trim();
        if(userRepository.existsByEmail(email) == false){
            return "일치하는 회원이 존재하지 않습니다.";
        }
        User user = userRepository.findByemail(email).get();
        if(password == null || password.trim().length() == 0){
            return "비민번호를 입력해주세요.";
        }
        password = password.trim();

        if(user.getPassword().equals(password) == false) {
            return "비밀번호가 올바르지 않습니다.";
        }
        HttpSession session = req.getSession();
        session.setAttribute("loginedUserId",user.getId() );
        //Cookie cookie = new Cookie("loginedUserId",user.getId()+"");
        //resp.addCookie(cookie);
        return "%s님 환영합니다.".formatted(user.getName());
    }
    @RequestMapping("me")
    @ResponseBody
    public User showMe (HttpSession session) {
        boolean isLogined = false;
        long loginedUserId =  0;
        session.getAttribute("loginedUserId");
        if(session.getAttribute("loginedUserId") != null){
            isLogined = true;
            loginedUserId = (long)session.getAttribute("loginedUserId");
        }

        if(isLogined == false){
            return  null;
        }
        Optional<User> user = userRepository.findById(loginedUserId);
        if(user.isEmpty()){
            return null;
        }
        return user.get();
    }
    @RequestMapping("doLogout")
    @ResponseBody
    public String doLogout (HttpSession session) {
        boolean isLogined = false;
        if(session.getAttribute("loginedUserId") != null){
            isLogined = true;
        }
        if(isLogined == false){
            return  "이미 로그아웃 되었습니다.";
        }
        session.removeAttribute("loginedUserId");
        return "회원이 로그아웃하셨습니다.";
    }
}
