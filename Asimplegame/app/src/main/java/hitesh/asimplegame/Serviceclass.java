package hitesh.asimplegame;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class Serviceclass extends Service {
    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("test", "서비스의 onCreate");
        mp = MediaPlayer.create(this, R.raw.bgm);
        mp.setLooping(true); // 반복재생
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("test", "서비스의 onStartCommand");
        mp.start(); // 노래 시작
        //return super.onStartCommand(intent, flags, startId);
        return Service.START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop(); // 음악 종료
        Log.d("test", "서비스의 onDestroy");
    }
}


