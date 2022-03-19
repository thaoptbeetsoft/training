package com.trainingfresher.sampleservice.model.entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "comments")
@Data
@Builder
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String text;

    @Column
    private Date date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Task task;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToOne(mappedBy = "parentComment")
    private Comment subComment;


}
