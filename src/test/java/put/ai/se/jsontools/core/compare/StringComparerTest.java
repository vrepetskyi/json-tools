package put.ai.se.jsontools.core.compare;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringComparerTest {
    @Test
    public void testGetLineNumbers_IdenticalMode_AllLines() { // does function would work for identical strings?
        String s1 = "abc\ndef\nghi\njkl";
        String s2 = "abc\ndef\nghi\njkl";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.identical);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_IdenticalMode_SomeLines() {  // does function would work for not-identical strings?
        String s1 = "abc\ndef\nghi\njkl";
        String s2 = "abc\ndef\n123\njkl";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.identical);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_DifferentMode_AllLines() { // does function would work for identical strings?
        String s1 = "abc\ndef\nghi\njkl";
        String s2 = "abc\ndef\nghi\njkl";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.different);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_DifferentMode_SomeLines() {
        String s1 = "abc\ndef\nghi\njkl";
        String s2 = "abc\ndef\n123\njkl";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.different);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(3);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_IdenticalMode_SingleLineString() {
        String s1 = "abc";
        String s2 = "abc";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.identical);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_DifferentMode_SingleLineString() {
        String s1 = "abc";
        String s2 = "def";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.different);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_IdenticalMode_EmptyStrings() {
        String s1 = "";
        String s2 = "";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.identical);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_DifferentMode_EmptyStrings() {
        String s1 = "";
        String s2 = "";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.different);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_IdenticalMode_MixedIdenticalAndDifferentLines() {
        String s1 = "abc\ndef\nghi\njkl\nmno";
        String s2 = "abc\n123\nghi\n123\n123";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.identical);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);
        expected.add(3);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetLineNumbers_DifferentMode_NoIdenticalLines() {
        String s1 = "abc\ndef\nghi";
        String s2 = "123\n456\n789";
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(CompareMode.different);

        LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        LinkedHashSet<Integer> result = StringComparer.getLineNumbers(s1, s2, arguments);

        Assertions.assertEquals(expected, result);
    }
}
