package HRMS.HRManagementSystem.Service;

import HRMS.HRManagementSystem.Entity.Employee;
import HRMS.HRManagementSystem.Entity.PerformanceReview;
import HRMS.HRManagementSystem.Repository.EmployeeRepo;
import HRMS.HRManagementSystem.Repository.PerformanceReviewRepo;
import HRMS.HRManagementSystem.ServiceImp.performanceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PerformanceReviewService implements performanceServiceImp {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    PerformanceReviewRepo performanceReviewRepo;
    @Override
    public PerformanceReview saveperformanceReview(PerformanceReview performanceReview){
        Long employeeId = performanceReview.getEmployee().getId();
        Optional<Employee> employee = employeeRepo.findById(employeeId);

        if(employee.isPresent()){
            performanceReview.setEmployee(employee.get());
        } else {
            throw new RuntimeException("Employee not found");
        }

        return performanceReviewRepo.save(performanceReview);
    }

    @Override
    public List<PerformanceReview> getAllPerformanceReview() {
        return performanceReviewRepo.findAll();
    }

    @Override
    public PerformanceReview update(long id, PerformanceReview performanceReview) {
        Optional<PerformanceReview> performanceReview1 = performanceReviewRepo.findById(id);
        if(performanceReview1.isPresent()){
            PerformanceReview review=performanceReview1.get();
            review.setReviewDate(performanceReview.getReviewDate());
            review.setComments(performanceReview.getComments());
            review.setRating(performanceReview.getRating());
            review.setEmployee(performanceReview.getEmployee());
            return performanceReviewRepo.save(review);
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }

    @Override
    public PerformanceReview getById(long id) {
        Optional<PerformanceReview> performanceReview = performanceReviewRepo.findById(id);
        if(performanceReview.isPresent()){
            return performanceReview.get();
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }

    @Override
    public String deleteById(long id) {
        Optional<PerformanceReview> performanceReview = performanceReviewRepo.findById(id);
        if(performanceReview.isPresent()){
            performanceReviewRepo.deleteById(id);
            return "Deleted Successfully";
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }
}
