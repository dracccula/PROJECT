package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Grades {
    @PrimaryKey
    public int student_id;
    public String name;
    public int mark;
}
