package xyz.klinker.enlarged_numbers;

public class EnlargedNumbersFactory {
    public static EnlargedNumbersString build(CharSequence text) {
        EnlargedNumbersString string = new EnlargedNumbersString(text);
        string.createRanges();

        return string;
    }

    public static EnlargedNumbersString build(CharSequence text, float numberSizeMultiplier) {
        EnlargedNumbersString string = new EnlargedNumbersString(text);

        string.setSizeMultiplier(numberSizeMultiplier);
        string.createRanges();

        return string;
    }

    public static EnlargedNumbersString build(CharSequence text, float numberSizeMultiplier,
                                              boolean boldNumbers) {
        EnlargedNumbersString string = new EnlargedNumbersString(text);

        string.setSizeMultiplier(numberSizeMultiplier);
        string.setBoldNumbers(boldNumbers);
        string.createRanges();

        return string;
    }
}
