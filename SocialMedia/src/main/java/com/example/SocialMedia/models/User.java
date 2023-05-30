package com.example.SocialMedia.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Automatically generated id of the user")
    private int id;

    @OneToMany(mappedBy = "owner")
    private List<Post> posts;

    @Column(name = "fullName")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @ApiModelProperty(notes = "Full name of the user for logging in")
    private String fullName;

    @Column(name = "email")
    @Email(regexp = ".+[@].+[\\.].+")
    @ApiModelProperty(notes = "Email of the user for logging in")
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "Roles that user is going to have")
    List<Role> roles;

    @Column(name = "password")
    @ApiModelProperty(notes = "Password of the user for logging in")
    private String password;

    public User() {

    }
}
