package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Homeworks.class, parentColumns = "id", childColumns = "homeworkId", onDelete = CASCADE, onUpdate = CASCADE, deferred = true))
public class Tasks {

    @PrimaryKey
    public long id;

    public long homeworkId;

    public String status;

    public String mark;
}
