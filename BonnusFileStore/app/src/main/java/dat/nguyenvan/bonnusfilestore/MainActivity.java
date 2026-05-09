package dat.nguyenvan.bonnusfilestore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FirestoreDebug";
    private static final String KEY_NAME = "KEY_NAME";
    private static final String KEY_ROOM = "KEY_ROOM";

    private EditText etName, etRoom;
    private Button btnSave, btnFetch;
    private TextView tvDisplay;

    private final com.google.firebase.firestore.CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("sampleData");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupListeners();
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etRoom = findViewById(R.id.etRoom);
        btnSave = findViewById(R.id.btnSave);
        btnFetch = findViewById(R.id.btnFetch);
        tvDisplay = findViewById(R.id.tvDisplay);
    }

    private void setupListeners() {
        btnSave.setOnClickListener(v -> saveData());
        btnFetch.setOnClickListener(v -> fetchData());
    }

    private void saveData() {
        String name = etName.getText().toString();
        String room = etRoom.getText().toString();

        if (name.isEmpty() || room.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put(KEY_NAME, name);
        dataToSave.put(KEY_ROOM, room);
        dataToSave.put("timestamp", com.google.firebase.firestore.FieldValue.serverTimestamp());

        collectionRef.add(dataToSave)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "Dữ liệu đã được thêm mới với ID: " + documentReference.getId());
                    Toast.makeText(MainActivity.this, "Đã thêm mới thành công!", Toast.LENGTH_SHORT).show();
                    etName.setText(""); // Xóa trống ô nhập sau khi lưu
                    etRoom.setText("");
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Lỗi khi thêm dữ liệu", e);
                    Toast.makeText(MainActivity.this, "Lỗi khi lưu!", Toast.LENGTH_SHORT).show();
                });
    }

    private void fetchData() {
        // Lấy bản ghi mới nhất dựa trên timestamp
        collectionRef.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING).limit(1).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        com.google.firebase.firestore.DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(0);
                        String name = doc.getString(KEY_NAME);
                        String room = doc.getString(KEY_ROOM);
                        tvDisplay.setText("Mới nhất: " + name + " - Phòng: " + room);
                    } else {
                        tvDisplay.setText("Không có dữ liệu");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Lỗi khi lấy dữ liệu", e);
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Lắng nghe bản ghi mới nhất được thêm vào thời gian thực
        collectionRef.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING).limit(1)
                .addSnapshotListener(this, (snapshots, e) -> {
                    if (e != null) {
                        Log.e(TAG, "Lắng nghe thất bại.", e);
                        return;
                    }

                    if (snapshots != null && !snapshots.isEmpty()) {
                        com.google.firebase.firestore.DocumentSnapshot doc = snapshots.getDocuments().get(0);
                        String name = doc.getString(KEY_NAME);
                        String room = doc.getString(KEY_ROOM);
                        tvDisplay.setText("Vừa cập nhật: " + name + " - Phòng: " + room);
                    }
                });
    }
}