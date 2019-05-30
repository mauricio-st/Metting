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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.owtraducoes.metting.bd_access.bd_class.designacao_class;
import com.owtraducoes.metting.bd_access.bd_class.matriculado_class;
import com.owtraducoes.metting.bd_access.bd_class.meiosemana_class;
import com.owtraducoes.metting.bd_access.bd_class.tema_class;
import com.owtraducoes.metting.bd_access.bd_crud.designacao_crud;
import com.owtraducoes.metting.bd_access.bd_crud.matriculado_crud;
import com.owtraducoes.metting.bd_access.bd_crud.tema_crud;
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

public class main extends AppCompatActivity {

    private open_helper bd_rules;
    private SQLiteDatabase conexao_bd;

    private matriculado_crud crud_matriculado;
    private tema_crud crud_tema;
    private designacao_crud crud_designacao;

    private ProgressDialog dialog;

    private LinearLayout ly_main;

    private TextView txt_oracao1;
    private TextView txt_tema_t1;
    private TextView txt_tema_t2;
    private TextView txt_tema_m1;
    private TextView txt_tema_m2;
    private TextView txt_tema_m3;
    private TextView txt_tema_m4;
    private TextView txt_tema_c1;
    private TextView txt_tema_c2;
    private TextView txt_tema_c3;
    private TextView txt_oracao2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ly_main = findViewById(R.id.ly_main);

        txt_oracao1 = findViewById(R.id.txt_oracao1);
        txt_tema_t1 = findViewById(R.id.txt_tema_t1);
        txt_tema_t2 = findViewById(R.id.txt_tema_t2);
        txt_tema_m1 = findViewById(R.id.txt_tema_m1);
        txt_tema_m2 = findViewById(R.id.txt_tema_m2);
        txt_tema_m3 = findViewById(R.id.txt_tema_m3);
        txt_tema_m4 = findViewById(R.id.txt_tema_m4);
        txt_tema_c1 = findViewById(R.id.txt_tema_c1);
        txt_tema_c2 = findViewById(R.id.txt_tema_c2);
        txt_tema_c3 = findViewById(R.id.txt_tema_c3);
        txt_oracao2 = findViewById(R.id.txt_oracao2);

        criarconexao();

        tema_class tema = crud_tema.buscar("2019-05-27");
        meiosemana_class meiosemana = crud_designacao.buscar("2019-05-27");

        txt_oracao1.setText(meiosemana.oracao1);
        txt_tema_t1.setText(tema.vid_t_1tem);
        txt_tema_t2.setText(tema.vid_t_2tem);
        txt_tema_m1.setText(tema.vid_m_1tem);
        txt_tema_m2.setText(tema.vid_m_2tem);
        txt_tema_m3.setText(tema.vid_m_3tem);
        txt_tema_m4.setText(tema.vid_m_4tem);
        txt_tema_c1.setText(tema.vid_c_1tem);
        txt_tema_c2.setText(tema.vid_c_2tem);
        //txt_tema_c3.setText(tema.vid_t_1tem);
        txt_oracao2.setText(meiosemana.oracao2);

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

                //new json_matriculados().execute("http://m.owltraducoes.com/jw/json/registered.php");

                //new json_temas().execute("http://m.owltraducoes.com/jw/json/week_theme.php");

