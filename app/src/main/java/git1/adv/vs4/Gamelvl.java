package git1.adv.vs4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gamelvl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelvl);

        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Команда кнопки назад
                try {
                    Intent intent = new Intent(Gamelvl.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //Кнопка на переход на первый уровень
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Gamelvl.this, Lvl1.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    //Изменение физической кнопки телефона назад
    @Override
    public  void onBackPressed() {
        //Команда кнопки назад
        try {
            Intent intent = new Intent(Gamelvl.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {

        }
    }
}