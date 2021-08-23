package com.adityatiwari.android.vlocker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.adityatiwari.android.vlocker.Dao.NotesDao;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.Entity.PersonalInfo;
import com.adityatiwari.android.vlocker.Dao.PasswordDao;
import com.adityatiwari.android.vlocker.Dao.PersonalInfoDao;

@Database(entities = {Notes.class, Password.class, PersonalInfo.class}, version = 1)
public abstract class AllItemDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();
    public abstract PasswordDao passwordDao();
    public abstract PersonalInfoDao personalInfoDao();

    public static AllItemDatabase INSTANCE;

    public static AllItemDatabase getDatabaseInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AllItemDatabase.class,
                    "MyDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
