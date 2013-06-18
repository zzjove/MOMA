/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import com.helperClass.moma.BrochureUpdate;
import com.helperClass.moma.UserUpdate;
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
    private ArrayList<UserUpdate> userUpdate = new ArrayList();
    private ArrayList<BrochureUpdate> brochureUpdates = new ArrayList();
    private ArrayList<Brochure> recommendedBros = new ArrayList();
    private String searchName;
        
    public ArrayList<UserUpdate> getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(ArrayList<UserUpdate> userUpdate) {
        this.userUpdate = userUpdate;
    }

    public List<BrochureUpdate> getBrochureUpdates() {
        return brochureUpdates;
    }

    public void setBrochureUpdates(ArrayList<BrochureUpdate> brochureUpdates) {
        this.brochureUpdates = brochureUpdates;
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

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * Creates a new instance of UserHomePageBean
     */
    public UserHomePageBean() {
        
        System.out.println("In userHomePageBean construction");
        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("friendName");
        if (userName == null) {
            userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        }
        user = UserDao.findby_userName(userName);
        getlatestUpdate();
        getRecommandBrochure();
        getFollowBrochureLatestChange();

    }

    private void getlatestUpdate() {
        ArrayList<Brochure> latestChangeBrochureList = new ArrayList();
        ArrayList<Brochure> allBrochureList = new ArrayList(BrochureDao.findby_userId(user.getUserId()));
        System.out.println("all BrochureList size is " + allBrochureList.size());
        if (!allBrochureList.isEmpty()) {
//            System.out.println("date is " + brochureTest.getBrochureModifyTime());
            while (latestChangeBrochureList.size() < 6  && !allBrochureList.isEmpty()) {
                Brochure temp_brochure = allBrochureList.get(0);
                for (Brochure brochure : allBrochureList) {
                    if (brochure.getBrochureModifyTime().after(temp_brochure.getBrochureModifyTime())) {
                        temp_brochure = brochure;
                    }
                }
                latestChangeBrochureList.add(temp_brochure);
                allBrochureList.remove(temp_brochure);
                userUpdate.add(new UserUpdate(temp_brochure));
            }
//            for (Brochure brochure : latestChangeBrochureList) {
//                System.out.println("IN Latest update" + brochure.getBrochureId());
//            }
            
//            System.out.println("userUpdate size is " + userUpdate.size());
//            for (UserUpdate brochure: userUpdate) {
//                System.out.println("In userUpadte" + brochure.getBrochure().getBrochureName());
//            }
        }
    }

    private void getRecommandBrochure() {
        recommendedBros = (ArrayList<Brochure>) BrochureDao.findby_maxBrochureVisit(BrochureDao.getMaxBrochureVisited());
        String currentUserName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        User currentUser = UserDao.findby_userName(currentUserName);
        int counter = 0;
        while (true) {
            counter++;
            if (!currentUser.getUsersForSecondUserId().isEmpty()) {
                int randomUserNumber = (int) (Math.random() * currentUser.getUsersForSecondUserId().size());
                ArrayList recommandFriendList = new ArrayList(currentUser.getUsersForSecondUserId());
                User friend = (User) recommandFriendList.get(randomUserNumber);
                if (!friend.getBrochures().isEmpty()) {
//                    System.out.println("IN recommand " + friend.getUserName());
                    int randomBrochureNumber = (int) (Math.random() * friend.getBrochures().size());
                    ArrayList recommandBrochureList = new ArrayList(friend.getBrochures());
                    Brochure brochure = (Brochure) recommandBrochureList.get(randomBrochureNumber);
                    if (recommendedBros.contains(brochure)) {
                        continue;
                    }
                    recommendedBros.add(brochure);
                    break;
                }
            }
            if (counter >= 20) {
                break;
            }

        }

//        for (Brochure brochure : recommendedBros) {
//            System.out.println("recommandedbros is " + brochure.getBrochureId());
//        }

    }

    private void getFollowBrochureLatestChange() {
        ArrayList<Brochure> brochureList = new ArrayList(user.getBrochures_1());
        System.out.println("brochurelist is " + brochureList.size());
        for (Brochure tempBrochure : brochureList) {
            System.out.println("brochureId is " + tempBrochure.getBrochureId());
            BrochureUpdate update = new BrochureUpdate(tempBrochure);
            brochureUpdates.add(update);
        }
    }

    public void toBrochure() {
        System.out.println("In toBrochure");
        String brochureName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureId");
        System.out.println("in to brochure " + brochureName);
    }

    public boolean isHost() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("friendName");
        if (userName == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void doSearch() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchName", searchName);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("searchList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
