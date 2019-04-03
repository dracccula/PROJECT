package kireev.ftshw.project.Database.Entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Tasks {
    @PrimaryKey
    public long id;

    public long homeworkId;

    public String status;

    public String mark;
}
