package com.example.louisompusunggu.demospinner;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by louis.ompusunggu on 5/12/2017.
 */
public interface OutboundService {
    @FormUrlEncoded
    @POST("rekening")
    Call<ResponseBody> listAccount(@Field("session_key") String session_key);
}
