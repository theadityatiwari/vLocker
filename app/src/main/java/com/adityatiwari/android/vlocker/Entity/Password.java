package com.adityatiwari.android.vlocker.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Password_Database")
public class Password {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Username")
    public String username;

    @ColumnInfo(name = "Password")
    public String password;

    @ColumnInfo(name = "Website")
    public String website;

}
