package es.uma.imagenotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.uma.imagenotes.R;
import es.uma.imagenotes.adapter.RecordAdapter;
import es.uma.imagenotes.dao.RecordDao;
import es.uma.imagenotes.entity.RecordEntity;
import es.uma.imagenotes.room.DatabaseSingleton;

public class MainActivity extends AppCompatActivity {
    RecordDao recordDao;
    RecyclerView recyclerView;
    TextView emptyMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init room
        DatabaseSingleton db = DatabaseSingleton.getInstance(this);
        recordDao = db.getRecordDao();

        // Get view elements
        recyclerView = findViewById(R.id.recordRecyclerView);
        emptyMessageTextView = findViewById(R.id.emptyMessageTextView);

        // Init view
        showRecords();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showRecords();
    }


    public void onClickAddRecord(View view) {
        Intent intent = new Intent(this, NewRecordActivity.class);
        startActivity(intent);
    }

    public void showRecords() {
        List<RecordEntity> sortedRecords = recordDao.getSortedRecords();

        RecordEntity[] records = sortedRecords.toArray(new RecordEntity[]{});
        showRecords(records);
    }

    public void showRecords(RecordEntity[] records) {
        // From https://stackoverflow.com/a/28352183/19176002
        if (records.length == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyMessageTextView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyMessageTextView.setVisibility(View.GONE);

            recyclerView.setAdapter(new RecordAdapter(records));
            recyclerView.setHasFixedSize(true);
        }
    }
}