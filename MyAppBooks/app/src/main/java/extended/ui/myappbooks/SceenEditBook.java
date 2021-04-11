package extended.ui.myappbooks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SceenEditBook extends AppCompatActivity {
    private EditText Name,Caterogies,Author,Desc,Count,Price;
    private ImageView Img_editImage;
    private Button btn_cancel,btn_Save;
    final int REQUEST_CODE_FOLDER=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sceen_edit_book);
        anhxa();
        Intent intent=getIntent();
        int ID=intent.getIntExtra("ID",0);
        LoadData(ID);
        Img_editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        SceenEditBook.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b= new AlertDialog.Builder(SceenEditBook.this);
                b.setTitle("Add Book");
                b.setMessage("Do you want to exit Edit book!");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog al=b.create();
                al.show();
            }
        });
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable=(BitmapDrawable) Img_editImage.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhanh=byteArray.toByteArray();
                MainActivity.database.UPDATE_BOOK(
                        Name.getText().toString().trim(),
                        Author.getText().toString().trim(),
                        Caterogies.getText().toString().trim(),
                        Desc.getText().toString().trim(),
                        Integer.parseInt(Price.getText().toString().trim()),
                        hinhanh,
                        Integer.parseInt(Count.getText().toString().trim()),
                        ID
                );
                Toast.makeText(SceenEditBook.this,"Successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SceenEditBook.this,Screenlistbooks.class));

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_CODE_FOLDER:
                if(requestCode==REQUEST_CODE_FOLDER && grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent= new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }
                else
                {
                    Toast.makeText(SceenEditBook.this,"Not Permission ",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_FOLDER && resultCode==RESULT_OK)
        {
            Uri uri=data.getData(); //lay duong dan
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                Img_editImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void LoadData(int ID)
    {

      Cursor cursor= MainActivity.database.GetData("SELECT id,NameBook,Author,Caterogy,DESCR,Cost,Count,Image FROM Books WHERE id= "+ID+"");
      if (cursor!=null && cursor.getCount()>0) {
          if(cursor.moveToFirst())
          {
              cursor.getInt(0);
              Name.setText(cursor.getString(1));
              Author.setText(cursor.getString(2));
              Caterogies.setText(cursor.getString(3));
              Desc.setText(cursor.getString(4));
              Price.setText(cursor.getInt(5)+"");
              Count.setText(cursor.getInt(6)+"");
              byte[] bytes=cursor.getBlob(7);
              Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
              Img_editImage.setImageBitmap(bitmap);

          }
          cursor.close();
      }





    }
    public void anhxa()
    {
        Name=(EditText)findViewById(R.id.edt_editName);
        Author=(EditText)findViewById(R.id.edt_editAuthor);
        Desc=(EditText)findViewById(R.id.edt_editDesc);
        Caterogies=(EditText)findViewById(R.id.edt_editCtrg);
        Count=(EditText)findViewById(R.id.edt_editCount);
        Price=(EditText)findViewById(R.id.edt_editPrice);
        Img_editImage=(ImageView)findViewById(R.id.img_edtBooks);
        btn_cancel=(Button) findViewById(R.id.btn__cancel);
        btn_Save=(Button) findViewById(R.id.btn_save);

    }
}