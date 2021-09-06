package com.smi.test.detail_brand;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smi.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.smi.test.MainActivity.brands_choisen;

public class SecondeActivity extends AppCompatActivity {


    @BindView(R.id.image_marque)
    ImageView image_marque;



    @BindView(R.id.txt_nom_marque)
    TextView txt_nom_marque;


    @BindView(R.id.txt_desc_marque)
    TextView txt_desc_marque;





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

    }
}
