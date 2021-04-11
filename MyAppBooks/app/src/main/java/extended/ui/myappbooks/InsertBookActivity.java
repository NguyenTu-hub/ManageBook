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

public class InsertBookActivity extends AppCompatActivity {
    private Button btnAddImg,btnAddBook,btnCancel;
    private EditText bookname,Caterogy,author,Desc,Count;
    private EditText cost;
    private ImageView imgBook;
    final int REQUEST_CODE_FOLDER=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        anhxa();
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imgBook.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhanh=byteArray.toByteArray();
                MainActivity.database.INSERT_BOOK(
                        bookname.getText().toString().trim(),
                        author.getText().toString().trim(),
                        Caterogy.getText().toString().trim(),
                        Desc.getText().toString().trim(),
                        Integer.parseInt(cost.getText().toString().trim()),
                        hinhanh,
                        Integer.parseInt(Count.getText().toString().trim())
                );
                Toast.makeText(InsertBookActivity.this,"Successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InsertBookActivity.this,Screenlistbooks.class));

            }
        });
            btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        InsertBookActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b= new AlertDialog.Builder(InsertBookActivity.this);
                b.setTitle("Add Book");
                b.setMessage("Do you want to exit add book!");
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
                    Toast.makeText(InsertBookActivity.this,"Not Permission ",Toast.LENGTH_SHORT).show();
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
                imgBook.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void anhxa()
    {
        btnAddImg=(Button)findViewById(R.id.btn_imgFolder);
        imgBook=(ImageView) findViewById(R.id.imgAddBook);
        bookname=(EditText) findViewById(R.id.edt_addBookName);
        Caterogy=(EditText) findViewById(R.id.edt_addCaterogy);
        author=(EditText) findViewById(R.id.edt_addAuthor);
        cost=(EditText) findViewById(R.id.edt_cost);
        Desc=(EditText)findViewById(R.id.edt_addDes);
        btnAddBook=(Button) findViewById(R.id.btn_addBook);
        Count=(EditText) findViewById(R.id.edtCount);
        btnCancel=(Button) findViewById(R.id.btnCancel);
    }

}