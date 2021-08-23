package com.adityatiwari.android.vlocker.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.adityatiwari.android.vlocker.Entity.PersonalInfo;

import java.util.List;

@androidx.room.Dao
public interface PersonalInfoDao {

    @Query("SELECT * FROM 'Personal_Info_Database'")
    LiveData<List<PersonalInfo>> getAllPersonalInfo();

    @Insert
    void insertPersonalInfo(PersonalInfo... personalInfos);

    @Query("DELETE FROM 'Personal_Info_Database' WHERE id=:id")
    void deletePersonalInfo(int id);

    @Update
    void updatePersonalInfo(PersonalInfo... personalInfos);
}
