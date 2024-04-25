package com.lmsdemo1.demo1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */





@Data
@Entity
@Table(name="addbook")
public class AddBookModel extends BaseEntity {


    /*
   * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
     @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
     @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
   * */


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int addbookid;

    @NotBlank(message="Title must not be blank")
    @Size(min=3, message="Title must be at least 3 characters long")
    private String title;

    @NotBlank(message="Auther Name must not be blank")
    @Size(min=3, message="Auther must be at least 3 characters long")
    private String auther;

    @NotBlank(message="Publisher  Name must not be blank")
    @Size(min=3, message="Publisher Name must be at least 3 characters long")
    private String publisher;

    @NotBlank(message="Genrec must not be blank")
      private String genre;

    @NotBlank(message="field must not be blank")
    private String image;

    @NotBlank(message="field must not be blank")
    private String book;

    @NotBlank(message="field must not be blank")
    @Pattern(regexp = "^(19|20)\\d{2}$",message="Year  should be Greater Than 1950" )
    private String year;

    private String available;

    @ManyToMany(mappedBy = "addBookModel", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Set<RegisterSt> registerSts = new HashSet<>();


    @OneToMany(mappedBy = "addBookModel", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Issued.class)
    private Set<Issued> issuedb;


}
