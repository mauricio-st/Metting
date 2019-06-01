package com.owtraducoes.metting.bd_access.bd_crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.owtraducoes.metting.bd_access.bd_class.meiosemana_class;

public class reuniao_crud {

    SQLiteDatabase conexao_bd;

    public reuniao_crud(SQLiteDatabase conexao) {
        conexao_bd = conexao;
    }

    public meiosemana_class buscar(String semana) {

        meiosemana_class meiosemana = new meiosemana_class();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT T.*, ");

        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_PRES)     AS VID_PRES, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_ORACAOINICIO) AS VID_ORACAOINICIO, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_ORACAOFINAL ) AS VID_ORACAOFINAL, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_SOM)      AS VID_SOM, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_VOLANT)   AS VID_VOLANT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_IND1)     AS VID_IND1, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_IND2)     AS VID_IND2, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_1)      AS VID_T_1, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_2)      AS VID_T_2, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEITA)  AS VID_T_LEITA, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEAPT)  AS VID_T_LEAPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEITB)  AS VID_T_LEITB, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEBPT)  AS VID_T_LEBPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_11A)    AS VID_M_11A, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_1APT)   AS VID_M_1APT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_12A)    AS VID_M_12A," );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_11B)    AS VID_M_11B, ");
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_1BPT)   AS VID_M_1BPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_12B)    AS VID_M_12B, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_21A)    AS VID_M_21A, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_2APT)   AS VID_M_2APT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_22A)    AS VID_M_22A, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_21B)    AS VID_M_21B, " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = VID_M_2BPT)   AS VID_M_2BPT, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_22B)    AS VID_M_22B, " );
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_31A)    AS VID_M_31A " );
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_3APT)   AS VID_M_3APT, " );

        sql.append("FROM TEMA AS T ");
        sql.append("LEFT JOIN MEIOSEMANA AS M ON M.VID_BUSCA=T.VID_BUSCA ");
        sql.append("LEFT JOIN PRIVILEGIO AS P ON P.PRI_BUSCA=T.VID_BUSCA ");
        sql.append("WHERE T.VID_BUSCA = ?");

        String[] parametros = new String[1];
        parametros[0] = semana;

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            //meiosemana.oracaoinicio = resultado.getString(resultado.getColumnIndexOrThrow("oracaoincio"));

            meiosemana.vid_t_1tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_1TEM"));
            meiosemana.vid_t_2tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_2TEM"));
            meiosemana.vid_m_1tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_1TEM"));
            meiosemana.vid_m_2tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_2TEM"));
            meiosemana.vid_m_3tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_3TEM"));
            meiosemana.vid_m_4tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_4TEM"));
            meiosemana.vid_c_1tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_C_1TEM"));
            meiosemana.vid_c_2tem = resultado.getString(resultado.getColumnIndexOrThrow("VID_C_2TEM"));

            //meiosemana.oracaoinicio = resultado.getString(resultado.getColumnIndexOrThrow("VID_ORACAOINICIO"));
            //meiosemana.oracaofinal  = resultado.getString(resultado.getColumnIndexOrThrow("VID_ORACAOFINAL"));
            //meiosemana.som          = resultado.getString(resultado.getColumnIndexOrThrow("VID_SOM"));
            //meiosemana.volante      = resultado.getString(resultado.getColumnIndexOrThrow("VID_VOLANT"));
            //meiosemana.indicador1   = resultado.getString(resultado.getColumnIndexOrThrow("VID_IND1"));
            //meiosemana.indicador2   = resultado.getString(resultado.getColumnIndexOrThrow("VID_IND2"));
            meiosemana.vid_t_1      = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_1"));
            meiosemana.vid_t_2      = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_2"));
            meiosemana.vid_t_leita  = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEITA"));
            meiosemana.vid_t_leita  = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEITB"));

        }

        return meiosemana;

    }

}
