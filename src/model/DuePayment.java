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
public class DuePayment {
    private int sl;
    private int caret;
    private int quantity;
    private String date;
    private String item;
    private String note;
    private float rate;
    private float paid;
    private float due;
    private float loss;
    private int id;
    
    public DuePayment(int sl, int id, int caret, int quantity, String date, String item, String note, float rate, float paid, float due, float loss){
        this.sl = sl;
        this.id = id;
        this.caret = caret;
        this.quantity = quantity;
        this.date = date;
        this.item = item;
        this.note = note;
        this.rate = rate;
        this.paid = paid;
        this.due = due;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public float getDue() {
        return due;
    }

    public void setDue(float due) {
        this.due = due;
    }

    public float getLoss() {
        return loss;
    }

    public void setLoss(float loss) {
        this.loss = loss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
