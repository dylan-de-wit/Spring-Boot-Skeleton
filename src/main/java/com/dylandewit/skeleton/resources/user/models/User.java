package com.dylandewit.skeleton.resources.user.models;

import com.dylandewit.skeleton.resources.base.models.BaseModel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}