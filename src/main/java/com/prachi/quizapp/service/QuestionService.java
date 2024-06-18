package com.prachi.quizapp.service;

import com.prachi.quizapp.bean.Question;
import com.prachi.quizapp.controller.QuestionsController;
import com.prachi.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
            try {
                return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
            }
            catch (Exception e){
                e.getMessage();
            }
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e){
            e.getMessage();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success1",HttpStatus.CREATED) ;
        }
        catch (Exception e){
            e.getMessage();
        }
        return new ResponseEntity<>("Question Not Saved",HttpStatus.BAD_REQUEST);


    }
}
