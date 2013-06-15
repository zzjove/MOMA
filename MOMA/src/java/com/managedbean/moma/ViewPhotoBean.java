/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.PhotoDao;
import com.entity.moma.Brochure;
import com.entity.moma.Photo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class ViewPhotoBean implements Serializable {
    private Brochure brochure;
    private ArrayList<Photo> images;
    private String photo_comment;

    @PostConstruct
    public void init() {
        brochure = BrochureDao.findby_brochureId(1);
        images = (ArrayList<Photo>) PhotoDao.findby_brochureId(brochure.getBrochureId());
        System.out.println("init_called");
       //这里仅按数字命名作为测试用，实际应该一个一个插入arraylist。。另外还需在上传按钮处加actionListner同步加入相片
//        for (int i = 1; i <= 10; i++) {
//            images.add(i + ".jpg");
//        }
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
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(brochure.getBrochureId());
        try {
            File targetFolder = new File("/Users/bianshujun/NetBeansProjects/MOMA/web/img/album");
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
            System.out.println("In handleFileUpload");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/**
 * Creates a new instance of ViewPhotoBean
 */

