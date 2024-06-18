package com.prachi.quizapp.controller;

import com.prachi.quizapp.bean.QuestionWrapper;
import com.prachi.quizapp.bean.Quiz;
import com.prachi.quizapp.bean.Response;
import com.prachi.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

@PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String cat, @RequestParam int noQ, @RequestParam String title){
       return  quizService.createQuiz(cat, noQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){

    return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
