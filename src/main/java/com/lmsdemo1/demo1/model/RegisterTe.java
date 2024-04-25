package com.lmsdemo1.demo1.model;

import com.lmsdemo1.demo1.model.BaseEntity;
import com.lmsdemo1.demo1.model.ClassworkT;
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
@Table(name="teacher")
public class RegisterTe extends BaseEntity {

    @NotBlank(message="First Name Must not be blank")
    @Size(min = 3, message = "First name must be at least 3 chars long")
    @Column(name="Tfirstname")
    private String fnameT;

    @NotBlank(message = "Last Name Must not be blank")
    @Size(min = 3, message = " Last name must be at least 3 chars long" )
    @Column(name="Tlastname")
    private String lnameT;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name ="native",strategy = "native")
    @Column(name="teacher_id")
    private int idT;

    @NotBlank(message = "Email ID Must not be blank")
    @Email(message = "please provide a valid email address")
    @Column(name="Tmailid")
    private String emailT;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Column(name="Tmobno")
    private String mobT;

    @Column(name="TPassword")
    private String rpT;

    @NotBlank(message = "Please select the gender")
    @Column(name="Tgender")
    private String genderT;

    @OneToMany(mappedBy = "registerTe", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = ClassworkT.class)
    private Set<ClassworkT> classworkT;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_student",
            joinColumns = {
                    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "student_id")})
    private Set<RegisterSth> registerSth = new HashSet<>();






}
