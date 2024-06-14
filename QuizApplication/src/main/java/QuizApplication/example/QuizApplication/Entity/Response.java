package QuizApplication.example.QuizApplication.Entity;

import lombok.*;
import org.hibernate.mapping.List;
@Data
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int id;
    private String response;
}
