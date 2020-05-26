package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class ActivityMenu extends AppCompatActivity {

    private DateFormat format = new SimpleDateFormat("HH:mm:ss ");
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private ArrayList<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        database.execSQL("DELETE FROM students");
        insertStartInfo();

        dbHelper.close();

        Button btn_openDB = findViewById(R.id.btn_openDB);
        Button btn_addItemDB = findViewById(R.id.btn_addItemDB);
        Button btn_replace = findViewById(R.id.btn_replace);


        btn_openDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityShowDatabase.class);
                startActivity(intent);
            }
        });

        btn_addItemDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = dbHelper.getWritableDatabase();

                Random random = new Random();
                int number;
                number = random.nextInt(name.size());
                Calendar thisDate = Calendar.getInstance();
                String data = format.format(thisDate.getTime());
                database.execSQL("INSERT INTO students(name, time) VALUES (\'" + name.get(number)+ "\','" + data + "');");
                name.remove(number);

                dbHelper.close();
            }
        });

        btn_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = dbHelper.getWritableDatabase();

                database.execSQL("UPDATE students SET name = 'Иванов Иван Иванович' WHERE id = (SELECT max(id) FROM students)");

                dbHelper.close();
            }
        });
    }

    private void insertStartInfo() {

        name = new ArrayList<>();
        name.add("Бакулин Артём Александрович");
        name.add("Уфимцев Геогрий Александрович");
        name.add("Колобаев Максим Александрович");
        name.add("Теймуров Теймур Октаевич");
        name.add("Лаковский Игорь Сергеевич");
        name.add("Ненашев Иван Валерьевич");
        name.add("Кашкина Кристина Георгиевна");
        name.add("Маковейкий Иван Андреевич");
        name.add("Шуралев Сергрей Игоревич");
        name.add("Путин Владимир Владимирович");
        name.add("Доронин Максим Владимирович");
        name.add("Попов Андрей Максимович");
        name.add("Бовсуновский Андрей Константинович");
        name.add("Цымляков Андрей Сергеевич");
        name.add("Кособуцкий Владислав Сергеевич");
        name.add("Принтц Василий Андреевич");
        name.add("Леонов Никита Владиславович");
        name.add("Достоевский Фёдор Михайлович");
        name.add("Куликов Алекандр Александрович");
        name.add("Медведев Дмитрий Анатольевич");
        name.add("Песков Дмитрий Сергеевич");
        name.add("Михаил Владимирович Мишустин");
        name.add("Голикова Татьяна Алексеевна");
        name.add("Зубков Денис Андреевич");
        name.add("Бродский Иосиф Александрович");
        name.add("Маяковский Владимир Владимирович");

        Random random = new Random();
        int number;

        for (int i = 0; i < 5; i++) {

            number = random.nextInt(name.size());

            Calendar thisDate = Calendar.getInstance();
            String data = format.format(thisDate.getTime());

            database.execSQL("INSERT INTO students(name, time) VALUES (\'" + name.get(number)+ "\',\'" + data + "\');");
            name.remove(number);
        }

    }
}