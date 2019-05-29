package com.owtraducoes.metting.bd_estrutura;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class open_helper extends SQLiteOpenHelper {

    public open_helper(@Nullable Context context) {
        super(context, "dados_metting", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(script_tables.getCreateTableMatriculados());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {

        if (old_version < 2) {
            db.execSQL(script_tables.getCreateTableTemas());
        }

    }

}
