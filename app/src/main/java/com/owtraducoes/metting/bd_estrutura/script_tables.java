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

    public static String getCreateTableDesignacoes() {

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS MEIOSEMANA (");
        sql.append(" VID_ID           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(" VID_ID_SITE      INTEGER DEFAULT 0, ");
        sql.append(" VID_PRES         INTEGER DEFAULT 0, ");
        sql.append(" VID_ORACAOINICIO INTEGER DEFAULT 0, ");
        sql.append(" VID_ORACAOFINAL  INTEGER DEFAULT 0, ");
        sql.append(" VID_SOM          INTEGER DEFAULT 0, ");
        sql.append(" VID_VOLANT       INTEGER DEFAULT 0, ");
        sql.append(" VID_IND1         INTEGER DEFAULT 0, ");
        sql.append(" VID_IND2         INTEGER DEFAULT 0, ");
        sql.append(" VID_T_1          INTEGER DEFAULT 0, ");
        sql.append(" VID_T_2          INTEGER DEFAULT 0, ");
        sql.append(" VID_T_LEITA      INTEGER DEFAULT 0, ");
        sql.append(" VID_T_LEAPT      INTEGER DEFAULT 0, ");
        sql.append(" VID_T_LEITB      INTEGER DEFAULT 0, ");
        sql.append(" VID_T_LEBPT      INTEGER DEFAULT 0, ");
        sql.append(" VID_M_11A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_1APT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_12A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_11B        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_1BPT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_12B        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_21A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_2APT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_22A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_21B        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_2BPT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_22B        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_31A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_3APT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_32A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_31B        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_3BPT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_32B        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_41A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_4APT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_42A        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_41B        INTEGER DEFAULT 0, ");
        sql.append(" VID_M_4BPT       INTEGER DEFAULT 0, ");
        sql.append(" VID_M_42B        INTEGER DEFAULT 0, ");
        sql.append(" VID_C_1          INTEGER DEFAULT 0, ");
        sql.append(" VID_C_2          INTEGER DEFAULT 0, ");
        sql.append(" VID_C_3          INTEGER DEFAULT 0, ");
        sql.append(" VID_DIR          INTEGER DEFAULT 0, ");
        sql.append(" VID_LEIT         INTEGER DEFAULT 0, ");
        sql.append(" VID_CONG         VARCHAR(200), ");
        sql.append(" VID_ESPECIAL     INTEGER DEFAULT 0, ");
        sql.append(" VID_BUSCA        DATE ");
        sql.append(");");

        return sql.toString();

    }

}
