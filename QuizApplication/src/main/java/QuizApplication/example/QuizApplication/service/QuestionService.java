package QuizApplication.example.QuizApplication.service;

import QuizApplication.example.QuizApplication.Entity.Question;
import QuizApplication.example.QuizApplication.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;
    public Question saveData(Question question) {
        return questionRepo.save(question);
    }
    public List<Question> getAllQuestions(){
        return questionRepo.findAll();
    }
    public List<Question> getQuestionbyCategorey(String category){
        return questionRepo.findByCategory(category);
    }
}
