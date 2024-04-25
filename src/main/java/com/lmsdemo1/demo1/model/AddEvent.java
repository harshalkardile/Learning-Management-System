package com.lmsdemo1.demo1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="addevent")
public class AddEvent extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    @Column(name="addevent_id")
    private int addeventid;




    @Column(name="class_name")
    @NotBlank(message=" Field  must not be blank")
    @Size(min=3, message=" Field must be at least 3 characters long")
    private String class1;


    @NotBlank(message=" Field  must not be blank")
    @Size(min=3, message=" Field must be at least 3 characters long")
    private String topic;

    @Column(name="faculty_name")
    @NotBlank(message=" Field  must not be blank")
    @Size(min=3, message=" Field must be at least 3 characters long")
    private String faculty;

    @NotBlank(message=" Field  must not be blank")
    @Size(min=3, message=" Field must be at least 3 characters long")
    private String duration;

    @Column(name="zoom_link")
    @NotBlank(message=" Field  must not be blank")
    @Size(min=3, message=" Field must be at least 3 characters long")
    private String link;

    private String status;


}
