package com.prachi.quizapp.service;

import com.prachi.quizapp.bean.Question;
import com.prachi.quizapp.bean.QuestionWrapper;
import com.prachi.quizapp.bean.Quiz;
import com.prachi.quizapp.bean.Response;
import com.prachi.quizapp.dao.QuestionDao;
import com.prachi.quizapp.dao.Quizdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    Quizdao quizdao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questionList= questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizdao.save(quiz);




        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> byId = quizdao.findById(id);
        List<Question> questionList = byId.get().getQuestions();
        List<QuestionWrapper> questionListForUser = new ArrayList<>();

        for(Question ques : questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper(ques.getId(),ques.getQuestionTitle(),ques.getOption1(),ques.getOption2(),ques.getOption3());
                questionListForUser.add(questionWrapper);
        }
        return new ResponseEntity<>(questionListForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Integer rightAns =0;
        Quiz quiz = quizdao.findById(id).get();
        int i =0;
        List<Question> questions = quiz.getQuestions();
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                rightAns++;

            i++;
        }


        return new ResponseEntity<>(rightAns, HttpStatus.OK);
    }
}
