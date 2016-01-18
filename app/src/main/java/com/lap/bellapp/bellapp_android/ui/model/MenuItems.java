package com.lap.bellapp.bellapp_android.ui.model;

/**
 * Created by juangarcia on 1/5/16.
 */
public enum MenuItems {
    APPOINTMENTS("drawer.menu.appointments.item"),
    ACCOUNT("drawer.menu.account.item"),
    CATEGORIES("drawer.menu.categories.item");

    private String keyValue;

    MenuItems(String keyValue) {
        this.keyValue = keyValue;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : keyValue.equals(otherName);
    }

    public String toString() {
        return this.keyValue;
    }

//    public static MenuItems getMenyByString(String key) {
//        for (MenuItems enumObj : MenuItems.values()) {
//            String itemKey = enumObj.toString();
//            Log.i("MenuItems","ScannedKey: "+itemKey);
//            Log.i("MenuItems","SelectedKey: "+key);
//            if ( itemKey.equals(key)) {
//                return enumObj;
//            }
//        }
//        return null;
//    }

    public static MenuItems getMenyByString(String text) {
        if (text != null) {
            for (MenuItems b : MenuItems.values()) {
                if (text.equalsIgnoreCase(b.keyValue)) {
                    return b;
                }
            }
        }
        return null;
    }

    public String getMenuTitleKey(){
        return keyValue+".title";
    }

    public String getIconName(){
        switch (this){
            case APPOINTMENTS:
                return "ic_calendar";
            case ACCOUNT:
                return "ic_account";
            case CATEGORIES:
                return "ic_calendar";
            default:
                return "N/A";
        }
    }

}
