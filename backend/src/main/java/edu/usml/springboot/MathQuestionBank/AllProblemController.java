package edu.usml.springboot.MathQuestionBank;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/prob")
@CrossOrigin(origins="http://localhost:8081")
public class AllProblemController {
	
	@Autowired
	private ProbRepository probRepository;
	
	public ProbRepository getProbRepository() {
		return probRepository;
	}
	
	@GetMapping("/getall")
	public Iterable<Problem> getAllProblemsInJSON() {
		return probRepository.findAll();
	}
	
	@GetMapping("/get/{pid}")
	public Optional<Problem> getProblemInJSON(@PathVariable("pid") Integer id) {
		return probRepository.findById(id);
	}
	
//	@PostMapping("/getall")
//	public Problem addProblem(@RequestBody Problem newProblem) {
//		return probRepository.save(newProblem);
//	}
	
	@RequestMapping(value = "/post/{title}/{content}", method = RequestMethod.POST)
	public void addProblem(@PathVariable("title") String title, @PathVariable("content") String content) {
		
		// Get a valid pid first
		Iterable<Problem> problist = probRepository.findAll();
		int min = 100000;
		int max = 0;
		int gap = 0;
		for (Problem myprob : problist) {
			if (myprob.getId() < min) {
				min = myprob.getId();
			} 
			if (myprob.getId() > max) {
				max = myprob.getId();
			}
		}
		for (int i = min + 1; i < max; i++) {
			if (!probRepository.existsById(i)) {
				gap = i;
				break;
			}
		}
		
		if (gap == 0) {
			gap = max + 1;
		}
		
		Problem prob = new Problem();
		prob.setId(gap);
		prob.setTitle(title);
		prob.setContent(content);
		probRepository.save(prob);
	}
	
	@PutMapping(value="get/{pid}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Problem updateProblem(@RequestBody Problem newProblem, @PathVariable("pid") int id) {
		return probRepository.findById(id).map(problem -> {
			problem.setTitle(newProblem.getTitle());
			problem.setContent(newProblem.getContent());
			return probRepository.save(problem);
		}).orElseGet(() -> {
			newProblem.setId(id);
			return probRepository.save(newProblem);
		});
	}
	
//	@RequestMapping(value = "/put/{pid}/{title}/{content}", method = RequestMethod.PUT)
//	public void updateProblem(@PathVariable("pid") Integer id, @PathVariable("title") String title,
//			@PathVariable("content") String content) {
//		
//		Problem prob = new Problem();
//		prob.setId(id);
//		prob.setTitle(title);
//		prob.setContent(content);
//		probRepository.save(prob);
//	}
	
//	@DeleteMapping("/get/{pid}")
//	public void removeProblem(@PathVariable int id){
//		probRepository.deleteById(id);
//	}
	@RequestMapping(value = "/del/{pid}", method = RequestMethod.DELETE)
	public void removeProblem(@PathVariable("pid") Integer id) {
		probRepository.deleteById(id);
	}

}
