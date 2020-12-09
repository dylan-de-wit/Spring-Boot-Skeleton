package com.dylandewit.skeleton.integration;

import com.dylandewit.skeleton.integration.documentation.UserDocumentation;
import com.dylandewit.skeleton.integration.seeders.UserTestSeeder;
import com.dylandewit.skeleton.resources.permission.models.Permissions;
import com.dylandewit.skeleton.resources.user.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class UserIntegrationTest extends BaseIntegrationTest<User> {
    private static final String username = "users";
    private static final List<Permissions> permissions = Permissions.forCategory("USERS");

    @Autowired
    public UserIntegrationTest(UserTestSeeder seeder, UserDocumentation documentation) {
        super(permissions, username, seeder, documentation);
    }

    @Test
    @WithMockUser(value = username)
    public void get_single() {
        super.get_single();
    }

    @Test
    @WithMockUser(value = username)
    public void get_list() {
        super.get_list();
    }

    @Test
    @WithMockUser(value = username)
    public void put_update(Object id, Map<String, Object> body) {
        super.put_update(id, body);
    }

    @Test
    @WithMockUser(value = username)
    public void post_create(Map<String, Object> body) {
        super.post_create(body);
    }

    @Test
    @WithMockUser(value = username)
    public void delete_item(Object id) {
        super.delete_item(id);
    }
}

