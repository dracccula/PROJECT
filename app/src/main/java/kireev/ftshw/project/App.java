package kireev.ftshw.project;

import android.app.Application;
import android.arch.persistence.room.Room;


import kireev.ftshw.project.Database.ProjectDatabase;

public class App extends Application {

    public static App instance;
    private ProjectDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, ProjectDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public ProjectDatabase getDatabase() {
        return database;
    }
}