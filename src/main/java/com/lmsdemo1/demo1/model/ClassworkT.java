package com.lmsdemo1.demo1.model;

import com.lmsdemo1.demo1.model.BaseEntity;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.model.RegisterTe;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "classwork")
public class ClassworkT extends BaseEntity {

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

    @Column(name = "end_date")
    private String edate;

    @Column(name = "upload_link_T")
    private String link;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = true)
    private RegisterSth registerSth;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id", nullable = true)
    private RegisterTe registerTe;

}
