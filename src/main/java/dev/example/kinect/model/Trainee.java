package dev.example.kinect.model;

import dev.example.kinect.listeners.TraineeListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainee", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@EntityListeners(TraineeListener.class)
public class Trainee extends AbstractEntity{
    @Column(unique = true)
    private String email;
    private String password;
    @ToString.Exclude
    @OneToOne(mappedBy = "trainee", cascade = CascadeType.ALL)
    private Profile profile;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trainee_role",
            joinColumns = {@JoinColumn(name = "trainee_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;
    private boolean active;

    public Trainee(String email, String password){
        this.email = email;
        this.password = password;
    }
}
