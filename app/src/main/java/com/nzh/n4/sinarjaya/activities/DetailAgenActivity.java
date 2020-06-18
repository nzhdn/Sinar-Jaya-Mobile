package com.nzh.n4.sinarjaya.activities;


import android.os.Bundle;
import android.content.SharedPreferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.nzh.n4.sinarjaya.R;
import com.nzh.n4.sinarjaya.db.DatabaseHelper;
import com.nzh.n4.sinarjaya.helper.Constants;


public class DetailAgenActivity extends AppCompatActivity {
    //deklarasi
    ImageView ivGambarAgen;
    TextView tvAlamatAgen, tvDeskripsiAgen;
    String idAgen, namaAgen, gambarAgen, alamatAgen, deskripsiAgen, latAgen, longAgen;
    private static final String TAG = "DetailAgenActivity";
    private static final String TAG_PREF = "setting";
    private static final String TAG_FAV = "favorite";
    Boolean isFav;
    FloatingActionButton fab;
    DatabaseHelper database = new DatabaseHelper(this);
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //hubungkan java dengan xml nya
        ivGambarAgen = (ImageView) findViewById(R.id.iv_detail_gambar);
        tvAlamatAgen = (TextView) findViewById(R.id.tv_detail_alamat);
        tvDeskripsiAgen = (TextView) findViewById(R.id.tv_detail_deskripsi);
        //menampung data yang dikirim
        Bundle b = getIntent().getExtras();
        final String namaAgen = b.getString(Constants.NAMA_AGEN);
        final String gambarAgen = b.getString(Constants.GAMBAR_AGEN);
        final String alamatAgen = b.getString(Constants.ALAMAT_AGEN);
        final String deskripsiAgen = b.getString(Constants.DESKRIPSI_AGEN);
        //memasukan data yg didapat ke layout
        getSupportActionBar().setTitle(namaAgen);
        Glide.with(this).load("https://drive.google.com/thumbnail?id=" + gambarAgen).into(ivGambarAgen);
        tvAlamatAgen.setText(alamatAgen);
        tvDeskripsiAgen.setText(deskripsiAgen);

        pref = getSharedPreferences(TAG_PREF, MODE_PRIVATE);
        isFav = pref.getBoolean(TAG_FAV + idAgen, false);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        cekFavorite(isFav);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFav) {
                    //jika sebelumnya favorit
                    //jika favorit akan didelete dari database favorit ygada di SQLite
                    //menghapus wisata di database
                    database.delete(namaAgen);

                    Snackbar.make(view, "Agen favorit berhasil dihapus", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    SharedPreferences sp = getSharedPreferences(TAG_PREF, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    //membuat statsu fav menjadi false
                    editor.putBoolean(TAG_FAV + idAgen, false);
                    editor.commit();
                    isFav = false;
                } else {
                    //jika sebelumnya not favorit
                    //data akan dimasukan ke dalam database favorit

                    //memasukan wisata favorit ke database
                    long id = database.insertData(namaAgen, gambarAgen, alamatAgen, deskripsiAgen, latAgen, longAgen);

                    if (id <= 0) {
                        Snackbar.make(view, "Gagal masuk ke favorit", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(view, "Agen berhasil dimasukan ke favorit", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        SharedPreferences sp = getSharedPreferences(TAG_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(TAG_FAV + idAgen, true);
                        editor.commit();
                        isFav = true;
                        //fab.setImageResource(R.drawable.ic_action_isfavorit);
                    }
                }
                //memanggil cekFavorit saat fab diklik
                cekFavorite(isFav);
            }
            //
        });
    }

    private void cekFavorite(Boolean isFav) {
        if (isFav) {
            fab.setImageResource(R.drawable.ic_action_favorite);
        } else {
            fab.setImageResource(R.drawable.ic_action_not_favorite);
        }

    }

}
