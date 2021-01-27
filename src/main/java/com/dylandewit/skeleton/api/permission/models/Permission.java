package com.dylandewit.skeleton.api.permission.models;

import com.dylandewit.skeleton.api.base.models.BaseModel;
import com.dylandewit.skeleton.api.role.models.Role;
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
@Table(name = "permissions")
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE permissions SET active = false WHERE id = ?")
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Permission extends BaseModel {
    @Column(unique = true)
    private Permissions name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();
}
