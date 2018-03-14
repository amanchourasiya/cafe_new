package ribboncafe.ribbon.com.ribboncafe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class new_sign_up extends AppCompatActivity {


    private Button btn_register;
    private EditText email;
    private EditText password;
    private ProgressDialog progress;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sign_up);

        btn_register=(Button)findViewById(R.id.btn_signup_2);
        email=(EditText)findViewById(R.id.etext_email_reg);
        password=(EditText)findViewById(R.id.etext_pass_reg);
        progress=new ProgressDialog(this);

        firebaseauth= FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                registeruser(user,pass);
            }
        });

    }


    private void registeruser(String user,String pass)
    {
        if(TextUtils.isEmpty(user))
        {
            Toast.makeText(this,"Please Enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please Enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progress.setMessage("Registering Please Wait");
        progress.show();




        firebaseauth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(new_sign_up.this,"Successfully Registered", Toast.LENGTH_LONG).show();
                    Intent change_activity=new Intent(new_sign_up.this,new_sign_in.class);
                    new_sign_up.this.startActivity(change_activity);

                }
                else
                {
                    Toast.makeText(new_sign_up.this,"Registration Error", Toast.LENGTH_LONG).show();

                }
                progress.dismiss();
            }
        });
    }


}
