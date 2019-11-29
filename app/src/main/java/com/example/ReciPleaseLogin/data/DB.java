

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

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DB {


    static private FirebaseAuth mAuth;
    static public FirebaseUser mUser;
    static private FirebaseFirestore fdb;
    static private RUser user;


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

//extra


//
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
