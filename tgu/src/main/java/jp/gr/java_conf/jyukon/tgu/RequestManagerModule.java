package jp.gr.java_conf.jyukon.tgu;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class RequestManagerModule {
    private Context mApplicationContext;

    public RequestManagerModule(TguApplication application) {
        this.mApplicationContext = application.getApplicationContext();
    }

    @Provides
    @Singleton
    RequestManager provideRequestManager() {
        return new RequestManager(mApplicationContext);
    }
}
