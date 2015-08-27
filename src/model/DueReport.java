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
public class DueReport {
    private int sl;
    private int id;
    private int caret;
    private int qty;
    private String date;
    private String name;
    private String phone;
    private String address;
    private String item;
    private String note;
    private float paid;
    private float due;
    private float loss;
    
    public DueReport(int sl, int id, String date, String name, String phone, String address, String item, int caret, int qty, float paid, float due, float loss, String note){
        this.sl = sl;
        this.id = id;
        this.caret = caret;
        this.qty = qty;
        this.date = date;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.item = item;
        this.note = note;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
