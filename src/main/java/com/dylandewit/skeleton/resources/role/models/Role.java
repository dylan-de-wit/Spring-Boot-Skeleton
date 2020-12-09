package com.dylandewit.skeleton.resources.role.models;

import com.dylandewit.skeleton.resources.base.models.BaseModel;
import com.dylandewit.skeleton.resources.permission.models.Permission;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE roles SET active = false WHERE id = ?")
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Role extends BaseModel {
    @Column(unique = true)
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Set<Permission> permissions = new HashSet<>();
}
