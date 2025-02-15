package com.example.ReciPleaseLogin.data;

import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Recipe {
    public Date posted;
    public     String owner;
    public     String ownerUid;
    public    String recipeUid;
    public     String recipe_name;
    public List<String> ingredients;
    public List<String> instructions;
    public int num_ingredients;
    public List<String> tags;
    public List<String> likers;
    public int num_likers;
    public boolean premium;
    public Messages comments;




    public Recipe(){
        ingredients=new Vector<String>();
        instructions= new Vector<String>();
        tags=new Vector<String>();
        likers =new Vector<String>();

    }

    public String getOwner(){
        return owner;
    }
    public String getOwnerUid(){
        return ownerUid;
    }

    public String getRecipe_name()
    {
        return recipe_name;
    }

    public List<String> getIngredients()
    {
    return ingredients;
    }
    public List<String> getInstructions()
    {
    return instructions;
    }
    public int getNum_ingredients()
    {
return num_ingredients;
    }
    public List<String> getTags()
    {
return tags;
    }
    public List<String> getLikers()
    {
return likers;
    }
    public int getNum_likers()
    {
return num_likers;
    }
    public boolean getPremium()
    {
return premium;
    }
    public Messages getComments()
    {
return comments;
    }


    public Date getPosted()
    {
        return posted;
    }


}
