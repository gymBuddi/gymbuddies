package dev.example.kinect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.example.kinect.model.enums.AuthorityEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority extends AbstractEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    @Override
    public String getAuthority() {
        return this.authority.name();
    }
}
