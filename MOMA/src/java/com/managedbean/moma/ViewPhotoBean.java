/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class ViewPhotoBean implements Serializable {

    private List<String> images;
    @PostConstruct
    public void init() {
        System.out.println("init_called");
        images = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            images.add(i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    /**
     * Creates a new instance of ViewPhotoBean
     */
   
}
