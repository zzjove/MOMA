/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.GroupOwner;
import com.entity.moma.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class UserGroupTestBean {

    private GroupOwner groupOwner1, groupOwner2, groupOwner3;
    private List<GroupOwner> groupList;
    private Set<User> users1, users2, users3;
    private User user1, user2, user3, user4, user5, user6, user7, user8, user9;
    private List<User> userFriendList;
    private String chosedGroupName;

    public String getChosedGroupName() {
        return chosedGroupName;
    }

    public void setChosedGroupName(String chosedGroupName) {
        this.chosedGroupName = chosedGroupName;
    }

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

    /**
     * Creates a new instance of UserGroupTestBean
     */
    public UserGroupTestBean() {
        user1 = new User();
        user2 = new User();
        user3 = new User();
        user4 = new User();
        user5 = new User();
        user6 = new User();
        user7 = new User();
        user8 = new User();
        user9 = new User();

        user1.setUserName("zzjove1");
        user2.setUserName("shit2");
        user3.setUserName("elena3");
        user4.setUserName("zzjove4");
        user5.setUserName("shit5");
        user6.setUserName("elena6");
        user7.setUserName("zzjove7");
        user8.setUserName("shit8");
        user9.setUserName("elena9");

        user1.setUserPortraitUrl("img/portrait.png");
        user2.setUserPortraitUrl("img/portrait.png");
        user3.setUserPortraitUrl("img/portrait.png");
        user4.setUserPortraitUrl("img/portrait.png");
        user5.setUserPortraitUrl("img/portrait.png");
        user6.setUserPortraitUrl("img/portrait.png");
        user7.setUserPortraitUrl("img/portrait.png");
        user8.setUserPortraitUrl("img/portrait.png");
        user9.setUserPortraitUrl("img/portrait.png");

        users1 = new HashSet<User>();
        users1.add(user1);
        users1.add(user2);
        users1.add(user3);
        users2 = new HashSet<User>();
        users2.add(user4);
        users2.add(user5);
        users2.add(user6);
        users3 = new HashSet<User>();
        users3.add(user7);
        users3.add(user8);
        users3.add(user9);

        groupOwner1 = new GroupOwner();
        groupOwner2 = new GroupOwner();
        groupOwner3 = new GroupOwner();

        groupOwner1.setGroupId(1);
        groupOwner2.setGroupId(2);
        groupOwner3.setGroupId(3);

        groupOwner1.setGroupName("Workmates");
        groupOwner2.setGroupName("Family Members");
        groupOwner3.setGroupName("Friends");

        groupOwner1.setOwnerId(1);
        groupOwner2.setOwnerId(1);
        groupOwner3.setOwnerId(1);

        groupOwner1.setUsers(users1);
        groupOwner2.setUsers(users2);
        groupOwner3.setUsers(users3);

        groupList = new ArrayList();
        groupList.add(groupOwner1);
        groupList.add(groupOwner2);
        groupList.add(groupOwner3);


        userFriendList = new ArrayList<User>();
        userFriendList.add(user1);
        userFriendList.add(user2);
        userFriendList.add(user3);
        userFriendList.add(user4);
        userFriendList.add(user5);
        userFriendList.add(user6);
        userFriendList.add(user7);
        userFriendList.add(user8);
        userFriendList.add(user9);




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
        System.out.println(selectedName + "in doAdd");

        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("friendUserName");
        System.out.println(userName + "in doAdd");

        User userTemp = UserDao.findby_userName(userName);
        userTemp.setUserPortraitUrl("img/portrait.png");
        for (GroupOwner ownerTemp : groupList) {
            if (ownerTemp.getGroupName().equals(selectedName)) {
                
                ownerTemp.getUsers().add(userTemp);
                //存入数据库
                ArrayList tempList = new ArrayList(ownerTemp.getUsers());
                userFriendList = tempList;
            }
        }
        
        
        return "UserFriends";
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
