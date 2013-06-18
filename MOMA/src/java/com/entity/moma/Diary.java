package com.entity.moma;
// Generated 2013-6-18 22:31:38 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Diary generated by hbm2java
 */
public class Diary  implements java.io.Serializable {


     private Integer diaryId;
     private User user;
     private Brochure brochure;
     private Date diaryStartTime;
     private String diaryUrl;
     private String diaryTitle;
     private Date diaryModifiedTime;

    public Diary() {
    }

	
    public Diary(Brochure brochure, Date diaryStartTime, String diaryUrl, String diaryTitle, Date diaryModifiedTime) {
        this.brochure = brochure;
        this.diaryStartTime = diaryStartTime;
        this.diaryUrl = diaryUrl;
        this.diaryTitle = diaryTitle;
        this.diaryModifiedTime = diaryModifiedTime;
    }
    public Diary(User user, Brochure brochure, Date diaryStartTime, String diaryUrl, String diaryTitle, Date diaryModifiedTime) {
       this.user = user;
       this.brochure = brochure;
       this.diaryStartTime = diaryStartTime;
       this.diaryUrl = diaryUrl;
       this.diaryTitle = diaryTitle;
       this.diaryModifiedTime = diaryModifiedTime;
    }
   
    public Integer getDiaryId() {
        return this.diaryId;
    }
    
    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
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
    public Date getDiaryStartTime() {
        return this.diaryStartTime;
    }
    
    public void setDiaryStartTime(Date diaryStartTime) {
        this.diaryStartTime = diaryStartTime;
    }
    public String getDiaryUrl() {
        return this.diaryUrl;
    }
    
    public void setDiaryUrl(String diaryUrl) {
        this.diaryUrl = diaryUrl;
    }
    public String getDiaryTitle() {
        if (this.diaryTitle == null) {
            return "";
        }
        return this.diaryTitle;
    }
    
    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }
    public Date getDiaryModifiedTime() {
        return this.diaryModifiedTime;
    }
    
    public void setDiaryModifiedTime(Date diaryModifiedTime) {
        this.diaryModifiedTime = diaryModifiedTime;
    }




}


