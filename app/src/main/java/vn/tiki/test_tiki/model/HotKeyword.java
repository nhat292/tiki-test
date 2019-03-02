package vn.tiki.test_tiki.model;

/**
 * Created by nhat on 3/2/19
 */
public class HotKeyword {

    private String keyword;
    private int color;

    public HotKeyword(String keyword, int color) {
        this.keyword = keyword;
        this.color = color;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
