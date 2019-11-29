

package com.example.ReciPleaseLogin.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.example.ReciPleaseLogin.ui.IRecipeListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Document;

import java.util.List;
import java.util.Vector;

//import firebase.RTD

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DB {


    static private FirebaseAuth mAuth;
    static public FirebaseUser mUser;
    static private FirebaseFirestore fdb; //firestore
    static private RUser user;
//realtime
    static FirebaseDatabase database;
    static DatabaseReference mRootRef;


    //singleton
    private static DB soloDB = null;

    public static DB getInstance() {

        if (soloDB == null)
            soloDB = new DB();
        return soloDB;
    }

    private DB() {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getInstance().getCurrentUser();
        fdb = FirebaseFirestore.getInstance();
        mRootRef = database.getReference("Root");
    }
//extra


//
    
    static public void pushWhoAreYou(String who_are_you) //Real Name
    {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Real Name").setValue(who_are_you);
        return;
    }

    static public void pushUserName(String displayName) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("UserName").setValue(displayName);
        return;
    }

    static public void pushCookingExp(String cooking_experience) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Cooking Experience").setValue(cooking_experience);
        return;}

    static public void pushWhatYouDo(String what_do_you_do) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("What do you do").setValue(what_do_you_do);
        return;
    }

    //submit any object to db


    static public void push(Object object) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        fdb = FirebaseFirestore.getInstance();

        DocumentReference doc;

        //user profile

        if (object instanceof UserProfile) {
            fdb.collection("users").document(mUser.getUid()).set(object);
        } else if (object instanceof Recipe) {
            if (((Recipe) object).premium == true)
                doc = (fdb.collection("PremiumRecipes").add(object)).getResult();
            else if (((Recipe) object).premium == false) {
                doc = fdb.collection("PublicRecipes").add(object).getResult();
                //fdb.collection("").get().addOnSuccessListener();
            }


            //messages

        } else if (object instanceof Message) {
            //send to sender and recipient
            //recipeuid field used to identify between messages and comments
            if (((Message) object).recipeUid == null) {
                //          ((Message) object).senderUid=mUser.getUid();
                Query recipient = fdb.collection("users").whereArrayContains("username", ((Message) object).recipient);
                doc = fdb.collection("UserFeed").document(mUser.getUid()).collection("sent").add(object).getResult();

                //search for user, get uid
                //fake recipient
                 //doc = db.collection("user").document(((Message) object).recipientUid).collection("inbox").add(object).getResult();

            } else {

                if (((Message) object).premium == true)
                    fdb.collection("PremiumRecipes").document(((Message) object).recipeUid).collection("comments").add(object);
                else
                    fdb.collection("PublicRecipes").document(((Message) object).recipeUid).collection("comments").add(object);

            }
        }

        //post Recipes
        else if (object instanceof Recipe){
                if(((Recipe)object).premium) {
                    fdb.collection("PremiumRecipe").add((Recipe) object);
                    //add to user list of recipes
                }
                else{
                    fdb.collection("PublicRecipe").add((Recipe) object);

                }
    static public void pushSomethingInteresting(String something_int) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Something Interesting").setValue(something_int);
        return;
    }

//private void pushPicture save for later

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushNumFollowers(int num_followers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Number of Followers").setValue(num_followers);
        return;
    }

    static public void pushNumLikers(int num_likers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Number of Likers").setValue(num_likers);
        return;
    }

    //Note for group: If the user was able to make an account to use, they had to be over 15, what is this check for?
    static public void pushAgeCheck(boolean over15) {
        String check = "";

        if (over15) {
            check = "true";
        } else {
            check = "false";
        }
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("User over 15").setValue(check);
        return;
    }


    static public void pushPremiumStatus(boolean premium) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Premium").setValue(premium);
        return;
    }

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushNumberOfRecipes(String num_recipes) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Number of Recipes").setValue(num_recipes);
        return;
    }

    static public void pushRecipeName(String recipe_name) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).setValue(recipe_name);
        return;
    }

    //Date needs to be converted to String/long: https://www.javatpoint.com/java-date-to-string
    static public void pushRecipeDate(String recipe_name, String posted) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("posted").setValue(posted);
        return;
    }

    //Check
    static public void pushIngredients(String recipe_name, List<String> ingredients) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("ingredients").setValue(ingredients);
        return;
    }

    static public void pushDescription(String recipe_name, String description) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("description").setValue(description);
        return;
    }

        }
        //userprofile
        //userfeed
        //messages
        //message
        //recipes
        //recipe
        //user
    }

    //}
//
    public void pull(final IFaceDB IFaceDB, UserProfile user) {


        fdb.collection("users").document(mUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                QueryDocumentSnapshot doc;
                //=new UserProfile();
                //profile.who_are_you="Not Found";
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        UserProfile profile      =  document.toObject(UserProfile.class);
                        Log.i("name", " " + profile.who_are_you+" "+profile.what_do_you_do +" "+profile.username +" "+profile.over15+" "+profile.premium );
                        profile=document.toObject(UserProfile.class);
                        IFaceDB.onRetrievalSuccess(profile);
                    }
                }
            }
        });
    }
}
    static public void pushTags(String recipe_name, List<String> tag) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("tags").setValue(tag);
        return;
    }

    static public void pushInstructions(String recipe_name, List<String> instructions) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("Instructions").setValue(instructions);
        return;
    }

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushLikersPerRecipe(String recipe_name, String recipe_likers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("num_likers").setValue(recipe_likers);
        return;
    }

    static public void pushLikers(String recipe_name, List<String> likers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("likers").setValue(likers);
        return;
    }

//--------------------------------------------------------------------------------------------------------
//Example of a pull

    public void pullRecipe(final IRecipeListener listener, String recipe_name) {
       //mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){

                //HashMap<String, Recipe> map = (HashMap<String, Recipe>) dataSnapshot.getValue();
                //String recipe = dataSnapshot.getValue(String.class);
                //Log.i(TAG, "recipe is:" + recipe_name.recipe_name);
                Recipe recipe = dataSnapshot.getValue(Recipe.class);
                if (recipe!=null) {

                    Log.i(TAG, "recipe is:" + recipe.recipe_name);
                    Log.i(TAG, "description is:" + recipe.description);
                    Log.i(TAG, "ingredients is:" + recipe.ingredients);
                    //listener.onRetrievalSuccess(dataSnapshot.getValue(String.class).toString());
                    listener.onRetrievalSuccess(recipe);
                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError){
                //pretend we have stuff here
                Log.i(TAG, "DB F");
            }
        };
        Log.i(TAG, "recipe is(outside):" + recipe_name);
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).addValueEventListener(postListener);
        //});
    }
// FirebaseDatabase.getInstance().getReference()
//    public void pullRecipe(final IRecipeListener listener, String recipe_name) {
//        mRootRef.child(mAuth.getCurrentUser().getUid())child(addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String recipe = dataSnapshot.getValue(String.class);
//                Log.i(TAG, "recipe is:" + recipe);
//                listener.onRetrievalSuccess(recipe);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //pretend we have stuff here
//                Log.i(TAG, "DB F");
//            }
//
//        });
//    }

};
