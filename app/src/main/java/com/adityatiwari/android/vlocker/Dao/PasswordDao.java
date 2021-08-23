package com.adityatiwari.android.vlocker.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.adityatiwari.android.vlocker.Entity.Password;
import java.util.List;

@androidx.room.Dao
public interface PasswordDao {

    @Query("SELECT * FROM 'Password_Database'ORDER BY Website ASC")
    LiveData<List<Password>> getAllPassword();


    @Insert
    void insertPassword(Password... passwords);

    @Query("DELETE FROM 'Password_database' WHERE id=:id")
    void deletePassword(int id);

    @Update
    void updatePassword(Password... passwords);
}
