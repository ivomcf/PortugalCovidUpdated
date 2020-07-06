package pt.ipg.updated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        username = (EditText)findViewById(R.id.editTextUsername);
        password = (EditText)findViewById(R.id.editTextPassword);

        RelativeLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        CardView cardView = (CardView) findViewById(R.id.cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMainActivity();
            }
        });

        TextView tv = (TextView)findViewById(R.id.textViewReg);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToRegisterActivity();
            }
        });
    }

    private void moveToMainActivity(){

        String user = username.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        Boolean res = db.checkUser(user, pwd);

        if(res == true)
        {
            Toast.makeText(LoginActivity.this,"@String/login_success",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(LoginActivity.this,"@String/login_error",Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToRegisterActivity(){

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}