package xyz.klinker.enlarged_numbers;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnlargedNumbersString extends SpannableString {

    private static final float DEFAULT_NUMBER_SIZE_MULTIPLIER = 2f;
    private static final String REGEX_FOR_NUMBER = "[0-9]+(\\.[0-9]+)?";
    private static final Pattern REGEX_NUM_PATTERN = Pattern.compile(REGEX_FOR_NUMBER);

    private CharSequence source;
    private float sizeMultiplier = DEFAULT_NUMBER_SIZE_MULTIPLIER;
    private boolean boldNumbers = true;

    protected EnlargedNumbersString(CharSequence source) {
        super(source);
        this.source = source;
    }

    protected void setSizeMultiplier(float multiplier) {
        this.sizeMultiplier = multiplier;
    }

    protected void setBoldNumbers(boolean boldNumbers) {
        this.boldNumbers = boldNumbers;
    }

    protected void createRanges() {
        for (Range range : findNumbers(source)) {
            setSpan(new RelativeSizeSpan(sizeMultiplier), range.start, range.end, 0);
            if (boldNumbers) {
                setSpan(new StyleSpan(Typeface.BOLD), range.start, range.end, 0);
            }
        }
    }

    protected List<Range> findNumbers(CharSequence source) {
        List<Range> ranges = new ArrayList<>();

        Matcher matcher = REGEX_NUM_PATTERN.matcher(source);
        while(matcher.find()) {
            ranges.add(new Range(matcher.start(), matcher.end()));
        }

        return ranges;
    }
}
