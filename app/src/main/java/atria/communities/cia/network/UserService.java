package atria.communities.cia.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by suryamurugan on 28/3/18.
 */

public interface UserService {

/*
    @GET("search")
    Call<HObj> search(@Query("checkin") String checkin,
                      @Query("checkout") String checkout,
                      @Query("nors") String nors,
                      @Query("city") String city);*/


    @FormUrlEncoded
    @POST("formResponse")
    Call<Void> register(@Field("emailAddress") String email,
                        @Field("entry.103895739") String name,
                        @Field("entry.2032393856") String usn,
                        @Field("entry.339139218") String phone,
                        @Field("entry.958008780") String other,
                        @Field("entry.1974519046") String aiml,
                        @Field("entry.1974519046") String web,
                        @Field("entry.1974519046") String mobile,
                        @Field("entry.1974519046") String iot,
                        @Field("entry.1974519046") String robotics



                        );

}
