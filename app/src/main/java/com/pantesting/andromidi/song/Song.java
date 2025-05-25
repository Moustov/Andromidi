package com.pantesting.andromidi.song;

public class Song {
    private String song;
    private int bank_id;
    private int bpm;
    private String ctrl1;
    private String ctrl2;
    private String ctrl3;
    private String ctrl4;

    // Getters
    public String getSong() { return song; }
    public String getCtrl(int id_ctrl){
        switch (id_ctrl){
            case 1: return ctrl1;
            case 2: return ctrl2;
            case 3: return ctrl3;
            case 4: return ctrl4;
        }
        return null;
    }
    public int getBankId() { return bank_id; }
    public int getBpm() { return bpm; }
    public String getCtrl1() { return ctrl1; }
    public String getCtrl2() { return ctrl2; }
    public String getCtrl3() { return ctrl3; }
    public String getCtrl4() { return ctrl4; }
}


