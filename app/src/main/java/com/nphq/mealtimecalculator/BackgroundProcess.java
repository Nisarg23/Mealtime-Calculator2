package com.nphq.mealtimecalculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.SystemClock;

public class BackgroundProcess extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Ringtone ringtone = RingtoneManager.getRingtone(context,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        ringtone.play();

        SystemClock.sleep(3000);

        ringtone.stop();
    }
}
