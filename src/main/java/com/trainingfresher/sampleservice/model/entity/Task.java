package com.trainingfresher.sampleservice.model.entity;
import com.fasterxml.jackson.annotation.*;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Table(name = "tasks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

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

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "section_id")
    private Section section;

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

    @JsonIgnore
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public  TaskDto toDto() {
        return  TaskDto.builder()
                .name(name)
                .startDay(startDay)
                .endDay(endDay)
                .type(type)
                .priority(priority)
                .jobDescription(jobDescription)
                .status(status)
                .assignee(name)
                .projectName(projectName)
                .section(section)
                .project(project)
                .comments(comments)
                .subTask(subTask)
                .histories(histories)
                .build();
    }
}
