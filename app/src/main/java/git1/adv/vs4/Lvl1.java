package git1.adv.vs4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.LineNumberReader;
import java.util.Random;

public class Lvl1 extends AppCompatActivity {
    Dialog dialogEnd;

    public int numLeft; //переменная для левого окна
    public int numRight; //переменная для правого окна
    Array array = new Array();
    Array arrayKeep = new Array();
    Array arrayText = new Array();
    Random random = new Random();
    public int count = 0; //счетчик ответов
    public static int countKeep = 0;
    public static int countLvl = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //переменная уровней text_lvl
        TextView text_lvl = findViewById(R.id.text_lvl);
        text_lvl.setText(arrayText.textLvl[countLvl]); //установили текст
        countLvl++;


        //код для главных кнопак выбора
        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);


        Window w = getWindow(); //игра на весь экран
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //вызов окна диалога
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрытие заголовка
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон за окном диалога
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT); //диалоговое акно на весь экран
        dialogEnd.setCancelable(false);//окно нельзя закрыть кнопкой назад

        //текст keep calm
        TextView textKeep1 = (TextView) dialogEnd.findViewById(R.id.textKeep);
        textKeep1.setText(arrayKeep.textKeep[countKeep]);
        countKeep++;



        TextView btnclose = (TextView)dialogEnd.findViewById(R.id.btn_cls); //кнопка закрытия окна диалога
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //нажатие кнопки
                try {
                    //выбор уровня
                    Intent intent = new Intent(Lvl1.this, Gamelvl.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss(); //закрываем окно диалога
                if (countKeep == 5) {
                    System.exit(0);
                }


            }
        });
        Button btncls = (Button) dialogEnd.findViewById(R.id.btn_cls);
        btncls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Lvl1.this, Lvl1.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }

                dialogEnd.dismiss();//закрыть окно диалога

            }
        });


        //dialogEnd.show();//показать окно

        // кнопка назад
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //вернуться назад к выбору урвня
                    Intent intent = new Intent(Lvl1.this, Gamelvl.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        //массив прогресса прохождения с сохранением результата
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10
        };

        //вкл анимация
        final Animation a = AnimationUtils.loadAnimation(Lvl1.this, R.anim.alpha);


        numLeft = random.nextInt(10); //генерируем число от 0 до 9
        img_left.setImageResource(array.images1[numLeft]); //получить из массива рандомную картинку
        numRight = random.nextInt(10);
        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images1[numRight]);

        //анимация и обработка нажатия на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //касание пальца по экрану
                    img_right.setEnabled(false);
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //отрыв пальца от экрана
                    if (numLeft > numRight) {
                        if (count < 10) {
                            count ++;
                        }
                        //заливка белым
                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource((R.drawable.style_points));
                        }
                        //заливка желтым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_white);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        //заливка белым
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource((R.drawable.style_points));
                        }
                        //заливка желтым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_white);
                        }
                    }
                    if (count == 10) {
                        //выход из уровня
                        dialogEnd.show();

                    } else {
                        numLeft = random.nextInt(10); //генерируем число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]); //получить из массива рандомную картинку
                        img_left.startAnimation(a);
                        numRight = random.nextInt(10);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        img_right.setEnabled(true);//вкл правую картинку
                    }
                }
                return true;
            }
        });
        //анимация и обработка нажатия на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //касание пальца по экрану
                    img_left.setEnabled(false);
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //отрыв пальца от экрана
                    if (numLeft < numRight) {
                        if (count < 10) {
                            count ++;
                        }
                        //заливка белым
                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource((R.drawable.style_points));
                        }
                        //заливка желтым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_white);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        //заливка белым
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource((R.drawable.style_points));
                        }
                        //заливка желтым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_white);
                        }
                    }
                    if (count == 10) {
                        //выход из уровня
                        dialogEnd.show();

                    } else {
                        numLeft = random.nextInt(10); //генерируем число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]); //получить из массива рандомную картинку
                        img_left.startAnimation(a);
                        numRight = random.nextInt(10);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        img_left.setEnabled(true);//вкл левую картинку
                    }
                }
                return true;
            }
        });

    }
    ////изменение физической кнопки телефона назад
    @Override
    public void onBackPressed() {
        try {
            //назад к выбору уровня
            Intent intent = new Intent(Lvl1.this, Gamelvl.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}