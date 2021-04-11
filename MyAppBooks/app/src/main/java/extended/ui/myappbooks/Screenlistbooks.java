package extended.ui.myappbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Screenlistbooks extends AppCompatActivity {

    ArrayList<Book> arrayList;
    ListView listView;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenlistbooks);
        listView=(ListView)findViewById(R.id.lv_listBook);
        arrayList=new ArrayList<>();
        Cursor cursor=MainActivity.database.GetData("SELECT Id, NameBook,DESCR,Cost,Image FROM Books");
        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                arrayList.add(new Book(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getBlob(4)
                ));
            }
        }
        adapter=new Adapter(this,R.layout.layout_book,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_add:
                startActivity(new Intent(Screenlistbooks.this,InsertBookActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menucontextbook,menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.menu_edit:
                int id =arrayList.get(i.position).getID();
                Intent intent=new Intent(this,SceenEditBook.class);
                intent.putExtra("ID",id);
                startActivity(intent);
                break;
            case R.id.menu_Delete:
                int position =arrayList.get(i.position).getID();
                MainActivity.database.QueryData("DELETE FROM Books WHERE ID= "+position);
                arrayList.remove(i.position);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}