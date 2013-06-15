package com.entity.moma;
// Generated 2013-6-16 3:35:49 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Photo generated by hbm2java
 */
public class Photo  implements java.io.Serializable {


     private Integer photoId;
     private User user;
     private Brochure brochure;
     private Date photoStartTime;
     private String photoUrl;
     private String photoTitle;
     private String photoLocation;

    public Photo() {
    }

	
    public Photo(User user, Brochure brochure, Date photoStartTime, String photoUrl, String photoTitle) {
        this.user = user;
        this.brochure = brochure;
        this.photoStartTime = photoStartTime;
        this.photoUrl = photoUrl;
        this.photoTitle = photoTitle;
    }
    public Photo(User user, Brochure brochure, Date photoStartTime, String photoUrl, String photoTitle, String photoLocation) {
       this.user = user;
       this.brochure = brochure;
       this.photoStartTime = photoStartTime;
       this.photoUrl = photoUrl;
       this.photoTitle = photoTitle;
       this.photoLocation = photoLocation;
    }
   
    public Integer getPhotoId() {
        return this.photoId;
    }
    
    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
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
    public Date getPhotoStartTime() {
        return this.photoStartTime;
    }
    
    public void setPhotoStartTime(Date photoStartTime) {
        this.photoStartTime = photoStartTime;
    }
    public String getPhotoUrl() {
        return this.photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getPhotoTitle() {
        return this.photoTitle;
    }
    
    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }
    public String getPhotoLocation() {
        return this.photoLocation;
    }
    
    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }




}


