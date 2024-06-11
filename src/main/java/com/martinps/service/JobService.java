package com.martinps.service;

import com.martinps.entity.Job;
import com.martinps.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobRepository.findAll());
    }

    public ResponseEntity<String> createJob(Job job){
        String uniqueId = generateUniqueId();
        job.setId(uniqueId);
        jobRepository.save(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.OK);
    }

    public ResponseEntity<Job> findById(String id){
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null)  return new ResponseEntity<>(job, HttpStatus.OK);
        else return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteJob(String id){
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            jobRepository.deleteById(id);
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateJob(String id, Job job){
        Job aux = jobRepository.findById(id).orElse(null);
        if (aux != null) {
            aux.setId(job.getId());
            aux.setDescription(job.getDescription());
            aux.setLocation(job.getLocation());
            aux.setTitle(job.getTitle());
            aux.setMinSalary(job.getMinSalary());
            aux.setMaxSalary(job.getMaxSalary());
            jobRepository.save(aux);
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
