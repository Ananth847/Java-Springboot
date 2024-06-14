package QuizApplication.example.QuizApplication.Entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
        private Integer id;
        private String questionTitle;
        private String option1;
        private String option2;
        private String option3;
        private String option4;


}
