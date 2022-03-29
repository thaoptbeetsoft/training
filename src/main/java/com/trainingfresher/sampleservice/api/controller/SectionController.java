package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.model.dto.SectionDto;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.service.impl.SectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sections")
public class SectionController {
    @Autowired
    private SectionServiceImpl sectionService;

    @GetMapping("/project/{id}")
    public ResponseEntity<List<SectionDto>> findAllByProjectId(@PathVariable Long _id){
        List<Section>sectionList=sectionService.findAllByProjectId(_id);
        if(sectionList.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<SectionDto>sectionDtos= sectionList.stream()
                .map(s -> sectionService.convertSectionToDto(s))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sectionDtos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Section>findById(@PathVariable Long _id){
        Optional<Section>section = sectionService.findById(_id);
        if(!section.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SectionDto sectionDto = sectionService.convertSectionToDto(section.get());
        return new ResponseEntity<>(section.get(),HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>save(@RequestBody SectionDto _sectionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR các trường nhập vào không hợp lệ:\n" +
                            " + Name có độ dài từ 4 đến 30 kí tự \n" +
                            " + ProjectId là số nguyên dương\n");
        }
        Section section =  sectionService.convertDtoToSection(_sectionDto);
        sectionService.save(section);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> edit (@PathVariable Long _id, @RequestBody SectionDto _sectionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR các trường nhập vào không hợp lệ:\n" +
                            " + Name có độ dài từ 4 đến 30 kí tự \n" +
                            " + ProjectId là số nguyên dương\n");
        }
        Optional<Section>sectionOptional = sectionService.findById(_id);
        if(!sectionOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Section section =  sectionService.convertDtoToSection(_sectionDto);
        section.setId(_id);
        sectionService.save(section);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
