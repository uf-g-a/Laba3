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
    private ArrayList<String> FIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //database.execSQL("DROP TABLE students");
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

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
                number = random.nextInt(FIO.size());
                Calendar thisDate = Calendar.getInstance();
                String data = format.format(thisDate.getTime());

                String[] arrFIO = FIO.get(number).split(" ");
                database.execSQL("INSERT INTO students(first_name, middle_name, last_name, time) " +
                        "VALUES (\'" + arrFIO[1] + "\', \'" + arrFIO[2] + "\', \'" + arrFIO[0] + "\', \'" + data + "\');");
                FIO.remove(number);

                dbHelper.close();
            }
        });

        btn_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = dbHelper.getWritableDatabase();

                database.execSQL("UPDATE students SET first_name = 'Иван', middle_name = 'Иванович', last_name = 'Иванов' WHERE id = (SELECT max(id) FROM students)");

                dbHelper.close();
            }
        });
    }

    private void insertStartInfo() {

        FIO = new ArrayList<>();
        FIO.add("Бакулин Артём Александрович");
        FIO.add("Уфимцев Геогрий Александрович");
        FIO.add("Колобаев Максим Александрович");
        FIO.add("Теймуров Теймур Октаевич");
        FIO.add("Лаковский Игорь Сергеевич");
        FIO.add("Ненашев Иван Валерьевич");
        FIO.add("Кашкина Кристина Георгиевна");
        FIO.add("Маковейкий Иван Андреевич");
        FIO.add("Шуралев Сергрей Игоревич");
        FIO.add("Путин Владимир Владимирович");
        FIO.add("Доронин Максим Владимирович");
        FIO.add("Попов Андрей Максимович");
        FIO.add("Бовсуновский Андрей Константинович");
        FIO.add("Цымляков Андрей Сергеевич");
        FIO.add("Кособуцкий Владислав Сергеевич");
        FIO.add("Принтц Василий Андреевич");
        FIO.add("Леонов Никита Владиславович");
        FIO.add("Достоевский Фёдор Михайлович");
        FIO.add("Куликов Алекандр Александрович");
        FIO.add("Медведев Дмитрий Анатольевич");
        FIO.add("Песков Дмитрий Сергеевич");
        FIO.add("Михаил Владимирович Мишустин");
        FIO.add("Голикова Татьяна Алексеевна");
        FIO.add("Зубков Денис Андреевич");
        FIO.add("Бродский Иосиф Александрович");
        FIO.add("Маяковский Владимир Владимирович");


        Random random = new Random();
        int number;
        String[] arrFIO;

        for (int i = 0; i < 5; i++) {

            number = random.nextInt(FIO.size());

            Calendar thisDate = Calendar.getInstance();
            String data = format.format(thisDate.getTime());

            arrFIO = FIO.get(number).split(" ");

            database.execSQL("INSERT INTO students(first_name, middle_name, last_name, time) " +
                    "VALUES (\'" + arrFIO[1] + "\', \'" + arrFIO[2] + "\', \'" + arrFIO[0] + "\', \'" + data + "\');");


            FIO.remove(number);
        }
    }
}