package com.esp.hangul.StudyDetail;

public class Item {

    private String korean;
    private String pronuncation;
    private String vietnamese;
    private byte[] sound;

    public Item(String korean, String vietnamese) {
        this.korean = korean;
        this.vietnamese = vietnamese;
    }

    public Item(String korean, String pronuncation, String vietnamese) {
        this.korean = korean;
        this.pronuncation = pronuncation;
        this.vietnamese = vietnamese;
    }

    public Item(String korean, String pronuncation, String vietnamese, byte[] sound) {
        this.korean = korean;
        this.pronuncation = pronuncation;
        this.vietnamese = vietnamese;
        this.sound = sound;
    }

    public String getKorean() {
        return korean;
    }

    public String getPronuncation() {
        return pronuncation;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public byte[] getSound() {
        return sound;
    }
}
