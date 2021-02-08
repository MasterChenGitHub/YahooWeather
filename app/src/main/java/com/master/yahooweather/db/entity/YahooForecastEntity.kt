package com.master.yahooweather.db.entity

import android.os.Parcelable
import androidx.room.*
import com.master.yahooweather.domain.model.YahooForecast
import com.master.yahooweather.domain.model.YahooForecastResponse
import kotlinx.parcelize.Parcelize
/**
 * Created by MasterChen on 2020/12/29
 */
@Parcelize
@Entity(tableName = "Weather")
data class YahooForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @Embedded
    val location: Location?,
    @Embedded
    val currentObservation: CurrentObservation?,
    @ColumnInfo(name = "forecasts")
    val forecasts: List<YahooForecast?>? = null
) : Parcelable {
    @Ignore
    constructor(currentWeather: YahooForecastResponse) : this(
        location = Location(currentWeather.location),
        currentObservation = CurrentObservation(currentWeather.currentObservation),
        forecasts = currentWeather.forecasts
    )

    fun getLow(): String {
        var res = "0"
        forecasts?.let { it ->
            if (it.isNotEmpty()) {
                it[0]?.let {
                    res = it.getLow()
                }
            }
        }
        return res
    }

    fun getHigh(): String {
        var res = "0"
        forecasts?.let { it ->
            if (it.isNotEmpty()) {
                it[0]?.let {
                    res = it.getHigh()
                }
            }
        }
        return res
    }

}

@Parcelize
@Entity(tableName = "Location")
data class Location(
    @ColumnInfo(name = "city")
    val city: String?,

    @ColumnInfo(name = "region")
    val region: String?,

    @ColumnInfo(name = "lat")
    val lat: String?,

    @ColumnInfo(name = "lon")
    val lon: String?
) : Parcelable {
    @Ignore
    constructor(location: com.master.yahooweather.domain.model.Location?) : this(
        city = location?.city,
        region = location?.region,
        lat = location?.lat,
        lon = location?.lon
    )
}

@Parcelize
@Entity(tableName = "Observation")
data class CurrentObservation(
    @Embedded
    val wind: YahooWind?,
    @Embedded
    val atmosphere: YahooAtmosphere?,
    @Embedded
    val astronomy: YahooAstronomy?,
    @Embedded
    val condition: YahooCondition?,
    @ColumnInfo(name = "pubDate")
    val pubDate: Long?
) : Parcelable {
    @Ignore
    constructor(o: com.master.yahooweather.domain.model.CurrentObservation?) : this(
        wind = YahooWind(o?.wind),
        atmosphere = YahooAtmosphere(o?.atmosphere),
        astronomy = YahooAstronomy(o?.astronomy),
        condition = YahooCondition(o?.condition),
        pubDate = o?.pubDate
    )
}

@Parcelize
@Entity(tableName = "Wind")
data class YahooWind(
    @ColumnInfo(name = "chill")
    val chill: String?,

    @ColumnInfo(name = "direction")
    val direction: String?,

    @ColumnInfo(name = "speed")
    val speed: String?
) : Parcelable {
    @Ignore
    constructor(wind: com.master.yahooweather.domain.model.YahooWind?) : this(
        chill = wind?.chill,
        direction = wind?.direction,
        speed = wind?.speed
    )
}

@Entity(tableName = "Atmosphere")
@Parcelize
data class YahooAtmosphere(
    @ColumnInfo(name = "humidity")
    val humidity: String?,
    @ColumnInfo(name = "visibility")
    val visibility: String?,
    @ColumnInfo(name = "pressure")
    val pressure: String?,
    @ColumnInfo(name = "rising")
    val rising: String?
) : Parcelable {
    @Ignore
    constructor(a: com.master.yahooweather.domain.model.YahooAtmosphere?) : this(
        humidity = a?.humidity,
        visibility = a?.visibility,
        pressure = a?.pressure,
        rising = a?.rising
    )
}

@Entity(tableName = "Astronomy")
@Parcelize

data class YahooAstronomy(
    @ColumnInfo(name = "sunrise")
    val sunrise: String?,
    @ColumnInfo(name = "sunset")
    val sunset: String?
) : Parcelable {
    @Ignore
    constructor(a: com.master.yahooweather.domain.model.YahooAstronomy?) : this(
        sunrise = a?.sunrise,
        sunset = a?.sunset
    )
}

@Entity(tableName = "Condition")
@Parcelize
data class YahooCondition(
    @ColumnInfo(name = "text")
    val text: String?,
    @ColumnInfo(name = "code")
    val code: String? = "32",
    @ColumnInfo(name = "temperature")
    val temperature: String?
) : Parcelable {
    @Ignore
    constructor(c: com.master.yahooweather.domain.model.YahooCondition?) : this(
        text = c?.text,
        code = c?.code,
        temperature = temperatureConverter(Integer.parseInt(c?.temperature ?: "0"))
    )

    fun getConditionIcon(): String = codeConverter(code)

}


fun codeConverter(code: String?): String = "http://l.yimg.com/a/i/us/we/52/$code.gif"

fun temperatureConverter(tem: Int?): String =
    if (tem == null) {
        "0"
    } else ((tem - 32) / 1.8).toInt().toString()







