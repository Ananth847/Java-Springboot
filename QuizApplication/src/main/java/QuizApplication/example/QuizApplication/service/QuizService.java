package QuizApplication.example.QuizApplication.service;

import QuizApplication.example.QuizApplication.Entity.Question;
import QuizApplication.example.QuizApplication.Entity.QuestionWrapper;
import QuizApplication.example.QuizApplication.Entity.Quiz;
import QuizApplication.example.QuizApplication.Entity.Response;
import QuizApplication.example.QuizApplication.Repository.QuestionRepo;
import QuizApplication.example.QuizApplication.Repository.QuizRepo;
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
      QuizRepo quizRepo;
     @Autowired
     QuestionRepo questionRepo;
       public String createQuiz(String categorey,int numQ,String title){
           List<Question> questions = questionRepo.findRandomQuestionsByCategory(categorey,numQ);
            Quiz quiz = new Quiz();
               quiz.setTitle(title);
               quiz.setQuestion(questions);
               quizRepo.save(quiz);
               return "success";
       }
    public List<QuestionWrapper> getQuizQuestions(Long id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestion();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),
                    q.getQuestionTitle(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return questionsForUser;

    }
    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestion();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
