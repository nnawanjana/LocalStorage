package lk.ac.kln.localstorage;

import android.content.Context;
import android.os.FileObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {
    EditText editText = null;
    TextView textView = null;
    private static final String FILE_NAME = "userData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.show1);
    }

    public void save(View view){
        String userInput = editText.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(userInput.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Toast.makeText(this,userInput, Toast.LENGTH_LONG).show();*/
    }

    public void show(View view){
        File directory = this.getFilesDir();
        File file= new File(directory, FILE_NAME);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
           /* while (bufferedReader.readLine()!=null){
                String line = bufferedReader.readLine();
                stringBuilder.append(line);
            }*/
            String line = bufferedReader.readLine();
            stringBuilder.append(line);

            textView.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
