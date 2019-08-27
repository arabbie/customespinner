package com.example.myautocomplete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    String[] fruits = {"تفاح", "موز", "عنب", "اناناس", "برتقال", "بطيخ", "كيوى", "مانجو", "يوسفى"};
    AutoCompleteTextView tv;
    List<Fruit> fruitslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.tv);

        getAllFruits();


        MyFruitAdapter adapter = new MyFruitAdapter
                (this, R.layout.list_item, fruitslist);
        tv.setThreshold(1); //will start working from first character
        tv.setAdapter(adapter);

        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //String fruitname = (String) adapterView.getItemAtPosition(i);

                Fruit fruit = fruitslist.get(i);


                Toast.makeText(MainActivity.this, "الكود :-  " + fruit.fruitid + "\n" + "الاسم :-  " + fruit.fruitname, Toast.LENGTH_LONG).show();


            }
        });



        tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    tv.showDropDown();
                }
            }
        });

        tv.addTextChangedListener(this);

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getAllFruits();
                MyFruitAdapter adapter = new MyFruitAdapter
                        (MainActivity.this, R.layout.list_item, fruitslist);
                tv.setAdapter(adapter);
                tv.showDropDown();
                return false;
            }
        });

    }


    public void getAllFruits() {
        fruitslist = new ArrayList<>();

        for (int i = 0; i < fruits.length; i++) {
            fruitslist.add(new Fruit(i + 1, fruits[i]));
        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        /*String s = tv.getText().toString();
        if(s.length() < 1){
            Toast.makeText(MainActivity.this,"لابد من كتابة حرف واحد على الاقل",Toast.LENGTH_LONG).show();
        }*/
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!tv.isPerformingCompletion()) {
            String s = tv.getText().toString();
            if(s.isEmpty()){
                getAllFruits();
                MyFruitAdapter adapter = new MyFruitAdapter
                        (MainActivity.this, R.layout.list_item, fruitslist);
                tv.setAdapter(adapter);
                tv.showDropDown();
            }
            return;
        }
    }
}

