package QuizApplication.example.QuizApplication.Controller;

import QuizApplication.example.QuizApplication.Entity.Question;
import QuizApplication.example.QuizApplication.Entity.QuestionWrapper;
import QuizApplication.example.QuizApplication.Entity.Quiz;
import QuizApplication.example.QuizApplication.Entity.Response;
import QuizApplication.example.QuizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam("category") String category,
                                             @RequestParam("numQ") int numQ,
                                             @RequestParam("title") String title) {
        quizService.createQuiz(category, numQ, title);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable  Long id) {
        return new ResponseEntity<>(quizService.getQuizQuestions(id),HttpStatus.OK);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}



