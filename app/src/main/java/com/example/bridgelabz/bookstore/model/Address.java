package com.example.bridgelabz.bookstore.model;

public class Address {
    private long AddressID;
    private String mobile;
    private String HouseNo;
    private String Street;
    private String city;
    private String State;
    private String Pincode;

    public Address(long AddressID, String mobile, String HouseNo, String Street, String city, String State, String Pincode) {
       this.AddressID = AddressID;
        this.mobile = mobile;
        this.HouseNo = HouseNo;
        this.Street = Street;
        this.city = city;
        this.State = State;
        this.Pincode = Pincode;
    }

    public long getAddressID() {
        return AddressID;
    }

    public void setAddressID(long AddressID) {
        this.AddressID = AddressID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(String HouseNo) {
        this.HouseNo = HouseNo;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String Pincode) {
        this.Pincode = Pincode;
    }
}
