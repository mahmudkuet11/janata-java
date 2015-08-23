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
public class Supplier {
    private int sl;
    private int id;
    private String name;
    private String phone;
    private String address;
    
    public Supplier(int sl, String name, String phone, String address){
        this.sl = sl;
        this.name = name;
        this.phone = phone;
        this.address  = address;
    }
    public Supplier(int sl, int id, String name, String phone, String address){
        this.id = id;
        this.sl = sl;
        this.name = name;
        this.phone = phone;
        this.address  = address;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String toString(){
        return this.getName();
    }
}
