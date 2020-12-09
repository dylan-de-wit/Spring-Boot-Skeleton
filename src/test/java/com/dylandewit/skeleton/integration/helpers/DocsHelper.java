package com.dylandewit.skeleton.integration.helpers;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.snippet.Snippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.applyPathPrefix;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class DocsHelper {
    public static RestDocumentationResultHandler buildDocumentHandler(String identifier, Snippet... snippets) {
        return document(identifier,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                snippets
        );
    }

    public static List<FieldDescriptor> buildListIgnores() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();
        fieldDescriptors.add(fieldWithPath("pageable.*").ignored());
        fieldDescriptors.add(fieldWithPath("pageable.sort.*").ignored());
        fieldDescriptors.add(fieldWithPath("totalPages").ignored());
        fieldDescriptors.add(fieldWithPath("totalElements").ignored());
        fieldDescriptors.add(fieldWithPath("last").ignored());
        fieldDescriptors.add(fieldWithPath("number").ignored());
        fieldDescriptors.add(fieldWithPath("sort.*").ignored());
        fieldDescriptors.add(fieldWithPath("numberOfElements").ignored());
        fieldDescriptors.add(fieldWithPath("empty").ignored());
        fieldDescriptors.add(fieldWithPath("size").ignored());
        fieldDescriptors.add(fieldWithPath("first").ignored());

        return fieldDescriptors;
    }

    public static List<FieldDescriptor> buildDefaults() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();
        fieldDescriptors.add(fieldWithPath("id"));
        fieldDescriptors.add(fieldWithPath("active"));
        fieldDescriptors.add(fieldWithPath("createdOn"));
        fieldDescriptors.add(fieldWithPath("updatedOn"));

        return fieldDescriptors;
    }

    public static List<FieldDescriptor> withListPrefix(List<FieldDescriptor> field) {
        return applyPathPrefix("content[].", field);
    }
}
