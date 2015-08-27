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
public class StockReport {
    private int sl;
    private int caret18;
    private int caret21;
    private int caret22;
    private int total;
    private String item;
    public StockReport(int sl, String item, int caret18, int caret21, int caret22, int total){
        this.sl = sl;
        this.item = item;
        this.caret18 = caret18;
        this.caret21 = caret21;
        this.caret22 = caret22;
        this.total = total;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getCaret18() {
        return caret18;
    }

    public void setCaret18(int caret18) {
        this.caret18 = caret18;
    }

    public int getCaret21() {
        return caret21;
    }

    public void setCaret21(int caret21) {
        this.caret21 = caret21;
    }

    public int getCaret22() {
        return caret22;
    }

    public void setCaret22(int caret22) {
        this.caret22 = caret22;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
