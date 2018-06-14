package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Wagner on 13/05/2018.
 */
@Dao
public interface CharacterDao {

    @Query("SELECT * FROM Character")
    List<Character> getAll();

    @Query("SELECT * FROM Character WHERE id = :id")
    Character loadById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Character> characters);

    @Update
    public void updateAll(List<Character> characters);

    @Delete
    void delete(Character character);

}
