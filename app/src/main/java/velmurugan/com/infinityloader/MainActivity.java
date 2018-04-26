package velmurugan.com.infinityloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import velmurugan.com.infinity_loader.InfinityLoader;

public class MainActivity extends AppCompatActivity {

    private InfinityLoader infinityLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
