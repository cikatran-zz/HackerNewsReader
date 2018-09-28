package com.test.uob.hacknewsreader.mvp;

import android.content.Context;
import android.os.Bundle;

public interface Presenter<T extends Scene> {
    void onSceneAdded(T scene, Bundle data, Context context);
    void onSceneRemoved();
}
