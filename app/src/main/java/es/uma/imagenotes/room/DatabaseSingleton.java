package es.uma.imagenotes.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import es.uma.imagenotes.dao.RecordDao;
import es.uma.imagenotes.entity.RecordEntity;

// From https://developer.android.com/training/data-storage/room#java
@Database(
        entities = {
                RecordEntity.class
        },
        version = 1
)
@TypeConverters({TypeConverterUtil.class})
public abstract class DatabaseSingleton extends RoomDatabase {

    private static final String DATABASE_NAME = "IMAGE_NOTES_DB";

    private static DatabaseSingleton instance = null;

    public abstract RecordDao getRecordDao();


    public static DatabaseSingleton getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseSingleton.class, DATABASE_NAME)
                    // This shouldn't be a problem with small datasets
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
