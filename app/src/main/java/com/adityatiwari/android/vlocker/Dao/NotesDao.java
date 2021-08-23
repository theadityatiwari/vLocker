package com.adityatiwari.android.vlocker.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.Entity.PersonalInfo;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM 'Notes_Database'")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM 'Notes_Database' ORDER BY notes_priority ASC")
    LiveData<List<Notes>> highToLow();


    @Query("SELECT * FROM 'Notes_Database' ORDER BY notes_priority DESC")
    LiveData<List<Notes>> lowToHigh();



    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM 'Notes_database' WHERE id=:id")
    void deleteNotes(int id);


    @Update
    void updateNotes(Notes... notes);

}
