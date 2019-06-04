package com.owtraducoes.metting.bd_access.bd_crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.owtraducoes.metting.bd_access.bd_class.designacao_class;
import com.owtraducoes.metting.bd_access.bd_class.meiosemana_class;

public class designacao_crud {

    SQLiteDatabase conexao_bd;

    public designacao_crud(SQLiteDatabase conexao) {
        conexao_bd = conexao;
    }

    public meiosemana_class buscar(String dt_designacao) {

        meiosemana_class meiosemana = new meiosemana_class();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT  " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_PRES)     AS VID_PRES, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_ORACAOINICIO) AS VID_ORACAOINICIO, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_ORACAOFINAL ) AS VID_ORACAOFINAL, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_SOM)      AS VID_SOM, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_VOLANT)   AS VID_VOLANT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_IND1)     AS VID_IND1, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_IND2)     AS VID_IND2, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_T_1)      AS VID_T_1, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_T_2)      AS VID_T_2, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_T_LEITA)  AS VID_T_LEITA, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_T_LEAPT)  AS VID_T_LEAPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_T_LEITB)  AS VID_T_LEITB, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_T_LEBPT)  AS VID_T_LEBPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_11A)    AS VID_M_11A, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_1APT)   AS VID_M_1APT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_12A)    AS VID_M_12A," );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_11B)    AS VID_M_11B, ");
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_1BPT)   AS VID_M_1BPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_12B)    AS VID_M_12B, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_21A)    AS VID_M_21A, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_2APT)   AS VID_M_2APT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_22A)    AS VID_M_22A, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_21B)    AS VID_M_21B, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_2BPT)   AS VID_M_2BPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_22B)    AS VID_M_22B, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_31A)    AS VID_M_31A " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_3APT)   AS VID_M_3APT, " );
        sql.append("FROM MEIOSEMANA AS M ");
        //sql.append("LEFT JOIN  AS M ");
        sql.append("WHERE VID_BUSCA = ?");

        String[] parametros = new String[1];
        parametros[0] = dt_designacao;

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

