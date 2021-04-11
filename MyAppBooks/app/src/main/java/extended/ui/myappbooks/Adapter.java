package extended.ui.myappbooks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<Book> bookList;

    public Adapter(Context context, int layout, List<Book> bookList) {
        this.context = context;
        Layout = layout;
        this.bookList = bookList;
    }
    private class ViewHolder{
        TextView Ten,mota,Giatri;
        ImageView Hinh;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(Layout,null);
            holder.Ten=(TextView) convertView.findViewById(R.id.txtCustomName);
            holder.mota=(TextView) convertView.findViewById(R.id.txtCustomDesc);
            holder.Giatri=(TextView)convertView.findViewById(R.id.txt_Price);
            holder.Hinh=(ImageView) convertView.findViewById(R.id.Custom_img);
            convertView.setTag(holder);

        }
        else {
            holder=(ViewHolder) convertView.getTag();
        }
        Book book=bookList.get(position);
        holder.Ten.setText(book.getNameBook());
        holder.mota.setText(book.getDesc());
        holder.Giatri.setText("Price: "+book.getCost());
        byte[] Hinhanh=book.getImg();
        Bitmap bitmap= BitmapFactory.decodeByteArray(Hinhanh,0,Hinhanh.length);
        holder.Hinh.setImageBitmap(bitmap);
        return convertView;
    }
}
