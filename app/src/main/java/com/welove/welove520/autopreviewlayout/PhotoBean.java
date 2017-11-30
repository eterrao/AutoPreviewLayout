package com.welove.welove520.autopreviewlayout;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raomengyang on 17-11-30.
 * Email    : ericrao@welove-inc.com
 * Desc     :
 * Version  : 1.0
 */

public class PhotoBean implements Serializable {

    private static final long serialVersionUID = 2386449415227803332L;

    private List<Photo> photoList;

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public static class Photo implements Serializable {
        private static final long serialVersionUID = -8181958045913394960L;

        private String url;
        private int resourceId;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }

        @Override
        public String toString() {
            return "Photo{" +
                    "url='" + url + '\'' +
                    ", resourceId=" + resourceId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PhotoBean{" +
                "photoList=" + photoList +
                '}';
    }
}
