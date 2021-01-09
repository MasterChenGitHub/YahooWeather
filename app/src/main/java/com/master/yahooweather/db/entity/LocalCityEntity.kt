package com.master.yahooweather.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by MasterChen on 2020/12/29
 */
@Entity(tableName = "city")
data class LocalCityEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    var cityId: Int?,
    @ColumnInfo(name = "city")
    var cityName: String?,
    @ColumnInfo(name = "province")
    var province: String? ,
    @ColumnInfo(name = "allpy")
    var allPinyin: String?,
    @ColumnInfo(name = "allfirstpy")
    var allFirstPinyin : String?,
    @ColumnInfo(name = "firstpy")
    var firstPinyin  : String?,
    @ColumnInfo(name = "number")
    var number: String?
)
