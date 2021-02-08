package com.master.yahooweather.domain.model

import android.graphics.Color
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.master.yahooweather.db.entity.codeConverter
import com.master.yahooweather.db.entity.temperatureConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by MasterChen on 2020/12/29
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class YahooForecastResponse(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,
    @Embedded
    @Json(name = "location")
    val location: Location?,
    @Embedded
    @Json(name = "current_observation")
    val currentObservation: CurrentObservation?,
    @Json(name = "forecasts")
    val forecasts: List<YahooForecast?>? = null
) : Parcelable

@Entity(tableName = "Location")
@Parcelize
@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "city")
    @ColumnInfo(name = "city")
    val city: String?,

    @Json(name = "region")
    @ColumnInfo(name = "region")
    val region: String?,

    @Json(name = "lat")
    @ColumnInfo(name = "lat")
    val lat: String?,

    @Json(name = "long")
    @ColumnInfo(name = "lon")
    val lon: String?
) : Parcelable


@Entity(tableName = "Observation")
@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentObservation(
    @Embedded
    val wind: YahooWind?,
    @Embedded
    @Json(name = "atmosphere")
    val atmosphere: YahooAtmosphere?,
    @Embedded
    @Json(name = "astronomy")
    val astronomy: YahooAstronomy?,
    @Embedded
    @Json(name = "condition")
    val condition: YahooCondition?,
    @Json(name = "pubDate")
    @ColumnInfo(name = "pubDate")
    val pubDate: Long?
) : Parcelable
@Entity(tableName = "Wind")
@Parcelize
@JsonClass(generateAdapter = true)
data class YahooWind(
    @Json(name = "chill")
    @ColumnInfo(name = "chill")
    val chill: String?,

    @Json(name = "direction")
    @ColumnInfo(name = "direction")
    val direction: String?,

    @Json(name = "speed")
    @ColumnInfo(name = "speed")
    val speed: String?
) : Parcelable
@Entity(tableName = "Atmosphere")
@Parcelize
@JsonClass(generateAdapter = true)
data class YahooAtmosphere(
    @Json(name = "humidity")
    @ColumnInfo(name = "humidity")
    val humidity: String?,
    @Json(name = "visibility")
    @ColumnInfo(name = "visibility")
    val visibility: String?,
    @Json(name = "pressure")
    @ColumnInfo(name = "pressure")
    val pressure: String?,
    @Json(name = "rising")
    @ColumnInfo(name = "rising")
    val rising: String?
) : Parcelable
@Entity(tableName = "Astronomy")
@Parcelize
@JsonClass(generateAdapter = true)
data class YahooAstronomy(
    @Json(name = "sunrise")
    @ColumnInfo(name = "sunrise")
    val sunrise: String?,
    @Json(name = "sunset")
    @ColumnInfo(name = "sunset")
    val sunset: String?
) : Parcelable
@Entity(tableName = "Condition")
@Parcelize
@JsonClass(generateAdapter = true)
data class YahooCondition(
    @Json(name = "text")
    @ColumnInfo(name = "text")
    val text: String?,
    @Json(name = "code")
    @ColumnInfo(name = "code")
    val code: String?,
    @Json(name = "temperature")
    @ColumnInfo(name = "temperature")
    val temperature: String?
) : Parcelable


@Parcelize
@JsonClass(generateAdapter = true)
data class YahooForecast(
    @Json(name = "day")
    val day: String?,
    @Json(name = "date")
    val date: Long?,
    @Json(name = "low")
    val low: Short?,
    @Json(name = "high")
    val high: Short?,
    @Json(name = "code")
    val code: Short?
) : Parcelable {


    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    fun getColor(): Int {
        return when (date?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> Color.parseColor("#28E0AE")
            DayOfWeek.TUESDAY -> Color.parseColor("#FF0090")
            DayOfWeek.WEDNESDAY -> Color.parseColor("#FFAE00")
            DayOfWeek.THURSDAY -> Color.parseColor("#0090FF")
            DayOfWeek.FRIDAY -> Color.parseColor("#DC0000")
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF")
            DayOfWeek.SUNDAY -> Color.parseColor("#3D28E0")
            else -> Color.parseColor("#28E0AE")
        }
    }

    fun getConditionIcon():String =codeConverter(code?.toString())

    fun getLow():String=temperatureConverter(low?.toInt())

    fun getHigh():String=temperatureConverter(high?.toInt())

}


