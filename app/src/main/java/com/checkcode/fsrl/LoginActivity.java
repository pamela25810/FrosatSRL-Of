package com.checkcode.fsrl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUser, editTextPass;
    private Button btnLogin;
    private TextView textViewInfo;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cargarLayouts();
        verificarLogueo();
        botonLogueo();

    }

    private void verificarLogueo() {
        if (sharedPreferences == null){
            sharedPreferences = getSharedPreferences("miLogueo", MODE_PRIVATE);
            }
        String usuario = sharedPreferences.getString("user", "");
        String contrasenia = sharedPreferences.getString("password", "");
        if (usuario != null && !usuario.equals("")){
            if (contrasenia != null&& !contrasenia.equals("")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            }
        }
    }

    private void botonLogueo() {
        btnLogin.setOnClickListener(v -> validarLogueo());
    }

    private void validarLogueo() {
        if (editTextUser.getText().toString().equals("")||editTextPass.getText().toString().equals("")){
            textViewInfo.setText(R.string.aviso1);
        }
        else {
            guardarLogueo(editTextUser.getText().toString(),editTextPass.getText().toString());

        }
    }

    private void guardarLogueo(String user, String password) {
        try {
            if (user.equals("vendedor")&& password.equals("vendedor1")) {
                if (sharedPreferences == null) {
                    sharedPreferences = getSharedPreferences("miLogueo", MODE_PRIVATE);
                }
                sharedEditor = sharedPreferences.edit();
                sharedEditor.putString("user", user);
                sharedEditor.putString("password",password);
                sharedEditor.commit();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else
                textViewInfo.setText(R.string.aviso2);
                editTextUser.clearFocus();
        } catch (Exception ex) {
            textViewInfo.setText(ex.getMessage());
        }

    }

    private void cargarLayouts() {
        editTextUser=findViewById(R.id.edtUser);
        editTextPass=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        textViewInfo=findViewById(R.id.txtInfo);
        sharedPreferences = getSharedPreferences("miLogueo", MODE_PRIVATE);
    }
}