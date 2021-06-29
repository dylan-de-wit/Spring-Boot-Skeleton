package com.dylandewit.skeleton.integration;

import com.dylandewit.skeleton.api.base.models.BaseModel;
import com.dylandewit.skeleton.api.permission.models.Permissions;
import com.dylandewit.skeleton.integration.documentation.BaseDocumentation;
import com.dylandewit.skeleton.integration.helpers.IntegrationSetupHelper;
import com.dylandewit.skeleton.integration.seeders.TestSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

public abstract class BaseIntegrationTest<T extends BaseModel> {
//
//    @MockBean
//    protected Med4allUserDetailService userDetailService;

    @Autowired
    protected IntegrationSetupHelper helper;

    protected TestSeeder<T> seeder;

    protected BaseDocumentation documentation;

    protected List<Permissions> permissions;

    protected String username;

    protected String baseUri = "/api";

    public BaseIntegrationTest(List<Permissions> permissions, String username, TestSeeder<T> seeder, BaseDocumentation documentation) {
        this.permissions = permissions;
        this.username = username;
        this.baseUri += "/" + username;
        this.seeder = seeder;
        this.documentation = documentation;
    }

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        helper.setup(webApplicationContext, restDocumentation, permissions, username);
    }

    public void get_single() {
        T t = seeder.seedOne();

        helper.performGet(baseUri + "/{id}", t.getId(), documentation.buildSingle());
    }

    public void get_list() {
        seeder.seedMultiple();

        helper.performGet(baseUri, documentation.buildList());
    }

    public void put_update(Object id, Map<String, Object> body) {
        helper.performPut(baseUri + "/{id}", id, body, documentation.buildUpdate());
    }

    public void post_create(Map<String, Object> body) {
        helper.performPost(baseUri, body, documentation.buildCreate());
    }

    public void delete_item(Object id) {
        helper.performDelete(baseUri + "/{id}", id, documentation.buildDeactivate());
    }
}
