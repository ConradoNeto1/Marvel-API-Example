package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Wagner on 13/05/2018.
 */
@Dao
public interface CharacterDao {

    @Query("SELECT * FROM Character")
    List<Character> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCharacters(List<Character> characters);


}
