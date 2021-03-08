package com.example.tp3_exercice5;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanningModel extends ViewModel  {

    String str;
 private  String TAG = this.getClass().getSimpleName();
MutableLiveData<List<String>> creaneu;
    public LiveData<List<String>> getCreneau(InputStream inputStream)
    {
        if (creaneu == null) {
            creaneu = new MutableLiveData<>();
            loadCreneau(inputStream);
        }
        return creaneu;
    }



    public void loadCreneau(InputStream inputStream)
    {
        try
        {


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            str="";
            while ((str = bufferedReader.readLine()) != null)
            {
                if (str.equals(getCurrentDate()))
                {
                        List<String> values = new ArrayList<>();
                        values.add(bufferedReader.readLine());
                        values.add(bufferedReader.readLine());
                        values.add(bufferedReader.readLine());
                        values.add(bufferedReader.readLine());
                        creaneu.setValue(values);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
private String getCurrentDate()
{
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    String currentDate = format.format(new Date());
   return  currentDate;
}

}
