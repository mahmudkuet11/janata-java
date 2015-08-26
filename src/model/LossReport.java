/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mohar
 */
public class LossReport {
    private int sl;
    private int caret;
    private String date;
    private String category;
    private float loss;
    
    public LossReport(int sl, String date, String category, int caret, float loss){
        this.sl  = sl;
        this.date = date;
        this.category = category;
        this.caret = caret;
        this.loss = loss;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getCaret() {
        return caret;
    }

    public void setCaret(int caret) {
        this.caret = caret;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getLoss() {
        return loss;
    }

    public void setLoss(float loss) {
        this.loss = loss;
    }
}
