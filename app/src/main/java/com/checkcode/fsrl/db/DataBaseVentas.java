package com.checkcode.fsrl.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dacorp.database.ConectorDB;
import com.dacorp.database.Configuration;
import com.dacorp.database.error.EjecutionDBExcepcion;
import com.dacorp.database.event.DataBaseListener;

import java.io.File;

public class DataBaseVentas extends ConectorDB {

    public DataBaseVentas(Context context) {
        super(context, new Configuration(context.getDir("database",
                Context.MODE_PRIVATE).getAbsolutePath()
                        + File.separator + "DbVentas.sqlitle"),
                new DataBaseListener() {
                    @Override
                    public void createTables(SQLiteDatabase sqliteDatabase) {
                        try{
                            Log.i("Path", sqliteDatabase.getPath());
                            sqliteDatabase.execSQL(ConectorDB.generateSQLCreateTable(Cliente.class));
                        } catch (EjecutionDBExcepcion e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void refreshTables(SQLiteDatabase sqliteDatabase) {

                    }
                });
    }
}
