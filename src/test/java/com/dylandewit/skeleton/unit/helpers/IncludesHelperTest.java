package com.dylandewit.skeleton.unit.helpers;

import com.dylandewit.skeleton.helpers.IncludesHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncludesHelperTest {

    @Test
    void fetchNestedIncludes_singleNesting_returnsOne() {
        List<String> result = IncludesHelper.fetchNestedIncludes(List.of("a.b"), "a");

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("b"));
    }

    @Test
    void fetchNestedIncludes_doubleNesting_returnsOne() {
        List<String> result = IncludesHelper.fetchNestedIncludes(List.of("b.a.c"), "a");

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("c"));
    }

    @Test
    void fetchNestedIncludes_doubleNesting_returnsTwo() {
        List<String> result = IncludesHelper.fetchNestedIncludes(List.of("b.a.c", "a.b"), "a");

        assertThat(result.size(), is(2));
        assertTrue(result.contains("c"));
        assertTrue(result.contains("b"));
    }

    @Test
    void fetchNestedIncludes_doubleNestingWithOwnRelations_returnsOne() {
        List<String> result = IncludesHelper.fetchNestedIncludes(List.of("b.a.c.d", "a.b"), "a");

        assertThat(result.size(), is(2));
        assertTrue(result.contains("c.d"));
        assertTrue(result.contains("b"));
    }

    @Test
    void fetchNestedIncludes_doubleNestingAndOneIrrelevant_returnsTwo() {
        List<String> result = IncludesHelper.fetchNestedIncludes(List.of("b.a.c", "a.b", "b.c"), "a");

        assertThat(result.size(), is(2));
        assertTrue(result.contains("c"));
        assertTrue(result.contains("b"));
    }

    @Test
    void fetchNestedIncludes_noMatches_returnsZero() {
        List<String> result = IncludesHelper.fetchNestedIncludes(List.of("b.a.c", "a.b", "b.c"), "d");

        assertThat(result.size(), is(0));
    }

    @Test
    void fetchNestedIncludes_lastNesting_returnsZero() {
        List<String> result = IncludesHelper.fetchNestedIncludes(List.of("b.a.c", "a.b", "b.c"), "c");

        assertThat(result.size(), is(0));
    }

    @Test
    void hasInclude_oneMatch_returnsTrue() {
        boolean result = IncludesHelper.hasInclude(List.of("a"), "a");

        assertTrue(result);
    }

    @Test
    void hasInclude_oneMatchOneIrrelevant_returnsTrue() {
        boolean result = IncludesHelper.hasInclude(List.of("a", "b"), "a");

        assertTrue(result);
    }

    @Test
    void hasInclude_noMatch_returnsFalse() {
        boolean result = IncludesHelper.hasInclude(List.of("b"), "a");

        assertFalse(result);
    }

    @Test
    void hasInclude_noMatchButClose_returnsFalse() {
        boolean result = IncludesHelper.hasInclude(List.of("b", "b.a"), "a");

        assertFalse(result);
    }

    @Test
    void hasInclude_matchWithRelation_returnsTrue() {
        boolean result = IncludesHelper.hasInclude(List.of("a.b", "b.d"), "a");

        assertTrue(result);
    }
}
