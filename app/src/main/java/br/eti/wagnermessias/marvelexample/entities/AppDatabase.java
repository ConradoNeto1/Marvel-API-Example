package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Wagner on 13/05/2018.
 */
@Database(entities = {Character.class, Comic.class, Event.class, Serie.class, Story.class, Url.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

        private static AppDatabase INSTANCE;

        public abstract CharacterDao characterDao();
        public abstract ComicDao comicDao();
        public abstract EventDao eventDao();
        public abstract SerieDao serieDao();
        public abstract StoryDao storyDao();
        public abstract UrlDao urlDao();


        public static AppDatabase getAppDatabase(Context context) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "marvel-database")
                                // allow queries on the main thread.
                                // Don't do this on a real app! See PersistenceBasicSample for an example.
                                .allowMainThreadQueries()
                                .build();
            }
            return INSTANCE;
        }

        public static void destroyInstance() {
            INSTANCE = null;
        }

}
