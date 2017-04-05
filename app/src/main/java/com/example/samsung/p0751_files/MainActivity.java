package com.example.samsung.p0751_files;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs",
                FILE_NAME = "file",
                DIR_SD = "MyFiles",
                FILE_NAME_SD = "fileSD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicOnButton(View view) {

        switch (view.getId()) {

            case R.id.btnWriteFile :
                writeFile();
                break;
            case R.id.btnReadeFile :
                readeFile();
                break;
            case R.id.btnWriteFileOnSD :
                writeFileSD();
                break;
            case R.id.btnReadeFileFromSD :
                readeFileSD();
                break;

        }
    }

    private void readeFileSD() {
        //Проверка доступности SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        //Получение пути к SD
        File sdPath = Environment.getExternalStorageDirectory();
        //Добавление к пути своего каталога
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        //Формирование объекта File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILE_NAME_SD);
        try {
            String s = "";
            //Открытие потока дял чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            //Чтение содержимого файла
            while ((s = br.readLine()) != null) {
                Log.d(LOG_TAG, s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileSD() {
        //Проверка доступности SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "Карта НЕ доступна: " + Environment.getExternalStorageState());
            return;
        }
        //Получение пути к SD
        File sdPath = Environment.getExternalStorageDirectory();
        //Добавление своего каталога к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        //Создание каталога
        sdPath.mkdirs();
        //Формирование объекта File, содержащего путь к файлу
        File sdFile = new File(sdPath, FILE_NAME_SD);
        try {
            //Открытие потока для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            //Запись данных
            bw.write("Содержимое файла на SD");
            //Закрытие потока
            bw.close();
            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readeFile() {
        try {
            String s = "";
            //Открытие потока для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
            //Чтени е содержимого файла
            while ((s = br.readLine()) != null) {
                Log.d(LOG_TAG, s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        try {
            //Открытие потока для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE)));
            //Запись данных в файл
            bw.write("Содержимое файла");
            //Закрытие потока
            bw.close();
            Log.d(LOG_TAG, "File is writed: " + getFilesDir());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
