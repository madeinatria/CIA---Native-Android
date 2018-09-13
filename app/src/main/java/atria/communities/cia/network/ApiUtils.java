package atria.communities.cia.network;

/**
 * Created by suryamurugan on 28/3/18.
 */

public class ApiUtils {

    //public static final String BASE_URL= "https://adversize.in/";
    public static final String BASE_URL= "https://docs.google.com/forms/d/1LQsSFcdPMmNcEGaNo1vup-PJQgXftbEj_n3dNGMauLQ/";
    public static UserService getUserService(){

        return RetrofitClient.getClient(BASE_URL).create(UserService.class);

    }
}
