/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helperClass.moma;

import com.dao.hibernate.BrochureDao;
import com.entity.moma.Brochure;

/**
 *
 * @author bianshujun
 */
public class UpdateInfo {

    Brochure brochure;
    String type;
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UpdateInfo(Brochure brochure) {
        System.out.println("In UpdateInfo Construction");
        this.brochure = brochure;
        this.type = brochure.getLatestChangeType();
        if (brochure.getLatestChange() != null) {
            this.content = brochure.getLatestChange();
            System.out.println(brochure.getLatestChange());
        } else {
            this.content = "NO Latest Change";
            System.out.println("brochure latest change is null");
        }
    }

}
