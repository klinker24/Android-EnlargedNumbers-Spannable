package xyz.klinker.enlarged_numbers;

import android.text.style.RelativeSizeSpan;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnlargedNumbersStringTest {

    @Test
    public void test_matcherLength() {
        EnlargedNumbersString spannable = EnlargedNumbersFactory.build("test");

        assertEquals(0, spannable.findNumbers("test").size());
        assertEquals(1, spannable.findNumbers("1.").size());
        assertEquals(2, spannable.findNumbers("1. 01").size());
        assertEquals(1, spannable.findNumbers("1.001 mb").size());
        assertEquals(1, spannable.findNumbers("1.00gb").size());
        assertEquals(2, spannable.findNumbers("1 1001").size());
        assertEquals(1, spannable.findNumbers("1.00").size());
        assertEquals(2, spannable.findNumbers("kb1 101.1").size());
    }

    @Test
    public void test_spannableType() {
        EnlargedNumbersString spannable = EnlargedNumbersFactory.build("1.001 MB");
        assertEquals(1, spannable.getSpans(0, 8, RelativeSizeSpan.class).length);

        spannable = EnlargedNumbersFactory.build("1.001 KB 11");
        assertEquals(2, spannable.getSpans(0,11, RelativeSizeSpan.class).length);
    }

}
