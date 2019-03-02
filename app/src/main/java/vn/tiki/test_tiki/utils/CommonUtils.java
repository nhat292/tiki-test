package vn.tiki.test_tiki.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by nhat on 3/2/19
 */
public final class CommonUtils {
    private CommonUtils() {

    }

    /**
     * Random color
     * @return
     */
    public static int randomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    /**
     * Break keyword into two lines algorithm
     * @param keyword
     * @return
     */
    public static String breakKeywordIntoTwoLinesAlgorithm(String keyword) {
        StringBuilder newKeyword = new StringBuilder();
        String[] words = keyword.split(" ");
        if (words.length == 2) {
            newKeyword.append(words[0]).append("\n").append(words[1]);
        } else if (words.length > 2) {
            StringBuilder segment1 = new StringBuilder();
            StringBuilder segment2 = new StringBuilder();
            int index = words.length / 2;
            for (int i = 0; i < index; i++) {
                segment1.append(words[i]);
            }
            for (int i = index; i < words.length; i++) {
                segment2.append(words[i]);
            }

            int newIndex = index;
            if (segment1.toString().length() > segment2.toString().length()) {
                newIndex = index - 1;
            }

            for (int i = 0; i < words.length; i++) {
                if (i == newIndex) {
                    newKeyword.append("\n");
                    newKeyword.append(words[i]);
                } else {
                    newKeyword.append(" ");
                    newKeyword.append(words[i]);
                }
            }
        } else {
            newKeyword.append(keyword);
        }
        return newKeyword.toString().trim();
    }
}
