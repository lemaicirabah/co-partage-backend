package com.evaluationservice.controller;

import com.evaluationservice.service.EvaluationService;
import com.evaluationservice.dto.EvaluationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<EvaluationDto> createEvaluation(@RequestBody EvaluationDto evaluationDto) {
        EvaluationDto createdEvaluation = evaluationService.createEvaluation(
                evaluationDto.getEvaluatorId(),
                evaluationDto.getEvaluateeId(),
                evaluationDto.getProjectId(),
                evaluationDto.getComments(),
                evaluationDto.getRating()
        );
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationDto> getEvaluation(@PathVariable Long id) {
        EvaluationDto evaluation = evaluationService.getEvaluation(id);
        return new ResponseEntity<>(evaluation, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationDto> updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDto evaluationDto) {
        EvaluationDto updatedEvaluation = evaluationService.updateEvaluation(id, evaluationDto);
        return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<EvaluationDto>> getAllEvaluations() {
        List<EvaluationDto> evaluations = evaluationService.getAllEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
}

