package com.example.ReciPleaseLogin.data;

import com.example.ReciPleaseLogin.data.Recipe;
import com.google.firebase.firestore.Query;

import java.util.List;
import java.util.Vector;

//container only not pushed to database
public class Recipes {

   public List<Recipe> PublicRecipes;
   public List<Recipe> PremiumRecipes;
   private Query searchfor;

    public Recipes(){
        PublicRecipes=new Vector<Recipe>();
        PremiumRecipes= new Vector<Recipe>();
    }

    public List<Recipe> getPublicRecipes(){
        return PublicRecipes;
    }
    public List<Recipe> getPremiumRecipes(){
        return PremiumRecipes;
    }























}
        //DB.push(this);
    }

    //fetch new data
    //public void updateView(){
        //DB.pull(this);
    }
