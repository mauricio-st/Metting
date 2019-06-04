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

        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDSOM)  AS PRI_VIDSOM, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDVID)  AS PRI_VIDVID, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDVOL)  AS PRI_VIDVOL, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDIND1) AS PRI_VIDIND1, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDIND2) AS PRI_VIDIND2, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDLIMP) AS PRI_VIDLIMP, ");

        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_PRES)    AS VID_PRES, ");
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_ORACAO1) AS VID_ORACAO1, ");
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_ORACAO2) AS VID_ORACAO2, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_1)     AS VID_T_1, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_2)     AS VID_T_2, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEITA) AS VID_T_LEITA, ");
        sql.append(" VID_T_LEAPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEITB) AS VID_T_LEITB, ");
        sql.append(" VID_T_LEBPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_11A)   AS VID_M_11A, ");
        sql.append(" VID_M_1APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_12A)   AS VID_M_12A,");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_11B)   AS VID_M_11B, ");
        sql.append(" VID_M_1BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_12B)   AS VID_M_12B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_21A)   AS VID_M_21A, ");
        sql.append(" VID_M_2APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_22A)   AS VID_M_22A, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_21B)   AS VID_M_21B, ");
        sql.append(" VID_M_2BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_22B)   AS VID_M_22B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_31A)   AS VID_M_31A, ");
        sql.append(" VID_M_3APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_32A)   AS VID_M_32A, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_31B)   AS VID_M_31B, ");
        sql.append(" VID_M_3BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_32B)   AS VID_M_32B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_41A)   AS VID_M_41A, ");
        sql.append(" VID_M_4APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_42A)   AS VID_M_42A, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_41B)   AS VID_M_41B, ");
        sql.append(" VID_M_4BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_42B)   AS VID_M_42B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_C_1)     AS VID_C_1, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_C_2)     AS VID_C_2, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_C_3)     AS VID_C_3, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_DIR)     AS VID_DIR, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_LEIT)    AS VID_LEIT ");
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

            meiosemana.presidente  = resultado.getString(resultado.getColumnIndexOrThrow("VID_PRES"));
            meiosemana.pri_vidsom  = resultado.getString(resultado.getColumnIndexOrThrow("PRI_VIDSOM"));
            meiosemana.pri_vidvid  = resultado.getString(resultado.getColumnIndexOrThrow("PRI_VIDVID"));
            meiosemana.pri_vidvol  = resultado.getString(resultado.getColumnIndexOrThrow("PRI_VIDVOL"));
            meiosemana.pri_vidind1 = resultado.getString(resultado.getColumnIndexOrThrow("PRI_VIDIND1"));
            meiosemana.pri_vidind2 = resultado.getString(resultado.getColumnIndexOrThrow("PRI_VIDIND2"));
            meiosemana.pri_vidlimp = resultado.getString(resultado.getColumnIndexOrThrow("PRI_VIDLIMP"));

            //meiosemana.vid_oracao1 = resultado.getString(resultado.getColumnIndexOrThrow("VID_ORACAO1"));
            //meiosemana.vid_oracao2 = resultado.getString(resultado.getColumnIndexOrThrow("VID_ORACAO2"));
            meiosemana.vid_t_1     = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_1"));
            meiosemana.vid_t_2     = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_2"));
            meiosemana.vid_t_la    = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEITA"));
            meiosemana.vid_t_lapt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEAPT"));
            meiosemana.vid_t_lb    = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEITA"));
            meiosemana.vid_t_lbpt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_T_LEAPT"));
            meiosemana.vid_m_11a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_11A"));
            meiosemana.vid_m_1apt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_1APT"));
            meiosemana.vid_m_12a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_12A"));
            meiosemana.vid_m_11b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_11B"));
            meiosemana.vid_m_1bpt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_1BPT"));
            meiosemana.vid_m_12b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_12B"));
            meiosemana.vid_m_21a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_21A"));
            meiosemana.vid_m_2apt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_2APT"));
            meiosemana.vid_m_22a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_22A"));
            meiosemana.vid_m_21b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_21B"));
            meiosemana.vid_m_2bpt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_2BPT"));
            meiosemana.vid_m_22b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_22B"));
            meiosemana.vid_m_31a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_31A"));
            meiosemana.vid_m_3apt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_3APT"));
            meiosemana.vid_m_32a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_32A"));
            meiosemana.vid_m_31b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_31B"));
            meiosemana.vid_m_3bpt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_3BPT"));
            meiosemana.vid_m_32b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_32B"));
            meiosemana.vid_m_41a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_41A"));
            meiosemana.vid_m_4apt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_4APT"));
            meiosemana.vid_m_42a   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_42A"));
            meiosemana.vid_m_41b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_41B"));
            meiosemana.vid_m_4bpt  = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_4BPT"));
            meiosemana.vid_m_42b   = resultado.getString(resultado.getColumnIndexOrThrow("VID_M_42B"));
            meiosemana.vid_c_1     = resultado.getString(resultado.getColumnIndexOrThrow("VID_C_1"));
            meiosemana.vid_c_2     = resultado.getString(resultado.getColumnIndexOrThrow("VID_C_2"));
            meiosemana.vid_c_3     = resultado.getString(resultado.getColumnIndexOrThrow("VID_C_3"));
            meiosemana.dirigente   = resultado.getString(resultado.getColumnIndexOrThrow("VID_DIR"));
            meiosemana.leitor      = resultado.getString(resultado.getColumnIndexOrThrow("VID_LEIT"));
            //meiosemana.cong        = resultado.getString(resultado.getColumnIndexOrThrow(""));

        }

        return meiosemana;

    }

}
