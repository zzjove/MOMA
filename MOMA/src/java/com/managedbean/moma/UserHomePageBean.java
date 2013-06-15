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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class UserHomePageBean {

    private User user;
    private ArrayList<UpdateInfo> updates;
    private ArrayList<Brochure> recommendedBros = new ArrayList();

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
        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("friendName");
        if (userName == null) {
            userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        }
        user = UserDao.findby_userName(userName);
        updates = new ArrayList();
        getRecommandBrochure();
//        ArrayList<Brochure> brochureList = (ArrayList<Brochure>) BrochureDao.findby_userName(userName);
        ArrayList<Brochure> brochureList = new ArrayList(user.getBrochures());
        System.out.println("brochurelist is " + brochureList.size());
        for (Brochure tempBrochure : brochureList) {
            System.out.println("brochureId is " + tempBrochure.getBrochureId());
            UpdateInfo update = new UpdateInfo(tempBrochure);
            updates.add(update);
        }
    }

    private void getRecommandBrochure() {
        recommendedBros = (ArrayList<Brochure>) BrochureDao.findby_maxBrochureVisit(BrochureDao.getMaxBrochureVisited());
        String currentUserName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        User currentUser = UserDao.findby_userName(currentUserName);
        int counter = 0;
        while (true) {
            System.out.println(counter);
            counter ++ ;
            int randomUserNumber = (int) (Math.random() * currentUser.getUsersForSecondUserId().size());
            ArrayList recommandFriendList = new ArrayList(currentUser.getUsersForSecondUserId());
            User friend = (User) recommandFriendList.get(randomUserNumber);
            if (!friend.getBrochures().isEmpty()) {
                System.out.println("IN recommand " + friend.getUserName());
                int randomBrochureNumber = (int) (Math.random() * friend.getBrochures().size());
                ArrayList recommandBrochureList = new ArrayList(friend.getBrochures());
                Brochure brochure = (Brochure) recommandBrochureList.get(randomBrochureNumber);
                if (recommendedBros.contains(brochure))
                    continue;
                recommendedBros.add(brochure);
                break;
            }
            if (counter >= 20) 
                break;
        }
        
        
        
    }

    public void toBrochure() {
        System.out.println("In toBrochure");
        String brochureName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureId");
        System.out.println("in to brochure " + brochureName);
    }
}
