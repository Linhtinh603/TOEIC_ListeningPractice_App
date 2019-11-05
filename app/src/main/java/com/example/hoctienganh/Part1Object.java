package com.example.hoctienganh;

import java.util.List;

public class Part1Object {
    private int id;
    private String rightAnswer;
    private String answerAScript;
    private String answerBScript;
    private String answerCScript;
    private String answerDScript;
    private boolean Pass;

    public Part1Object(int id, String rightAnswer, String answerAScript, String answerBScript, String answerCScript, String answerDScript, boolean pass) {
        this.id = id;
        this.rightAnswer = rightAnswer;
        this.answerAScript = answerAScript;
        this.answerBScript = answerBScript;
        this.answerCScript = answerCScript;
        this.answerDScript = answerDScript;
        Pass = pass;
    }

    public Part1Object() {
    }

    public int getId() {
        return id;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getAnswerAScript() {
        return answerAScript;
    }

    public String getAnswerBScript() {
        return answerBScript;
    }

    public String getAnswerCScript() {
        return answerCScript;
    }

    public String getAnswerDScript() {
        return answerDScript;
    }

    public boolean isPass() {
        return Pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setAnswerAScript(String answerAScript) {
        this.answerAScript = answerAScript;
    }

    public void setAnswerBScript(String answerBScript) {
        this.answerBScript = answerBScript;
    }

    public void setAnswerCScript(String answerCScript) {
        this.answerCScript = answerCScript;
    }

    public void setAnswerDScript(String answerDScript) {
        this.answerDScript = answerDScript;
    }

    public void setPass(boolean pass) {
        Pass = pass;
    }


    @Override
    public String toString() {
        return "Part1Object{" +
                "id=" + id +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", answerAScript='" + answerAScript + '\'' +
                ", answerBScript='" + answerBScript + '\'' +
                ", answerCScript='" + answerCScript + '\'' +
                ", answerDScript='" + answerDScript + '\'' +
                ", Pass=" + Pass +
                '}';
    }
}
