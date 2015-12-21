package net.henriqueof.contactlist.Helpers;

import android.provider.BaseColumns;

/**
 * Created by Carlos Henrique on 21/12/2015.
 */
public final class ContactContract {
    public ContactContract() {}

    // Base contact info table
    public class Contact implements BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String COLUMN_NAME_CONTACT_NAME = "name";
    }

    // Groups table
    public class Group implements BaseColumns {
        public static final String TABLE_NAME = "groups";
        public static final String COLUMN_NAME_GROUP_NAME = "name";
    }

    // Contact-group relationship
    public class ContactGroup {
        public static final String TABLE_NAME = "contact_group";
        public static final String COLUMN_NAME_CONTACT_ID = "contact_id";
        public static final String COLUMN_NAME_GROUP_ID = "group_id";
    }

    // Email addresses
    public class Email {
        public static final String TABLE_NAME = "email";
        public static final String COLUMN_NAME_CONTACT_ID = "contact_id";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_EMAIL = "email";
    }

    // Phone numbers table
    public class Phone {
        public static final String TABLE_NAME = "phone";
        public static final String COLUMN_NAME_CONTACT_ID = "contact_id";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_NUMBER = "number";

    }
}
