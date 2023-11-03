package com.example.ameacas.ameacas.ambientais;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class AmeacaDataBase {
    Context ctx;
    public static final String DATABASE_NAME = "ameaca.db";
    public static final Integer DATABASE_VERSION = 13;
    private static SQLiteDatabase db;

    public AmeacaDataBase(Context ctx) {
        this.ctx = ctx;
        db = new AmeacaSQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class AmeacaTable implements BaseColumns {
        public static final String NOME_DA_TABELA = "ameaca";
        public static final String COLUNA_ENDERECO = "endereco";
        public static final String COLUNA_DATA = "data";
        public static final String COLUNA_DESCRICAO = "descricao";

        public static String getSQL() {
            String sql = "CREATE TABLE " + NOME_DA_TABELA + " (" + _ID + " INTEGER PRIMARY KEY, " +
                    COLUNA_ENDERECO + " TEXT, " + COLUNA_DATA + " TEXT, " + COLUNA_DESCRICAO + " TEXT)";
            return sql;
        }
    }
    public Long addAmeaca(Ameaca ameaca) {
        ContentValues values = new ContentValues();
        values.put(AmeacaTable.COLUNA_ENDERECO, ameaca.getEndereco());
        values.put(AmeacaTable.COLUNA_DATA, ameaca.getData());
        values.put(AmeacaTable.COLUNA_DESCRICAO, ameaca.getDescricao());

        return db.insert(AmeacaTable.NOME_DA_TABELA, null, values);
    }

    @SuppressLint("Range")
    public Ameaca getAmeaca(Long id) {
        String cols[] = {AmeacaTable._ID, AmeacaTable.COLUNA_ENDERECO, AmeacaTable.COLUNA_DESCRICAO,
                AmeacaTable.COLUNA_DATA};
        String args[] = {id.toString()};
        Cursor cursor = db.query(AmeacaTable.NOME_DA_TABELA, cols, AmeacaTable._ID +
                "=?", args, null, null, AmeacaTable._ID);

        if (cursor.getCount() != 1) {
            return null;
        }

        cursor.moveToNext();

        return new Ameaca(
                cursor.getLong(cursor.getColumnIndex(AmeacaTable._ID)),
                cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUNA_ENDERECO)),
                cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUNA_DATA)),
                cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUNA_DESCRICAO))
        );
    }

    @SuppressLint("Range")
    public List<Ameaca> getAmeacas() {
        String cols[] = {AmeacaTable._ID, AmeacaTable.COLUNA_ENDERECO, AmeacaTable.COLUNA_DESCRICAO,
                AmeacaTable.COLUNA_DATA};
        Cursor cursor = db.query(AmeacaTable.NOME_DA_TABELA, cols, null, null,
                null, null, AmeacaTable.COLUNA_ENDERECO);
        List<Ameaca> ameacas = new ArrayList<>();
        Ameaca ameaca;

        while (cursor.moveToNext()) {
            ameaca = new Ameaca(cursor.getLong(cursor.getColumnIndex(AmeacaTable._ID)),
                    cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUNA_ENDERECO)),
                    cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUNA_DATA)),
                    cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUNA_DESCRICAO))
            );
            ameacas.add(ameaca);
        }

        return ameacas;
    }

    public Integer removeAmeaca(Ameaca ameaca) {
        String args[] = {ameaca.getId().toString()};
        return db.delete(AmeacaTable.NOME_DA_TABELA, AmeacaTable._ID + "=?", args);
    }

    public Integer updateAmeaca(Ameaca s) {
        String args[] = {s.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(AmeacaTable.COLUNA_ENDERECO, s.getEndereco());
        values.put(AmeacaTable.COLUNA_DESCRICAO, s.getDescricao());
        values.put(AmeacaTable.COLUNA_DATA, s.getData());
        return db.update(AmeacaTable.NOME_DA_TABELA, values, AmeacaTable._ID + "=?", args);
    }
    private class AmeacaSQLiteDatabaseHelper extends SQLiteOpenHelper {
        public AmeacaSQLiteDatabaseHelper() {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(AmeacaTable.getSQL());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + AmeacaTable.NOME_DA_TABELA);
            onCreate(db);
        }
    }
}

