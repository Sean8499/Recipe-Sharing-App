package com.example.ReciPleaseLogin.data;

import java.util.Date;
import java.util.List;
import java.util.Vector;

// Stores list of uuid item indexes and  Messages
public class UserFeed {

    public  UserFeed(){
        //user uuid
        following=new Vector<String>();

        //date of last read per uuid
        last_followed=new Vector<Date>();

        //followers to update of new recipes
        followers =new Vector<String>();

        //move to local only usermodule ?
        mailbox=new Messages();;
        last_read=new Vector<Date>();;

        //uuid of submitted recipes
        recipes= new Vector<String>();

        //uuid of favorate recipes
        recipes_liked=new Vector<String>();

    }

        List<String> following;
        List<Date> last_followed;
        List<String> followers;
        Messages mailbox;
        List<Date> last_read;
        List<String> recipes ;
        List<String> recipes_liked;


        public List<String> getFollowing(){
            return following;
        }
        public List<Date> getLast_followed(){
                return last_followed;
        }
        public List<String> getFollowers(){
            return followers;
        }
        public Messages getMailbox(){
            return mailbox;
        }
        public List<Date> getLast_read(){
        return last_read;
        }
        public List<String> getRecipes(){
            return recipes;
        }
        public List<String> getRecipes_liked(){
            return recipes_liked;
        }


    public void updateDB(){

        //DB.push(this);


        //nolonger needed
        /*mAuth=FirebaseAuth.getInstance();
        user=mAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        //db.collection(user.getUid()).add()
        db.collection("users").document(user.getEmail()).set(this);*/

    }

    //fetch new data
    //public void updateView(){
         //DB.pull(this);
    //}

}

