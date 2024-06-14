package QuizApplication.example.QuizApplication.Repository;

import QuizApplication.example.QuizApplication.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Long> {

}
