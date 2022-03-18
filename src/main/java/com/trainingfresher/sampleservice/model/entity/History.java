package com.trainingfresher.sampleservice.model.entity;


import lombok.Data;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "history")
@Data
public class History {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String text;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
