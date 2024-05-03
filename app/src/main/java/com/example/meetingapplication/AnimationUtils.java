package com.example.meetingapplication;

import android.view.View;

public class AnimationUtils {
    public static void scaleUpView(View view, long duration) {
        view.setScaleX(0f);
        view.setScaleY(0f);
        view.animate().scaleX(1f).scaleY(1f).setDuration(duration).start();
    }

    public static void scaleDownAndFadeOutView(View view, long duration, Runnable endAction) {
        view.animate()
                .alpha(0f)
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(duration)
                .withEndAction(endAction)
                .start();
    }

}
