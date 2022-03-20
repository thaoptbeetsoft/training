package com.trainingfresher.sampleservice.model.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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
    private boolean enable;

    @OneToMany
    private List<Task> tasks = new ArrayList<>();

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
