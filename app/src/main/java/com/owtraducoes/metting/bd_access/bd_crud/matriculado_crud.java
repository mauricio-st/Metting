package com.owtraducoes.metting.bd_access.bd_crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.owtraducoes.metting.bd_access.bd_class.matriculado_class;

import java.util.ArrayList;
import java.util.List;

public class matriculado_crud {

    SQLiteDatabase conexao_bd;

    public matriculado_crud(SQLiteDatabase conexao) {
        conexao_bd = conexao;
    }

    public List<matriculado_class> buscar() {

        List<matriculado_class> matriculado = new ArrayList<matriculado_class>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM MATRICULADO ORDER BY NOME");

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            do {

                matriculado_class matriculado_reg = new matriculado_class();

                matriculado_reg.id      = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                matriculado_reg.id_site = resultado.getInt(resultado.getColumnIndexOrThrow("ID_SITE"));
                matriculado_reg.nome    = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                matriculado_reg.grupo   = resultado.getString(resultado.getColumnIndexOrThrow("NOME")); //resultado.getString(resultado.getColumnIndexOrThrow("GRUPO"));
                matriculado_reg.cong    = resultado.getInt(resultado.getColumnIndexOrThrow("CONG"));

                matriculado.add(matriculado_reg);

            } while (resultado.moveToNext());

        }

        return matriculado;

    }

    public void insere_matriculado(matriculado_class matriculado) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("ID", ,matriculado.id);
        contentvalues.put("ID_SITE", matriculado.id_site);
        contentvalues.put("NOME", matriculado.nome);
        contentvalues.put("ID_GRUPO", matriculado.id_grupo);
        contentvalues.put("CONG", matriculado.cong);

        conexao_bd.insertOrThrow("MATRICULADO", null, contentvalues);

    }

    public void altera_matriculdo(matriculado_class matriculado) {

        ContentValues contentvalues = new ContentValues();

        //contentvalues.put("ID", ,matriculado.id);
        //contentvalues.put("ID_SITE", matriculado.id_site);
        contentvalues.put("NOME", matriculado.nome);
        contentvalues.put("ID_GRUPO", matriculado.id_grupo);
        contentvalues.put("CONG", matriculado.cong);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(matriculado.id);

        conexao_bd.update("MATRICULADO", contentvalues, "ID = ?", parametros);

    }

    public int matriculado_count(int id_site) {

        int contador;

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(ID) AS TOTAL FROM MATRICULADO WHERE ID_SITE = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id_site);

        Cursor resultado = conexao_bd.rawQuery(sql.toString(), parametros);

        resultado.moveToFirst();

        contador = resultado.getInt(resultado.getColumnIndexOrThrow("TOTAL"));

        return contador;

    }

}
