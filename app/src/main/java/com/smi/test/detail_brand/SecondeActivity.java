package com.smi.test.detail_brand;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.Shimmer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smi.test.R;
import com.smi.test.entity.Brands;
import com.smi.test.entity.SliderItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.smi.test.MainActivity.brands_choisen;

public class SecondeActivity extends AppCompatActivity {


    @BindView(R.id.image_marque)
    ImageView image_marque;

public double SUM_AMOUNT=0;
public int COUNT_VENTE=0;
public double COMMISION=0;

    @BindView(R.id.txt_nom_marque)
    TextView txt_nom_marque;


    @BindView(R.id.txt_desc_marque)
    TextView txt_desc_marque;


    @BindView(R.id.txt_nombre_vente)
    TextView txt_nombre_vente;


    @BindView(R.id.txt_commision)
    TextView txt_commision;


    @BindView(R.id.txt_ca)
    TextView txt_ca;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
        ButterKnife.bind(this);


        Glide.with(SecondeActivity.this)
                .load(brands_choisen.getPic())
                .fitCenter()
                .into(image_marque);

        txt_nom_marque.setText(brands_choisen.getDisplayName());
        txt_desc_marque.setText(brands_choisen.getDescription());


        //brands_choisen




        Query query = FirebaseDatabase.getInstance().getReference("conversions").child("purchase").orderByChild("offerId").equalTo(brands_choisen.getOfferId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                  try {
                      SUM_AMOUNT=SUM_AMOUNT+childSnapshot.child("amount").getValue(double.class);
                  }catch (Exception e)
                  {
                      e.printStackTrace();
                  }


                  try {
                      COMMISION=COMMISION+childSnapshot.child("commission").getValue(double.class);
                  }catch (Exception e)
                  {
                      e.printStackTrace();
                  }

                    COUNT_VENTE=COUNT_VENTE+1;
                }
                Log.e("amount",SUM_AMOUNT+"");
                txt_ca.setText("" + new DecimalFormat("###,###0.000").format(SUM_AMOUNT));
                txt_commision.setText("" + new DecimalFormat("###,###0.000").format(COMMISION));
                txt_nombre_vente.setText("" + COUNT_VENTE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("amount11",databaseError.getMessage()+"");

            }
        });





    }
}
