package com.dylandewit.skeleton.integration.documentation;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@TestComponent
public class UserDocumentation extends BaseDocumentation {
    public UserDocumentation() {
        super("users");
    }

    @Override
    protected List<FieldDescriptor> buildDocumentation() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();
        fieldDescriptors.add(fieldWithPath("id"));

        return fieldDescriptors;
    }

    @Override
    protected List<FieldDescriptor> buildIgnores() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();

        return fieldDescriptors;
    }

    @Override
    protected List<FieldDescriptor> buildUpdateRequest() {
        return buildShared();
    }

    @Override
    protected List<FieldDescriptor> buildCreateRequest() {
        return buildShared();
    }

    @Override
    protected List<FieldDescriptor> buildShared() {
        return new ArrayList<>();
    }
}
