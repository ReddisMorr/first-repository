package com.example.thi.circles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class GameView extends View {
    private Bitmap circle = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
    private int viewWidth;
    private int viewHeight;
    private int points = 0;
    private float newX = 0;
    private float newY = 0;
//    private float newCX = 0;
//    private float newCY = 0;
    private Paint p;
    private final int timerInterval = 3000;
//    private boolean hit = false;
    private float radius = circle.getWidth()/2;
    public List<CircleHolder> circles;
    public int circleCount=5;


    public GameView(Context context) {
        super(context);
        p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
        new Timer().start();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_UP) {
            newX = event.getX();
            newY = event.getY();
        }
        for(CircleHolder circle:circles) {
            if (!circle.isHit() && isInCircle(circle.getX(),circle.getY())) {
                circle.setHit(true);
                points += 10;
                invalidate();
            }
            else{
                invalidate();
            }

        }
        return true;//Все равно не понимаю, как какие-либо другие методы смогут обработать действие, если работаем мы с ним только здесь.
    }


    private void paint(Canvas canvas) {// Вопрос, касательно 2-го задания: canvas.drawBitmap начинает рисовать изображение с верхнего левого угла?
        canvas.drawARGB(250, 127, 199, 255);
        for(CircleHolder circle1: circles){
            if (!circle1.isHit()) {
                canvas.drawBitmap(circle,circle1.getX()-radius,circle1.getY()-radius,p);// Вроде как, именно этот метод должен выводить рисунок из ресурсов, но зачем здесь нужен paint. У него же есть свой цвет и размер; он не перебьет bitmap?
            }
            canvas.drawText(points + " tsp", viewWidth - 100, 70, p);
            Log.i("GAME", "painting...");
        }

    }

    private void update() {//По поводу 1-го задания: его можно выполнить двумя путями. 1)Если модуль разности между координатой круга и краем экрана меньше радиуса круга, то мы присваеваем этой координате численное значение радиуса( тогда у нас круг всегда будет либо находиться внутри экрана, либо касаться его краев); 2)Если выполняется такое же условие, как и в первом случае, то мы просто заново переопределяем координаты- это, как мне кажется, более правильно, хоть и сводит шанс касания краев экрана на нуль. Я, как вы можете заметить ниже, реализовал первую ситуацию, как основную, а вторую закомментировал.
        circles=new ArrayList<CircleHolder>();
        for(int i=0; i<circleCount;i++){
            CircleHolder circle=new CircleHolder();
            circle.setHit(false);
            circle.setX((float) (Math.random()) * viewWidth);
            circle.setY((float) (Math.random()) * viewHeight);
            if(circle.getX()<radius){
                circle.setX(radius);
            }else if(circle.getX()>viewWidth-radius){
                circle.setX(viewWidth - radius);
            }
            if(circle.getY()<radius){
                circle.setY(radius);
            }else if(circle.getY()>viewHeight-radius){
                circle.setY(viewHeight-radius);
            }
            circles.add(circle);
            // 1-ый вариант

            Log.i("GAME", "tick: x:" + circle.getX() + "; y:" + circle.getY());// А что это?
        }
        invalidate();
       }
        //2-ой вариант
//        if ((newCX < radius) || (newCX > viewWidth - radius) || (newCY < radius) || (newCY > viewHeight - radius)) {// Вот, кстати, вопрос возник. Как андроид считает координаты: снизу вверх, как в координатной плоскости, или сверху вниз, как в Бэйсике?
//            update();
//        } else {
//            Log.i("GAME", "tick: x:" + newCX + "; y:" + newCY);// А что это?
//            invalidate();
 //       }



    private boolean isInCircle(float x, float y) {
        return Math.sqrt(Math.pow(x - newX, 2) + Math.pow(y - newY, 2)) <= radius;
    }


    public class Timer extends CountDownTimer {
        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            update();
        }

        @Override
        public void onFinish() {
        }
    }
}
