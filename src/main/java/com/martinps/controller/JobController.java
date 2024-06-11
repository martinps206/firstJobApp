package com.martinps.controller;

import com.martinps.entity.Job;
import com.martinps.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return jobService.findAll();
    }

    @PostMapping("/job")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        return jobService.createJob(job);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id){
        return jobService.findById(id);
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable String id){
        return jobService.deleteJob(id);
    }

    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    @PutMapping("/job/{id}")
    public ResponseEntity<String> updateJob(@PathVariable String id, @RequestBody Job job){
        return jobService.updateJob(id, job);
    }

 }
