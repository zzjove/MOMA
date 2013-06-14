/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.*;


/**
 *
 * @author ZZ
 */
@ManagedBean
@RequestScoped
public class BrochureBean {

    private int brochureId;
    @NotNull
    private String brochureName;
    private Date brochureDate;
    private int brochureType;
    @NotNull
    private int brochureRoot;
    @NotNull
    private String brochureDescription;
    private List<String> brochureRootString;
    Brochure brochure=new Brochure();
    
    public BrochureBean() {
        System.out.println("for test brochure");
    }  

    public int getBrochureType() {
        return brochureType;
    }
    public void setBrochureType(int brochureType) {
        this.brochureType = brochureType;
    }  
    public int getBrochureId() {
        return brochureId;
    }
    public void setBrochureId(int brochureId) {
        this.brochureId = brochureId;
    }
    public String getBrochureName() {
        return brochureName;
    }
    public void setBrochureName(String brochureName) {
        this.brochureName = brochureName;
    }
    public Date getBrochureDate() {
        return brochureDate;
    }
    public void setBrochureDate(Date brochureDate) {
        this.brochureDate = brochureDate;
    }
    public int getBrochureRoot() {
        return brochureRoot;
    }
    public void setBrochureRoot(int brochureRoot) {
        this.brochureRoot = brochureRoot;
    }
    public String getBrochureDescription() {
        return brochureDescription;
    }
    public void setBrochureDescription(String brochureDescription) {
        this.brochureDescription = brochureDescription;
    }
    
    public void initBrochureRootString(){
        brochureRootString=new ArrayList<String>();
        brochureRootString.add("只限纪念册所有者浏览");
        brochureRootString.add("只限纪念册所有者好友浏览");
        brochureRootString.add("公开所有人都可以浏览");
        
    }
            
    public void newBrochure(){
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        brochureDate = new Date();
        System.out.println(userName + " in brochure");
        brochure.setBrochureName(this.brochureName);
        brochure.setBrochureRoot(this.brochureRoot);
        brochure.setBrochureDescription(this.brochureDescription);
        brochure.setBrochureStartTime(brochureDate);
        BrochureDao.add_brochure(brochure);
        System.out.println(brochure.getBrochureId());
        UserDao.add_user_brochure(userName, brochure.getBrochureId());
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentBrochureId", brochure.getBrochureId());
    }
    
    public String addDiary() {
        return "writeDiary";
    }
    
   
}
