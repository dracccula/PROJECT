package kireev.ftshw.project.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


import kireev.ftshw.project.Database.Entity.Homeworks;

@Dao
public interface HomeworksDao {

    @Query("SELECT * FROM homeworks")
    List<Homeworks> getAll();

    @Query("SELECT * FROM homeworks WHERE id = :id")
    Homeworks getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Homeworks homeworks);

    @Update
    void update(Homeworks homeworks);

    @Delete
    void delete(Homeworks homeworks);

}