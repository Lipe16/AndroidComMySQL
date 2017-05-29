package androidmysql.novasideiasesolucoes.com.br.androidcommysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ListarActivity extends AppCompatActivity {
    ArrayAdapter<JsonObject> clientesAd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


    }

    @Override
    protected  void onResume(){
        super.onResume();

        clientesAd = new ArrayAdapter<JsonObject>(this, 0){

            @Override
            public View getView(int position, View view, ViewGroup viewGrou){
                if(view == null){
                    view = getLayoutInflater().inflate(R.layout.model,null);
                }

                JsonObject obj = getItem(position);

                TextView id = (TextView) view.findViewById(R.id.idModel);
                TextView nome = (TextView) view.findViewById(R.id.nomeModel);
                TextView email = (TextView) view.findViewById(R.id.emailModel);

                id.setText(obj.get("id").getAsString());
                nome.setText(obj.get("nome").getAsString());
                email.setText(obj.get("email").getAsString());

                return view;
            }
        };

        Ion.with(getBaseContext()).load("http://192.168.1.4/android/listar.php")
                .asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray result) {
                for(int i = 0; i<result.size(); i++){
                    JsonObject obj = result.get(i).getAsJsonObject();
                    clientesAd.add(obj);
                }

                ListView ltw = (ListView) findViewById(R.id.listView);
                ltw.setAdapter(clientesAd);

                ltw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        JsonObject obj = (JsonObject) parent.getItemAtPosition(position);

                        String codigo = obj.get("id").getAsString();
                        String nome = obj.get("nome").getAsString();
                        String email = obj.get("email").getAsString();


                        Intent intent = new Intent(getBaseContext(), EditarActivity.class);
                        intent.putExtra("id", codigo);
                        intent.putExtra("nome", nome);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }
                });
            }
        });





    }
}
