package QuizApplication.example.QuizApplication.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
public class Quiz{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToMany
    private List<Question> question;
}
