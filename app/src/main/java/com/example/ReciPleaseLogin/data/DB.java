

package com.example.ReciPleaseLogin.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class DB {

    static private FirebaseAuth mAuth;
    static public FirebaseUser mUser;
    static private FirebaseFirestore fdb;
    static private RUser user;


//singleton
    private static DB soloDB = null;

    public static DB getInstance(){

        if (soloDB==null)
            soloDB=new DB();
        return soloDB;
    }

    private DB() {

     mAuth= FirebaseAuth.getInstance();
     mUser= mAuth.getInstance().getCurrentUser();
     fdb= FirebaseFirestore.getInstance();



    }


















    //submit any object to db



    static public void push( Object object){

        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getInstance().getCurrentUser();
        fdb = FirebaseFirestore.getInstance();

        DocumentReference doc;
        if (object instanceof UserProfile) {
             fdb.collection("users").document(mUser.getUid()).set(object);
        }
        else if (object instanceof Recipe) {
            if (((Recipe) object).premium==true)
                doc = (fdb.collection("PremiumRecipes").add(object)).getResult();
            else if (((Recipe) object).premium==false){
                doc = fdb.collection("PublicRecipes").add(object).getResult();
            }
        }
        else if (object instanceof Message){
            //send to sender and recipient
            if (((Message) object).recipeUid ==null) {
      //          ((Message) object).senderUid=mUser.getUid();
                Query recipient= fdb.collection("users").whereArrayContains("username" ,((Message) object).recipient);
                doc = fdb.collection("UserFeed").document(mUser.getUid()).collection("sent").add(object).getResult();

                //search for user, get uid
                //fake recipient
           //     doc = db.collection("user").document(((Message) object).recipientUid).collection("inbox").add(object).getResult();



            }else{
                if (((Message) object).premium==true)
                   fdb.collection("PremiumRecipes").document(((Message) object).recipeUid).collection("comments").add(object);
                else
                    fdb.collection("PublicRecipes").document(((Message) object).recipeUid).collection("comments").add(object);

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



    //not sure yet
    //currently does nothing, returns object it was given;
    static public Object pull(Object object){
      //  DocumentSnapshot doc=db.collection("users").document(mAuth.getCurrentUser().getUid()).get();











    return (Object) object;
    }



    static public  void query(Search query ){
   //     String collection=Search.collection;

        //CollectionReference ref=db.collection(collection);

        /*(< , <= , ==, > >=, array-contains, in, array-contains-any "*/
      /*  for (int i=0;i<Search.num_queries;i++){
            if (Search.type.get(i)=="<") {
                ref.whereLessThan(collection, Search.searchfor.get(i));
            }
            else if (Search.type.get(i)=="<=") {
                ref.whereLessThanOrEqualTo(collection, Search.searchfor.get(i));
            }
            else if (Search.type.get(i)=="==") {
                ref.whereEqualTo(collection, Search.searchfor.get(i));
            }
            else if (Search.type.get(i)==">") {
                ref.whereGreaterThan(collection, Search.searchfor.get(i));
            }
            else if (Search.type.get(i)==">=") {
                ref.whereGreaterThanOrEqualTo(collection, Search.searchfor.get(i));
            }
            else if (Search.type.get(i)=="array-contains") {
                ref.whereArrayContains(collection, Search.searchfor.get(i));
            }
            */
            //not found
            //else if (Search.type[i]=="in") {
              //  ref.w(collection, Search.searchfor[i]);
    //        }
  //          else if (Search.type[i]=="array-contains-any") {
//                ref.whereEqualTo(collection, Search.searchfor[i]);
           // }
        }







}
