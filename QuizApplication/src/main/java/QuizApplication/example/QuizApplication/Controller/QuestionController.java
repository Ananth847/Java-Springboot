package QuizApplication.example.QuizApplication.Controller;

import QuizApplication.example.QuizApplication.Entity.Question;
import QuizApplication.example.QuizApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @PostMapping("/save/data")
    public ResponseEntity<Question> saveData(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.saveData(question),HttpStatus.OK);
    }
    @GetMapping("/questions/getall")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }
    @GetMapping("/get/{categorey}")
    public ResponseEntity<List<Question>> getByCategorey(@PathVariable String categorey){
        return new ResponseEntity<>(questionService.getQuestionbyCategorey(categorey),HttpStatus.OK);
    }

}
