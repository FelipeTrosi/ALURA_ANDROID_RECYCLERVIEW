package br.com.curso.alura.ceap.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.curso.alura.ceap.R;
import br.com.curso.alura.ceap.dao.NotaDAO;
import br.com.curso.alura.ceap.model.Nota;
import br.com.curso.alura.ceap.ui.recyclerview.adapter.ListaNotasAdaper;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        NotaDAO dao = new NotaDAO();

        for (int i = 1;  i < 10000; i++){
            dao.insere(
                    new Nota("Titulo " + i,
                            "Descrição "+ i)
            );
        }

        List<Nota> listaNotas = dao.todos();

        RecyclerView listView = findViewById(R.id.lista_notas_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new ListaNotasAdaper(this, listaNotas));
    }
}
