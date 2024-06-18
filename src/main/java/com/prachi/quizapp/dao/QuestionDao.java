package com.prachi.quizapp.dao;

import com.prachi.quizapp.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM questions q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
