package com.example.ReciPleaseLogin.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

public class RUser
{
        private static RUser single_instance=null;
        public UserFeed feed;

        public UserProfile profile;

        //container for Recipes
        public Recipes recipes;


        public Query queries;

        public DB db;


        private RUser()
        {
            feed=new UserFeed();
            profile =new UserProfile();
            recipes=new Recipes();

            db=  DB.getInstance();
        }

        public static RUser getInstance(){
            if (single_instance==null)
                    single_instance =new RUser();
            return single_instance;
        }


        public String getUid(){
            return db.mUser.getUid();
        }

}
