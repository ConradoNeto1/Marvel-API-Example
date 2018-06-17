package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SerieDao {

    @Query("SELECT * FROM Serie")
    List<Serie> getAll();

    @Query("SELECT * FROM Serie WHERE id = :id")
    Serie loadById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Serie> series);

    @Update
    public void updateAll(List<Serie> series);

    @Delete
    void delete(Serie serie);
}
