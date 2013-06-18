package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.PhotoDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.Photo;
import com.entity.moma.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class ViewPhotoBean implements Serializable {

    private Brochure brochure;
    private ArrayList<Photo> images;
    private String photo_comment;
    private int brochureId;

    public ViewPhotoBean() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureToeditId") != null) {
            brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureToeditId"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("brochureToeditId", brochureId);
        } else {
            brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("brochureToeditId").toString());
        }
        brochure = BrochureDao.findby_brochureId(brochureId);
        images = new ArrayList(PhotoDao.findby_brochureId(brochure.getBrochureId()));
        System.out.println(images.size() + "init_called");
    }

    public ArrayList<Photo> getImages() {
        return images;
    }

    public void setImages(ArrayList<Photo> images) {
        this.images = images;
    }

    public String getPhoto_comment() {
        return photo_comment;
    }

    public void setPhoto_comment(String photo_comment) {
        this.photo_comment = photo_comment;
    }

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public int getBrochureId() {
        return brochureId;
    }

    public void setBrochureId(int brochureId) {
        this.brochureId = brochureId;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            File file = new File("/Users/bianshujun/Downloads/MOMA/MOMA/web/Data/Brochure/" + brochure.getBrochureId() + "/photo");
            if (!file.exists()) {
                file.mkdirs();
            }
            File targetFolder = new File("/Users/bianshujun/Downloads/MOMA/MOMA/web/Data/Brochure/" + brochure.getBrochureId() + "/photo");
            //注意：有关上传照片的小问题。。这里new File（）里的路径必须写本地的实际路径，运行时注意更改
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();

            String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
            User user = UserDao.findby_userName(userName);
            Photo newPhoto = new Photo();
            newPhoto.setPhotoTitle(event.getFile().getFileName());
            newPhoto.setBrochure(brochure);
            newPhoto.setUser(user);
            newPhoto.setPhotoStartTime(new Date());
            System.out.println(event.getFile().getFileName());
            newPhoto.setPhotoUrl("./Data/Brochure/" + brochure.getBrochureId() + "/photo/" + event.getFile().getFileName());
            PhotoDao.add_photo(newPhoto);
            changeBrochureLatestChange(brochure, newPhoto);
            images = (ArrayList<Photo>) PhotoDao.findby_brochureId(brochure.getBrochureId());
            System.out.println("In handleFileUpload");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("viewPhoto.xhtml");
//            System.out.println("IN redirect");
//        } catch (IOException ex) {
//            Logger.getLogger(ViewPhotoBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void changeBrochureLatestChange(Brochure brochure, Photo photo) {
        System.out.println("In changeBrochureLatestChange");
        String userNameChangeDiary = photo.getUser().getUserName();
        brochure.setBrochureModifyTime(new Date());
        brochure.setLatestChange(userNameChangeDiary + " Photo: " + photo.getPhotoTitle());
        brochure.setLatestChangeType("photo");
        BrochureDao.modify_brochure(brochure);
    }
}
/**
 * Creates a new instance of ViewPhotoBean
 */
