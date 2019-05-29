package com.owtraducoes.metting.bd_estrutura;

public class script_tables {

    public static String getCreateTableMatriculados() {

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS MATRICULADO (");
        sql.append(" ID       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(" ID_SITE  INTEGER DEFAULT 0, ");
        sql.append(" NOME     VARCHAR(100), ");
        sql.append(" ID_GRUPO INTEGER DEFAULT 0, ");
        sql.append(" CONG     INTEGER DEFAULT 0 ");
        sql.append(");");

        return sql.toString();

    }

    public static String getCreateTableTemas() {

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS TEMA (");
        sql.append(" VID_ID       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(" VID_ID_SITE  INTEGER DEFAULT 0, ");
        sql.append(" VID_LEITURA  VARCHAR(50), ");
        sql.append(" VID_T_1TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_T_1TEM   VARCHAR(200), ");
        sql.append(" VID_T_2TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_T_2TEM   VARCHAR(200), ");
        sql.append(" VID_T_LEIT   VARCHAR(50), ");
        sql.append(" VID_M_1TEM   VARCHAR(50), ");
        sql.append(" VID_M_1VIDEO INTEGER DEFAULT 0, ");
        sql.append(" VID_M_1TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_M_2TEM   VARCHAR(50), ");
        sql.append(" VID_M_2VIDEO INTEGER DEFAULT 0, ");
        sql.append(" VID_M_2TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_M_3TEM   VARCHAR(50), ");
        sql.append(" VID_M_3VIDEO INTEGER DEFAULT 0, ");
        sql.append(" VID_M_3TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_M_4TEM   VARCHAR(50), ");
        sql.append(" VID_M_4VIDEO INTEGER DEFAULT 0, ");
        sql.append(" VID_M_4TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_C_1TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_C_1TEM   VARCHAR(200), ");
        sql.append(" VID_C_2TMP   INTEGER DEFAULT 0, ");
        sql.append(" VID_C_2TEM   VARCHAR(200), ");
        sql.append(" VID_UPDT     DATETIME, ");
        sql.append(" VID_BUSCA    DATE ");
        sql.append(");");

        return sql.toString();

    }

}
