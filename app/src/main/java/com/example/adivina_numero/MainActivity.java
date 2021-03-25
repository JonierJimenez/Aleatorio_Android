package com.example.adivina_numero;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText intentos, numeroIngresado;
    Button iniciar, validar;
    RadioButton cinco,siete,diez;
    TextView RegistrodeNumero,Respuesta;

    Random random;
    int Nintento=0;
    int r,n=1;
    String valor;

    boolean Bandera=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegistrodeNumero =findViewById(R.id.txtRegistrodeNumero);
        Respuesta = findViewById(R.id.txtRespuesta);

        intentos= findViewById(R.id.edtMostrar);
        numeroIngresado =findViewById(R.id.edtNumero);

        iniciar = findViewById(R.id.btnInicio);
        validar =findViewById(R.id.btnValidar);

        cinco = findViewById(R.id.rd5);
        siete =findViewById(R.id.rd7);
        diez =findViewById(R.id.rd10);

        iniciar.setOnClickListener(this);
        validar.setOnClickListener(this);
        cinco.setOnClickListener(this);
        siete.setOnClickListener(this);
        diez.setOnClickListener(this);


        validar.setEnabled(false);

        numeroIngresado.setEnabled(false);

        intentos.setEnabled(false);
        RegistrodeNumero.setText("");
        validar.setEnabled(false);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnInicio:
                Respuesta.setText("");
                if(Bandera){
                    desactivarRadioBoton();
                    RegistrodeNumero.setText("");
                    Toast.makeText(this, "Puede ingresar un numero", Toast.LENGTH_SHORT).show();
                    intentos.setText("1");
                    numeroIngresado.setEnabled(true);
                    numeroIngresado.setFocusable(true);
                    numeroIngresado.setCursorVisible(true);
                    iniciar.setEnabled(false);
                    validar.setEnabled(true);
                    r=Aleatorio();
                    //Toast.makeText(this, "El numero es:"+r, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Seleccione el numero de intentos", Toast.LENGTH_SHORT).show();
                }

                break;
            case  R.id.btnValidar:

                String c1= numeroIngresado.getText().toString();
                if(Nintento>=n){
                    if(c1.isEmpty()){
                        Toast.makeText(this, "Ingrese un numero", Toast.LENGTH_SHORT).show();
                    }else {
                        if(Bandera){
                                int numero = Integer.parseInt(c1);
                                if(numero>r){

                                    RegistrodeNumero.setText("El numero es < que "+c1);
                                }
                                else if (numero<r){

                                    RegistrodeNumero.setText("El numero es > que "+c1);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"EL numero es el deseado",Toast.LENGTH_LONG).show();
                                    RegistrodeNumero.setText("Ganaste el numero es: " +c1);
                                    restablecerGanador();
                                }
                                valor=Integer.toString(n);
                                intentos.setText(valor);
                                n=n+1;
                                numeroIngresado.setText("");
                        }else{
                            Toast.makeText(this, "Seleccione numero intentos", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    Toast.makeText(this, "Numero de intentos agotados", Toast.LENGTH_SHORT).show();
                    Respuesta.setText("El numero era: "+r);
                    restablecer();
                }



                break;

            case R.id.rd5:  Bandera=true; Nintento=5; break;
            case R.id.rd7:  Bandera=true; Nintento=7; break;
            case R.id.rd10: Bandera=true; Nintento=10;break;
        }

    }

    public int Aleatorio(){
        random = new Random();
        random.setSeed(System.currentTimeMillis());
        int numeroRandom = random.nextInt(101);
        return numeroRandom;
    }

    public void restablecer(){
        n=1;
        intentos.setText("intentos");

        numeroIngresado.setText("");
        RegistrodeNumero.setText("");

        numeroIngresado.setEnabled(false);
        iniciar.setEnabled(true);
        validar.setEnabled(false);
        activarRadioBoton();
    }

    public void restablecerGanador(){
        n=1;
        intentos.setText("intentos");

        numeroIngresado.setText("");
        numeroIngresado.setEnabled(false);
        iniciar.setEnabled(true);
        validar.setEnabled(false);
        activarRadioBoton();
    }

    public void desactivarRadioBoton(){
        if(Nintento==5){
            siete.setEnabled(false);
            diez.setEnabled(false);
        }
        else if(Nintento ==7){
            cinco.setEnabled(false);
            diez.setEnabled(false);

        }else{
            cinco.setEnabled(false);
            siete.setEnabled(false);
        }
    }

    public void activarRadioBoton(){
        cinco.setEnabled(true);
        siete.setEnabled(true);
        diez.setEnabled(true);
    }

}//clas fin