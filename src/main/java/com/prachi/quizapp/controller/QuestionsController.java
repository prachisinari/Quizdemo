package com.prachi.quizapp.controller;

import com.prachi.quizapp.bean.Question;
import com.prachi.quizapp.service.QuestionService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionsController {
    QuestionService questionService;

    public QuestionsController(QuestionService service){
        this.questionService=service;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {

            return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
            return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
