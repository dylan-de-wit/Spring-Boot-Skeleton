package com.dylandewit.skeleton.api.user.models;

import com.dylandewit.skeleton.api.base.models.BaseModel;
import com.dylandewit.skeleton.api.role.models.Role;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE users SET active = false WHERE id = ?")
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class User extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;

    @Column(unique = true)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
