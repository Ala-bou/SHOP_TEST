package com.smi.test.authentifIcation;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smi.test.MainActivity;
import com.smi.test.R;
import com.tfb.fbtoast.FBToast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.username)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;



    LoadingButton mLoginBtn;


    @BindView(R.id.createText)
    TextView mCreateBtn;


    FirebaseAuth fAuth;


    View animate_view;


    public int clickcount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);



          mLoginBtn=findViewById(R.id.loading_btn);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        mLoginBtn.setBackgroundShader(new LinearGradient(0f, 0f, 1000f, 100f, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimary), Shader.TileMode.CLAMP));
        mLoginBtn.setCornerRadius(50f);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                clickcount = clickcount + 1;
                if (clickcount == 1) {
                    mLoginBtn.startLoading(); //start loading
//mLoginBtn.setEnabled(false);


                    if (mEmail.getText().toString().equals("")) {

                        mLoginBtn.loadingFailed();

                        Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                // yourMethod();
                                FBToast.errorToast(LoginActivity.this, "E-mail obligatoire", FBToast.LENGTH_SHORT);
                                clickcount = 0;
                                //  username.setError("Nom d'utilisateur obligatoire");
                                mEmail.requestFocus();
                                //  mLoginBtn.reset();
                            }
                        }, 1000);


                    } else if (mPassword.getText().toString().equals("")) {
                        mLoginBtn.loadingFailed();

                        Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                // yourMethod();

                                FBToast.errorToast(LoginActivity.this, "Mot de passe obligatoire", FBToast.LENGTH_SHORT);
                                clickcount = 0;
                                //    password.setError("Mot de passe obligatoire");
                                mPassword.requestFocus();
                                //  mLoginBtn.reset();

                            }
                        }, 1000);

                    } else {


                        fAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                                    mLoginBtn.loadingSuccessful();


                                    Handler handler2 = new Handler();
                                    handler2.postDelayed(new Runnable() {
                                        public void run() {
                                            // yourMethod();

                                            Handler handler1 = new Handler();
                                            handler1.postDelayed(new Runnable() {
                                                public void run() {
                                                    double cx = (mLoginBtn.getLeft() + mLoginBtn.getRight()) / 2;
                                                    double cy = (mLoginBtn.getTop() + mLoginBtn.getBottom()) / 2;

                                                    animate_view = findViewById(R.id.animate_view);
                                                    Animator animator = ViewAnimationUtils.createCircularReveal(animate_view, (int) cx, (int) cy, 0f, getResources().getDisplayMetrics().heightPixels * 1.2f);
                                                    animator.setDuration(500);
                                                    animate_view.setVisibility(View.VISIBLE);
                                                    animator.start();


                                                    animator.addListener(new Animator.AnimatorListener() {
                                                        @Override
                                                        public void onAnimationStart(Animator animator) {
                                                            mLoginBtn.reset();

                                                            animate_view.setVisibility(View.INVISIBLE);

                                                        }

                                                        @Override
                                                        public void onAnimationEnd(Animator animator) {


                                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                            startActivity(intent);


                                                            mLoginBtn.reset();
                                                            overridePendingTransition(0, 0);


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
                                } else {


                                    mLoginBtn.loadingFailed();

                                    Handler handler2 = new Handler();
                                    handler2.postDelayed(new Runnable() {
                                        public void run() {
                                            // yourMethod();

                                            FBToast.errorToast(LoginActivity.this, "error", FBToast.LENGTH_SHORT);
                                            clickcount = 0;
                                            //    password.setError("Mot de passe obligatoire");
                                            mPassword.requestFocus();
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



        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistreActivity.class));
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();
        clickcount = 0;
        try {
            animate_view.setVisibility(View.INVISIBLE);
            mLoginBtn.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}