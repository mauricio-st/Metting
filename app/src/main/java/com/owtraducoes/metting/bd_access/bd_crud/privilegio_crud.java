package com.owtraducoes.metting.bd_access.bd_crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.owtraducoes.metting.bd_access.bd_class.designacao_class;
import com.owtraducoes.metting.bd_access.bd_class.privilegio_class;

public class privilegio_crud {

    SQLiteDatabase conexao_bd;

    public privilegio_crud(SQLiteDatabase conexao) {
        conexao_bd = conexao;
    }

    public void insere_privilegio(privilegio_class privilegio) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("PRI_ID", privilegio.pri_id);
        contentvalues.put("PRI_ID_SITE", privilegio.pri_id_site);
        contentvalues.put("PRI_VIDSOM", privilegio.pri_vidsom);
        contentvalues.put("PRI_VIDVID", privilegio.pri_vidvid);
        contentvalues.put("PRI_VIDVOL", privilegio.pri_vidvol);
        contentvalues.put("PRI_VIDIND1", privilegio.pri_vidind1);
        contentvalues.put("PRI_VIDIND2", privilegio.pri_vidind2);
        contentvalues.put("PRI_VIDLIMP", privilegio.pri_vidlimp);
        contentvalues.put("PRI_CAMP", privilegio.pri_camp);
        contentvalues.put("PRI_DISSOM", privilegio.pri_dissom);
        contentvalues.put("PRI_DISVID", privilegio.pri_disvid);
        contentvalues.put("PRI_DISPRES", privilegio.pri_dispres);
        contentvalues.put("PRI_DISNUM", privilegio.pri_disnum);
        contentvalues.put("PRI_DISORA", privilegio.pri_disora);
        contentvalues.put("PRI_DISNCONG", privilegio.pri_disncong);
        contentvalues.put("PRI_DISNCID", privilegio.pri_disncid);
        contentvalues.put("PRI_DISVOL", privilegio.pri_disvol);
        contentvalues.put("PRI_DISIND1", privilegio.pri_disind1);
        contentvalues.put("PRI_DISIND2", privilegio.pri_disind2);
        contentvalues.put("PRI_DISANF", privilegio.pri_disanf);
        contentvalues.put("PRI_DISLEIT", privilegio.pri_disleit);
        contentvalues.put("PRI_DISLIMP", privilegio.pri_dislimp);
        contentvalues.put("PRI_ESPEC", privilegio.pri_espec);
        contentvalues.put("PRI_CONG", privilegio.pri_cong);
        contentvalues.put("PRI_UPDT", privilegio.pri_updt);
        contentvalues.put("PRI_BUSCA", privilegio.pri_busca);

        conexao_bd.insert("PRIVILEGIO", null, contentvalues);

    }

    public void altera_privilegio(privilegio_class privilegio) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("PRI_ID", privilegio.pri_id);
        //contentvalues.put("PRI_ID_SITE", privilegio.pri_id_site);
        contentvalues.put("PRI_VIDSOM", privilegio.pri_vidsom);
        contentvalues.put("PRI_VIDVID", privilegio.pri_vidvid);
        contentvalues.put("PRI_VIDVOL", privilegio.pri_vidvol);
        contentvalues.put("PRI_VIDIND1", privilegio.pri_vidind1);
        contentvalues.put("PRI_VIDIND2", privilegio.pri_vidind2);
        contentvalues.put("PRI_VIDLIMP", privilegio.pri_vidlimp);
        contentvalues.put("PRI_CAMP", privilegio.pri_camp);
        contentvalues.put("PRI_DISSOM", privilegio.pri_dissom);
        contentvalues.put("PRI_DISVID", privilegio.pri_disvid);
        contentvalues.put("PRI_DISPRES", privilegio.pri_dispres);
        contentvalues.put("PRI_DISNUM", privilegio.pri_disnum);
        contentvalues.put("PRI_DISORA", privilegio.pri_disora);
        contentvalues.put("PRI_DISNCONG", privilegio.pri_disncong);
        contentvalues.put("PRI_DISNCID", privilegio.pri_disncid);
        contentvalues.put("PRI_DISVOL", privilegio.pri_disvol);
        contentvalues.put("PRI_DISIND1", privilegio.pri_disind1);
        contentvalues.put("PRI_DISIND2", privilegio.pri_disind2);
        contentvalues.put("PRI_DISANF", privilegio.pri_disanf);
        contentvalues.put("PRI_DISLEIT", privilegio.pri_disleit);
        contentvalues.put("PRI_DISLIMP", privilegio.pri_dislimp);
        contentvalues.put("PRI_ESPEC", privilegio.pri_espec);
        contentvalues.put("PRI_CONG", privilegio.pri_cong);
        contentvalues.put("PRI_UPDT", privilegio.pri_updt);
        contentvalues.put("PRI_BUSCA", privilegio.pri_busca);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(privilegio.pri_id_site);

        conexao_bd.update("PRIVILEGIO", contentvalues, "PRI_ID_SITE = ?", parametros);

    }

    public int privilegio_count(int id_site) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(PRI_ID) AS TOTAL FROM PRIVILEGIO WHERE PRI_ID_SITE = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id_site);

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        resultado.moveToFirst();

        return resultado.getInt(resultado.getColumnIndexOrThrow("TOTAL"));

    }
}
