package com.sjs.exam.jpaBoard.user.controller;


import com.sjs.exam.jpaBoard.article.domain.Article;
import com.sjs.exam.jpaBoard.user.dao.UserRepository;
import com.sjs.exam.jpaBoard.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/usr/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @RequestMapping("join")
    public String join() {
        return "usr/user/join";
    }
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
    @RequestMapping("modify")
    public String showModify(long id, Model model) {
        Optional<User> opuser = userRepository.findById(id);
        User user = opuser.get();

        model.addAttribute("user",user);

        return "usr/user/modify";
    }
    @RequestMapping("doModify")
    @ResponseBody
    public String doModify(long id, String name, String password,String email,HttpSession session) {
        boolean isLogined = false;
        long loginedUserId = 0;

        if (session.getAttribute("loginedUserId") != null) {
            isLogined = true;
            loginedUserId = (long) session.getAttribute("loginedUserId");
        }

        if (isLogined == false) {
            return """
                    <script>
                    alert('로그인 후 이용해주세요.');
                    history.back();
                    </script>
                    """;
        }
        User user = userRepository.findById(id).get();
        if (name != null) {
            user.setName(name);
        }

        if (password != null) {
            user.setPassword(password);
        }
        if (email != null) {
            user.setEmail(email);
        }


        user.setUpdateDate(LocalDateTime.now());

        userRepository.save(user);

        return """
                <script>
                alert('%d번 회원의 정보가 수정되었습니다.');
                location.replace('me?id=%d');
                </script>
                """.formatted(user.getId(), user.getId());
    }


    @RequestMapping("login")
    public String Login() {
        return "usr/user/login";
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
