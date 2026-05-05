package dat.nguyenvan.vieccanlam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ThemTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_task);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ExtendedFloatingActionButton btnSave = findViewById(R.id.floatingActionButton2);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextName = findViewById(R.id.editTextTenCV);
                EditText editTextMess = findViewById(R.id.editTextMesage);
                EditText editTextDate = findViewById(R.id.editTextDate);
                EditText editTextPrio = findViewById(R.id.editTextPrio);

                String tenCV = editTextName.getText().toString().trim();
                String mess = editTextMess.getText().toString().trim();
                String dat = editTextDate.getText().toString().trim();
                String pri = editTextPrio.getText().toString().trim();


                if (tenCV.isEmpty()) {
                    editTextName.setError("Vui lòng nhập tên công việc");
                    return;
                }


                Tasks task = new Tasks(tenCV, pri, mess, dat);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("Task");
                String key = databaseReference.push().getKey();

                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put(key, task.toFirebaseObject());

                databaseReference.updateChildren(item, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            Toast.makeText(ThemTaskActivity.this, "Đã thêm công việc!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ThemTaskActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}