package com.example.tp3_exercice5;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Planning extends AppCompatActivity {

ListView list;
InputStream inputStream;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_activity);
        list = findViewById(R.id.list);
        try {
            AssetManager ma = getAssets();

          inputStream = ma.open("planning");
          if (inputStream == null)
              Log.i(this.getClass().getSimpleName() , "kuiiiiiii");
         //   inputStream = getResources().openRawResource(R.raw.planning);

        } catch (IOException e) {
            e.printStackTrace();
        }
        PlanningModel model = new ViewModelProvider(this).get(PlanningModel.class);
        model.getCreneau(inputStream).observe(this , creneau -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, creneau);
            list.setAdapter(adapter);

        });

    }
}
