package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import kireev.ftshw.project.Courses.Rating.HomeworkVO;

@Entity
public class Homeworks {
    @PrimaryKey
    public int id;

    public String title;
}
