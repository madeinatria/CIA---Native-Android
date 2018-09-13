package atria.communities.cia.User;

/**
 * Created by suryamurugan on 8/4/18.
 */

public class Usermain {





    String username, useremail, usnnumber,phonenumber;



    public Usermain(String username, String usnnumber, String useremail, String phonenumber) {
        this.username = username;
        this.usnnumber = usnnumber;
        this.useremail = useremail;
        this.phonenumber = phonenumber;
    }

    public Usermain(String useremail, String usnnumber) {
        this("", "1", useremail, usnnumber);
    }



}
