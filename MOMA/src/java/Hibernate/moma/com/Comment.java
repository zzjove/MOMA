package Hibernate.moma.com;
// Generated 2013-5-12 3:09:07 by Hibernate Tools 3.2.1.GA



/**
 * Comment generated by hbm2java
 */
public class Comment  implements java.io.Serializable {


     private Integer commentId;
     private String commentUrl;
     private int commentTargetId;
     private int commentBrochureType;
     private int commentBrochureId;

    public Comment() {
    }

    public Comment(String commentUrl, int commentTargetId, int commentBrochureType, int commentBrochureId) {
       this.commentUrl = commentUrl;
       this.commentTargetId = commentTargetId;
       this.commentBrochureType = commentBrochureType;
       this.commentBrochureId = commentBrochureId;
    }
   
    public Integer getCommentId() {
        return this.commentId;
    }
    
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
    public String getCommentUrl() {
        return this.commentUrl;
    }
    
    public void setCommentUrl(String commentUrl) {
        this.commentUrl = commentUrl;
    }
    public int getCommentTargetId() {
        return this.commentTargetId;
    }
    
    public void setCommentTargetId(int commentTargetId) {
        this.commentTargetId = commentTargetId;
    }
    public int getCommentBrochureType() {
        return this.commentBrochureType;
    }
    
    public void setCommentBrochureType(int commentBrochureType) {
        this.commentBrochureType = commentBrochureType;
    }
    public int getCommentBrochureId() {
        return this.commentBrochureId;
    }
    
    public void setCommentBrochureId(int commentBrochureId) {
        this.commentBrochureId = commentBrochureId;
    }




}


