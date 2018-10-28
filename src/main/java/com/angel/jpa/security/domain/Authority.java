package com.angel.jpa.security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "roles")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private final String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
