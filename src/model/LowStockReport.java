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
public class LowStockReport {
    private int sl;
    private int warning_qty;
    private int current_qty;
    private String item;
    public LowStockReport(int sl, String item, int warning_qty, int current_qty){
        this.sl = sl;
        this.item = item;
        this.warning_qty = warning_qty;
        this.current_qty = current_qty;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getWarning_qty() {
        return warning_qty;
    }

    public void setWarning_qty(int warning_qty) {
        this.warning_qty = warning_qty;
    }

    public int getCurrent_qty() {
        return current_qty;
    }

    public void setCurrent_qty(int current_qty) {
        this.current_qty = current_qty;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
