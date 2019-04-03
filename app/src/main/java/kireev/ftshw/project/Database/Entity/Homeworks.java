package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Homeworks {
    @PrimaryKey
    public long id;

    public String title;
}
