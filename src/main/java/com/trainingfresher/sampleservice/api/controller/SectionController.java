package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.service.Section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
@CrossOrigin("/*")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("/project/{id}")
    public ResponseEntity<List<Section>> findAllByProjectId(@PathVariable Long id){
        List<Section>sectionList=sectionService.findAllByProjectId(id);
        if(sectionList.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sectionList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Section>findById(@PathVariable Long id){
        Optional<Section>section = sectionService.findById(id);
        if(!section.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(section.get(),HttpStatus.OK);
    }
}
