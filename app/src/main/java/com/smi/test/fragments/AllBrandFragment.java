package com.smi.test.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smi.test.MainActivity;
import com.smi.test.R;
import com.smi.test.SliderItem;
import com.smi.test.adapter.ListDocumentAdapter;
import com.smi.test.entity.Brands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllBrandFragment extends Fragment {

    @BindView(R.id.gridview)
    GridView gridview;
    ListDocumentAdapter listDocumentAdapter;
    public  List<Brands> brandsList_all = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_brand, container, false);
        ButterKnife.bind(this, view);





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

                    brandsList_all.add(brands);



                }

                listDocumentAdapter = new ListDocumentAdapter(getActivity(), R.layout.brand_item, brandsList_all);
                listDocumentAdapter.setListData(brandsList_all);

                gridview.setAdapter(listDocumentAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


        return view;

    }
}