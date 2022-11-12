package es.uma.imagenotes.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import es.uma.imagenotes.R;
import es.uma.imagenotes.dao.RecordDao;
import es.uma.imagenotes.entity.RecordEntity;
import es.uma.imagenotes.room.DatabaseSingleton;

// Camera code https://stackoverflow.com/a/5991757/19176002
public class NewRecordActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;

    RecordDao recordDao;

    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        // Get view elements
        editText = findViewById(R.id.recordTitleTextEdit);
        imageView = findViewById(R.id.imageView);

        // Init db
        DatabaseSingleton db = DatabaseSingleton.getInstance(this);
        recordDao = db.getRecordDao();
    }

    public void onClickImage(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, 1);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.record_camera_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            // I love android ...
            image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);
        }
    }


    public void onClickOkButton(View view) {
        try {
            if (image == null) throw new IllegalArgumentException();

            String title = editText.getText().toString();
            if (title.isEmpty()) throw new IllegalArgumentException();

            RecordEntity record = new RecordEntity();
            record.title = title;
            record.date = new Date();
            record.image = image;
            recordDao.insertRecord(record);

            Toast.makeText(this, R.string.record_created_sucesfull, Toast.LENGTH_SHORT).show();
            finish();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, R.string.record_required_fields, Toast.LENGTH_SHORT).show();
        } catch (RuntimeException e) {
            Toast.makeText(this, R.string.record_created_error, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void onClickCancelButton(View view) {
        finish();
    }
}