package com.lmsdemo1.demo1.model;


import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="registeration_book")
public class Issued  extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int ibid;




    @NotBlank(message="First Name Must not be blank")
    @Size(min = 3, message = "First name must be at least 3 chars long")
    private String firstname;

    @Size(min = 10, message = "Field name must be at least 10 chars long")
    @Column(name="addressi")
    private String addressi;

    @NotBlank(message="book name Must not be blank")
    @Size(min = 3, message = "Field must be at least 3 chars long")
    private String bookname;


    @NotBlank(message="book name Must not be blank")
    @Size(min = 3, message = "Field must be at least 3 chars long")
    private String startdate;

    @NotBlank(message="book name Must not be blank")
    @Size(min = 3, message = "Field must be at least 3 chars long")
    private String enddate;

    @NotBlank(message="book name Must not be blank")
    @Size(min = 3, message = "Field must be at least 3 chars long")
    private String blink;

    @NotBlank(message="fine  Must be Greater Than Zero")
    @Size(min = 3, message = "Field must be at least 3 chars long")
    private String fine;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "Id", referencedColumnName = "Id", nullable = true)
    private RegisterSt registerSt;


    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "addbookid", referencedColumnName = "addbookid", nullable = true)
    private AddBookModel addBookModel;

}
