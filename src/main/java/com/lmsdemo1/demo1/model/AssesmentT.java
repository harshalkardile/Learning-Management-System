package com.lmsdemo1.demo1.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "Assesment")
public class AssesmentT extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name ="native",strategy = "native")
    @Column(name="assessment_id")
    private int Aid;

    @Column(name = "assesment_sub")
    private String ASubject;

    @Column(name = "assesment_topic")
    private String Atopic;

    @Column(name = "assessment_stdate")
    private String sdate;

    @Column(name = "assessment_endate")
    private String edate;

    @Column(name = "assessment_link")
    private String Alink;

    @Column(name = "assessment_duration")
    private String duration;

    @Column(name = "assesment_desc")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id", nullable = true)
    private RegisterTe registerTe;

}
