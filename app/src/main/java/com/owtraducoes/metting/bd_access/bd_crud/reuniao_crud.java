package com.owtraducoes.metting.bd_access.bd_crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.owtraducoes.metting.bd_access.bd_class.meiosemana_class;
import com.owtraducoes.metting.bd_access.bd_class.semana_class;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

            meiosemana.busca       = resultado.getString(resultado.getColumnIndexOrThrow("VID_BUSCA"));
            //meiosemana.cong        = resultado.getString(resultado.getColumnIndexOrThrow(""));

        }

        return meiosemana;

    }

    public List<String> seach_week(Date semana) {

        List<String> week_list = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar dt1 = Calendar.getInstance();
        Calendar dt2 = Calendar.getInstance();

        dt1.setTime(semana);
        dt2.setTime(semana);

        dt1.add(Calendar.MONTH, -1);
        dt2.add(Calendar.MONTH, 1);

        String datainicio = sdf.format(dt1.getTime());
        String datafinal  = sdf.format(dt2.getTime());

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT VID_BUSCA FROM TEMA WHERE VID_BUSCA BETWEEN ? AND ? ORDER BY VID_BUSCA");

        String[] parametros = new String[2];
        parametros[0] = datainicio;
        parametros[1] = datafinal;

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            do {
                week_list.add(resultado.getString(resultado.getColumnIndexOrThrow("VID_BUSCA")));
            } while (resultado.moveToNext());

        }

        return week_list;

    }

    public List<semana_class> search_semana(String busca) {

        List<semana_class> semana = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");

        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDSOM)  AS PRI_VIDSOM, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDVID)  AS PRI_VIDVID, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDVOL)  AS PRI_VIDVOL, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDIND1) AS PRI_VIDIND1, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = P.PRI_VIDIND2) AS PRI_VIDIND2, ");
        sql.append(" 'Qualquer grupo' AS PRI_VIDLIMP, ");

        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_PRES)    AS VID_PRES, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_PRES)    AS VID_PRES, ");
        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_ORACAO1) AS VID_ORACAO1, ");

        sql.append(" T.VID_T_1TEM, ");//8
        sql.append(" T.VID_T_1TMP, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_1) AS VID_T_1, ");

        sql.append(" T.VID_T_2TEM, ");//11
        sql.append(" T.VID_T_2TMP, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_2) AS VID_T_2, ");

        sql.append(" T.VID_T_LEIT, ");//14
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEITA) AS VID_T_LEITA, ");
        sql.append(" VID_T_LEAPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_T_LEITB) AS VID_T_LEITB, ");
        sql.append(" VID_T_LEBPT, ");

        sql.append(" T.VID_M_1TEM, ");//19
        sql.append(" T.VID_M_1VIDEO, ");
        sql.append(" T.VID_M_1TMP, ");
        sql.append(" VID_M_1APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_11A) AS VID_M_11A, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_12A) AS VID_M_12A,");
        sql.append(" VID_M_1BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_11B) AS VID_M_11B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_12B) AS VID_M_12B, ");

        sql.append(" T.VID_M_2TEM, ");//28
        sql.append(" T.VID_M_2VIDEO, ");
        sql.append(" T.VID_M_2TMP, ");
        sql.append(" VID_M_2APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_21A) AS VID_M_21A, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_22A) AS VID_M_22A, ");
        sql.append(" VID_M_2BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_21B) AS VID_M_21B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_22B) AS VID_M_22B, ");

        sql.append(" T.VID_M_3TEM, ");//37
        sql.append(" T.VID_M_3VIDEO, ");
        sql.append(" T.VID_M_3TMP, ");
        sql.append(" VID_M_3APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_31A) AS VID_M_31A, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_32A) AS VID_M_32A, ");
        sql.append(" VID_M_3BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_31B) AS VID_M_31B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_32B) AS VID_M_32B, ");

        sql.append(" T.VID_M_4TEM, ");//46
        sql.append(" T.VID_M_4VIDEO, ");
        sql.append(" T.VID_M_4TMP, ");
        sql.append(" VID_M_4APT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_41A) AS VID_M_41A, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_42A) AS VID_M_42A, ");
        sql.append(" VID_M_4BPT, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_41B) AS VID_M_41B, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_M_42B) AS VID_M_42B, ");

        sql.append(" T.VID_C_1TEM, ");//55
        sql.append(" T.VID_C_1TMP, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_C_1) AS VID_C_1, ");

        sql.append(" T.VID_C_2TEM, ");//58
        sql.append(" T.VID_C_2TMP, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_C_2) AS VID_C_2, ");


        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_C_3)     AS VID_C_3, ");

        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_DIR)     AS VID_DIR, ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_LEIT)    AS VID_LEIT, ");

        //sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_ORACAO2) AS VID_ORACAO2 ");
        sql.append(" (SELECT NOME FROM MATRICULADO WHERE ID_SITE = M.VID_PRES)    AS VID_PRES ");

        sql.append("FROM TEMA AS T ");
        sql.append("LEFT JOIN MEIOSEMANA AS M ON M.VID_BUSCA=T.VID_BUSCA ");
        sql.append("LEFT JOIN PRIVILEGIO AS P ON P.PRI_BUSCA=T.VID_BUSCA ");
        sql.append("WHERE T.VID_BUSCA = ?");

        String[] parametros = new String[1];
        parametros[0] = busca;

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            for (int i=0; i <= 60; i++) {

                Log.d("Contador", "registro - "+i);

                if (resultado.getString(i) != null) {

                    if (! resultado.getString(i).equals("")) {

                        if (i <= 7) {

                            semana_class semana_reg = new semana_class();

                            semana_reg.tempo = 0;
                            semana_reg.privilegio = privilegio(i);
                            semana_reg.nome1 = resultado.getString(i);
                            semana_reg.nome2 = "";

                            semana.add(semana_reg);

                        } else if (i == 8 || i == 11) {

                            semana_class semana_reg = new semana_class();

                            semana_reg.tempo = resultado.getInt(i + 1);
                            semana_reg.privilegio = resultado.getString(i);
                            semana_reg.nome1 = resultado.getString(i + 2);
                            semana_reg.nome2 = "";

                            semana.add(semana_reg);

                        } else if (i == 14) {

                            semana_class semana_reg = new semana_class();

                            semana_reg.tempo = 4;
                            semana_reg.privilegio = "Leitura da bíblia – " + resultado.getString(i);
                            semana_reg.nome1 = resultado.getString(i + 1);
                            semana_reg.nome2 = "";

                            semana.add(semana_reg);

                        } else if (i == 19 || i == 28 || i == 37 || i == 46) {

                            semana_class semana_reg = new semana_class();

                            semana_reg.tempo = resultado.getInt(i + 2);
                            semana_reg.privilegio = resultado.getString(i);
                            semana_reg.nome1 = resultado.getString(i + 4);

                            if (resultado.getString(i + 5) != null) {
                                semana_reg.nome2 = resultado.getString(i + 5);
                            } else {
                                semana_reg.nome2 = "";
                            }

                            semana.add(semana_reg);

                        } else if (i == 55 || i == 58) {

                            semana_class semana_reg = new semana_class();

                            semana_reg.tempo = resultado.getInt(i + 1);
                            semana_reg.privilegio = resultado.getString(i);
                            semana_reg.nome1 = resultado.getString(i + 2);
                            semana_reg.nome2 = "";

                            semana.add(semana_reg);

                        }

                    }

                }

            }

        }

        return semana;

    }

    private String privilegio(int id) {

        switch (id) {

            case 0:
                return "Operador de Som";

            case 1:
                return "Operador de Vídeo";

            case 2:
                return "Operador de Microfone";

            case 3:
                return "Indicador";

            case 4:
                return "Indicador";

            case 5:
                return "Limpeza";

            case 6:
                return "Presidente";

            case 7:
                return "Oração Inicial";

            default:
                return "";

        }

    }

}
