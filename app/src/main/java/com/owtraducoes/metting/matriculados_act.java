package com.owtraducoes.metting;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.owtraducoes.metting.bd_access.bd_class.matriculado_class;
import com.owtraducoes.metting.bd_access.bd_crud.matriculado_crud;
import com.owtraducoes.metting.bd_adapter.matriculado_adapter;
import com.owtraducoes.metting.bd_estrutura.open_helper;

import java.util.List;

public class matriculados_act extends AppCompatActivity {

    private open_helper bd_rules;
    private SQLiteDatabase conexao_bd;

    private matriculado_crud crud_matriculado;

    private matriculado_adapter adapter_matriculado;

    private ConstraintLayout ly_matriculado;

    private RecyclerView lst_matriculado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matriculados);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ly_matriculado  = findViewById(R.id.ly_matriculado);

        lst_matriculado = findViewById(R.id.lst_matriculado);

        criarconexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lst_matriculado.setLayoutManager(linearLayoutManager);

        List<matriculado_class> matriculados = crud_matriculado.buscar();

        adapter_matriculado = new matriculado_adapter(matriculados);

        lst_matriculado.setAdapter(adapter_matriculado);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void criarconexao() {

        try {

            bd_rules = new open_helper(this);

            conexao_bd = bd_rules.getWritableDatabase();

            crud_matriculado = new matriculado_crud(conexao_bd);

        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();

        }

    }

}
