package com.smi.test.authentifIcation;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smi.test.MainActivity;
import com.smi.test.R;
import com.tfb.fbtoast.FBToast;

public class RegistreActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mEmail,mPassword;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    String userID;
    LoadingButton mRegisterBtn;


    public int clickcount = 0;
    View animate_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);


        mEmail      = findViewById(R.id.Email);
        mPassword   = findViewById(R.id.password);
        mRegisterBtn= findViewById(R.id.loading_btn);
        mLoginBtn   = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }



        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });





        mRegisterBtn.setBackgroundShader(new LinearGradient(0f, 0f, 1000f, 100f, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimary), Shader.TileMode.CLAMP));
        mRegisterBtn.setCornerRadius(50f);


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                clickcount = clickcount + 1;
                if (clickcount == 1) {
                    mRegisterBtn.startLoading(); //start loading
//mLoginBtn.setEnabled(false);


                    if (mEmail.getText().toString().equals("")) {

                        mRegisterBtn.loadingFailed();

                        Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                // yourMethod();
                                FBToast.errorToast(RegistreActivity.this, "E-mail obligatoire", FBToast.LENGTH_SHORT);
                                clickcount = 0;
                                //  username.setError("Nom d'utilisateur obligatoire");
                                mEmail.requestFocus();
                                //  mLoginBtn.reset();
                            }
                        }, 1000);


                    } else if (mPassword.getText().toString().equals("")) {
                        mRegisterBtn.loadingFailed();

                        Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                // yourMethod();

                                FBToast.errorToast(RegistreActivity.this, "Mot de passe obligatoire", FBToast.LENGTH_SHORT);
                                clickcount = 0;
                                //    password.setError("Mot de passe obligatoire");
                                mPassword.requestFocus();
                                //  mLoginBtn.reset();

                            }
                        }, 1000);

                    } else {


                        fAuth.createUserWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    // send verification link

                                    FirebaseUser fuser = fAuth.getCurrentUser();
                                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {


                                            mRegisterBtn.loadingSuccessful();


                                            Handler handler2 = new Handler();
                                            handler2.postDelayed(new Runnable() {
                                                public void run() {
                                                    // yourMethod();

                                                    Handler handler1 = new Handler();
                                                    handler1.postDelayed(new Runnable() {
                                                        public void run() {
                                                            double cx = (mRegisterBtn.getLeft() + mRegisterBtn.getRight()) / 2;
                                                            double cy = (mRegisterBtn.getTop() + mRegisterBtn.getBottom()) / 2;

                                                            animate_view = findViewById(R.id.animate_view);
                                                            Animator animator = ViewAnimationUtils.createCircularReveal(animate_view, (int) cx, (int) cy, 0f, getResources().getDisplayMetrics().heightPixels * 1.2f);
                                                            animator.setDuration(500);
                                                            animate_view.setVisibility(View.VISIBLE);
                                                            animator.start();


                                                            animator.addListener(new Animator.AnimatorListener() {
                                                                @Override
                                                                public void onAnimationStart(Animator animator) {
                                                                    mRegisterBtn.reset();

                                                                    animate_view.setVisibility(View.INVISIBLE);

                                                                }

                                                                @Override
                                                                public void onAnimationEnd(Animator animator) {


                                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                                    startActivity(intent);


                                                                    mRegisterBtn.reset();
                                                                    overridePendingTransition(0, 0);
                                                                    userID = fAuth.getCurrentUser().getUid();


                                                                }

                                                                @Override
                                                                public void onAnimationCancel(Animator animator) {

                                                                }

                                                                @Override
                                                                public void onAnimationRepeat(Animator animator) {

                                                                }
                                                            });


                                                        }
                                                    }, 0);

                                                }
                                            }, 1000);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            mRegisterBtn.loadingFailed();

                                            Handler handler2 = new Handler();
                                            handler2.postDelayed(new Runnable() {
                                                public void run() {
                                                    // yourMethod();

                                                    FBToast.errorToast(RegistreActivity.this, "Probleme d'inscription", FBToast.LENGTH_SHORT);
                                                    clickcount = 0;
                                                    //    password.setError("Mot de passe obligatoire");
                                                    //  mLoginBtn.reset();

                                                }
                                            }, 1000);
                                        }
                                    });




                                }else {
                                    mRegisterBtn.loadingFailed();

                                    Handler handler2 = new Handler();
                                    handler2.postDelayed(new Runnable() {
                                        public void run() {
                                            // yourMethod();

                                          //  FBToast.errorToast(RegistreActivity.this, "Probleme d'inscription", FBToast.LENGTH_SHORT);
                                            Toast.makeText(RegistreActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                            clickcount = 0;
                                            //    password.setError("Mot de passe obligatoire");
                                            //  mLoginBtn.reset();

                                        }
                                    }, 1000);
                                }
                            }
                        });


                    }


                    //5 seconds
                } else {

                }


            }
        });

    }
}