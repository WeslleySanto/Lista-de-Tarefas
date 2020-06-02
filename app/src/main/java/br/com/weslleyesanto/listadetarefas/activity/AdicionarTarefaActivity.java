package br.com.weslleyesanto.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.weslleyesanto.listadetarefas.R;
import br.com.weslleyesanto.listadetarefas.helper.TarefaDAO;
import br.com.weslleyesanto.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        textInputEditTextTarefa = findViewById(R.id.textInputEditTextTarefa);

        //Recuperar tarefa, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa na caixa de texto
        if ( tarefaAtual != null ){
            textInputEditTextTarefa.setText(tarefaAtual.getNomeTarefa());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                String nomeTarefa = textInputEditTextTarefa.getText().toString();

                Tarefa tarefa = new Tarefa();

                if (tarefaAtual != null){//Editar
                    tarefa.setNomeTarefa(nomeTarefa);
                    tarefa.setId(tarefaAtual.getId());

                    //Atualizar no banco de dados
                    if( tarefaDAO.atualizar(tarefa) ){
                        //Fecha activity
                        finish();
                        Toast.makeText(
                                getApplicationContext(),
                                "Sucesso ao atualizar tarefa!",
                                Toast.LENGTH_LONG
                        ).show();
                    }else{
                        Toast.makeText(
                                getApplicationContext(),
                                "Erro ao atualizar tarefa!",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }else{//Salvar
                    if ( !nomeTarefa.isEmpty() ){
                        tarefa.setNomeTarefa(nomeTarefa);
                        if( tarefaDAO.salvar(tarefa) ){
                            //Fecha activity
                            finish();
                            Toast.makeText(
                                getApplicationContext(),
                                "Sucesso ao salvar tarefa!",
                                Toast.LENGTH_LONG
                            ).show();
                        }else{
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Erro ao salvar tarefa!",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                }
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}
