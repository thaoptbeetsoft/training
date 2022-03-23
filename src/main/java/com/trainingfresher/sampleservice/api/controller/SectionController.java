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
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("/project/{id}")
    public ResponseEntity<List<Section>> findAllByProjectId(@PathVariable Long _id){
        List<Section>sectionList=sectionService.findAllByProjectId(_id);
        if(sectionList.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sectionList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Section>findById(@PathVariable Long _id){
        Optional<Section>section = sectionService.findById(_id);
        if(!section.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(section.get(),HttpStatus.OK);
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<Section>save(@PathVariable Long _projectId,@RequestBody Section _section){
        if(_section.getName().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        sectionService.save(_section);
        return new ResponseEntity<>(_section, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> edit (@PathVariable Long _id, @RequestBody Section _section){
        Optional<Section>sectionOptional = sectionService.findById(_id);
        if(!sectionOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        _section.setId(_id);
        sectionService.save(_section);
        return new ResponseEntity<>(_section,HttpStatus.OK);
    }

}
