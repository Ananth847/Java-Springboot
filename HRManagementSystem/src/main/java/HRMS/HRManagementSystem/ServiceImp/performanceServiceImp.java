package HRMS.HRManagementSystem.ServiceImp;

import HRMS.HRManagementSystem.Entity.PerformanceReview;

import java.util.*;

public interface performanceServiceImp {
    public PerformanceReview saveperformanceReview(PerformanceReview performanceReview);
    public List<PerformanceReview> getAllPerformanceReview();
    public PerformanceReview update(long id,PerformanceReview performanceReview);
    public PerformanceReview getById(long id);
    public String deleteById(long id);
}
