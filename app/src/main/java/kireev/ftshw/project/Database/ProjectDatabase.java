package kireev.ftshw.project.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kireev.ftshw.project.Database.Dao.HomeworksDao;
import kireev.ftshw.project.Database.Dao.TasksDao;
import kireev.ftshw.project.Database.Entity.Homeworks;
import kireev.ftshw.project.Database.Entity.Tasks;

@Database(entities = {Homeworks.class, Tasks.class}, version = 1, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract HomeworksDao homeworksDao();
    public abstract TasksDao tasksDao();
}
