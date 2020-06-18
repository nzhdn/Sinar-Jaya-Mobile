package com.nzh.n4.sinarjaya.activities;

import android.app.ProgressDialog;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nzh.n4.sinarjaya.R;
import com.nzh.n4.sinarjaya.helper.ServiceClient;
import com.nzh.n4.sinarjaya.helper.ServiceGenerator;
import com.nzh.n4.sinarjaya.model.Agenbus;
import com.nzh.n4.sinarjaya.model.ListAgen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetaAgenActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta_agen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    List<Agenbus> ListAgen = new ArrayList<>();
    ProgressDialog pd;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        //membuat objek serivisnya
        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        //memilih jeinis serivice yang dibutuhkan
        final Call<ListAgen> getListAgenbus = service.getAgenbus("sinjay");

        //efek loading mengambil data
        pd = new ProgressDialog(PetaAgenActivity.this);
        pd.setMessage("Mohon Menunggu");
        pd.show();

        //mengirim request dan menerima respon dari server
        getListAgenbus.enqueue(new Callback<ListAgen>() {
            @Override
            public void onResponse(Call<ListAgen> call, Response<ListAgen> response) {

               //menghilangkan efek loading
                pd.dismiss();
                //menyimpan respon server ke listAgenbus
                ListAgen = response.body().getListAgenSinarJaya();
                //menampilkan semua marker
                for (int i = 0; i < ListAgen.size(); i++) {
                    //parsing data dari string menjadi Double
                    Double latAgen = Double. valueOf(ListAgen.get(i).getLatitudeAgenbus());
                    Double lngAgen = Double. valueOf(ListAgen.get(i).getLongitudeAgenbus());

                    LatLng lokasiagen = new LatLng(latAgen, lngAgen);
                    mMap.addMarker(new MarkerOptions().position(lokasiagen)
                            .title(ListAgen.get(i).getNamaAgenbus())
                    );
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasiagen,13));
                }
            }

            @Override
            public void onFailure(Call<ListAgen> call, Throwable t) {
                Toast. makeText(PetaAgenActivity. this, "" + t.getMessage(),
                        Toast. LENGTH_SHORT).show();
            }
        });
    }
}