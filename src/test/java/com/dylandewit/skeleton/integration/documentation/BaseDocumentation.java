package com.dylandewit.skeleton.integration.documentation;

import com.dylandewit.skeleton.integration.helpers.DocsHelper;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.snippet.Snippet;

import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public abstract class BaseDocumentation {

    protected String docName;
    protected boolean buildDefaults;

    protected BaseDocumentation(String docName, boolean buildDefaults) {
        this.docName = docName;
        this.buildDefaults = buildDefaults;
    }

    protected BaseDocumentation(String docName) {
        this(docName, true);
    }

    public RestDocumentationResultHandler buildSingle() {
        Snippet[] snippets = {
                pathParameters(parameterWithName("id").description(docName + " id")),
                responseFields(buildDocumentation()).and(buildIgnores())
        };

        addDefaultsToLast(snippets, false);

        return buildCustom("get", "single", snippets);
    }

    public RestDocumentationResultHandler buildList() {
        Snippet[] snippets = {
                responseFields(DocsHelper.withListPrefix(buildDocumentation()))
                        .and(DocsHelper.withListPrefix(buildIgnores()))
                        .and(DocsHelper.buildListIgnores())
        };

        addDefaultsToLast(snippets, true);

        return buildCustom("get", "list", snippets);
    }


    public RestDocumentationResultHandler buildUpdate() {
        Snippet[] snippets = {
                pathParameters(parameterWithName("id").description(docName + " id")),
                requestFields(buildUpdateRequest()),
                responseFields(buildDocumentation())
                        .and(buildIgnores())
        };

        addDefaultsToLast(snippets, false);

        return buildCustom("put", "update", snippets);
    }

    public RestDocumentationResultHandler buildDeactivate() {
        return buildCustom("delete", "deactivate",
                pathParameters(parameterWithName("id").description(docName + " id")),
                responseFields(fieldWithPath("message").description("Ok response")));
    }

    public RestDocumentationResultHandler buildCreate() {
        Snippet[] snippets = {
                requestFields(buildCreateRequest()),
                responseFields(buildDocumentation())
                        .and(buildIgnores())
        };

        addDefaultsToLast(snippets, false);

        return buildCustom("post", "create", snippets);
    }

    protected RestDocumentationResultHandler buildCustom(String restMethod, String postfix, Snippet... snippets) {
        return DocsHelper.buildDocumentHandler(restMethod + "-" + docName + "-" + postfix, snippets);
    }

    private void addDefaultsToLast(Snippet[] snippets, boolean isList) {
        if (!buildDefaults || !(snippets[snippets.length - 1] instanceof ResponseFieldsSnippet))
            return;

        ResponseFieldsSnippet responseField = ((ResponseFieldsSnippet) snippets[snippets.length - 1]);
        if (isList) {
            responseField = responseField.and(DocsHelper.withListPrefix(DocsHelper.buildDefaults()));
        } else {
            responseField = responseField.and(DocsHelper.buildDefaults());
        }

        snippets[snippets.length - 1] = responseField;
    }

    protected abstract List<FieldDescriptor> buildDocumentation();

    protected abstract List<FieldDescriptor> buildShared();

    protected abstract List<FieldDescriptor> buildUpdateRequest();

    protected abstract List<FieldDescriptor> buildCreateRequest();

    protected abstract List<FieldDescriptor> buildIgnores();
}
