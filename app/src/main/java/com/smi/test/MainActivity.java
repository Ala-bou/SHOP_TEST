package com.smi.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smi.test.adapter.ListDocumentAdapter;
import com.smi.test.adapter.PageAdapter;
import com.smi.test.authentifIcation.LoginActivity;
import com.smi.test.entity.Brands;
import com.tfb.fbtoast.FBToast;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends AppCompatActivity {
    SlidingRootNavBuilder slidingRootNavBuilder;

    @BindView(R.id.menu)
    ImageView menu;


    SlidingRootNav slidingRootNav;
    List<SliderItem> sliderItemList = new ArrayList<>();

    public  List<Brands> brandsList = new ArrayList<>();
    public static   List<Brands> brandsList_new = new ArrayList<>();
public static Brands brands_choisen=new Brands();

    SliderView sliderView;
    private SliderAdapterExample adapter;


    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    PageAdapter pageAdapter;

    @BindView(R.id.tab_all)
    TabItem tab_all;

    @BindView(R.id.tab_premium)
    TabItem tab_premium;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tablayout);

        viewPager = findViewById(R.id.viewPager);


        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.gold));

                } else {

                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.white));

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        sliderView = findViewById(R.id.imageSlider);


        adapter = new SliderAdapterExample(this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                brands_choisen=brandsList_new.get(sliderView.getCurrentPagePosition());
                startActivity(new Intent(MainActivity.this, SecondeActivity.class));

            }
        });


        setupDrawer();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("brands");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                List<String> list = new ArrayList<>();


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.e("Database", "=====" + ds.getKey() + "=========");


                    Brands brands = new Brands();


                    Log.e("Database1", "  " + ds.getKey());

                    brands.setKEY_BRAND(ds.getKey());
                    try {
                        brands.setDisplayName(ds.child("displayName").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        brands.setAdvertiser(ds.child("advertiser").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        brands.setDescription(ds.child("description").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        brands.setDescription_en(ds.child("description-en").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        brands.setHref(ds.child("href").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        brands.setName(ds.child("name").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        brands.setPic(ds.child("pic").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        brands.setIndependant(ds.child("isIndependant").getValue(boolean.class));

                    } catch (Exception e) {
                        brands.setIndependant(false);

                        e.printStackTrace();
                    }

                    try {
                        brands.setNew(ds.child("isNew").getValue(boolean.class));


                    } catch (Exception e) {
                        brands.setNew(false);

                        e.printStackTrace();
                    }

                    try {
                        brands.setPremium(ds.child("premium").getValue(boolean.class));

                    } catch (Exception e) {
                        brands.setPremium(false);
                        e.printStackTrace();
                    }

                    try {
                        brands.setPrivate(ds.child("private").getValue(boolean.class));

                    } catch (Exception e) {
                        brands.setPrivate(false);

                        e.printStackTrace();
                    }

                    brands.setOfferId(ds.child("offerId").getValue(int.class));


                    for (DataSnapshot ds_commissions_en : ds.child("categories-en").getChildren()) {
                        String key = ds_commissions_en.getKey();
                        String value = ds_commissions_en.getValue() + "";
                        HashMap<String, String> commissions_en = new HashMap<String, String>();
                        commissions_en.put(key, value);
                        brands.setCategories_en(commissions_en);


                    }

                    for (DataSnapshot ds_catégorie : ds.child("catégorie").getChildren()) {
                        String key = ds_catégorie.getKey();
                        String value = ds_catégorie.getValue() + "";
                        HashMap<String, String> catégorie = new HashMap<String, String>();
                        catégorie.put(key, value);
                        brands.setCatégorie(catégorie);


                    }
                    for (DataSnapshot ds_commissions : ds.child("commissions").getChildren()) {
                        String key = ds_commissions.getKey();
                        String value = ds_commissions.getValue() + "";
                        HashMap<String, String> commissions = new HashMap<String, String>();
                        commissions.put(key, value);
                        brands.setCommissions(commissions);


                    }


                    Log.e("Database1", "  " + ds.getKey());

                    for (DataSnapshot ds_commissions_en : ds.child("commissions-en").getChildren()) {
                        String key = ds_commissions_en.getKey();
                        String value = ds_commissions_en.getValue() + "";
                        HashMap<String, String> commissions_en = new HashMap<String, String>();
                        commissions_en.put(key, value);
                        brands.setCommissions_en(commissions_en);
                        Log.e("Database1", key + " _____ " + value);


                    }


//                    if (ds.getKey().equals("-M44qVpLHDIBvVqNJxRF"))


                    if (brands.isNew) {


                        brandsList_new.add(brands);


                        //dummy data
                        SliderItem sliderItem = new SliderItem();
                        sliderItem.setDescription(brands.getDisplayName());
                        Log.e("getDisplayName", brands.getDisplayName() + "  --   " + brands.getPic());
                        sliderItem.setImageUrl(brands.getPic());

                        //   if (i % 2 == 0) {
                        //    } else {
                        //      sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
                        //}
                        sliderItemList.add(sliderItem);

                        adapter.renewItems(sliderItemList);

                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


    }

    public void logout() {
        Log.e("User_ID", FirebaseAuth.getInstance().getCurrentUser().getEmail() + "");

        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }


    public void setupDrawer() {


        menu = findViewById(R.id.menu);


        slidingRootNavBuilder = new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.menu_layout);

        slidingRootNav = slidingRootNavBuilder.inject();

TextView email=findViewById(R.id.email);

email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail()+"");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (slidingRootNav.isMenuOpened())
                    slidingRootNav.closeMenu();
                else
                    slidingRootNav.openMenu();


            }
        });


        LinearLayout lin_logout = findViewById(R.id.lin_logout);
        lin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                logout();


            }
        });


    }
}