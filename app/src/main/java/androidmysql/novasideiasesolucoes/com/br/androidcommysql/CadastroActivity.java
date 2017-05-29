package androidmysql.novasideiasesolucoes.com.br.androidcommysql;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edNome = (EditText) findViewById(R.id.txtNomeSalvar);
                EditText edEmail = (EditText) findViewById(R.id.txtEmailSalvar);



                Ion.with(getBaseContext()).load("http://192.168.1.4/android/inserir.php")
                        .setBodyParameter("nome", edNome.getText().toString())
                        .setBodyParameter("email", edEmail.getText().toString())
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                if(result.get("retorno").getAsString().equals("YES")){
                                    Toast.makeText(getBaseContext(), "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), ListarActivity.class));

                                }



                            }
                        });


               /*
                try {
                    StringBuilder url = new StringBuilder();
                    url.append("http://192.168.1.4/android/inserir.php?");

                    url.append("nome=");
                    url.append(URLEncoder.encode(edNome.getText().toString(),"UTF-8"));

                    url.append("&email=");
                    url.append(URLEncoder.encode(edEmail.getText().toString(),"UTF-8"));

                    new HttpRequest().execute(url.toString());

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                */


            }
        });





    }







/*

    private class HttpRequest extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String retorno = null;
            try {
                String urlHttp = params[0];
                URL url = new URL(urlHttp);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                BufferedReader bfr = new BufferedReader(ips);
                retorno = bfr.readLine();

            } catch (Exception ex){

            }
            return retorno;
        }

        @Override
        protected void onPostExecute(String result){
            if (result.equals("YES")){
                Toast.makeText(getBaseContext(), "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getBaseContext(), "Erro ao cadastrar o cliente!", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

}

