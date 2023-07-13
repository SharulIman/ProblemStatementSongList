package sg.edu.rp.c346.id22018526.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText titleinp;
    EditText singerinp;
    EditText yearinp;
    RadioGroup starinp;
    Button insertit;
    Button showit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleinp = findViewById(R.id.inputTitle);
        singerinp = findViewById(R.id.inputSinger);
        yearinp = findViewById(R.id.inputYear);
        starinp = findViewById(R.id.starRadio);
        insertit = findViewById(R.id.insertBtn);
        showit = findViewById(R.id.showBtn);


        insertit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = e.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(data);

                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllNotes());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}