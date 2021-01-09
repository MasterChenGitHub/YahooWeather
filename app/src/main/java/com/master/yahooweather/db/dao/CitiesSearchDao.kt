package com.master.yahooweather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.master.yahooweather.db.entity.LocalCityEntity

/**
 * Created by MasterChen on 2020/12/29
 */

@Dao
interface CitiesSearchDao {

    @Query("Select count(*) from city")
    fun getCount(): Int

    @Query("SELECT * FROM city ")
    fun getCities(): LiveData<List<LocalCityEntity>>

    @Query("SELECT * FROM city  WHERE city like '%' || :city || '%'OR allpy like '%' || :city || '%' OR allfirstpy like '%' || :city || '%' ORDER BY firstpy  ASC")
    fun getCityByName(city: String? = ""): LiveData<List<LocalCityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(localCityEntity: LocalCityEntity)

}
