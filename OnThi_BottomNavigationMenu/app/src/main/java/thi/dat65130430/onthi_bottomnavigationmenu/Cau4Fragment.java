package thi.dat65130430.onthi_bottomnavigationmenu;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Cau4Fragment extends Fragment {

    public Cau4Fragment() {
    }

    public static Cau4Fragment newInstance(String param1, String param2) {
        Cau4Fragment fragment = new Cau4Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Khởi tạo CSDL: tạo bảng và chèn dữ liệu mẫu nếu chưa có
    private void initDatabase(SQLiteDatabase db) {
        // Tạo bảng nếu chưa tồn tại
        String sqlTaoBang = "CREATE TABLE IF NOT EXISTS Books(" +
                "BookID integer PRIMARY KEY, " +
                "BookName text, " +
                "Page integer, " +
                "Price Float, " +
                "Description text);";
        db.execSQL(sqlTaoBang);

        // Kiểm tra nếu bảng rỗng thì mới chèn dữ liệu mẫu
        Cursor check = db.rawQuery("SELECT COUNT(*) FROM Books", null);
        check.moveToFirst();
        int count = check.getInt(0);
        check.close();

        if (count == 0) {
            db.execSQL("INSERT INTO Books VALUES(1, 'Lập Trình Java', 450, 120000, 'Sách học Java từ cơ bản đến nâng cao');");
            db.execSQL("INSERT INTO Books VALUES(2, 'Android Cơ Bản', 320, 85000, 'Hướng dẫn lập trình Android');");
            db.execSQL("INSERT INTO Books VALUES(3, 'Cấu Trúc Dữ Liệu', 280, 95000, 'Giải thuật và cấu trúc dữ liệu');");
            db.execSQL("INSERT INTO Books VALUES(4, 'Lập Trình Web', 380, 110000, 'HTML, CSS, JavaScript cơ bản');");
            db.execSQL("INSERT INTO Books VALUES(5, 'Cơ Sở Dữ Liệu', 200, 75000, 'SQL và quản trị cơ sở dữ liệu');");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // B1. Mở CSDL
        SQLiteDatabase db = getActivity().openOrCreateDatabase("QLSach.db",
                MODE_PRIVATE,
                null
        );

        // B1.5. Khởi tạo bảng + dữ liệu mẫu nếu chưa có
        initDatabase(db);

        // B2. Thực thi câu lệnh select
        String sqlSelect = "SELECT * FROM Books;";
        Cursor cs = db.rawQuery(sqlSelect, null);

        // B3: Đổ vào danh sách
        ArrayList<Book> dsSach = new ArrayList<Book>();
        while (cs.moveToNext()) {
            int idSach = cs.getInt(0);
            String tenSach = cs.getString(1);
            int soTrang = cs.getInt(2);
            float gia = cs.getFloat(3);
            String mota = cs.getString(4);
            Book b = new Book(idSach, tenSach, soTrang, gia, mota);
            dsSach.add(b);
        }
        cs.close();
        db.close();

        // B4. Inflate layout và hiển thị lên ListView
        View viewCau4 = inflater.inflate(R.layout.fragment_cau4, container, false);
        ListView listViewTenSach = viewCau4.findViewById(R.id.lvTenSach);

        // Dùng custom adapter với ViewHolder pattern
        BookAdapter bookAdapter = new BookAdapter(getContext(), dsSach);
        listViewTenSach.setAdapter(bookAdapter);

        return viewCau4;
    }

    // ===================== Custom Adapter =====================
    private static class BookAdapter extends BaseAdapter {
        private final Context context;
        private final ArrayList<Book> books;
        private final LayoutInflater inflater;

        BookAdapter(Context context, ArrayList<Book> books) {
            this.context = context;
            this.books = books;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() { return books.size(); }

        @Override
        public Object getItem(int position) { return books.get(position); }

        @Override
        public long getItemId(int position) { return books.get(position).getBookID(); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.book_item, parent, false);
                holder = new ViewHolder();
                holder.tvIndex = convertView.findViewById(R.id.tvBookIndex);
                holder.tvName = convertView.findViewById(R.id.tvBookName);
                holder.tvDesc = convertView.findViewById(R.id.tvBookDesc);
                holder.tvPrice = convertView.findViewById(R.id.tvBookPrice);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Book book = books.get(position);
            holder.tvIndex.setText(String.valueOf(position + 1));
            holder.tvName.setText(book.getBookName());
            holder.tvDesc.setText(book.getDescription());
            // Hiển thị giá bằng VNĐ
            holder.tvPrice.setText(String.format("%,.0f đ", book.getPrice()));

            return convertView;
        }

        static class ViewHolder {
            TextView tvIndex, tvName, tvDesc, tvPrice;
        }
    }
}