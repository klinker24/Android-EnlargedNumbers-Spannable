package xyz.klinker.enlarged_numbers;

import android.text.style.RelativeSizeSpan;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class EnlargedNumbersStringTest {

    private EnlargedNumbersString spannable;

    @Before
    public void setUp() {
        spannable = spy(EnlargedNumbersFactory.build("1.001 MB"));
    }

    @Test
    public void test_matcherLength() {
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
        assertEquals(1, spannable.getSpans(0, 8, RelativeSizeSpan.class).length);

        spannable = spy(EnlargedNumbersFactory.build("1.001 KB 11"));
        assertEquals(2, spannable.getSpans(0,11, RelativeSizeSpan.class).length);
    }

}
