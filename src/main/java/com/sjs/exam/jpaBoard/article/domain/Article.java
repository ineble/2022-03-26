package com.sjs.exam.jpaBoard.article.domain;

<<<<<<< HEAD
import com.sjs.exam.jpaBoard.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String title;
    private String body;
<<<<<<< HEAD
    @ManyToOne
    private User user;
=======
    private long user_id;
>>>>>>> b9f349d8275fc2badb7dec0ee5378a54f60031b7
}
