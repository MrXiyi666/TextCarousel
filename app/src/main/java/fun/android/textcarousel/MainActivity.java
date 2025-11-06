package fun.android.textcarousel;

import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import fun.android.textcarousel.fun.Fun;
import fun.android.textcarousel.system.Static;
import fun.android.textcarousel.view.View_Edit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        Static.main = findViewById(R.id.layout);
        Static.main.setPadding(0, Fun.getStatusBarHeight(this),0,0);
        Static.view_main = new View_Edit(this);
        Static.main.removeAllViews();
        Static.main.setGravity(Gravity.START);
        Static.main.addView(Static.view_main.getView());
    }

}