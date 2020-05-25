package hitesh.asimplegame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;


import java.util.HashMap;

import androidx.annotation.RequiresApi;

public class Soundclass {

    public static final int Click = R.raw.click;
    public static final int Wow = R.raw.wow;

    private static SoundPool soundPool;
    private static HashMap<Integer, Integer> soundPoolMap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void initSounds(Context context) {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        soundPoolMap = new HashMap(2);
        soundPoolMap.put(Click, soundPool.load(context, Click, 1));
        soundPoolMap.put(Wow, soundPool.load(context, Wow, 2));
    }

    public static void play(int raw_id){
        if( soundPoolMap.containsKey(raw_id) ) {
            soundPool.play(soundPoolMap.get(raw_id), 1, 1, 1, 0, 1f);
        }
    }


}
