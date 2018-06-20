package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ComicCreatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ComicCreator comicCreator);

    @Query("SELECT * FROM Comic INNER JOIN comic_creator ON Comic.id= comic_creator.comicId WHERE comic_creator.creatorId =:creatorId")
    List<Comic> getComicsForCreator(Integer creatorId);

    @Query("SELECT * FROM Creator INNER JOIN comic_creator ON Creator.id = comic_creator.creatorId WHERE comic_creator.comicId =:comicId")
    List<Creator>getCreatorsForComic(Integer comicId);
}
