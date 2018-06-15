package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EventDao {

    @Query("SELECT * FROM Event")
    List<Event> getAll();

    @Query("SELECT * FROM Event WHERE id = :id")
    Event loadById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Event> events);

    @Update
    public void updateAll(List<Event> events);

    @Delete
    void delete(Event event);
}
