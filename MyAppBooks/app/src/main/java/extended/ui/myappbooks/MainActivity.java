package extended.ui.myappbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_login;
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login=(Button) findViewById(R.id.btn_login);
        database=new Database(this,"Books.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Books(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",NameBook VARCHAR(20),Author VARCHAR(20), Caterogy VARCHAR(20), DESCR VARCHAR(100), Cost INTEGER, Image BLOB, Count INTEGER )");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainScreenActivity.class));
            }
        });
    }
}