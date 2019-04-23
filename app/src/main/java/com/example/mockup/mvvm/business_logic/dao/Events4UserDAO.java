package com.example.mockup.mvvm.business_logic.dao;

import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.data.Events4UserResponse;

import java.util.List;

import javax.inject.Singleton;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import io.reactivex.Single;

@Dao
@Singleton
public interface Events4UserDAO extends UserDAO {
    @Transaction
    @Query("SELECT * FROM users WHERE user_id LIKE :user_id")
    Events4UserResponse selectAllForUser(int user_id);
    @Query("SELECT * FROM events")
    List<Event> selectEvents();
    @Query("SELECT * FROM events WHERE event_code_id LIKE :event_id")
    Event selectEvent(int event_id);
    @Query("DELETE * FROM events WHERE event_code_id LIKE :event_id")
    void delete(int event_id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);
    @Update
    void update(Event event);
    @Delete
    void delete(Event event);
}
