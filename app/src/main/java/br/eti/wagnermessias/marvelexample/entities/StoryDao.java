package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StoryDao {

    @Query("SELECT * FROM Story")
    List<Story> getAll();

    @Query("SELECT * FROM Story WHERE id = :id")
    Story loadById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Story> stories);

    @Update
    public void updateAll(List<Story> stories);

    @Delete
    void delete(Story story);
}
