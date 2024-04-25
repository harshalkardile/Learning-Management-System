package com.lmsdemo1.demo1.model;


import com.lmsdemo1.demo1.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name="student")
public class  RegisterSth extends BaseEntity {
//    @NotBlank(message = " Teacher ID Must not be blank")
//    @Size(min =6, message=" ID must be at least 6 digits long ")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name ="native",strategy = "native")
    @Column(name="student_id")
    private int id;

    @NotBlank(message="First Name Must not be blank")
    @Size(min = 3, message = "First name must be at least 3 chars long")
    @Column(name="Sfirstname")
    private String fname;

    @NotBlank(message = "Last Name Must not be blank")
    @Size(min = 3, message = " Last name must be at least 3 chars long" )
    @Column(name="Slastname")
    private String lname;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Column(name="Smobno")
    private String mob;

    @NotBlank(message = "Email ID Must not be blank")
    @Email(message = "please provide a valid email address")
    @Column(name="Smailid")
    private String email;

    @NotBlank(message = "Please select the gender")
    @Column(name="Sgender")
    private String gender;

    @Column(name="Password")
    private String pr;

    @OneToMany(mappedBy = "registerSth", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = ClassworkT.class)
    private Set<ClassworkT> classworkT;

    @ManyToMany(mappedBy = "registerSth", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Set<RegisterTe> registerTe = new HashSet<>();
}
