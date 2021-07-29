
package com.example.user.myapplication;

        import android.preference.PreferenceManager;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.content.*;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.util.concurrent.ExecutionException;

public class home extends AppCompatActivity {
    Button button ;
    TextView signup ;
    TextView forgotb ;
    EditText user_name,passwor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button = (Button) findViewById(R.id.button);
        signup = (TextView) findViewById(R.id.textView2);
        forgotb = (TextView) findViewById(R.id.textView3);
        user_name= (EditText)findViewById(R.id.editText2);
        passwor= (EditText)findViewById(R.id.editTextValue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onlogin(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), account.class);
                startActivity(nextScreen);

            }
        });
        forgotb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), forgot.class);
                startActivity(nextScreen);

            }
        });
    }
    public void onlogin(View v) throws Exception{
        String username= user_name.getText().toString();
        String password= passwor.getText().toString();
        String type ="login";
        if((username.equals("")) || (password.equals(""))) {
            AlertDialog.Builder alert;
            alert = new AlertDialog.Builder(home.this);
            alert.setTitle("Error Message");
            alert.setMessage("Please fill all fields");
            alert.show();
        }
        else {
            customerhomebackground  backg;
            backg = new customerhomebackground(this);
            String myv = backg.execute(type, username, password).get();
            if(myv.contains("sign in successfully")){
               Toast.makeText(this,myv,Toast.LENGTH_SHORT).show();
             // Intent nextScreen = new Intent(getApplicationContext(), third.class);
                Intent nextScreen = new Intent(getApplicationContext(), userprofile.class);
                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor spreferencesEditor = spreferences.edit();
                spreferencesEditor .putString("usern", username);
                customerhomebackground set = new customerhomebackground(this);
                String image = set.execute("getim",username).get();
                if (image.equals("null")) {
                    spreferencesEditor.putString("im", "noimage");
                    spreferencesEditor.apply();
                    startActivity(nextScreen);
                }
                else {
                    spreferencesEditor.putString("im", image);
                    spreferencesEditor.apply();
                    startActivity(nextScreen);
                }
            }
            else {
                Toast.makeText(this,myv,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
