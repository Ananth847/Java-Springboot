package HRMS.HRManagementSystem.DTO;
import HRMS.HRManagementSystem.Entity.Department;
import HRMS.HRManagementSystem.Entity.PerformanceReview;
import HRMS.HRManagementSystem.Entity.Role;
import lombok.*;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private Date hireDate;
        private Department department;
        private Role role;

    }

