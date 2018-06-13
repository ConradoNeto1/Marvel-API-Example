package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ComicDao {

    @Query("SELECT * FROM Comic LIMIT :limit OFFSET :offset ")
    List<Comic> getAll(int limit, int offset);

    @Query("SELECT * FROM Comic WHERE id = :id")
    Comic loadById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Comic> comics);

    @Update
    public void updateAll(List<Comic> comics);

    @Delete
    void delete(Comic comic);

}
