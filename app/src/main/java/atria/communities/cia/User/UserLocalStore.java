package atria.communities.cia.User;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by suryamurugan on 8/4/18.
 */

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(Usermain user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("username", user.username);
        userLocalDatabaseEditor.putString("useremail", user.useremail);
        userLocalDatabaseEditor.putString("phonenumber",user.phonenumber);
        userLocalDatabaseEditor.putString("usnnumber",user.usnnumber);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public Usermain getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String name = userLocalDatabase.getString("username", "");
        String useremail = userLocalDatabase.getString("useremail", "");
        String phonenumber = userLocalDatabase.getString("phonenumber", "");
        String usnnumber = userLocalDatabase.getString("usnnumber","");
        //int vendorid = userLocalDatabase.getInt("vendorid", -1);

        Usermain user = new Usermain(name,usnnumber,useremail,phonenumber);
        return user;
    }
}
