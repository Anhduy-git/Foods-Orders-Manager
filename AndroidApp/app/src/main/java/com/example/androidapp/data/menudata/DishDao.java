package com.example.androidapp.data.menudata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidapp.data.clientdata.Client;

import java.util.List;

@Dao
public interface DishDao {

    @Query("SELECT * FROM dish_table ORDER BY name ASC")
    LiveData<List<Dish>> getAllDishes();

    @Query("SELECT * FROM dish_table WHERE name =:newName AND price =:newPrice AND image =:newImage")
    List<Dish> checkDishExist(String newName, int newPrice, byte[] newImage);

    @Insert
    void insertDish(Dish dish);

    @Update
    void updateDish(Dish dish);

    @Delete
    void deleteDish(Dish dish);

    @Query("DELETE FROM dish_table")
    void deleteAllDishes();
}
