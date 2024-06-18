package com.prachi.quizapp.dao;

import com.prachi.quizapp.bean.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Quizdao extends JpaRepository<Quiz,Integer> {

}
