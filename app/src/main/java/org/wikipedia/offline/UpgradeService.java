package org.wikipedia.offline;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import org.wikipedia.WikipediaApp;
import org.wikipedia.settings.Prefs;
import org.wikipedia.util.log.L;

public class UpgradeService extends JobIntentService {
    // Unique job ID for this service (do not duplicate).
    private static final int JOB_ID = 1001;
    private static final int ENQUEUE_DELAY_MILLIS = 1000;

    private static Runnable ENQUEUE_RUNNABLE = () -> enqueueWork(WikipediaApp.getInstance(),
            UpgradeService.class, JOB_ID, new Intent(WikipediaApp.getInstance(), UpgradeService.class));

    public static void enqueue() {
        // TODO
        //if (upgrade complete) {
        //    return;
        //}
        WikipediaApp.getInstance().getMainThreadHandler().removeCallbacks(ENQUEUE_RUNNABLE);
        WikipediaApp.getInstance().getMainThreadHandler().postDelayed(ENQUEUE_RUNNABLE, ENQUEUE_DELAY_MILLIS);
    }

    @Override protected void onHandleWork(@NonNull Intent intent) {
        // TODO
        //if (upgrade complete) {
        //    return;
        //}
        L.d("Starting offline content upgrade...");


        if (!Prefs.isOfflinePcsToMobileHtmlConversionComplete()) {
            //runOneTimeSavedPagesConversion();
        }
    }

}
