package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/8.
 */

public class InfoBean {

    private String theme;
    private String answer;

    public InfoBean(String theme, String answer) {
        this.theme = theme;
        this.answer = answer;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
