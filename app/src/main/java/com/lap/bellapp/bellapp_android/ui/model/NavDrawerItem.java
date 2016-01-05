package com.lap.bellapp.bellapp_android.ui.model;

/**
 * Created by Ravi on 29/07/15.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String titleItemKey;
    private String imageName;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.titleItemKey = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitleItemKey() {
        return titleItemKey;
    }

    public void setTitleItemKey(String titleItemKey) {
        this.titleItemKey = titleItemKey;
    }

    public String getIcMenu() {
        return imageName;
    }

    public void setIcMenu(String icMenu) {
        this.imageName = icMenu;
    }
}
