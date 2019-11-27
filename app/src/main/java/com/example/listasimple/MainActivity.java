package com.example.listasimple;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

//1) Definicion de la clase evento; esta clase la utilizaremos para rellenar una lista
class Evento {
    private String categoria;
    private String descripcion;
    private String dia;
    private String hora;
    private String id;
    private String titulo;
    public Evento(String titulo) {
        this.titulo = titulo;
    }
    public String getTitulo() { return this.titulo; }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1) Creación de la activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2) Obtenemos el id del list View (con el id definido en el layout), mas tarde la rellenaremos
        ListView listView = (ListView) findViewById(R.id.listViewEventos);

        //3) Vamos a leer el fichero lista json del a carpeta de assets
        String json ="";
        try {
            InputStream stream = getAssets().open("lista.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json  = new String(buffer);
        } catch (Exception e) { }

        //4) En el string json ahora tenemos el listado como texto, en la siguente linea vamos a parsear
        //el string y convertirlo en una lista de objetos eventos
        List<Evento> listaEventos = Arrays.asList(new Gson().fromJson(json, Evento[].class));

        //5 En la listaEventos tenemos todos los objetos (con todos los campos), vamos a quedarnos solo con el campo que queremos
        //mostrar en la lista  por ejemplo el titulo
        String[] arrayEventos = new String[listaEventos.size()];
        for (int i = 0; i < listaEventos.size(); i++) {
            arrayEventos[i] = listaEventos.get(i).getTitulo();
        }

        //6 Vamos a rellenar el component listView
        ListView listView1 = (ListView) findViewById(R.id.listViewEventos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayEventos);
        listView1.setAdapter(adapter);
    }
}
