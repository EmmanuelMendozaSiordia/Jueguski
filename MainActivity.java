package com.example.jueguski;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private int puntaje =10;
    private int intentos;
    private int numsecreto;
    private TextView puntos;
    private EditText ingreso;
    private Button boton;
    private boolean salir=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        ingreso = (EditText) findViewById(R.id.ingreso);
        puntos = (TextView) findViewById(R.id.textpuntos);
        boton=(Button) findViewById(R.id.button);
        init();
    }


    private void init(){
        this.intentos=0;
        img1.setVisibility(View.INVISIBLE);
        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        ingreso.setText("");
        this.numsecreto= (int)(Math.random()*9)+1;
        puntos.setText(String.valueOf("Puntaje = "+ puntaje));

    }
    private void builderN0(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Jugar de nuevo?").setTitle("El numero era = "+this.numsecreto);
        builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                finish();
            }
        });
        builder.show();
    }
    private void builderSI(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Jugar de nuevo?").setTitle("Felicitaciones!!");
        builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                finish();
            }
        });
        builder.show();
    }
    private void nintentos(){
        switch (intentos){
            case 1:
                img1.setVisibility(View.VISIBLE);
                break;
            case 2:
                img2.setVisibility(View.VISIBLE);
                break;
            case 3:
                img3.setVisibility(View.VISIBLE);
                builderN0();
                break;
        }
    }

    public void Comprobar(View view) {
        String respuesta = ingreso.getText().toString();
        if(TextUtils.isEmpty(respuesta)){
            Toast.makeText(this,"Debes ingresar un numero",Toast.LENGTH_SHORT).show();
        }else{
            int r = Integer.parseInt(respuesta);
            if(r==this.numsecreto){
                builderSI();

            }else if(r>this.numsecreto){
                ingreso.setText("");
                Toast.makeText(this,"El numero es menor",Toast.LENGTH_SHORT).show();
                this.intentos++;
                nintentos();
            }else if(r<this.numsecreto){
                ingreso.setText("");
                Toast.makeText(this,"El numero es mayor",Toast.LENGTH_SHORT).show();
                this.intentos++;
                nintentos();
            }


        }
    }
}
