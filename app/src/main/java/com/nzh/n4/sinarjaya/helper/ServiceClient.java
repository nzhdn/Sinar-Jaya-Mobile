package com.nzh.n4.sinarjaya.helper;

import com.nzh.n4.sinarjaya.model.ListAgen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ServiceClient {
    @GET("exec")
    Call<ListAgen> getAgenbus(@Query("sheet") String sinjay);
}