/*          meiosemana.oracaoinicio = resultado.getString(resultado.getColumnIndexOrThrow("VID_ORACAOINICIO"));
            meiosemana.oracaofinal  = resultado.getString(resultado.getColumnIndexOrThrow("VID_ORACAOFINAL"));
            meiosemana.som          = resultado.getString(resultado.getColumnIndexOrThrow("VID_SOM"));
            meiosemana.volante      = resultado.getString(resultado.getColumnIndexOrThrow("VID_VOLANT"));
            meiosemana.indicador1   = resultado.getString(resultado.getColumnIndexOrThrow("VID_IND1"));
            meiosemana.indicador2   = resultado.getString(resultado.getColumnIndexOrThrow("VID_IND2"));
            meiosemana.vid_t_1      = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_1"));
            meiosemana.vid_t_2      = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_2"));
            meiosemana.vid_t_leita  = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEITA"));
            meiosemana.vid_t_leita  = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEITB"));*/

        }

        return meiosemana;

    }

    public void insere_designacao(designacao_class designacao) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("VID_ID", designacao.vid_id);
        contentvalues.put("VID_ID_SITE", designacao.vid_id_site);
        contentvalues.put("VID_PRES", designacao.vid_pres);
        contentvalues.put("VID_ORACAOINICIO", designacao.vid_oracao1);
        contentvalues.put("VID_ORACAOFINAL", designacao.vid_oracao2);
        contentvalues.put("VID_T_1", designacao.vid_t_1);
        contentvalues.put("VID_T_2", designacao.vid_t_2);
        contentvalues.put("VID_T_LEITA", designacao.vid_t_leita);
        contentvalues.put("VID_T_LEAPT", designacao.vid_t_leapt);
        contentvalues.put("VID_T_LEITB", designacao.vid_t_leitb);
        contentvalues.put("VID_T_LEBPT", designacao.vid_t_lebpt);
        contentvalues.put("VID_M_11A", designacao.vid_m_11a);
        contentvalues.put("VID_M_1APT", designacao.vid_m_1apt);
        contentvalues.put("VID_M_12A", designacao.vid_m_12a);
        contentvalues.put("VID_M_11B", designacao.vid_m_11b);
        contentvalues.put("VID_M_1BPT", designacao.vid_m_1bpt);
        contentvalues.put("VID_M_12B", designacao.vid_m_12b);
        contentvalues.put("VID_M_21A", designacao.vid_m_21a);
        contentvalues.put("VID_M_2APT", designacao.vid_m_2apt);
        contentvalues.put("VID_M_22A", designacao.vid_m_22a);
        contentvalues.put("VID_M_21B", designacao.vid_m_21b);
        contentvalues.put("VID_M_2BPT", designacao.vid_m_2bpt);
        contentvalues.put("VID_M_22B", designacao.vid_m_22b);
        contentvalues.put("VID_M_31A", designacao.vid_m_31a);
        contentvalues.put("VID_M_3APT", designacao.vid_m_3apt);
        contentvalues.put("VID_M_32A", designacao.vid_m_32a);
        contentvalues.put("VID_M_31B", designacao.vid_m_31b);
        contentvalues.put("VID_M_3BPT", designacao.vid_m_3bpt);
        contentvalues.put("VID_M_32B", designacao.vid_m_32b);
        contentvalues.put("VID_M_41A", designacao.vid_m_41a);
        contentvalues.put("VID_M_4APT", designacao.vid_m_4apt);
        contentvalues.put("VID_M_42A", designacao.vid_m_42a);
        contentvalues.put("VID_M_41B", designacao.vid_m_41b);
        contentvalues.put("VID_M_4BPT", designacao.vid_m_4bpt);
        contentvalues.put("VID_M_42B", designacao.vid_m_42b);
        contentvalues.put("VID_C_1", designacao.vid_c_1);
        contentvalues.put("VID_C_2", designacao.vid_c_2);
        contentvalues.put("VID_C_3", designacao.vid_c_3);
        contentvalues.put("VID_DIR", designacao.vid_dir);
        contentvalues.put("VID_LEIT", designacao.vid_leit);
        contentvalues.put("VID_CONG", designacao.vid_cong);
        contentvalues.put("VID_ESPECIAL", designacao.vid_especial);
        contentvalues.put("VID_BUSCA", designacao.vid_busca);

        conexao_bd.insert("MEIOSEMANA", null, contentvalues);

    }

    public void altera_designacao(designacao_class designacao) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("VID_ID", designacao.vid_id);
        //contentvalues.put("VID_ID_SITE", designacao.vid_id_site);
        contentvalues.put("VID_PRES", designacao.vid_pres);
        contentvalues.put("VID_ORACAOINICIO", designacao.vid_oracao1);
        contentvalues.put("VID_ORACAOFINAL", designacao.vid_oracao2);
        contentvalues.put("VID_T_1", designacao.vid_t_1);
        contentvalues.put("VID_T_2", designacao.vid_t_2);
        contentvalues.put("VID_T_LEITA", designacao.vid_t_leita);
        contentvalues.put("VID_T_LEAPT", designacao.vid_t_leapt);
        contentvalues.put("VID_T_LEITB", designacao.vid_t_leitb);
        contentvalues.put("VID_T_LEBPT", designacao.vid_t_lebpt);
        contentvalues.put("VID_M_11A", designacao.vid_m_11a);
        contentvalues.put("VID_M_1APT", designacao.vid_m_1apt);
        contentvalues.put("VID_M_12A", designacao.vid_m_12a);
        contentvalues.put("VID_M_11B", designacao.vid_m_11b);
        contentvalues.put("VID_M_1BPT", designacao.vid_m_1bpt);
        contentvalues.put("VID_M_12B", designacao.vid_m_12b);
        contentvalues.put("VID_M_21A", designacao.vid_m_21a);
        contentvalues.put("VID_M_2APT", designacao.vid_m_2apt);
        contentvalues.put("VID_M_22A", designacao.vid_m_22a);
        contentvalues.put("VID_M_21B", designacao.vid_m_21b);
        contentvalues.put("VID_M_2BPT", designacao.vid_m_2bpt);
        contentvalues.put("VID_M_22B", designacao.vid_m_22b);
        contentvalues.put("VID_M_31A", designacao.vid_m_31a);
        contentvalues.put("VID_M_3APT", designacao.vid_m_3apt);
        contentvalues.put("VID_M_32A", designacao.vid_m_32a);
        contentvalues.put("VID_M_31B", designacao.vid_m_31b);
        contentvalues.put("VID_M_3BPT", designacao.vid_m_3bpt);
        contentvalues.put("VID_M_32B", designacao.vid_m_32b);
        contentvalues.put("VID_M_41A", designacao.vid_m_41a);
        contentvalues.put("VID_M_4APT", designacao.vid_m_4apt);
        contentvalues.put("VID_M_42A", designacao.vid_m_42a);
        contentvalues.put("VID_M_41B", designacao.vid_m_41b);
        contentvalues.put("VID_M_4BPT", designacao.vid_m_4bpt);
        contentvalues.put("VID_M_42B", designacao.vid_m_42b);
        contentvalues.put("VID_C_1", designacao.vid_c_1);
        contentvalues.put("VID_C_2", designacao.vid_c_2);
        contentvalues.put("VID_C_3", designacao.vid_c_3);
        contentvalues.put("VID_DIR", designacao.vid_dir);
        contentvalues.put("VID_LEIT", designacao.vid_leit);
        contentvalues.put("VID_CONG", designacao.vid_cong);
        contentvalues.put("VID_ESPECIAL", designacao.vid_especial);
        contentvalues.put("VID_BUSCA", designacao.vid_busca);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(designacao.vid_id_site);

        conexao_bd.update("MEIOSEMANA", contentvalues, "VID_ID_SITE = ?", parametros);

    }

    public int designacao_count(int id_site) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(VID_ID) AS TOTAL FROM MEIOSEMANA WHERE VID_ID_SITE = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id_site);

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        resultado.moveToFirst();

        return resultado.getInt(resultado.getColumnIndexOrThrow("TOTAL"));

    }

}
