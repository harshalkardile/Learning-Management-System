package com.lmsdemo1.demo1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;


@Slf4j
@Getter
@Setter
@Entity
@Table(name = "classwork")
public class ClassworkS extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name ="native",strategy = "native")
    @Column(name="classwork_id")
    private int Cid;

    @Column(name = "subject")
    private String Subject;

    @Column(name = "work_topic")
    private String topic;

    @Column(name = "start_date")
    private String sdate;

    @Column(name = "sub_date")
    private String edate;

    @Column(name = "upload_link_T")
    private String link;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = true)
    private RegisterSth registerSth;



}
