package com.shuaige.ggq.mytopbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonTopbar mytopbar = (CommonTopbar) findViewById(R.id.mytopbar);
        mytopbar.setTitleTextNature("哈哈", 25, getResources().getColor(R.color.colorPrimaryDark));
        mytopbar.setLeftButtonNature(getResources().getDrawable(R.drawable.fanhuianniu), 0);
        mytopbar.setrightButtonNature(getResources().getDrawable(R.drawable.pseson_icon), 0);
        mytopbar.setLeftButtonMaginLeft(20);
        mytopbar.setRightButtonMaginLeft(30);
        assert mytopbar != null;
        mytopbar.setOnTitleButtonClickListener(new CommonTopbar.OnTitleButtonClickListener() {
            @Override
            public void onRightClickListener() {
                Toast.makeText(MainActivity.this, "点击右边", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftClickListener() {
                Toast.makeText(MainActivity.this, "点击左边", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
