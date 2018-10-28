package com.angel.jpa.security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private final String username;

    @Column(nullable = false)
    private final String password;

    @Column(nullable = false, name = "full_name")
    private final String fullName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
            )
    private final List<Authority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authorities = roles.stream()
                .map((Authority role) -> role.getName())
                .toArray((int size) -> new String[size]);
        return AuthorityUtils.createAuthorityList(authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
