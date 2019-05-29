package com.owtraducoes.metting.bd_access.bd_crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.owtraducoes.metting.bd_access.bd_class.tema_class;

public class tema_crud {

    SQLiteDatabase conexao_bd;

    public tema_crud(SQLiteDatabase conexao) {
        conexao_bd = conexao;
    }

    public tema_class buscar(String dt_tema) {

        tema_class tema = new tema_class();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM TEMA WHERE VID_BUSCA = ?");

        String[] paramemtros = new String[1];
        paramemtros[0] = dt_tema;

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), paramemtros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            tema.vid_t_1tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_1TEM"));

        }

        return tema;

    }

    public void insere_tema(tema_class tema) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("VID_ID", tema.vid_id);
        contentvalues.put("VID_ID_SITE", tema.vid_id_site);
        contentvalues.put("VID_LEITURA", tema.vid_leitura);
        contentvalues.put("VID_T_1TMP", tema.vid_t_1tmp);
        contentvalues.put("VID_T_1TEM", tema.vid_t_1tem);
        contentvalues.put("VID_T_2TMP", tema.vid_t_2tmp);
        contentvalues.put("VID_T_2TEM", tema.vid_t_2tem);
        contentvalues.put("VID_T_LEIT", tema.vid_t_leit);
        contentvalues.put("VID_M_1TEM", tema.vid_m_1tem);
        contentvalues.put("VID_M_1VIDEO", tema.vid_m_1video);
        contentvalues.put("VID_M_1TMP", tema.vid_m_1tmp);
        contentvalues.put("VID_M_2TEM", tema.vid_m_2tem);
        contentvalues.put("VID_M_2VIDEO", tema.vid_m_2video);
        contentvalues.put("VID_M_2TMP", tema.vid_m_2tmp);
        contentvalues.put("VID_M_3TEM", tema.vid_m_3tem);
        contentvalues.put("VID_M_3VIDEO", tema.vid_m_3video);
        contentvalues.put("VID_M_3TMP", tema.vid_m_3tmp);
        contentvalues.put("VID_M_4TEM", tema.vid_m_4tem);
        contentvalues.put("VID_M_4VIDEO", tema.vid_m_4video);
        contentvalues.put("VID_M_4TMP", tema.vid_m_4tmp);
        contentvalues.put("VID_C_1TMP", tema.vid_c_1tmp);
        contentvalues.put("VID_C_1TEM", tema.vid_c_1tem);
        contentvalues.put("VID_C_2TMP", tema.vid_c_2tmp);
        contentvalues.put("VID_C_2TEM", tema.vid_c_2tem);
        contentvalues.put("VID_UPDT", tema.vid_updt);
        contentvalues.put("VID_BUSCA", tema.vid_busca);

        conexao_bd.insert("TEMA", null, contentvalues);

    }

    public void altera_tema(tema_class tema) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("VID_ID", tema.vid_id);
        //contentvalues.put("VID_ID_SITE", tema.vid_id_site);
        contentvalues.put("VID_LEITURA", tema.vid_leitura);
        contentvalues.put("VID_T_1TMP", tema.vid_t_1tmp);
        contentvalues.put("VID_T_1TEM", tema.vid_t_1tem);
        contentvalues.put("VID_T_2TMP", tema.vid_t_2tmp);
        contentvalues.put("VID_T_2TEM", tema.vid_t_2tem);
        contentvalues.put("VID_T_LEIT", tema.vid_t_leit);
        contentvalues.put("VID_M_1TEM", tema.vid_m_1tem);
        contentvalues.put("VID_M_1VIDEO", tema.vid_m_1video);
        contentvalues.put("VID_M_1TMP", tema.vid_m_1tmp);
        contentvalues.put("VID_M_2TEM", tema.vid_m_2tem);
        contentvalues.put("VID_M_2VIDEO", tema.vid_m_2video);
        contentvalues.put("VID_M_2TMP", tema.vid_m_2tmp);
        contentvalues.put("VID_M_3TEM", tema.vid_m_3tem);
        contentvalues.put("VID_M_3VIDEO", tema.vid_m_3video);
        contentvalues.put("VID_M_3TMP", tema.vid_m_3tmp);
        contentvalues.put("VID_M_4TEM", tema.vid_m_4tem);
        contentvalues.put("VID_M_4VIDEO", tema.vid_m_4video);
        contentvalues.put("VID_M_4TMP", tema.vid_m_4tmp);
        contentvalues.put("VID_C_1TMP", tema.vid_c_1tmp);
        contentvalues.put("VID_C_1TEM", tema.vid_c_1tem);
        contentvalues.put("VID_C_2TMP", tema.vid_c_2tmp);
        contentvalues.put("VID_C_2TEM", tema.vid_c_2tem);
        contentvalues.put("VID_UPDT", tema.vid_updt);
        contentvalues.put("VID_BUSCA", tema.vid_busca);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(tema.vid_id_site);

        conexao_bd.update("TEMA", contentvalues, "ID = ?", parametros);

    }

    public int tema_count(int id_site) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(VID_ID) AS TOTAL FROM TEMA WHERE VID_ID_SITE = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id_site);

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        resultado.moveToFirst();

        return resultado.getInt(resultado.getColumnIndexOrThrow("TOTAL"));

    }

}
