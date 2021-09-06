package com.smi.test.entity;

import java.util.HashMap;

public class Brands {

    public String KEY_BRAND;
    public String advertiser;

    public HashMap<String, String> commissions_en = new HashMap<String, String>();
    public HashMap<String, String> commissions = new HashMap<String, String>();
    public HashMap<String, String> catégorie = new HashMap<String, String>();
    public HashMap<String, String> categories_en = new HashMap<String, String>();

    public String description;
    public String description_en;
    public String displayName;
    public String href;
    public boolean isIndependant;
    public boolean isNew;
    public String name;
    public int offerId;
    public String pic;
    public boolean premium;
    public boolean Private;


    public String getKEY_BRAND() {
        return KEY_BRAND;
    }

    public void setKEY_BRAND(String KEY_BRAND) {
        this.KEY_BRAND = KEY_BRAND;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }


    public HashMap<String, String> getCommissions_en() {
        return commissions_en;
    }

    public void setCommissions_en(HashMap<String, String> commissions_en) {
        this.commissions_en = commissions_en;
    }

    public HashMap<String, String> getCommissions() {
        return commissions;
    }

    public void setCommissions(HashMap<String, String> commissions) {
        this.commissions = commissions;
    }

    public HashMap<String, String> getCatégorie() {
        return catégorie;
    }

    public void setCatégorie(HashMap<String, String> catégorie) {
        this.catégorie = catégorie;
    }

    public HashMap<String, String> getCategories_en() {
        return categories_en;
    }

    public void setCategories_en(HashMap<String, String> categories_en) {
        this.categories_en = categories_en;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isIndependant() {
        return isIndependant;
    }

    public void setIndependant(boolean independant) {
        isIndependant = independant;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public boolean isPrivate() {
        return Private;
    }

    public void setPrivate(boolean aPrivate) {
        Private = aPrivate;
    }
}
