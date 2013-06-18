package com.entity.moma;
// Generated 2013-6-17 3:25:11 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * GroupOwner generated by hbm2java
 */
public class GroupOwner  implements java.io.Serializable {


     private Integer groupId;
     private int ownerId;
     private String groupName;
     private Set<User> users = new HashSet<User>(0);

    public GroupOwner() {
    }

	
    public GroupOwner(int ownerId) {
        this.ownerId = ownerId;
    }
    public GroupOwner(int ownerId, String groupName, Set<User> users) {
       this.ownerId = ownerId;
       this.groupName = groupName;
       this.users = users;
    }
   
    public Integer getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public int getOwnerId() {
        return this.ownerId;
    }
    
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    @Override
    public String toString() {
        return this.getGroupName();
    }


}

