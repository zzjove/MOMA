package com.entity.moma;
// Generated 2013-6-16 3:35:49 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Blessing generated by hbm2java
 */
public class Blessing  implements java.io.Serializable {


     private Integer blessingId;
     private User user;
     private Brochure brochure;
     private Date blessingStartTime;
     private String blessingUrl;

    public Blessing() {
    }

    public Blessing(User user, Brochure brochure, Date blessingStartTime, String blessingUrl) {
       this.user = user;
       this.brochure = brochure;
       this.blessingStartTime = blessingStartTime;
       this.blessingUrl = blessingUrl;
    }
   
    public Integer getBlessingId() {
        return this.blessingId;
    }
    
    public void setBlessingId(Integer blessingId) {
        this.blessingId = blessingId;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Brochure getBrochure() {
        return this.brochure;
    }
    
    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }
    public Date getBlessingStartTime() {
        return this.blessingStartTime;
    }
    
    public void setBlessingStartTime(Date blessingStartTime) {
        this.blessingStartTime = blessingStartTime;
    }
    public String getBlessingUrl() {
        return this.blessingUrl;
    }
    
    public void setBlessingUrl(String blessingUrl) {
        this.blessingUrl = blessingUrl;
    }




}


