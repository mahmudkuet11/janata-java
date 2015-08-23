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
public class Category {
    private int sl;
    private int id;
    private int warning_qty;
    private int current_qty;
    private String name;
    private float p_price;
    private float s_price;
    
    public Category(int sl, int id, String name, float p_price, float s_price, int warning_qty, int current_qty){
        this.sl = sl;
        this.id = id;
        this.name = name;
        this.p_price = p_price;
        this.s_price = s_price;
        this.warning_qty = warning_qty;
        this.current_qty = current_qty;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getP_price() {
        return p_price;
    }

    public void setP_price(float p_price) {
        this.p_price = p_price;
    }

    public float getS_price() {
        return s_price;
    }

    public void setS_price(float s_price) {
        this.s_price = s_price;
    }
    
    public String toString(){
        return this.name;
    }
    
}
