package com.lmsdemo1.demo1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="coursedetial")
public class cdvideo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int cdid;

    @NotBlank(message=" Field  must not be blank")
    private String ccvideo;

    @Lob
    private byte[] data;



}
