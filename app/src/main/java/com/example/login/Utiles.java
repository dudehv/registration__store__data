package com.example.login;

import static android.content.Context.MODE_PRIVATE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Utiles {


    /*
    * toast to display end user
    * */
    public static void showingToast(Context context) {
        Toast.makeText(context, "Please check your entered value !!", Toast.LENGTH_SHORT).show();
    }
    /*
    * Register validation
    * */
    public static  boolean isValid(String userFullName, String useremail, String userpass1, String userpass2) {
        if (userFullName.isEmpty() && useremail.isEmpty() && userpass1.isEmpty() && userpass2.isEmpty()) {
            return false;
        } else {
            if (userFullName.length() < 2 && userFullName.length() > 8) {
                return false;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
                return false;
            }
            if (userpass1.length() == userpass2.length()) {
                if (!userpass1.equals(userpass2)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /*
    *Log In Validation
    */
    public static boolean isValid( String useremail,String userpass){
        if(useremail.isEmpty() && userpass.isEmpty()){
            return false;
        }else {
            if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
                return false;
            }
        }
        return true;
    }

    /*
    * sharedpreference object creation
    * */

    private static SharedPreferences getPreference(Context context){
        SharedPreferences sh1=context.getSharedPreferences("MySahredPref",MODE_PRIVATE);
        return sh1;
    }

    public static SharedPreferences.Editor getPreferenceManager(Context context){
        SharedPreferences.Editor sh=getPreference(context).edit();
        return sh;
    }



    private static String getIncreaseCount(String count) {
        if(count!=null){
            int vl=Integer.parseInt(count);
            return ""+(vl+1);
        }else{
            return ""+1;
        }
    }

    public static String getValueFromAp(Context context,String key){
        SharedPreferences sp=getPreference(context);
        String value=sp.getString(key,null);
        return value;
    }

    public static int saveIntoSp(Context context, String userFullName, String useremail, String userpass1) {
        if(!isRegistredUser(context,useremail)){
            SharedPreferences.Editor sh=getPreferenceManager(context);
            String myCount=getIncreaseCount(getValueFromAp(context,"count"));
            sh.putString("fullname"+myCount,userFullName);
            sh.putString("email"+myCount,useremail);
            sh.putString("pass1"+myCount,userpass1);
            sh.putString("count",myCount);
            sh.apply();
        }else{
            return 101;
        }
        return 100;
    }

    private static boolean isRegistredUser(Context context, String useremail) {
        String countStr=getValueFromAp(context,"count");
        if(countStr!=null) {
            int count = Integer.parseInt(countStr);
            if (count > 0) {
                for (int i = count; i > 0; i--) {
                    String email = getValueFromAp(context, "email" + (i));
                    if (email.equals(useremail)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int checkAuthentication(Context context, String useremail, String userpass) {
        String countStr=getValueFromAp(context,"count");
        int responseCode=103;
        if(countStr!=null){
            int count = Integer.parseInt(countStr);
            if (count > 0) {
                for (int i = count; i > 0; i--) {
                    String email = getValueFromAp(context, "email" + (i));
                    String pass = getValueFromAp(context, "pass1" + (i));
                    if (email.equals(useremail)) {
                        if (pass.equals(userpass)){
                            responseCode = 100;
                            break;
                        }else{
                            responseCode = 101;
                            break;
                        }
                    }
                }
                if (responseCode!=100 && responseCode!=101){
                    responseCode =102;
                }
            }
        }
        return responseCode;
    }

    public static ArrayList<Users> getAllRegisteredUser(Context context) {
        ArrayList<Users> list=new ArrayList<Users>();
        String countStr=getValueFromAp(context,"count");
        if(countStr!=null){
            int count = Integer.parseInt(countStr);
            if (count > 0) {
                for (int i = count; i > 0; i--) {
                    Users u=new Users();
                    String name = getValueFromAp(context, "fullname" + (i));
                    String email = getValueFromAp(context, "email" + (i));
                    String pass = getValueFromAp(context, "pass1" + (i));
                    u.setName(name);
                    u.setEmail(email);
                    u.setPass(pass);
                    list.add(u);
                }
            }
        }
        return list;
    }
}