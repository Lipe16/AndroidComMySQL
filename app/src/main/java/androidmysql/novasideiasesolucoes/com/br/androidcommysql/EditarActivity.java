package androidmysql.novasideiasesolucoes.com.br.androidcommysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class EditarActivity extends AppCompatActivity {
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        final EditText edNome = (EditText) findViewById(R.id.txtNomeEditar);
        final EditText edEmail = (EditText) findViewById(R.id.txtEmailEditar);
        Button btnExcluir = (Button) findViewById(R.id.btnExcluir);
        Button btnEditar = (Button) findViewById(R.id.btnEditar);

        Intent intent = getIntent();
        edNome.setText(intent.getStringExtra("nome"));
        edEmail.setText(intent.getStringExtra("email"));
        id = intent.getStringExtra("id");


       btnExcluir.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Ion.with(getBaseContext()).load("http://192.168.1.4/android/excluir.php")
                       .setBodyParameter("id", id)
                       .asJsonObject()
                       .setCallback(new FutureCallback<JsonObject>() {
                           @Override
                           public void onCompleted(Exception e, JsonObject result) {

                               if(result.get("retorno").getAsString().equals("YES")){
                                   Toast.makeText(getBaseContext(), "Cliente excluido com sucesso!", Toast.LENGTH_SHORT).show();
                                   finish();

                               }
                           }
                       });

           }
       });

     btnEditar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Ion.with(getBaseContext()).load("http://192.168.1.4/android/editar.php")
                     .setBodyParameter("id", id)
                     .setBodyParameter("nome", edNome.getText().toString())
                     .setBodyParameter("email", edEmail.getText().toString())
                     .asJsonObject()
                     .setCallback(new FutureCallback<JsonObject>() {
                         @Override
                         public void onCompleted(Exception e, JsonObject result) {

                             if(result.get("retorno").getAsString().equals("YES")){
                                 Toast.makeText(getBaseContext(), "Cliente excluido com sucesso!", Toast.LENGTH_SHORT).show();
                                 finish();

                             }
                         }
                     });

         }
     });


    }
}
