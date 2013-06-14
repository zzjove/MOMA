/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import com.helperClass.moma.UpdateInfo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class UserHomePageBean {

    private User user;
    private ArrayList<UpdateInfo> updates;
    private ArrayList<Brochure>recommendedBros;
    
    
    public List<UpdateInfo> getUpdates() {
        return updates;
    }

    public void setUpdates(ArrayList<UpdateInfo> updates) {
        this.updates = updates;
    }

    public ArrayList<Brochure> getRecommendedBros() {
        return recommendedBros;
    }

    public void setRecommendedBros(ArrayList<Brochure> recommendedBros) {
        this.recommendedBros = recommendedBros;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Creates a new instance of UserHomePageBean
     */
    public UserHomePageBean() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        user = UserDao.findby_userName(userName);
        updates = new ArrayList();
//        recommendedBros = user.getBrochures();
        ArrayList<Brochure> brochureList = (ArrayList<Brochure>) BrochureDao.findby_userName(userName);
        System.out.println("brochurelist is " + brochureList.size());
        for(Brochure tempBrochure : brochureList) {
            System.out.println("brochureId is " + tempBrochure.getBrochureId());
            UpdateInfo update = new UpdateInfo(tempBrochure);
            updates.add(update);
        }
    }
    
    public void toBrochure() {
        System.out.println("In toBrochure");
        String brochureName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureId");
        System.out.println("in to brochure " + brochureName);
    }
    
}
