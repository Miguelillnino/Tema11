package com.tecmilenio.conexionsqliteapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tecmilenio.conexionsqliteapp.database.ConstructorData;
import com.tecmilenio.conexionsqliteapp.pojo.Information;
import com.tecmilenio.conexionsqliteapp.pojo.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edName;
    private EditText edUniversity;
    private EditText edCareer;
    private TextView tvInformation;
    private FloatingActionButton btnSave;
    private FloatingActionButton btnEdit;
    private FloatingActionButton btnDelete;
    private LinearLayout lyTextSaved;
    Long idUser;
    private Activity activity;

private ConstructorData constructorData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        activity = this;
        constructorData = new ConstructorData(getApplicationContext());
        constructorData.startDb();
        idUser = 0L;

        edName = (EditText) findViewById(R.id.edName);
        edUniversity = (EditText) findViewById(R.id.edUniversity);
        edCareer = (EditText) findViewById(R.id.edCareer);
        tvInformation = (TextView) findViewById(R.id.tvInformation);
        lyTextSaved = (LinearLayout) findViewById(R.id.lyTextSaved);
        btnSave = (FloatingActionButton) findViewById(R.id.btnSave);
        btnEdit = (FloatingActionButton) findViewById(R.id.btnEdit);
        btnDelete = (FloatingActionButton) findViewById(R.id.btnDelete);

        btnSave.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("Tecmi guardar");
                Information inf = new Information();
                inf.setId(idUser);
                inf.setCarrer(edCareer.getText().toString());
                inf.setName(edName.getText().toString());
                inf.setUniversity(edUniversity.getText().toString());

                Response resp ;
                if (idUser > 0){
                    resp = constructorData.editInformation(inf);
                } else {
                    resp = constructorData.insertInformation(inf);
                }

                Toast.makeText(getApplicationContext(), resp.getMessage(), Toast.LENGTH_LONG).show();

                if (resp.isSuccess()) {
                    idUser = resp.getId();
                    lyTextSaved.setVisibility(View.VISIBLE);

                    tvInformation.setText("La persona con ID " + idUser + " se llama " + edName.getText() + " asiste a la escuela " + edUniversity.getText() + " y estudia la carrera de " + edCareer.getText() + ".");

                    edName.setText("");
                    edUniversity.setText("");
                    edCareer.setText("");
                    btnSave.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnEdit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("Tecmi btnEdit");
                edName.setText("");
                edUniversity.setText("");
                edCareer.setText("");

                lyTextSaved.setVisibility(View.GONE);
                btnSave.setVisibility(View.VISIBLE);

            }
        });

        btnDelete.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("Tecmi btnDelete");
                boolean resp = constructorData.deleteUser(idUser);
                if (resp){
                    Toast.makeText(getApplicationContext(), "Operación exitosa.", Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}