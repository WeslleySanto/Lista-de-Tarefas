package br.com.weslleyesanto.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "db_tarefas";
    public static String TABELA_TAREFAS = "tarefas";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTarefas =     "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                                        " ( " +
                                            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                            "nome TEXT NOT NULL"+
                                        " )";
        try {
            db.execSQL(sqlTarefas);
            Log.i("Info DB", "Sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("Info DB", "Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
