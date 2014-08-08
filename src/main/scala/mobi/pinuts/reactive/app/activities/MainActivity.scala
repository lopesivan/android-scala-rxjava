package mobi.pinuts.reactive.app.activities

import android.content.Intent
import android.widget.{Button, EditText, ImageView, TextView}
import com.squareup.picasso.Picasso
import mobi.pinuts.reactive.app.R
import mobi.pinuts.reactive.app.services.MockableService
import mobi.pinuts.reactive.app.values.Banner
import org.scaloid.common._
import rx.android.schedulers.AndroidSchedulers
import rx.android.{Events, Properties}
import rx.lang.scala.JavaConversions._
import rx.lang.scala.Observable

class MainActivity extends SActivity {
  private val TAG = "MainActivity"

  onCreate {
    setContentView(R.layout.main_activity)

    val messageField = find[EditText](R.id.message)
    val charCountLabel = find[TextView](R.id.char_count)

    val message: Observable[String] = Events.text(messageField)
    val charCount = Properties.text(charCountLabel)

    message.map(str=> str.length.toString).subscribe(charCount)

    val send = find[Button](R.id.send)
    val sendClicked = Events.click(send)

    message.combineLatest(sendClicked).subscribe { textAndObject =>
      startActivity(new Intent(Intent.ACTION_VIEW, "http://www.google.com/search?q="+textAndObject._1))
    }
  }

  onCreate {
    val bannerImage = find[ImageView](R.id.banner)
    val banner =
      (Banner("http://www.pudim.com.br", "") +: MockableService.banner())
        .onExceptionResumeNext(Observable.empty)
        .cache
        .observeOn(AndroidSchedulers.mainThread())

    banner.subscribe { banner =>
      if (banner.image.isEmpty) {
        Picasso.`with`(this).load(R.drawable.funny_banner)
      } else {
        Picasso.`with`(this).load(banner.image).placeholder(R.drawable.funny_banner)
      }.into(bannerImage)
    }

    banner.combineLatest(Events.click(bannerImage)).subscribe { bannerAndObject =>
      val b = bannerAndObject._1
      startActivity(new Intent(Intent.ACTION_VIEW, b.url))
    }
  }


}
