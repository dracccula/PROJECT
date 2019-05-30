package kireev.ftshw.project.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kireev.ftshw.project.Database.Entity.Grades;

@Dao
public interface GradesDao {
    @Query("SELECT * FROM grades ORDER BY mark")
    List<Grades> getAllOrderedByMark();

    @Query("SELECT * FROM grades WHERE student_id = :id")
    Grades getGradeByStudentId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Grades grades);

    @Update
    void update(Grades grades);

    @Delete
    void delete(Grades grades);
}
