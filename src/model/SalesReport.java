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
public class SalesReport {
    private int sl;
    private int id;
    private int caret;
    private int qty;
    private String date;
    private String category;
    private String customer;
    private String note;
    private float rate;
    private float total;
    private float paid;
    private float due;
    private float loss;
    
    public SalesReport(int sl, int id, String date, String category, int caret, String customer, int qty, float rate, float total, float paid, float due, float loss, String note){
        this.sl = sl;
        this.id = id;
        this.date = date;
        this.category = category;
        this.caret = caret;
        this.customer = customer;
        this.qty = qty;
        this.rate = rate;
        this.total = total;
        this.paid = paid;
        this.due = due;
        this.loss = loss;
        this.note = note;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaret() {
        return caret;
    }

    public void setCaret(int caret) {
        this.caret = caret;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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
}
