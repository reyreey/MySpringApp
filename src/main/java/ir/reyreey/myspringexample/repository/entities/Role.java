package ir.reyreey.myspringexample.repository.entities;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long id;
    @Column(name = "role_name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    public Role() {
    }

    public Role(String name, Set<Authority> authorities) {
        this.name = name;
        this.authorities = authorities;
    }

    public Role(long id, Set<Authority> authorities, String name) {
        this.id = id;
        this.authorities = authorities;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
