package rx.android;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;
import rx.Observer;

public class Properties {
    private Properties() { }

    static public Observer<String> text(final TextView view) {
        return new Observer<String>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String text) {
                view.setText(text);
            }
        };
    }

    static public Observer<Drawable> image(final ImageView view) {
        return new Observer<Drawable>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Drawable drawable) {
                view.setImageDrawable(drawable);
            }
        };
    }
}
