package mobi.pinuts.reactive.app.services

import java.util.concurrent.Executors

import mobi.pinuts.reactive.app.values.Banner
import retrofit.RestAdapter
import retrofit.RestAdapter.LogLevel
import retrofit.http.GET
import rx.lang.scala.Observable


trait MockableService {
  @GET("/banner")
  def banner(): rx.Observable[Banner]
}

object MockableService {
  import rx.lang.scala.JavaConversions._

  private lazy val restAdapter = new RestAdapter.Builder()
    .setLogLevel(LogLevel.FULL)
    .setEndpoint("http://demo8302100.mockable.io").build()
  private lazy val service = restAdapter.create(classOf[MockableService])

  def banner(): Observable[Banner] = service.banner()
}