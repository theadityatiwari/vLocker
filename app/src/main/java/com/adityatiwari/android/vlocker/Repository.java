package com.adityatiwari.android.vlocker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.adityatiwari.android.vlocker.Dao.NotesDao;
import com.adityatiwari.android.vlocker.Dao.PasswordDao;
import com.adityatiwari.android.vlocker.Dao.PersonalInfoDao;
import com.adityatiwari.android.vlocker.Database.AllItemDatabase;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.Entity.PersonalInfo;

import java.util.List;

public class Repository {

    public NotesDao notesDao;
    public PasswordDao passwordDao;
    public PersonalInfoDao personalInfoDao;

    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> lowToHigh;
    public LiveData<List<Notes>> highToLow;

    public LiveData<List<Password>> getAllPassword;

    public LiveData<List<PersonalInfo>> getAllPersonalInfo;

    public Repository (Application application){
        AllItemDatabase allItemDatabase = AllItemDatabase.getDatabaseInstance(application);
        notesDao = allItemDatabase.notesDao();
        getAllNotes = notesDao.getAllNotes();
        lowToHigh = notesDao.lowToHigh();
        highToLow = notesDao.highToLow();


        passwordDao = allItemDatabase.passwordDao();
        getAllPassword = passwordDao.getAllPassword();

        personalInfoDao = allItemDatabase.personalInfoDao();
        getAllPersonalInfo = personalInfoDao.getAllPersonalInfo();

    }


    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    public void insertPassword(Password password){
        passwordDao.insertPassword(password);
    }

    public void insertPersonalInfo(PersonalInfo personalInfo){
        personalInfoDao.insertPersonalInfo(personalInfo);
    }


    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }

    public void updatePassword(Password password){
        passwordDao.updatePassword(password);
    }

    public void updatePersonalInfo(PersonalInfo personalInfo){
        personalInfoDao.updatePersonalInfo(personalInfo);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void deletePassword(int id){
        passwordDao.deletePassword(id);
    }

    public void deletePersonalInfo(int id){
        personalInfoDao.deletePersonalInfo(id);
    }

}
