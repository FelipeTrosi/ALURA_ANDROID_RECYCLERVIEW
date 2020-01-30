package br.com.curso.alura.ceap.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.curso.alura.ceap.R;
import br.com.curso.alura.ceap.dao.NotaDAO;
import br.com.curso.alura.ceap.model.Nota;
import br.com.curso.alura.ceap.ui.recyclerview.adapter.ListaNotasAdapter;

import static br.com.curso.alura.ceap.ui.activity.NotaActivityConstantes.CHAVE_NOTA;

public class ListaNotasActivity extends AppCompatActivity {

    private ListaNotasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        List<Nota> todasNotas = notasDeExemplo();
        configuraRecyclerView(todasNotas);
        configuraBotaoInserirNota();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void configuraBotaoInserirNota() {
        Button insereNota = findViewById(R.id.lista_notas_insere_nota);
        insereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 2 && data.hasExtra(CHAVE_NOTA)){
            Nota nota = (Nota) data.getSerializableExtra(CHAVE_NOTA);
            new NotaDAO().insere(nota);
            adapter.adiciona(nota);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private List<Nota> notasDeExemplo() {
        NotaDAO dao = new NotaDAO();
        dao.insere(new Nota("Primeira nota", "Descrição pequena"),
                new Nota("Segunda nota", "Segunda descrição é bem maior que a da primeira nota"));

        return dao.todos();
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);
    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);
    }

}
