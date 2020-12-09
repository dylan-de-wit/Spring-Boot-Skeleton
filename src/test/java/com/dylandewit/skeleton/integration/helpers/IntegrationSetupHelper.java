package com.dylandewit.skeleton.integration.helpers;

import com.dylandewit.skeleton.resources.permission.models.Permissions;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Getter
@Component
public class IntegrationSetupHelper {

    @Autowired
    private PrincipalHelper principalHelper;

    @Autowired
    private ObjectMapper objectMapper;

    private Principal principalMock;

    private MockMvc mockMvc;

    public void setup(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation,
//                      Med4allUserDetailService userDetailService,
                      List<Permissions> permissions,
                      String username) {
        mockMvc = MockMvcHelper.buildMvc(webApplicationContext, restDocumentation);
        PermissionMockHelper.setupMockUser(null, username, permissions);
        principalMock = principalHelper.mockPrincipal(username, permissions);
    }

    @SneakyThrows
    public void performGet(String url, RestDocumentationResultHandler handler) {
        mockMvc.perform(get(url)
                .principal(principalMock))
                .andExpect(status().isOk())
                .andDo(handler);
    }

    @SneakyThrows
    public void performGet(String url, Object id, RestDocumentationResultHandler handler) {
        mockMvc.perform(get(url, id)
                .principal(principalMock))
                .andExpect(status().isOk())
                .andDo(handler);
    }

    @SneakyThrows
    public void performPut(String url, Object id, Map<String, Object> body, RestDocumentationResultHandler handler) {
        mockMvc.perform(put(url, id)
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principalMock)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andDo(handler);
    }

    @SneakyThrows
    public void performPut(String url, Map<String, Object> body, RestDocumentationResultHandler handler) {
        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principalMock)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andDo(handler);
    }

    @SneakyThrows
    public void performPost(String url, Map<String, Object> body, RestDocumentationResultHandler handler) {
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principalMock)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andDo(handler);
    }

    @SneakyThrows
    public void performDelete(String url, Object id, RestDocumentationResultHandler handler) {
        mockMvc.perform(delete(url, id)
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principalMock))
                .andExpect(status().isOk())
                .andDo(handler);
    }
}
