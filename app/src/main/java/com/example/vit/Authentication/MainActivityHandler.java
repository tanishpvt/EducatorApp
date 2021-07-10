package com.example.vit.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vit.Drawer.DashBoardFragment;
import com.example.vit.MainActivity;
import com.example.vit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivityHandler extends AppCompatActivity {

    EditText username,fullname,email,password;
//    Button register;
    TextView txt_login,register;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;
    String str_password;
    String str_username ;
    String str_fullname ;
    String str_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_handler);
        username=findViewById(R.id.username);
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        txt_login=findViewById(R.id.txt_login);

        auth=FirebaseAuth.getInstance();

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHandler.this,LoginActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(MainActivityHandler.this);
                pd.setMessage("Please wait..");
                pd.show();

                str_username = username.getText().toString();
                str_fullname = fullname.getText().toString();
                str_email = email.getText().toString();
                str_password = password.getText().toString();


                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_fullname) ||
                        TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)) {
                    Toast.makeText(MainActivityHandler.this, "All fields are required!", Toast.LENGTH_SHORT).show();

                } else if (str_password.length() < 6) {
                    Toast.makeText(MainActivityHandler.this, "Password must have 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    register(str_username, str_fullname, str_email, str_password);
                }

            }
        });
    }

    private void register(final String username, final String fullname, String email, String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(MainActivityHandler.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser =auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                            HashMap<String,Object> hashMap=new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username.toLowerCase());
                            hashMap.put("fullname",fullname);
                            hashMap.put("bio","");
                            hashMap.put("imageurl","https://firebasestorage.googleapis.com/v0/b/vit-education.appspot.com/o/a.png?alt=media&token=6b76bce6-94ce-4c0e-a96d-3ec8be75ad61");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        pd.dismiss();
                                        Intent intent = new Intent(MainActivityHandler.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        } else {
                            pd.dismiss();
                            Toast.makeText(MainActivityHandler.this,"You can't register with this email or password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


