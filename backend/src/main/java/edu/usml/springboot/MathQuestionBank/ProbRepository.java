package edu.usml.springboot.MathQuestionBank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:8081")
public interface ProbRepository extends JpaRepository<Problem, Integer> {

}
