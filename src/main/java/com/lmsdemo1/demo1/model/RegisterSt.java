package com.lmsdemo1.demo1.model;


import com.lmsdemo1.demo1.annotation.FieldsValueMatch;
import com.lmsdemo1.demo1.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;


@FieldsValueMatch.List({
        @FieldsValueMatch(field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(field = "email",
                fieldMatch = "email",
                message = "Email addresses do not match!"
        )
})
@Data
@Entity
@Table(name="Registeration")
public class RegisterSt extends BaseEntity {
    @NotBlank(message="First Name Must not be blank")
    @Size(min = 3, message = "First name must be at least 3 chars long")
    @Column(name="firstname")
    private String fname;

    @NotBlank(message = "Last Name Must not be blank")
    @Size(min = 3, message = " Last name must be at least 3 chars long" )
    @Column(name="lastname")
    private String lname;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="Id")
    private int id;


    @NotBlank(message = "Email ID Must not be blank")
    @Email(message = "please provide a valid email address")
    @Column(name="email")
    private String email;


    @NotBlank(message = "Password Must not be blank")
    @Size(min = 6, message = "Password must be at least 6 chars long" )
    @PasswordValidator
    private String pwd;


    @NotBlank(message = "Confrim Password Must not be blank")
    @Size(min = 6, message = "confirm Password must be at least 6 chars long" )
    @Transient
    private String confirmPwd;



    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Column(name="Mobile")
    private String mob;

//    @Transient
//    private String pr;

    @NotBlank(message = "Please select the gender")
    private String gender;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
    private Roles roles;


    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
    private Address address;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "classid",referencedColumnName = "classId", nullable = true)
    private Classroom classroom;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "registeration_coursecard",
            joinColumns = {
                    @JoinColumn(name = "Id", referencedColumnName = "Id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "cc_id", referencedColumnName = "cc_id")})
    private Set<Coursecard> coursecard = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "registeration_book",
            joinColumns = {
                    @JoinColumn(name = "Id", referencedColumnName = "Id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "addbookid", referencedColumnName = "addbookid")})
    private Set<AddBookModel> addBookModel = new HashSet<>();

    @OneToMany(mappedBy = "registerSt", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Issued.class)
    private Set<Issued> issuedr;


}
