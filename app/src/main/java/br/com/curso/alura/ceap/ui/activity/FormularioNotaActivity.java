package br.com.curso.alura.ceap.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.curso.alura.ceap.R;
import br.com.curso.alura.ceap.model.Nota;

public class FormularioNotaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ic_salvar_nota, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_ic_salva_nota){
            EditText titulo = findViewById(R.id.formulario_nota_titulo);
            EditText descricao = findViewById(R.id.formulario_nota_descricao);

            criaNota(titulo, descricao);
        }
        return super.onOptionsItemSelected(item);
    }

    private void criaNota(EditText titulo, EditText descricao) {
        Nota notaCriada = new Nota(titulo.getText().toString(), descricao.getText().toString());
        Intent intent = new Intent();
        intent.putExtra(CHAVE_NOTA, notaCriada);
        setResult(RESULTADO_NOTA_CRIADA, intent);
        finish();
    }
}


