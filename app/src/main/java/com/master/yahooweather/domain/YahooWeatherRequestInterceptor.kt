package com.master.yahooweather.domain

import com.master.yahooweather.base.Constants
import com.master.yahooweather.base.Constants.YahooWeatherNetworkService.CONSUMER_KEY
import com.master.yahooweather.base.Constants.YahooWeatherNetworkService.CONSUMER_SECRET
import net.oauth.OAuth
import net.oauth.OAuthAccessor
import net.oauth.OAuthConsumer
import net.oauth.OAuthMessage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by MasterChen on 2020-12-12
 */

@Singleton
class YahooWeatherRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
                .newBuilder()
                .addQueryParameter("format", "json")
                .build()
        var consumer = OAuthConsumer(null, CONSUMER_KEY, CONSUMER_SECRET, null)
        consumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.HMAC_SHA1)
        val accessor = OAuthAccessor(consumer)
        var req = accessor.newRequestMessage(OAuthMessage.GET, url.toString(), null)
        val authorizationLine = req.getAuthorizationHeader(null);

        val request = chain.request().newBuilder().url(url)
                .header("Authorization", authorizationLine)
                .header("X-Yahoo-App-Id", Constants.YahooWeatherNetworkService.APP_ID)
                .header("Content-Type", "application/json")
                .build()
        return chain.proceed(request)
    }
}
