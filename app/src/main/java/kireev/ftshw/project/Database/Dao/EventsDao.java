package kireev.ftshw.project.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kireev.ftshw.project.Database.Entity.Events;

@Dao
public interface EventsDao {
    @Query("SELECT * FROM events")
    List<Events> getAll();

    @Query("SELECT * FROM events WHERE isArchive = 0")
    List<Events> getAllActive();

    @Query("SELECT * FROM events WHERE isArchive = 1")
    List<Events> getAllArchive();

    @Query("SELECT * FROM events WHERE title = :title")
    Events getByTitle(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Events events);

    @Update
    void update(Events events);

    @Delete
    void delete(Events events);
}
