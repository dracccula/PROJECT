package kireev.ftshw.project.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kireev.ftshw.project.Database.Entity.Profile;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM profile")
    List<Profile> getAll();

    @Query("SELECT * FROM profile WHERE id = :id")
    Profile getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Profile profile);

    @Update
    void update(Profile profile);

    @Delete
    void delete(Profile profile);
}
