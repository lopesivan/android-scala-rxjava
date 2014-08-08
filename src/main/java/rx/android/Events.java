package rx.android;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class Events {
    /*
     * Creates a subject that emits events for the current text and each text change event
     */
    public static Observable<String> text(TextView view) {
        String currentText = String.valueOf(view.getText());
        final BehaviorSubject<String> subject = BehaviorSubject.create(currentText);
        view.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(
                    CharSequence charSequence, int i, int i2, int i3) { }

            @Override public void onTextChanged(
                    CharSequence charSequence, int i, int i2, int i3) { }

            @Override public void afterTextChanged(Editable editable) {
                subject.onNext(editable.toString());
            }
        });
        return subject.asObservable();
    }

    /*
     * Creates a subject that emits events for each click on view
     */
    public static Observable<Object> click(View view) {
        final PublishSubject<Object> subject = PublishSubject.create();
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                subject.onNext(new Object());
            }
        });
        return subject.asObservable();
    }

    // no instances for helper class
    private Events() { }
}
