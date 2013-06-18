/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.GroupDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.GroupOwner;
import com.entity.moma.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class FriendsBean {
    private User user ;
    private List<GroupOwner> groupList;
    private String chosedGroupName;
    private List<User> userFriendList;
    
    public List<User> getUserFriendList() {
        return userFriendList;
    }

    public void setUserFriendSet(List<User> userFriendList) {
        this.userFriendList = userFriendList;
    }

    public List<GroupOwner> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupOwner> groupList) {
        this.groupList = groupList;
    }

    public String getChosedGroupName() {
        return chosedGroupName;
    }

    public void setChosedGroupName(String chosedGroupName) {
        System.out.println("In set method " + chosedGroupName);
        this.chosedGroupName = chosedGroupName;
    }
    
    
    /**
     * Creates a new instance of FriendsBean
     */
    public FriendsBean() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        user = UserDao.findby_userName(userName);
        userFriendList = new ArrayList(user.getUsersForFirstUserId());
        System.out.println("userFriendList is " + userFriendList.size());
        groupList = GroupDao.findby_ownerId(user.getUserId());
        System.out.println("groupList size is " + groupList.size());
    }
    
    public void doRedirect() {
        System.out.println
                ("inDoredirect");
        String name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userName");
        System.out.println(name);
    }
    
    
    public void doFilter() {
        String group_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("group_id");
        int id = Integer.parseInt(group_id);
        for (GroupOwner ownerTemp : groupList) {
            if (ownerTemp.getGroupId() == id) {
                // userFriendList = ownerTemp.getUsers();
                ArrayList tempList = new ArrayList(ownerTemp.getUsers());
                userFriendList = tempList;
            }
        }
    }

    public SelectItem[] getGroupNameList() {
        int size = groupList.size() + 1;
        SelectItem[] items = new SelectItem[size];
        items[0] = new SelectItem("", "请选择一个分组");
        int i = 1;
        for (GroupOwner groupOwnerTemp : groupList) {
            items[i++] = new SelectItem(groupOwnerTemp, groupOwnerTemp.getGroupName());

        }
        return items;
    }

    public String doAdd() {
        String selectedName = chosedGroupName;
        System.out.println("seleectedName is " + selectedName + " in doAdd");

        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("friendUserName");
        System.out.println("userName is " + userName + " in doAdd");

        User userTemp = UserDao.findby_userName(userName);
        userTemp.setUserPortraitUrl("img/portrait.png");
        for (GroupOwner ownerTemp : groupList) {
            if (ownerTemp.getGroupName().equals(selectedName)) {
                
                ownerTemp.getUsers().add(userTemp);
                //存入数据库
                GroupDao.modify_group(ownerTemp);
                
                ArrayList tempList = new ArrayList(ownerTemp.getUsers());
                userFriendList = tempList;
            }
        }
        
        
        return "Friends";
    }

    public GroupOwner findByGroupName(String groupName) {
        for (GroupOwner ownerTemp : groupList) {
            if (ownerTemp.getGroupName().equals(groupName)) {
                return ownerTemp;
            }
        }
        return null;
    }
    
}
