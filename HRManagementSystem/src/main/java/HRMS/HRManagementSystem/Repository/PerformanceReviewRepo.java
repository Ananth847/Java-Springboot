package HRMS.HRManagementSystem.Repository;

import HRMS.HRManagementSystem.Entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReviewRepo extends JpaRepository<PerformanceReview,Long> {
}
