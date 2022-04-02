package com.sjs.exam.jpaBoard.article.controller;

import com.sjs.exam.jpaBoard.article.dao.ArticleRepository;
<<<<<<< HEAD
import com.sjs.exam.jpaBoard.user.dao.UserRepository;
import com.sjs.exam.jpaBoard.article.domain.Article;
import com.sjs.exam.jpaBoard.user.domain.User;
=======
import com.sjs.exam.jpaBoard.article.domain.Article;
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
<<<<<<< HEAD
    @Autowired
    private UserRepository userRepository;
=======
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7

    @RequestMapping("list")
    @ResponseBody
    public List<Article> articles() {
        return articleRepository.findAll();
    }
<<<<<<< HEAD

=======
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
    @RequestMapping("detail")
    @ResponseBody
    public Article showDetail(long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.get();
    }
<<<<<<< HEAD

=======
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
    @RequestMapping("doDelete")
    @ResponseBody
    public String deleteItem(long id) {
        Optional<Article> article = articleRepository.findById(id);
<<<<<<< HEAD
        if (article.isPresent()) {
=======
        if(article.isPresent()) {
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
            articleRepository.delete(article.get());
        }
        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }
<<<<<<< HEAD

=======
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
    @RequestMapping("doModify")
    @ResponseBody
    public Article showModify(long id, String title, String body) {
        Article article = articleRepository.findById(id).get();
<<<<<<< HEAD
        if (title != null) {
            article.setTitle(title);
        }
        if (body != null) {
=======
        if(title != null) {
            article.setTitle(title);
        }
        if(body != null) {
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
            article.setBody(body);
        }
        article.setUpdateDate(LocalDateTime.now());
        articleRepository.save(article);
        return article;
    }
<<<<<<< HEAD
    @RequestMapping("doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if(title == null || title.trim().length() == 0){
            return "제목을 입력해주세요.";
        }
        title = title.trim();
        if(body == null || body.trim().length() == 0){
            return "내용을 입력해주세요.";
        }
        body = body.trim();
        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);
        User user = userRepository.findById(1L).get();
        article.setUser(user);
        articleRepository.save(article);
        return "%d번 게시물이 등록되었습니다.".formatted(article.getId());
    }
=======


>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7

}
