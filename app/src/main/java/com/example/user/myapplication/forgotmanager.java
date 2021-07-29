package com.example.user.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ybs.passwordstrengthmeter.PasswordStrength;

import java.util.concurrent.ExecutionException;

public class forgotmanager extends AppCompatActivity implements TextWatcher {
    Button confirm;
    Button cancel;
    EditText newpass, user, confirmpass;
    String npass, username, cpass;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotmanager);
        confirm = (Button) findViewById(R.id.button10);
        cancel = (Button) findViewById(R.id.button4);
        newpass = (EditText) findViewById(R.id.editTextValue1);
        user = (EditText) findViewById(R.id.editText5);
        confirmpass = (EditText) findViewById(R.id.editTextValue14);
        newpass.addTextChangedListener(this);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    change(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homemanger.class);
                startActivity(nextScreen);

            }
        });
        progressbar = (ProgressBar) findViewById(R.id.simpleProgressBar);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        updatePasswordStrengthView(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void updatePasswordStrengthView(String password) {
        TextView strengthView = (TextView) findViewById(R.id.textView25);
        if (TextView.VISIBLE != strengthView.getVisibility())
            return;

        if (password.isEmpty()) {
            strengthView.setText("");
            progressbar.setProgress(0);
            return;
        }

        PasswordStrength str = PasswordStrength.calculateStrength(password);
        strengthView.setText(str.getText(this));
        strengthView.setTextColor(str.getColor());

        progressbar.getProgressDrawable().setColorFilter(str.getColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        if (str.getText(this).equals("Weak")) {
            progressbar.setProgress(25);
        } else if (str.getText(this).equals("Medium")) {
            progressbar.setProgress(50);
        } else if (str.getText(this).equals("Strong")) {
            progressbar.setProgress(75);
        } else {
            progressbar.setProgress(100);
        }
    }

    public void change(View v) throws ExecutionException, InterruptedException {
        username = user.getText().toString();
        npass = newpass.getText().toString();
        cpass = confirmpass.getText().toString();
        if ((username.equals("")) || (npass.equals("")) || (cpass.equals(""))) {
            AlertDialog.Builder alert;
            alert = new AlertDialog.Builder(forgotmanager.this);
            alert.setMessage("Please fill all the fields");
            alert.setTitle("Error message");
            alert.show();
        } else if (npass.equals(cpass)) {
            backgroundhome back = new backgroundhome(this);
            String myvalue = back.execute("change", username, npass).get();
            Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            if (myvalue.contains("change successfully")) {
                Intent nextScreen = new Intent(getApplicationContext(), homemanger.class);
                startActivity(nextScreen);
            }
        }
        else {
            AlertDialog.Builder alert;
            alert = new AlertDialog.Builder(forgotmanager.this);
            alert.setMessage("The confirm password and The new password should be identical");
            alert.setTitle("Error message");
            alert.show();
        }
    }
}