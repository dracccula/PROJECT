package kireev.ftshw.project.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kireev.ftshw.project.Database.Dao.CourseDao;
import kireev.ftshw.project.Database.Dao.EventsDao;
import kireev.ftshw.project.Database.Dao.HomeworksDao;
import kireev.ftshw.project.Database.Dao.ProfileDao;
import kireev.ftshw.project.Database.Dao.TasksDao;
import kireev.ftshw.project.Database.Entity.Course;
import kireev.ftshw.project.Database.Entity.Events;
import kireev.ftshw.project.Database.Entity.Homeworks;
import kireev.ftshw.project.Database.Entity.Profile;
import kireev.ftshw.project.Database.Entity.Tasks;

@Database(entities = {Homeworks.class, Tasks.class, Profile.class, Events.class, Course.class}, version = 1, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract HomeworksDao homeworksDao();
    public abstract TasksDao tasksDao();
    public abstract ProfileDao profileDao();
    public abstract EventsDao eventsDao();
    public abstract CourseDao courseDao();
}
