package com.example.easy_attendance.firebase.model;

import android.util.Log;

import com.example.easy_attendance.firebase.model.dataObject.UserObj;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class FirebaseDBUser extends FirebaseBaseModel
{

    public void addUserToDB(String userID,String orgKey, String ID, String fName, String lName, String email, String password, boolean isManager)
    {
        writeNewUser(userID , orgKey, ID, fName, lName, email, password, isManager);
    }

    private void writeNewUser(String userID ,String orgKey, String ID, String fName, String lName, String email, String password, boolean isManager)
    {
        UserObj userRej = new UserObj(orgKey ,ID,  fName, lName ,email, password, isManager);
        myRef.child("Users").child(userID).setValue(userRej);
        writeUserToOrg(orgKey , ID ,fName ,lName,isManager);
    }

    private void writeUserToOrg(String orgKey , String ID,String fName,String lName , boolean isManager)
    {

        if(isManager==true) {
            myRef.child("organization").child(orgKey).child("Manager").setValue(ID);
        }
        myRef.child("organization").child(orgKey).child(ID).setValue(fName+"-"+lName);


    }

    public DatabaseReference getUserFromDB (String userID)
    {
        return myRef.child("Users").child(userID);
    }

    public DatabaseReference getOrganization(String orgKey)
    {
        return myRef.getRef().child("organization").child(orgKey);
    }

    public DatabaseReference getAllUsers()
    {
        return myRef.getRef().child("Users");
    }

    public DatabaseReference getAllMessages()
    {
        return myRef.getRef().child("Messages");
    }

    public void deleteMessages(String ID)
    {
        myRef.getRef().child("Messages").child(ID).removeValue();
    }





}
