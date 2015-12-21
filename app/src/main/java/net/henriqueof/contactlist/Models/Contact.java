package net.henriqueof.contactlist.Models;

import android.net.Uri;

/**
 * Created by Carlos Henrique on 06/12/2015.
 */
public class Contact {
    public Contact(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public Uri getPicture() {
        return Picture;
    }

    public String getPhone() {
        return null;
    }

    private String Name;
    private Uri Picture;

    enum DataTable { PHONE, EMAIL, BITHDAY, COMPANY, TITLE, GROUP, WEBSITE }
    enum DataType { MOBILE, WORK, HOME, OTHER}
}
