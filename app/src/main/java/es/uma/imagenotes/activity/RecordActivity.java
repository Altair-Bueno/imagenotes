package es.uma.imagenotes.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;

import es.uma.imagenotes.R;
import es.uma.imagenotes.dao.RecordDao;
import es.uma.imagenotes.entity.RecordEntity;
import es.uma.imagenotes.room.DatabaseSingleton;

public class RecordActivity extends AppCompatActivity {
    public final static String RECORD_ID = "record_id";

    private RecordDao recordDao;
    private RecordEntity record;

    private TextView titleTextView;
    private TextView dateTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        int id = getIntent().getExtras().getInt(RECORD_ID);

        recordDao = DatabaseSingleton.getInstance(this).getRecordDao();
        record = recordDao.getRecordById(id);

        titleTextView = findViewById(R.id.titleTextView);
        imageView = findViewById(R.id.recordImageView);
        dateTextView = findViewById(R.id.dateTextView);

        showRecord();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.record_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_menu_item:
                recordDao.deleteRecord(record);
                Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void showRecord() {
        dateTextView.setText(record.date.toString());
        titleTextView.setText(record.title);
        imageView.setImageBitmap(record.image);
    }
}