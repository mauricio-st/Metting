package com.owtraducoes.metting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.owtraducoes.metting.bd_access.bd_class.designacao_class;
import com.owtraducoes.metting.bd_access.bd_class.matriculado_class;
import com.owtraducoes.metting.bd_access.bd_class.meiosemana_class;
import com.owtraducoes.metting.bd_access.bd_class.privilegio_class;
import com.owtraducoes.metting.bd_access.bd_class.semana_class;
import com.owtraducoes.metting.bd_access.bd_class.tema_class;
import com.owtraducoes.metting.bd_access.bd_crud.designacao_crud;
import com.owtraducoes.metting.bd_access.bd_crud.matriculado_crud;
import com.owtraducoes.metting.bd_access.bd_crud.privilegio_crud;
import com.owtraducoes.metting.bd_access.bd_crud.reuniao_crud;
import com.owtraducoes.metting.bd_access.bd_crud.tema_crud;
import com.owtraducoes.metting.bd_adapter.semana_adapter;
import com.owtraducoes.metting.bd_estrutura.open_helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class main extends AppCompatActivity {

    private open_helper bd_rules;
    private SQLiteDatabase conexao_bd;

    private matriculado_crud crud_matriculado;
    private tema_crud crud_tema;
    private designacao_crud crud_designacao;
    private privilegio_crud crud_privilegio;
    private reuniao_crud crud_reuniao;

    private semana_adapter semana_adapter;

    private ProgressDialog dialog;

    private LinearLayout ly_main;

    private TextView txt_tema_t1;
    private TextView txt_tema_t2;
    private TextView txt_tema_m1;
    private TextView txt_tema_m2;
    private TextView txt_tema_m3;
    private TextView txt_tema_m4;
    private TextView txt_tema_c1;
    private TextView txt_tema_c2;
    private TextView txt_tema_c3;

    private TextView txt_oracao1;
    private TextView txt_nome_t1;
    private TextView txt_nome_t2;
    private TextView txt_nome_la;
    private TextView txt_nome_lb;
    private TextView txt_nome_m11a;
    private TextView txt_nome_m12a;
    private TextView txt_nome_m21a;
    private TextView txt_nome_m22a;
    private TextView txt_nome_m31a;
    private TextView txt_nome_m32a;
    private TextView txt_nome_m41a;
    private TextView txt_nome_m42a;
    private TextView txt_nome_m11b;
    private TextView txt_nome_m12b;
    private TextView txt_nome_m21b;
    private TextView txt_nome_m22b;
    private TextView txt_nome_m31b;
    private TextView txt_nome_m32b;
    private TextView txt_nome_m41b;
    private TextView txt_nome_m42b;
    private TextView txt_nome_c1;
    private TextView txt_nome_c2;
    private TextView txt_nome_c3;
    private TextView txt_estudodir;
    private TextView txt_estudoleit;
    private TextView txt_oracao2;

    private TextView txt_som;
    private TextView txt_video;
    private TextView txt_microfone;
    private TextView txt_indicador1;
    private TextView txt_indicador2;
    private TextView txt_limpeza;

    private Spinner spn_week;

    public RecyclerView lst_semana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ly_main = findViewById(R.id.ly_main);


        //temas
        txt_tema_t1 = findViewById(R.id.txt_tema_t1);
        txt_tema_t2 = findViewById(R.id.txt_tema_t2);
        txt_tema_m1 = findViewById(R.id.txt_tema_m1);
        txt_tema_m2 = findViewById(R.id.txt_tema_m2);
        txt_tema_m3 = findViewById(R.id.txt_tema_m3);
        txt_tema_m4 = findViewById(R.id.txt_tema_m4);
        txt_tema_c1 = findViewById(R.id.txt_tema_c1);
        txt_tema_c2 = findViewById(R.id.txt_tema_c2);
        txt_tema_c3 = findViewById(R.id.txt_tema_c3);

        //designações
        txt_oracao1    = findViewById(R.id.txt_oracao1);
        txt_nome_t1    = findViewById(R.id.txt_nome_t1);
        txt_nome_t2    = findViewById(R.id.txt_nome_t2);
        txt_nome_la    = findViewById(R.id.txt_nome_la);
        //txt_nome_lb   = findViewById(R.id.txt_nome_lb);
        txt_nome_m11a  = findViewById(R.id.txt_nome_m11a);
        txt_nome_m12a  = findViewById(R.id.txt_nome_m12a);
        txt_nome_m21a  = findViewById(R.id.txt_nome_m21a);
        txt_nome_m22a  = findViewById(R.id.txt_nome_m22a);
        txt_nome_m31a  = findViewById(R.id.txt_nome_m31a);
        txt_nome_m32a  = findViewById(R.id.txt_nome_m32a);
        txt_nome_m41a  = findViewById(R.id.txt_nome_m41a);
        txt_nome_m42a  = findViewById(R.id.txt_nome_m42a);
        //txt_nome_m11b = findViewById(R.id.txt_nome_m11b);
        //txt_nome_m12b = findViewById(R.id.txt_nome_m12b);
        //txt_nome_m21b = findViewById(R.id.txt_nome_m21b);
        //txt_nome_m22b = findViewById(R.id.txt_nome_m22b);
        //txt_nome_m31b = findViewById(R.id.txt_nome_m31b);
        //txt_nome_m32b = findViewById(R.id.txt_nome_m32b);
        //txt_nome_m41b = findViewById(R.id.txt_nome_m41b);
        //txt_nome_m42b = findViewById(R.id.txt_nome_m42b);
        txt_nome_c1    = findViewById(R.id.txt_nome_c1);
        txt_nome_c2    = findViewById(R.id.txt_nome_c2);
        txt_nome_c3    = findViewById(R.id.txt_nome_c3);
        txt_estudodir  = findViewById(R.id.txt_estudodir);
        txt_estudoleit = findViewById(R.id.txt_estudoleit);
        txt_oracao2    = findViewById(R.id.txt_oracao2);

        //privilegios
        txt_som        = findViewById(R.id.txt_som);
        txt_video      = findViewById(R.id.txt_video);
        txt_microfone  = findViewById(R.id.txt_microfone);
        txt_indicador1 = findViewById(R.id.txt_indicador1);
        txt_indicador2 = findViewById(R.id.txt_indicador2);
        txt_limpeza    = findViewById(R.id.txt_limpeza);

        spn_week       = findViewById(R.id.spn_week);

        lst_semana     = findViewById(R.id.lst_semana);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lst_semana.setLayoutManager(linearLayoutManager);

        criarconexao();

        //load_meiosemana("2019-05-27");

        load_semana();

        //load_semana2("2019-05-27");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //crud_matriculado.matriculado_count(1);

                /*
                if (ContextCompat.checkSelfPermission(main.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(main.this, Manifest.permission.INTERNET)) {

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(main.this, new String[]{Manifest.permission.INTERNET}, );

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }*/

                dialog = new ProgressDialog(main.this);
                dialog.setIndeterminate(true);
                dialog.setCancelable(false);
                dialog.setMessage("Carregando. Aguarde por favor ...");
                dialog.show();

                fila_json(0);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        spn_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //if (spn_week.isFocused()) {
                    //load_meiosemana(spn_week.getSelectedItem().toString());
                    load_semana2(spn_week.getSelectedItem().toString());
                //}

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spn_week.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.action_week:
                spn_week.performClick();
                break;

            case R.id.action_settings:
                Intent it = new Intent(main.this, matriculados_act.class);
                startActivity(it);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void criarconexao(){

        try{

            bd_rules = new open_helper(this);

            conexao_bd = bd_rules.getWritableDatabase();

            crud_matriculado = new matriculado_crud(conexao_bd);
            crud_tema        = new tema_crud(conexao_bd);
            crud_designacao  = new designacao_crud(conexao_bd);
            crud_privilegio  = new privilegio_crud(conexao_bd);
            crud_reuniao     = new reuniao_crud(conexao_bd);

            Snackbar.make(ly_main, "Conexão criada com sucesso!", Snackbar.LENGTH_SHORT)
                    .setAction("Ok", null).show();

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();

        }

    }

    private void load_meiosemana(String semana) {

        //tema_class tema = crud_tema.buscar(semana);
        meiosemana_class meiosemana = crud_reuniao.buscar(semana);

        if (meiosemana.busca != null) {

            txt_tema_t1.setText(meiosemana.vid_t_1tem);
            txt_tema_t2.setText(meiosemana.vid_t_2tem);

            //designações
            txt_oracao1.setText(meiosemana.vid_oracao1);
            txt_nome_t1.setText(meiosemana.vid_t_1);
            txt_nome_t2.setText(meiosemana.vid_t_2);
            txt_nome_la.setText(meiosemana.vid_t_la);
            //txt_nome_lb.setText(meiosemana.vid_m_2tem);

            if (meiosemana.vid_m_1tem.equals("")) {
                txt_tema_m1.setVisibility(View.GONE);
                txt_nome_m11a.setVisibility(View.GONE);
                txt_nome_m12a.setVisibility(View.GONE);
            } else {
                txt_tema_m1.setText(load_tema(meiosemana.vid_m_1tem));
                txt_nome_m11a.setText(meiosemana.vid_m_11a);
                txt_nome_m12a.setText(meiosemana.vid_m_12a);
                //txt_nome_m11b.setText(meiosemana.vid_m_11b);
                //txt_nome_m12b.setText(meiosemana.vid_m_12b);
            }

            if (meiosemana.vid_m_2tem.equals("")) {
                txt_tema_m2.setVisibility(View.GONE);
                txt_nome_m21a.setVisibility(View.GONE);
                txt_nome_m22a.setVisibility(View.GONE);
            } else {
                txt_tema_m2.setText(load_tema(meiosemana.vid_m_2tem));
                txt_nome_m21a.setText(meiosemana.vid_m_21a);
                txt_nome_m22a.setText(meiosemana.vid_m_22a);
                //txt_nome_m21b.setText(meiosemana.vid_m_21b);
                //txt_nome_m22b.setText(meiosemana.vid_m_22b);
            }

            if (meiosemana.vid_m_3tem.equals("")) {
                txt_tema_m3.setVisibility(View.GONE);
                txt_nome_m31a.setVisibility(View.GONE);
                txt_nome_m32a.setVisibility(View.GONE);
            } else {
                txt_tema_m3.setText(load_tema(meiosemana.vid_m_3tem));
                txt_nome_m31a.setText(meiosemana.vid_m_31a);
                txt_nome_m32a.setText(meiosemana.vid_m_32a);
                //txt_nome_m31b.setText(meiosemana.vid_m_31b);
                //txt_nome_m32b.setText(meiosemana.vid_m_32b);
            }

            if (meiosemana.vid_m_4tem.equals("")) {
                txt_tema_m4.setVisibility(View.GONE);
                txt_nome_m41a.setVisibility(View.GONE);
                txt_nome_m42a.setVisibility(View.GONE);
            } else {

                txt_tema_m4.setVisibility(View.VISIBLE);
                txt_nome_m41a.setVisibility(View.VISIBLE);
                txt_nome_m42a.setVisibility(View.VISIBLE);

                txt_tema_m4.setText(load_tema(meiosemana.vid_m_4tem));
                txt_nome_m41a.setText(meiosemana.vid_m_41a);
                txt_nome_m42a.setText(meiosemana.vid_m_42a);
                //txt_nome_m41b.setText(meiosemana.vid_m_41b);
                //txt_nome_m42b.setText(meiosemana.vid_m_42b);
            }


            if (meiosemana.vid_c_1 == null) {
                txt_nome_c1.setVisibility(View.GONE);
                txt_tema_c1.setVisibility(View.GONE);
            } else {
                txt_tema_c1.setText(meiosemana.vid_c_1tem);
                txt_nome_c1.setText(meiosemana.vid_c_1);
            }


            if (meiosemana.vid_c_2 == null) {
                txt_nome_c2.setVisibility(View.GONE);
                txt_tema_c2.setVisibility(View.GONE);
            } else {
                txt_tema_c2.setText(meiosemana.vid_c_2tem);
                txt_nome_c2.setText(meiosemana.vid_c_2);
            }


        /*
        if (meiosemana.vid_c_3.equals("")) {
            txt_nome_c3.setVisibility(View.GONE);
            //txt_tema_c3.setText(tema.vid_t_1tem);
        } else {
            //txt_tema_c2.setText(meiosemana.vid_c_3tem);
            txt_nome_c3.setText(meiosemana.vid_c_3);
        }
        */


            txt_estudodir.setText(meiosemana.dirigente);
            txt_estudoleit.setText(meiosemana.leitor);
            txt_oracao2.setText(meiosemana.vid_oracao2);

            //privilégios
            //txt_oracao1.setText(meiosemana.vid_oracao1);
            txt_som.setText(meiosemana.pri_vidsom);
            txt_video.setText(meiosemana.pri_vidvid);
            txt_microfone.setText(meiosemana.pri_vidvol);
            txt_indicador1.setText(meiosemana.pri_vidind1);
            txt_indicador2.setText(meiosemana.pri_vidind2);
            txt_limpeza.setText(meiosemana.pri_vidlimp);
            //txt_oracao2.setText(meiosemana.vid_oracao2);

        }

    }

    private void load_semana() {

        Date data_hoje = new Date();

        List<String> week_list = crud_reuniao.seach_week(data_hoje);

        ArrayAdapter<String> adapter_week = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, week_list);

        spn_week.setAdapter(adapter_week);

    }

    private void load_semana2(String semana) {

        List<semana_class> dados = crud_reuniao.search_semana(semana);

        Toast.makeText(this, dados.size()+" registros", Toast.LENGTH_SHORT).show();

        semana_adapter = new semana_adapter(dados);

        lst_semana.setAdapter(semana_adapter);



    }

    private String load_tema(String tema) {

        switch (tema) {

            case "pc":
                return "Primeira conversa";

            case "pr":
                return "Primeira revisita";

            case "sr":
                return "Segunda revisita";

            case "tc":
                return "Terceira Revisita";

            case "eb":
                return "Estudo bíblico";

            case "d":
                return "Discurso";

            default:
                return null;

        }

    }

    public void fila_json(int fila_sequence) {

        //0 = matriculados
        //1 = temas
        //2 = designacoes
        //3 = privilegios

        switch (fila_sequence) {

            case 0:
                new json_matriculados().execute("http://m.owltraducoes.com/jw/json/registered.php");
                break;

            case 1:
                new json_temas().execute("http://m.owltraducoes.com/jw/json/week_theme.php");
                break;

            case 2:
                new json_designacoes().execute("http://m.owltraducoes.com/jw/json/week_name.php");
                break;

            case 3:
                new json_privilegios().execute("http://m.owltraducoes.com/jw/json/week_designation.php");
                break;

            default:
                dialog.dismiss();
                break;

        }

    }

    public class json_matriculados extends AsyncTask<String, String, String> {

        int fila_sequence = 0;

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{

                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("cong","50592")
                        .appendQueryParameter("updt","2010-01-01");
                String query = builder.build().getEncodedQuery();

                Log.d("matriculados", query);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null){

                    buffer.append(line);

                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("matriculados");

                StringBuffer finalBufferedData = new StringBuffer();

                matriculado_class matriculado = new matriculado_class();

                for (int i=0; i<parentArray.length(); i++ ) {

                    JSONObject finalObject = parentArray.getJSONObject(i);

                    matriculado.id_site  = finalObject.getInt("id");
                    matriculado.nome     = finalObject.getString("nome");
                    matriculado.id_grupo = finalObject.getInt("id_grupo");
                    matriculado.cong     = finalObject.getInt("cong");

                    Log.d("matriculados", matriculado.nome);

                    if (crud_matriculado.matriculado_count(matriculado.id_site) > 0) {
                        crud_matriculado.altera_matriculdo(matriculado);
                    } else {
                        crud_matriculado.insere_matriculado(matriculado);
                    }

                }

                return finalBufferedData.toString();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                    e.printStackTrace();
            } finally {

                if (connection != null){
                    connection.disconnect();
                }

                try{
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            fila_sequence++;
            fila_json(fila_sequence);
        }
    }

    public class json_temas extends AsyncTask<String, String, String> {

        int fila_sequence = 1;

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{

                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("cong","50592")
                        .appendQueryParameter("updt","2010-01-01");
                String query = builder.build().getEncodedQuery();

                Log.d("temas", query);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null){

                    buffer.append(line);

                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("temas");

                StringBuffer finalBufferedData = new StringBuffer();

                tema_class temas = new tema_class();

                for (int i=0; i<parentArray.length(); i++ ) {

                    JSONObject finalObject = parentArray.getJSONObject(i);

                    //temas.vid_id       = finalObject.getInt("id");;
                    temas.vid_id_site  = finalObject.getInt("id");
                    temas.vid_leitura  = finalObject.getString("vid_leitura");
                    temas.vid_t_1tmp   = finalObject.getInt("vid_t_1tmp");
                    temas.vid_t_1tem   = finalObject.getString("vid_t_1tem");
                    temas.vid_t_2tmp   = finalObject.getInt("vid_t_2tmp");
                    temas.vid_t_2tem   = finalObject.getString("vid_t_2tem");
                    temas.vid_t_leit   = finalObject.getString("vid_t_leit");
                    temas.vid_m_1tem   = finalObject.getString("vid_m_1tem");
                    temas.vid_m_1video = finalObject.getInt("vid_m_1video");
                    temas.vid_m_1tmp   = finalObject.getInt("vid_m_1tmp");
                    temas.vid_m_2tem   = finalObject.getString("vid_m_2tem");
                    temas.vid_m_2video = finalObject.getInt("vid_m_2video");
                    temas.vid_m_2tmp   = finalObject.getInt("vid_m_2tmp");
                    temas.vid_m_3tem   = finalObject.getString("vid_m_3tem");
                    temas.vid_m_3video = finalObject.getInt("vid_m_3video");
                    temas.vid_m_3tmp   = finalObject.getInt("vid_m_3tmp");
                    temas.vid_m_4tem   = finalObject.getString("vid_m_4tem");
                    temas.vid_m_4video = finalObject.getInt("vid_m_4video");
                    temas.vid_m_4tmp   = finalObject.getInt("vid_m_4tmp");
                    temas.vid_c_1tmp   = finalObject.getInt("vid_c_1tmp");
                    temas.vid_c_1tem   = finalObject.getString("vid_c_1tem");
                    temas.vid_c_2tmp   = finalObject.getInt("vid_c_2tmp");
                    temas.vid_c_2tem   = finalObject.getString("vid_c_2tem");
                    temas.vid_updt     = finalObject.getString("vid_updt");
                    temas.vid_busca    = finalObject.getString("vid_busca");

                    Log.d("temas", temas.vid_busca);

                    if (crud_tema.tema_count(temas.vid_id_site) > 0) {
                        crud_tema.altera_tema(temas);
                    } else {
                        crud_tema.insere_tema(temas);
                    }

                }

                return finalBufferedData.toString();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                e.printStackTrace();
            } finally {

                if (connection != null){
                    connection.disconnect();
                }

                try{
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            fila_sequence++;
            fila_json(fila_sequence);
        }
    }

    public class json_designacoes extends AsyncTask<String, String, String> {

        int fila_sequence = 2;

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{

                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("cong","50592")
                        .appendQueryParameter("updt","2010-01-01");
                String query = builder.build().getEncodedQuery();

                Log.d("designacoes", query);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null){

                    buffer.append(line);

                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("designacoes");

                StringBuffer finalBufferedData = new StringBuffer();

                designacao_class designacao = new designacao_class();

                for (int i=0; i<parentArray.length(); i++ ) {

                    JSONObject finalObject = parentArray.getJSONObject(i);

                    //designacao.vid_id       = finalObject.getInt("id");
                    designacao.vid_id_site  = finalObject.getInt("vid_cod");
                    designacao.vid_pres     = finalObject.getInt("vid_pres");
                    designacao.vid_oracao1  = finalObject.getInt("vid_oracaoinicio");
                    designacao.vid_oracao2  = finalObject.getInt("vid_oracaofinal");
                    designacao.vid_t_1      = finalObject.getInt("vid_t_1");
                    designacao.vid_t_2      = finalObject.getInt("vid_t_2");
                    designacao.vid_t_leita  = finalObject.getInt("vid_t_leita");
                    designacao.vid_t_leapt  = finalObject.getInt("vid_t_leapt");
                    designacao.vid_t_leitb  = finalObject.getInt("vid_t_leitb");
                    designacao.vid_t_lebpt  = finalObject.getInt("vid_t_lebpt");
                    designacao.vid_m_11a    = finalObject.getInt("vid_m_11a");
                    designacao.vid_m_1apt   = finalObject.getInt("vid_m_1apt");
                    designacao.vid_m_12a    = finalObject.getInt("vid_m_12a");
                    designacao.vid_m_11b    = finalObject.getInt("vid_m_11b");
                    designacao.vid_m_1bpt   = finalObject.getInt("vid_m_1bpt");
                    designacao.vid_m_12b    = finalObject.getInt("vid_m_12b");
                    designacao.vid_m_21a    = finalObject.getInt("vid_m_21a");
                    designacao.vid_m_2apt   = finalObject.getInt("vid_m_2apt");
                    designacao.vid_m_22a    = finalObject.getInt("vid_m_22a");
                    designacao.vid_m_21b    = finalObject.getInt("vid_m_21b");
                    designacao.vid_m_2bpt   = finalObject.getInt("vid_m_2bpt");
                    designacao.vid_m_22b    = finalObject.getInt("vid_m_22b");
                    designacao.vid_m_31a    = finalObject.getInt("vid_m_31a");
                    designacao.vid_m_3apt   = finalObject.getInt("vid_m_3apt");
                    designacao.vid_m_32a    = finalObject.getInt("vid_m_32a");
                    designacao.vid_m_31b    = finalObject.getInt("vid_m_31b");
                    designacao.vid_m_3bpt   = finalObject.getInt("vid_m_3bpt");
                    designacao.vid_m_32b    = finalObject.getInt("vid_m_32b");
                    designacao.vid_m_41a    = finalObject.getInt("vid_m_41a");
                    designacao.vid_m_4apt   = finalObject.getInt("vid_m_4apt");
                    designacao.vid_m_42a    = finalObject.getInt("vid_m_42a");
                    designacao.vid_m_41b    = finalObject.getInt("vid_m_41b");
                    designacao.vid_m_4bpt   = finalObject.getInt("vid_m_4bpt");
                    designacao.vid_m_42b    = finalObject.getInt("vid_m_42b");
                    designacao.vid_c_1      = finalObject.getInt("vid_c_1");
                    designacao.vid_c_2      = finalObject.getInt("vid_c_2");
                    designacao.vid_c_3      = finalObject.getInt("vid_c_3");
                    designacao.vid_dir      = finalObject.getInt("vid_dir");
                    designacao.vid_leit     = finalObject.getInt("vid_leit");
                    designacao.vid_cong     = finalObject.getString("vid_cong");
                    designacao.vid_especial = finalObject.getInt("vid_especial");
                    designacao.vid_busca    = finalObject.getString("vid_busca");

                    Log.d("designacoes", designacao.vid_busca);

                    if (crud_designacao.designacao_count(designacao.vid_id_site) > 0) {
                        crud_designacao.altera_designacao(designacao);
                    } else {
                        crud_designacao.insere_designacao(designacao);
                    }

                }

                return finalBufferedData.toString();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                e.printStackTrace();
            } finally {

                if (connection != null){
                    connection.disconnect();
                }

                try{
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            fila_sequence++;
            fila_json(fila_sequence);
        }
    }

    public class json_privilegios extends AsyncTask<String, String, String> {

        int fila_sequence = 3;

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{

                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("cong","50592")
                        .appendQueryParameter("updt","2010-01-01");
                String query = builder.build().getEncodedQuery();

                Log.d("privilegios", query);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null){

                    buffer.append(line);

                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("privilegios");

                StringBuffer finalBufferedData = new StringBuffer();

                privilegio_class privilegio = new privilegio_class();

                for (int i=0; i<parentArray.length(); i++ ) {

                    JSONObject finalObject = parentArray.getJSONObject(i);

                    //privilegio.pri_id       = finalObject.getInt("");
                    privilegio.pri_id_site  = finalObject.getInt("pri_cod");
                    privilegio.pri_vidsom   = finalObject.getInt("pri_vidsom");
                    privilegio.pri_vidvid   = finalObject.getInt("pri_vidvid");
                    privilegio.pri_vidvol   = finalObject.getInt("pri_vidvol");
                    privilegio.pri_vidind1  = finalObject.getInt("pri_vidind1");
                    privilegio.pri_vidind2  = finalObject.getInt("pri_vidind2");
                    privilegio.pri_vidlimp  = finalObject.getInt("pri_vidlimp");
                    privilegio.pri_camp     = finalObject.getInt("pri_camp");
                    privilegio.pri_dissom   = finalObject.getInt("pri_dissom");
                    privilegio.pri_disvid   = finalObject.getInt("pri_disvid");
                    privilegio.pri_dispres  = finalObject.getInt("pri_dispres");
                    privilegio.pri_disnum   = finalObject.getInt("pri_disnum");
                    privilegio.pri_disora   = finalObject.getString("pri_disora");
                    privilegio.pri_disncong = finalObject.getString("pri_disncong");
                    privilegio.pri_disncid  = finalObject.getString("pri_disncid");
                    privilegio.pri_disvol   = finalObject.getInt("pri_disvol");
                    privilegio.pri_disind1  = finalObject.getInt("pri_disind1");
                    privilegio.pri_disind2  = finalObject.getInt("pri_disind2");
                    privilegio.pri_disanf   = finalObject.getInt("pri_disanf");
                    privilegio.pri_disleit  = finalObject.getInt("pri_disleit");
                    privilegio.pri_dislimp  = finalObject.getInt("pri_dislimp");
                    privilegio.pri_espec    = finalObject.getInt("pri_espec");
                    privilegio.pri_cong     = finalObject.getInt("pri_cong");
                    privilegio.pri_updt     = finalObject.getString("pri_updt");
                    privilegio.pri_busca    = finalObject.getString("pri_busca");

                    Log.d("privilegios",  privilegio.pri_busca);

                    if (crud_privilegio.privilegio_count(privilegio.pri_id_site) > 0) {
                        crud_privilegio.altera_privilegio(privilegio);
                    } else {
                        crud_privilegio.insere_privilegio(privilegio);
                    }

                }

                return finalBufferedData.toString();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                e.printStackTrace();
            } finally {

                if (connection != null){
                    connection.disconnect();
                }

                try{
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            fila_sequence++;
            fila_json(fila_sequence);
        }
    }
}
