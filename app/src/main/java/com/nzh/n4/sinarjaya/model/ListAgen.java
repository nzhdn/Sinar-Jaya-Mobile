package com.nzh.n4.sinarjaya.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ListAgen {
    @SerializedName("sinjay")
    private List<Agenbus> listAgenSinarJaya;

    public List<Agenbus> getListAgenSinarJaya(){
        return listAgenSinarJaya;
    }
}
