package com.angel.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "album")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private final String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "SINGER_ID", referencedColumnName = "ID")
    private Singer singer;

}
