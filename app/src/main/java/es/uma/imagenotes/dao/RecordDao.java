package es.uma.imagenotes.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.uma.imagenotes.entity.RecordEntity;

@Dao
public interface RecordDao {
    @Query("select * from RecordEntity order by date desc")
    List<RecordEntity> getSortedRecords();

    @Query("select * from RecordEntity where id = :id")
    RecordEntity getRecordById(int id);

    @Insert
    void insertRecord(RecordEntity entity);

    @Delete
    void deleteRecord(RecordEntity entity);
}
