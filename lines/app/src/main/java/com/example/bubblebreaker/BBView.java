package com.example.bubblebreaker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BBView extends View {

    public BBView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

//		initBBView();
    }

    public BBView(Context context, AttributeSet attrs) {
        super(context, attrs);
//		initBBView();
    }

    public void initBBView() {
        setFocusable(true);

        Resources r = this.getContext().getResources();
        if(mBBSettings.isBomb()){
        resetBubbles(mBBSettings.getBubble_color()+1);
        }else{
            resetBubbles(mBBSettings.getBubble_color());
        }


        loadBubble(BBConstants.BLUE_BUBBLE,
                r.getDrawable(R.drawable.bubble_blue));
        loadBubble(BBConstants.CYAN_BUBBLE,
                r.getDrawable(R.drawable.bubble_cyan));
        loadBubble(BBConstants.GREEN_BUBBLE,
                r.getDrawable(R.drawable.bubble_green));
        if (mBBSettings.getBubble_color() >= 4) {
            loadBubble(BBConstants.MAGENTA_BUBBLE,
                    r.getDrawable(R.drawable.bubble_magenta));
        }
        if (mBBSettings.getBubble_color() >= 5) {
            loadBubble(BBConstants.RED_BUBBLE,
                    r.getDrawable(R.drawable.bubble_red));
        }
        if (mBBSettings.getBubble_color() >= 6) {
            loadBubble(BBConstants.YELLOW_BUBBLE,
                    r.getDrawable(R.drawable.bubble_yellow));
        }
        if(mBBSettings.isBomb()){
            loadBubble(BBConstants.BOMB_BUBBLE,
                    r.getDrawable(R.drawable.bg1));
        }

    }

    public void setMode(int newMode) {
        int oldMode = mMode;
        mMode = newMode;

        if (newMode == BBConstants.RUNNING & oldMode != BBConstants.RUNNING) {
            mStatusText.setVisibility(View.INVISIBLE);
            this.invalidate();
            return;
        }

        Resources res = getContext().getResources();
        CharSequence str = "";
        if (newMode == BBConstants.PAUSE) {
            str = res.getText(R.string.mode_pause);
        }
        if (newMode == BBConstants.READY) {
            str = res.getText(R.string.mode_ready);
        }
        if (newMode == BBConstants.LOSE) {
            str = res.getString(R.string.mode_lose_prefix) + mScore
                    + res.getString(R.string.mode_lose_suffix);
            if (System.currentTimeMillis() == 5000) {
                setMode(BBConstants.RUNNING);
                //newMode = BBConstants.RUNNING;
            }
        }

        mStatusText.setText(str);
        mStatusText.setVisibility(View.VISIBLE);
    }

    public void newGame() {
        mScore = 0;

        mBBMatrix = new BBMatrix(mViewWidth, mViewHeight, this.mBBSettings);

        mXOffset = 1;
        mYOffset = 1;

        refreshPlayground(this);
    }

    public void loadBubble(int key, Drawable bubble) {
        Bitmap bitmap = Bitmap.createBitmap(mBBSettings.getBubble_diameter(),
                mBBSettings.getBubble_diameter(), Bitmap.Config.ARGB_8888);
//		Bitmap bitmap = Bitmap.createBitmap(BBConstants.BUBBLE_DIAMETER,
//				BBConstants.BUBBLE_DIAMETER, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        bubble.setBounds(0, 0, mBBSettings.getBubble_diameter(),
                mBBSettings.getBubble_diameter());
        bubble.draw(canvas);

        mBubbleArray[key] = bitmap;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewWidth = mBitmap != null ? mBitmap.getWidth() : 0;
        mViewHeight = mBitmap != null ? mBitmap.getHeight() : 0;
        if (mViewWidth >= w && mViewHeight >= h) {
            return;
        }

        if (mViewWidth < w) {
            mViewWidth = w;
        }
        if (mViewHeight < h) {
            mViewHeight = h;
        }

        newGame();
    }

    @SuppressLint("NewApi")
    public boolean onKeyDown(int keyCode, KeyEvent msg, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            keyCode = KeyEvent.KEYCODE_DPAD_DOWN;
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            // game begin
            newGame();
            setMode(BBConstants.RUNNING);
            return (true);
        }

        return super.onKeyDown(keyCode, msg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Log.d("bacon", "onTouchEvent X=" + event.getX() + " Y=" + event.getY()
                + " action=" + action);

        if (action != MotionEvent.ACTION_UP || mMode != BBConstants.RUNNING) {
            return true;
        }

        int curX = (int) Math.floor(event.getX());
        int curY = (int) Math.floor(event.getY());

        int bubbleX = (curX - mXOffset) / mBBSettings.getBubble_diameter();
        int bubbleY = (curY - mYOffset) / mBBSettings.getBubble_diameter();

        mBBMatrix.findSameBubble(bubbleX, bubbleY);

        if (mBBMatrix.mSameBubbleCount > 1) {
            mScore += calculateScore(mBBMatrix.mSameBubbleCount);

            mBBMatrix.removeMarkedBubbles();

            refreshPlayground(this);

            mBBMatrix.removeMark();

            this.invalidate();
        }
        Toast ts = Toast.makeText(getContext(), "Your score " + mScore,
                Toast.LENGTH_SHORT);
        ts.setGravity(Gravity.TOP, 0, 10);
        ts.show();

        if (mBBMatrix.isBBMatrixSolvable() == false) {
            setMode(BBConstants.LOSE);
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (BBConstants.RUNNING == mMode && mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    public void refreshPlayground(View v) {
        mBitmap = Bitmap.createBitmap(mViewWidth, mViewHeight,
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        if (mBitmap != null) {
            mCanvas.drawBitmap(mBitmap, 0, 0, null);
        }
        for (int i = 0; i < mBBMatrix.mXBubbleCount; i++) {
            for (int j = 0; j < mBBMatrix.mYBubbleCount; j++) {
                if (mBBMatrix.mBubbleGrid[i][j] != BBConstants.NULL_BUBBLE) {
                    mCanvas.drawBitmap(
                            mBubbleArray[mBBMatrix.mBubbleGrid[i][j]], mXOffset
                                    + i * mBBSettings.getBubble_diameter(), mYOffset
                                    + j * mBBSettings.getBubble_diameter(), mPaint);

                }
            }
        }
    }

    /*
     * n!
     */
    public int calculateScore(int pSameBubbleCount) {
        int result = 1;

        if (pSameBubbleCount <= 1) {
            return 0;
        }

        for (int i = 2; i <= pSameBubbleCount; i++) {
            result += i;
        }
        return result;
    }

    public void resetBubbles(int pBubbleCount) {
        mBubbleArray = new Bitmap[pBubbleCount];
    }

    public void setTextView(TextView newView) {
        mStatusText = newView;
    }

    public TextView mStatusText;
    public int mMode = BBConstants.RUNNING;
    public final Paint mPaint = new Paint();
    public Bitmap mBitmap;
    public Canvas mCanvas;
    public BBMatrix mBBMatrix;
    public Bitmap[] mBubbleArray;
    public BBSettings mBBSettings;
    public static int mXOffset;
    public static int mYOffset;
    public static int mViewWidth;
    public static int mViewHeight;
    public static int mScore;

}
