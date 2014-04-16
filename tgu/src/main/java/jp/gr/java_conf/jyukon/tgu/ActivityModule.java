package jp.gr.java_conf.jyukon.tgu;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
        ItemsActivity.class,
        ItemDirectionFragment.class
    },
    complete = false,
    library = true
)
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
}