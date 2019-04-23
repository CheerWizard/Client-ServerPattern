package com.example.mockup.mvvm.business_logic.dao;

import com.example.mockup.mvvm.business_logic.data.User;
import javax.inject.Singleton;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

@Dao
@Singleton
public interface UserDAO {
    @Query("SELECT user_id FROM users")
    int selectUserId();
    @Query("SELECT * FROM users")
    User selectUser();
    @Query("SELECT * FROM users WHERE id LIKE :id")
    Single<User> selectUserById(int id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
}
