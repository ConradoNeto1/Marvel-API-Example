package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CreatorDao {

    @Query("SELECT * FROM Creator")
    List<Creator> getAll();

    @Query("SELECT * FROM Creator WHERE id = :id")
    Creator loadById(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Creator> creators);

    @Update
    public void updateAll(List<Creator> creators);

    @Delete
    void delete(Creator creator);
}
