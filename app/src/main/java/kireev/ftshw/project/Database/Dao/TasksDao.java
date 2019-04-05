package kireev.ftshw.project.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kireev.ftshw.project.Database.Entity.Tasks;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    List<Tasks> getAll();

    @Query("SELECT * FROM tasks WHERE id = :id")
    Tasks getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tasks tasks);

    @Update
    void update(Tasks tasks);

    @Delete
    void delete(Tasks tasks);

}