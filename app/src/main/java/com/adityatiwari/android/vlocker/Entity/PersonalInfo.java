package com.adityatiwari.android.vlocker.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Personal_Info_Database")
public class PersonalInfo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Email")
    public String email;

    @ColumnInfo(name = "Phone")
    public String phone;

    @ColumnInfo(name = "Address")
    public String address;
}
