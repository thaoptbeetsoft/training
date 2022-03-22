package com.trainingfresher.sampleservice.model.entity;
import lombok.Data;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "section")
@Data
public class Section{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
