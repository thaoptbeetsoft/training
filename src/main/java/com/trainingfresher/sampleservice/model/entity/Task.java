package com.trainingfresher.sampleservice.model.entity;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import com.trainingfresher.sampleservice.utils.constants.Constant;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User assignee;

    @Column(name = "start_day")
    private Date startDay;

    @Column(name = "end_day")
    private Date endDay;

    @Column
    private String type;

    @Column(name = "project_name")
    private String projectName;

    @Column
    private String priority;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne()
    @JoinColumn(name = "section_id")
    private Section  section;

    @Column(name = "job_description")
    private String jobDescription;

    @Column
    private String status;

    @OneToMany()
    private List<History> histories =  new ArrayList<>();

    @OneToOne()
    @JoinColumn(name = "parent_id")
    private Task parent;

    @OneToMany(mappedBy = "parent")
    private List<Task> subTask;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}
