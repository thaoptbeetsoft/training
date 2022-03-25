package com.trainingfresher.sampleservice.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "section")
@Data
public class Section{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @JsonIgnore
    @OneToMany(mappedBy = "section")
    private Set<Task> tasks = new HashSet<>();

}