package com.angel.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instrument")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "instrumentId")
public class Instrument {

    @Id
    @Column(name = "ID")
    private final String instrumentId;

    @ManyToMany(mappedBy = "instruments", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Set<Singer> singers = new HashSet<>();

    public void addSinger(Singer singer) {
        this.singers.add(singer);
        singer.getInstruments().add(this);
    }

    public void removeSinger(Singer singer) {
        this.singers.remove(singer);
        singer.getInstruments().remove(this);
    }

    public void delete() {
        this.singers.stream()
                .forEach((Singer singer) -> removeSinger(singer));
    }
}
