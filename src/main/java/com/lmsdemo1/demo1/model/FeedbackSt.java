package com.lmsdemo1.demo1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

@Slf4j
@Data
@Entity
@Table(name="feedback")
public class FeedbackSt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name ="native",strategy = "native")
    @Column(name="feedback_id")
    private int Fid;

    @Column(name = "question1")
    private int qone;

    @Column(name = "question2")
    private int qtwo;

    @Column(name = "question3")
    private int qthree;

    @Column(name = "question4")
    private int qfour;

    @Column(name = "question5")
    private int qfive;

    @Column(name = "question6")
    private int qsix;

    @Column(name = "feedback_desc")
    private String desc;

    @Column(name = "feedback_status")
    private String status;

    @Column(name = "feedback_date")
    private String date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = RegisterSth.class)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    private RegisterSth registerSth;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = RegisterTe.class)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id", nullable = false)
    private RegisterTe registerTe;

}
