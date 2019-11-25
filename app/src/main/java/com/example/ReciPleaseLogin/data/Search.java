package com.example.ReciPleaseLogin.data;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Search {
    public int max;
    public CollectionReference ref;
    public FirebaseFirestore db;
    public Query getResults;
    public Object searchfor;





    public Search(Object obj, int page_size){
        db.getInstance();
        max=page_size;

//TODO: make sure to run database indexing on Recipe name, who_are_you, tags field in firebase Console


        if (searchfor instanceof UserProfile){

            UserProfile ulook= (UserProfile) searchfor;

            ref=db.collection("users");
            if ((ulook.username!="")||(ulook.username==null)){
                getResults= ref.whereEqualTo("username", ulook.username);
            }
            else if ((ulook.uuid!="")||(ulook.uuid==null)) {

                getResults = ref.whereEqualTo("uuid", ulook.uuid);
            }
            else if(ulook.num_followers!=0){

                getResults= ref.whereGreaterThanOrEqualTo("num_followers", ulook.num_followers).orderBy("num_followers",Query.Direction.DESCENDING);
            }
            else if (ulook.num_likers!=0){
                getResults= ref.whereGreaterThanOrEqualTo("num_likers", ulook.num_followers).orderBy("num_likers",Query.Direction.DESCENDING);

              }
            else if(ulook.who_are_you!="") {
                getResults = ref.whereGreaterThanOrEqualTo("who_are_you", ulook.who_are_you).orderBy("who_are_you",Query.Direction.ASCENDING);;
            }

            //return userprofile of first match???
           getResults.get();//.addOnCompleteListener(listened);


           // data=getResults.get().getResult().toObjects(UserProfile.class);

            //user=data.get(data.size());
        }
        else if (searchfor instanceof Recipe){
            Recipe rlook=(Recipe)  searchfor;
            if( ((rlook.premium ==true))) {
                ref = db.collection("PremiumRecipes");

                //by owner uuid
                if (rlook.ownerUid!=""){

                }
                else {


                }

                //by uuid

                //by name

                //by tags

                //by num likes

                //by???


            }

            else {
                ref = db.collection("PremiumRecipes");


            }
        }
    }
    public  void next(){

    }

    public void prev(){

    }
    }