                new json_designacoes().execute("http://m.owltraducoes.com/jw/json/week_name.php");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent it = new Intent(main.this, matriculados_act.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void criarconexao(){

        try{

            bd_rules = new open_helper(this);

            conexao_bd = bd_rules.getWritableDatabase();

            crud_matriculado = new matriculado_crud(conexao_bd);
            crud_tema = new tema_crud(conexao_bd);
            crud_designacao = new designacao_crud(conexao_bd);

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

    public class json_matriculados extends AsyncTask<String, String, String> {

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

            dialog.dismiss();

        }
    }

    public class json_temas extends AsyncTask<String, String, String> {

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

            dialog.dismiss();

        }
    }

    public class json_designacoes extends AsyncTask<String, String, String> {

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

                Log.d("designacoes", finalJson);

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("designacoes");

                StringBuffer finalBufferedData = new StringBuffer();

                designacao_class designacao = new designacao_class();

                for (int i=0; i<parentArray.length(); i++ ) {

                    JSONObject finalObject = parentArray.getJSONObject(i);

                    //designacao.vid_id           = finalObject.getInt("id");
                    designacao.vid_id_site      = finalObject.getInt("vid_cod");
                    designacao.vid_pres         = finalObject.getInt("vid_pres");
                    designacao.vid_oracaoinicio = finalObject.getInt("vid_oracaoinicio");
                    designacao.vid_oracaofinal  = finalObject.getInt("vid_oracaofinal");
                    designacao.vid_som          = finalObject.getInt("vid_som");
                    designacao.vid_volant       = finalObject.getInt("vid_volant");
                    designacao.vid_ind1         = finalObject.getInt("vid_ind1");
                    designacao.vid_ind2         = finalObject.getInt("vid_ind2");
                    designacao.vid_t_1          = finalObject.getInt("vid_t_1");
                    designacao.vid_t_2          = finalObject.getInt("vid_t_2");
                    designacao.vid_t_leita      = finalObject.getInt("vid_t_leita");
                    designacao.vid_t_leapt      = finalObject.getInt("vid_t_leapt");
                    designacao.vid_t_leitb      = finalObject.getInt("vid_t_leitb");
                    designacao.vid_t_lebpt      = finalObject.getInt("vid_t_lebpt");
                    designacao.vid_m_11a        = finalObject.getInt("vid_m_11a");
                    designacao.vid_m_1apt       = finalObject.getInt("vid_m_1apt");
                    designacao.vid_m_12a        = finalObject.getInt("vid_m_12a");
                    designacao.vid_m_11b        = finalObject.getInt("vid_m_11b");
                    designacao.vid_m_1bpt       = finalObject.getInt("vid_m_1bpt");
                    designacao.vid_m_12b        = finalObject.getInt("vid_m_12b");
                    designacao.vid_m_21a        = finalObject.getInt("vid_m_21a");
                    designacao.vid_m_2apt       = finalObject.getInt("vid_m_2apt");
                    designacao.vid_m_22a        = finalObject.getInt("vid_m_22a");
                    designacao.vid_m_21b        = finalObject.getInt("vid_m_21b");
                    designacao.vid_m_2bpt       = finalObject.getInt("vid_m_2bpt");
                    designacao.vid_m_22b        = finalObject.getInt("vid_m_22b");
                    designacao.vid_m_31a        = finalObject.getInt("vid_m_31a");
                    designacao.vid_m_3apt       = finalObject.getInt("vid_m_3apt");
                    designacao.vid_m_32a        = finalObject.getInt("vid_m_32a");
                    designacao.vid_m_31b        = finalObject.getInt("vid_m_31b");
                    designacao.vid_m_3bpt       = finalObject.getInt("vid_m_3bpt");
                    designacao.vid_m_32b        = finalObject.getInt("vid_m_32b");
                    designacao.vid_m_41a        = finalObject.getInt("vid_m_41a");
                    designacao.vid_m_4apt       = finalObject.getInt("vid_m_4apt");
                    designacao.vid_m_42a        = finalObject.getInt("vid_m_42a");
                    designacao.vid_m_41b        = finalObject.getInt("vid_m_41b");
                    designacao.vid_m_4bpt       = finalObject.getInt("vid_m_4bpt");
                    designacao.vid_m_42b        = finalObject.getInt("vid_m_42b");
                    designacao.vid_c_1          = finalObject.getInt("vid_c_1");
                    designacao.vid_c_2          = finalObject.getInt("vid_c_2");
                    designacao.vid_c_3          = finalObject.getInt("vid_c_3");
                    designacao.vid_dir          = finalObject.getInt("vid_dir");
                    designacao.vid_leit         = finalObject.getInt("vid_leit");
                    designacao.vid_cong         = finalObject.getString("vid_cong");
                    designacao.vid_especial     = finalObject.getInt("vid_especial");
                    designacao.vid_busca        = finalObject.getString("vid_busca");

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
            super.onPostExecute(result);

            dialog.dismiss();

        }
    }

    public class json_privilegios extends AsyncTask<String, String, String> {

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

            dialog.dismiss();

        }
    }
}
