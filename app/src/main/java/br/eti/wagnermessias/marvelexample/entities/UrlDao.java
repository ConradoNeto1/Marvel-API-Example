package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UrlDao {

    @Query("SELECT * FROM Url")
    List<Story> getAll();

    @Query("SELECT * FROM Url WHERE characterId = :id")
    Story loadByIdCharacter(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Url> urls);

    @Update
    public void updateAll(List<Url> urls);

    @Delete
    void delete(Url urls);
}
