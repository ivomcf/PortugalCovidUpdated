package pt.ipg.updated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText username;
    EditText password;
    EditText cpassword;
    Button login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        CardView cardView = (CardView) findViewById(R.id.cardView);

        username = (EditText)findViewById(R.id.editTextUsername);
        password = (EditText)findViewById(R.id.editTextPassword);
        cpassword = (EditText) findViewById(R.id.editTextCPassword);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String cpwd = cpassword.getText().toString().trim();

                if(pwd.equals(cpwd)){
                    long val = db.addUser(user,pwd);
                    if(val > 0){
                        Toast.makeText(RegisterActivity.this,"@string/register_success",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin  = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveToLogin);
                    }else{
                        Toast.makeText(RegisterActivity.this,"@string/register_error",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisterActivity.this,"@string/password_doesnt_match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}