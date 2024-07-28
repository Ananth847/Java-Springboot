package HRMS.HRManagementSystem.Controller;

import HRMS.HRManagementSystem.Entity.PerformanceReview;
import HRMS.HRManagementSystem.Service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performanceReview")
public class PerformanceReviewController {
    @Autowired
    PerformanceReviewService performanceReviewService;
@PostMapping("/save")
    public ResponseEntity<PerformanceReview> savePerformanceReview(@RequestBody PerformanceReview performanceReview) {
        try {
            PerformanceReview savedReview = performanceReviewService.saveperformanceReview(performanceReview);
            return ResponseEntity.ok(savedReview);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/getreviews")
    public ResponseEntity<List<PerformanceReview>> getAllPerformanceReview() {
        return new ResponseEntity<>(performanceReviewService.getAllPerformanceReview(),HttpStatus.OK);
    }
    @PostMapping("/updatereview/{id}")
    public ResponseEntity<PerformanceReview> update(@PathVariable long id, @RequestBody PerformanceReview performanceReview) {
    try {
        return new ResponseEntity<>(performanceReviewService.update(id, performanceReview), HttpStatus.OK);
    }
    catch (RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@RequestBody long id) {
     try {
         performanceReviewService.deleteById(id);
        return "Deleted Successfully";
     }
     catch (RuntimeException e){
         throw new RuntimeException("Employee not found for id: " + id);
     }
    }
    @GetMapping("emp/{id}")
    public PerformanceReview getById(@PathVariable long id){
    return performanceReviewService.getById(id);
    }
}
