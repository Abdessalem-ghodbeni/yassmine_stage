package com.nejib.authentifcation_verif_email.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    String nom;
    @Column(unique=true)
    String email;
    String passwordResetToken;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String image;
    private String verificationCode;
    private boolean verified = false;
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Set<Questions> questions;
@JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private Set<Reponse> reponses;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
