package com.example.ReciPleaseLogin.data;

import java.util.List;
import java.util.Vector;

public class Recipes {

    public List<Recipe> recipes;


    public Recipes(){
        recipes=new Vector<Recipe>();
    }


    public List<Recipe> getRecipes(){
        return recipes;
    }

    public void updateDB(){

        DB.push(this);
    }


    }