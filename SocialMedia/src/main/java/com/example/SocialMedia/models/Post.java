package com.example.SocialMedia.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "This is the auto generated post id")
    private Integer id;

    @ApiModelProperty(notes = "Userid of the person who posted the post")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @NotNull
    @Size(max = 100)
    @Column(name = "title")
    @ApiModelProperty(notes = "Heading or title of the post")
    private String title;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @NotNull
    @Size(max = 250)
    @ApiModelProperty(notes = "The main text content of the post")
    private String description;

    @NotNull
    @Lob
    @ApiModelProperty(notes = "The url of the content")
    @Column(columnDefinition = "TEXT", name = "content")
    private String content;

    public Post() {
    }

}
