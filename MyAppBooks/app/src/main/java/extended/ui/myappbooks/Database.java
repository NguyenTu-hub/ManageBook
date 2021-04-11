package extended.ui.myappbooks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.Statement;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    public void QueryData(String sql)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql)
    {
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public void INSERT_BOOK(String Name,String Author,String caterogy,String desc,int cost, byte[]imgBook,int Count)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="INSERT INTO Books VALUES(null,?,?,?,?,?,?,?)";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,Name);
        statement.bindString(2,Author);
        statement.bindString(3,caterogy);
        statement.bindString(4,desc);
        statement.bindLong(5,cost);
        statement.bindBlob(6,imgBook);
        statement.bindLong(7,Count);
        statement.executeInsert();
    }
    public void UPDATE_BOOK(String Name,String Author,String caterogy,String Descr,int cost, byte[]img_Book,int a,int id)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE Books SET NameBook= ?,Author= ?,caterogy= ?,DESCR= ?,cost= ?, Image= ?, Count=? WHERE id= ?";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,Name);
        statement.bindString(2,Author);
        statement.bindString(3,caterogy);
        statement.bindString(4,Descr);
        statement.bindLong(5,cost);
        statement.bindBlob(6,img_Book);
        statement.bindLong(7,a);
        statement.bindLong(8,id);
        statement.execute();
        database.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
