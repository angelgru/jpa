package com.angel.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Slf4j
public class Singer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private final String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private final String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToMany(
            mappedBy = "singer",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE,
                    CascadeType.MERGE},
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "singer_instrument",
            joinColumns = {@JoinColumn(name = "SINGER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "INSTRUMENT_ID", referencedColumnName = "ID")}
    )
    private Set<Instrument> instruments = new HashSet<>();

    public void addAlbum(Album album) {
        log.error("ALBUM 1" + album.getTitle());
        album.setSinger(this);
        this.albums.add(album);
    }

    public void removeAlbum(Album album) {
        this.albums.remove(album);
        album.setSinger(null);
    }

    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
        log.error("SINGER BEFORE" + instrument.getInstrumentId());
        instrument.getSingers().add(this);
        log.error("SINGER " + instrument.getInstrumentId());
    }

    public void removeInstrument(Instrument instrument) {
        this.instruments.remove(instrument);
        instrument.getSingers().remove(this);
    }

    public void remove(){
        this.instruments.forEach((Instrument ins) -> removeInstrument(ins));
    }
}
