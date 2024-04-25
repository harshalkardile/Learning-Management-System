package com.lmsdemo1.demo1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "coursecard")
public class Coursecard extends BaseEntity {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int cc_id;

    @NotBlank(message="Image field  Must not be blank")
    @Size(min = 3,max=20, message = "Field must be at least 3 chars long and max 20")
    private  String ccimage;

    @NotBlank(message="Title Field Must Not Be Balnk")
    @Size(min = 3,max=50, message = "Fielde must be at least 3 chars long and max is 50")
    private String cctitle;

    @NotBlank(message="Discription must bot be Blank Must not be blank")
    @Size(min = 3,max=500, message = "Field must be at least 3 chars long and 500 is max")
    private String ccdescrip;

    @NotBlank(message="Link Field Must not be blank")
    @Size(min =3,max=50, message = "Field size is to short or  to Long")
    private String cclink;

    @NotBlank(message="Link Field Must not be blank")
    @Size(min =3,max=50, message = "Field size is to short or  to Long")
    private String videolink;

    @NotBlank(message="Link Field Must not be blank")
    @Size(min =3,max=50, message = "Field size is to short or  to Long")
    private String pdflink;


    @ManyToMany(mappedBy = "coursecard", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Set<RegisterSt> registerSts = new HashSet<>();



}
