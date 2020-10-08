package com.example.siburtest.domains;

import javax.persistence.Id;

public class Order {
    @Id
    private Integer id;

    public Order(Integer id, Integer customerId, Integer transportTypeId, Integer transportId, String fullname, String phone, String status) {
        this.id = id;
        this.customerId = customerId;
        this.transportTypeId = transportTypeId;
        this.transportId = transportId;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
    }

    private Integer customerId;
    private Integer transportTypeId;
    private Integer transportId;
    private String fullname;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String status;

    public Integer getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(Integer transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
