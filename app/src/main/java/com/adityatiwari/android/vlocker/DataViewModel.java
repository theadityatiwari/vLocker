package com.adityatiwari.android.vlocker;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.Entity.PersonalInfo;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    public Repository dataRepository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> lowToHigh;
    public LiveData<List<Notes>> highToLow;


    public LiveData<List<Password>> getAllPassword;
    public LiveData<List<PersonalInfo>> getAllPersonalInfo;

    public DataViewModel(Application application) {
        super(application);

        dataRepository = new Repository(application);
        getAllNotes = dataRepository.getAllNotes;
        lowToHigh = dataRepository.lowToHigh;
        highToLow = dataRepository.highToLow;


        getAllPassword = dataRepository.getAllPassword;
        getAllPersonalInfo = dataRepository.getAllPersonalInfo;
    }

    public void insertNote(Notes notes){
        dataRepository.insertNotes(notes);
    }

    public void insertPasswords(Password password){
        dataRepository.insertPassword(password);
    }

    public void insertPersonalInfos(PersonalInfo personalInfo){
        dataRepository.insertPersonalInfo(personalInfo);
    }

    public void deleteNote(int id){
        dataRepository.deleteNotes(id);
    }

    public void deletePasswords(int id){
        dataRepository.deletePassword(id);
    }

    public void deletePersonalInfos(int id){
        dataRepository.deletePersonalInfo(id);
    }

    public void updateNote(Notes notes){
        dataRepository.updateNotes(notes);
    }

    public void updatePasswords(Password password){
        dataRepository.updatePassword(password);
    }

    public void updatePersonalInfos(PersonalInfo personalInfo){
        dataRepository.updatePersonalInfo(personalInfo);
    }

}
