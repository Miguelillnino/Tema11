package com.tecmilenio.conexionsqliteapp.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tecmilenio.conexionsqliteapp.pojo.Information;
import com.tecmilenio.conexionsqliteapp.pojo.Response;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class BaseDatos extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = ConstantesBaseDatos.DATABASE_VERSION;
    public static final String DATABASE_NAME = ConstantesBaseDatos.DATABASE_NAME;

    Date currentTime = Calendar.getInstance().getTime();

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        close();
        this.getWritableDatabase();
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    /**
     * Se crea la estructura de la base de datos, todas las tablas (Create table)
     */
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public Response insertInformation (Information information) {
        Response resp = new Response();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.DG_NAME, information.getName());
            contentValues.put(ConstantesBaseDatos.DG_UNIVERSITY, information.getUniversity());
            contentValues.put(ConstantesBaseDatos.DG_CARRER, information.getCarrer());
            Long id = db.insert(ConstantesBaseDatos.DG_TABLE_NAME, null, contentValues);
            System.out.println("Si guarde inf ID: "+ id);
            Logger.getLogger("Looger").info("Si guarde inf ID: "+ id);
            resp.setSuccess(true);
            resp.setMessage("Operación exitosa.");
            resp.setId(id);
        }catch (Exception e){
            System.out.println("Tecmi err: "+ e);
            resp.setSuccess(false);
            resp.setMessage("Operación fallida.");
            resp.setId(0L);
        } finally {
            db.close();
        }
        return resp;
    }

    public Response editInformation (Information information) {
        Response resp = new Response();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            System.out.println("Tecmi getName: "+ information.getName());
            System.out.println("Tecmi getName: "+ information.getId());
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.DG_ID, information.getId());
            contentValues.put(ConstantesBaseDatos.DG_NAME, information.getName());
            contentValues.put(ConstantesBaseDatos.DG_UNIVERSITY, information.getUniversity());
            contentValues.put(ConstantesBaseDatos.DG_CARRER, information.getCarrer());
            db.update(ConstantesBaseDatos.DG_TABLE_NAME, contentValues, ConstantesBaseDatos.DG_ID + "=" + information.getId(), null);

            System.out.println("Tecmi si actualice inf ID: "+ information.getId());
            resp.setSuccess(true);
            resp.setMessage("Operación exitosa.");
            resp.setId(information.getId());
        }catch (Exception e){
            System.out.println("Tecmi err: "+ e);
            resp.setSuccess(false);
            resp.setMessage("Operación fallida.");
            resp.setId(0L);
        } finally {
            db.close();
        }
        return resp;
    }

    public Boolean deleteUser (Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "DELETE FROM " + ConstantesBaseDatos.DG_TABLE_NAME + " WHERE " + ConstantesBaseDatos.DG_ID + " = " + id + " ;";
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

}