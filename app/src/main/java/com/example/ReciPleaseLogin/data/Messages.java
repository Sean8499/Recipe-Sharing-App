package com.example.ReciPleaseLogin.data;

import java.util.List;
import java.util.Vector;

public class Messages {

    public List<Message> unread;
    public List<Message> recieved;
    public List<Message> sent;

    public Messages(){
        unread= new Vector<Message>();
        recieved= new Vector<Message>();
        sent= new Vector<Message>();
    }

    public List<Message> getUnread(){
        return unread;
    }
    public List<Message> getRecieved(){
        return recieved;
    }
    public List<Message> getSent(){
        return sent;
    }




    public void updateDB(){

        //DB.push(this);

    }

    //fetch new data
    //public void updateView(){
        //DB.pull(this);
    //}

}
