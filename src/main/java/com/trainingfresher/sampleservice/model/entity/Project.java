package com.trainingfresher.sampleservice.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
@Data
public class Project {
    public Project() {

    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    @Column
    private Boolean enable;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<Task> tasks = new HashSet<>();

    @JsonIgnore
    @OneToMany
    private List<Section> sections = new ArrayList<>();

    @ManyToMany(mappedBy = "projects")
    private List<Department> departments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "project_team",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

}
