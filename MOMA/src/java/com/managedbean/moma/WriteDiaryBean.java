/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.DiaryDao;
import com.entity.moma.Brochure;
import com.entity.moma.Diary;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class WriteDiaryBean {
    private Diary diary;
    private String title;
    private String content;
    private int brochureId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }
    

    /**
     * Creates a new instance of WriteDiaryBean
     */
    public WriteDiaryBean() {
        diary = new Diary();
    }
    
    public void doCompletion() {
        System.out.println("In diary doCompletion");
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentBrochureId").toString());
        System.out.println("currentBrochureId is " + brochureId);
        diary.setDiaryUrl("./" + brochureId + "/diary/");
        diary.setDiaryStartTime(new Date());
        diary.setDiaryModifiedTime(new Date());
        diary.setDiaryUrl(diary.getDiaryUrl() + diary.getDiaryTitle());
        diary.setBrochure(BrochureDao.findby_brochureId(brochureId));
        DiaryDao.add_diary(diary);
        changeBrochureLatestChange(brochureId);
//        DiaryDao.add_diary_brochure(diary.getDiaryId(), brochureId);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentBrochureId");
//        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentBrochureId") == null) {
//            System.out.println("Remove Successfully");
//        }
    }
    
    public void doModified() {
        diary.setDiaryModifiedTime(new Date());
        DiaryDao.modify_diary(diary);
    }
    
    private void changeBrochureLatestChange(int brochureId) {
        System.out.println("In changeBrochureLatestChange");
        Brochure currentBrochure = BrochureDao.findby_brochureId(brochureId);
        String userNameChangeDiary;
        if (diary.getUser() != null) {
            userNameChangeDiary = diary.getUser().getUserName();
        }
        else {
            userNameChangeDiary = "Someone";
        }
        currentBrochure.setLatestChange(userNameChangeDiary + " Diary: " + diary.getDiaryTitle());
        BrochureDao.modify_brochure(currentBrochure);
    }
}
