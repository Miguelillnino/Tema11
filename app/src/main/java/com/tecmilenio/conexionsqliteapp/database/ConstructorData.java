package com.tecmilenio.conexionsqliteapp.database;

import android.content.Context;

import com.tecmilenio.conexionsqliteapp.pojo.Information;
import com.tecmilenio.conexionsqliteapp.pojo.Response;

import java.util.List;

public class ConstructorData {

    private Context context;

    public ConstructorData(Context context) {
        this.context = context;
    }

    public void startDb() {
        TestAdapter mDbHelper = new TestAdapter(context);
        mDbHelper.createDatabase();
        mDbHelper.open();
        //Cursor testdata = mDbHelper.getTestData();
        mDbHelper.close();
    }

    public Response insertInformation (Information information) {
        BaseDatos db = new BaseDatos(context);
        System.out.println("Tecmi guardare la inf :D");
        return db.insertInformation(information);
    }

    public Response editInformation (Information information) {
        BaseDatos db = new BaseDatos(context);
        System.out.println("Tecmi actualizareee la inf :D");
        return db.editInformation(information);
    }

    public Boolean deleteUser (Long idUser) {
        BaseDatos db = new BaseDatos(context);
        System.out.println("Tecmi eliminare idUser : "+idUser);
        return db.deleteUser(idUser);
    }
}
