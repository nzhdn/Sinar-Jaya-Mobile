package com.nzh.n4.sinarjaya.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.nzh.n4.sinarjaya.model.Agenbus;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "dbagen";
    private final static String DATABASE_TABLE = "table_agen";
    private final static String AGEN_ID = "_id";
    private final static String NAMA_AGEN = "nama_agen";
    private final static String GAMBAR_AGEN = "gambar_agen";
    private final static String ALAMAT_AGEN = "alamat_agen";
    private final static String DESKRIPSI_AGEN = "deskripsi_agen";
    private final static String LATITUDE_AGEN = "latitude_agen";
    private final static String LONGITUDE_AGEN = "longitude_agen";

    private final static int DATABASE_VERSION = 4;

    private final static String CREATE_TABLE = "CREATE TABLE "+ DATABASE_TABLE
            + " ("+AGEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NAMA_AGEN+" VARCHAR(200), "
            +GAMBAR_AGEN+" VARCHAR(200), "
            +ALAMAT_AGEN+" TEXT, "
            +DESKRIPSI_AGEN+" TEXT, "
            +LATITUDE_AGEN+" VARCHAR(20), "
            +LONGITUDE_AGEN+" VARCHAR(20));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
        onCreate(db);
    }

    public long insertData(String namaAgen,
                           String gambarAgen,
                           String alamatAgen,
                           String deskripsiAgen,
                           String latAgen,
                           String longAgen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_AGEN, namaAgen);
        contentValues.put(GAMBAR_AGEN, gambarAgen);
        contentValues.put(ALAMAT_AGEN, alamatAgen);
        contentValues.put(DESKRIPSI_AGEN, deskripsiAgen);
        contentValues.put(LATITUDE_AGEN, latAgen);
        contentValues.put(LONGITUDE_AGEN, longAgen);
        long id = db.insert(DATABASE_TABLE,null,contentValues);
        db.close();
        return id;
    }

    public int delete(String namaAgen){
        SQLiteDatabase db = this.getWritableDatabase();
        String namaKolomnya = NAMA_AGEN + " = ?";
        String[] nilaiFieldnya = {namaAgen};

        int count = db.delete(DATABASE_TABLE,namaKolomnya,nilaiFieldnya);
        return count;
    }

    public ArrayList<Agenbus> getDataFavorite() {
        ArrayList<Agenbus>listAgenbusFavorite = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columName = {AGEN_ID, NAMA_AGEN,
                                GAMBAR_AGEN,
                                ALAMAT_AGEN,
                                DESKRIPSI_AGEN,
                                LATITUDE_AGEN,
                                LONGITUDE_AGEN};

        Cursor cursor = db.query(DATABASE_TABLE,
                columName,
                null,
                null,
                null,
                null,
                null);
        if(cursor!=null){
            while (cursor.moveToNext()){

                int idAgen = cursor.getInt(cursor.getColumnIndex(AGEN_ID));
                String namaAgen = cursor
                        .getString(cursor.getColumnIndex(NAMA_AGEN));
                String gambarAgen = cursor
                        .getString(cursor.getColumnIndex(GAMBAR_AGEN));
                String alamatAgen = cursor
                        .getString(cursor.getColumnIndex(ALAMAT_AGEN));
                String deskripsiAgen = cursor
                        .getString(cursor.getColumnIndex(DESKRIPSI_AGEN));
                String latAgen = cursor
                        .getString(cursor.getColumnIndex(LATITUDE_AGEN));
                String longAgen = cursor
                        .getString(cursor.getColumnIndex(LONGITUDE_AGEN));

                Agenbus agenbusFavorite = new Agenbus();
                agenbusFavorite.setIdAgenbus(String.valueOf(idAgen));
                agenbusFavorite.setNamaAgenbus(namaAgen);
                agenbusFavorite.setGambarAgenbus(gambarAgen);
                agenbusFavorite.setAlamatAgenbus(alamatAgen);
                agenbusFavorite.setDeskripsiAgenbus(deskripsiAgen);
                agenbusFavorite.setLatitudeAgenbus(latAgen);
                agenbusFavorite.setLongitudeAgenbus(longAgen);



                listAgenbusFavorite.add(agenbusFavorite);
            }
        }
        db.close();
        return listAgenbusFavorite;
    }
}
