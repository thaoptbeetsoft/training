package com.trainingfresher.sampleservice.model.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter

@Setter
public class Project {
    public Project() {

    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
