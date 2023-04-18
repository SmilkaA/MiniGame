package com.test.minigame.gameplay.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.test.minigame.gameplay.model.Bird;
import com.test.minigame.gameplay.model.Pipes;
import com.test.myapplication.R;

import java.nio.channels.Pipe;
import java.util.List;

public class MSurface extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap bitmap;
    private Paint bitmapPaint;
    private Bird bird;
    private Bitmap sprite;
    private SurfaceHolder holder;
    private int dy = 0;
    private int millis = 0;
    private Pipes pipes;
    private Bitmap bottomPipe;
    private Bitmap topPipe;
    private int score = 0;
    private Paint textPaint;
    private RectF spriteRect;
    private RectF topPipeRect = new RectF();
    private RectF bottomPipeRect = new RectF();
    private boolean spriteScaled = false;
    private boolean responded = false;
    String numberFact = "";
    private Canvas canvas;
    private List<Pipe> pipeList;

    public MSurface(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        holder = getHolder();
        getHolder().addCallback(this);

        bird = new Bird();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        sprite = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        bottomPipe = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_pipe);
        Matrix transform = new Matrix();
        transform.preScale(1.0f, -1.0f);
        topPipe = Bitmap.createBitmap(bottomPipe, 0, 0, bottomPipe.getWidth(), bottomPipe.getHeight(), transform, true);

        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        updateThread.start();
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawBackground(this.canvas);
        drawBird(bird.getX(), bird.getY());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas(null);
            synchronized (holder) {
                draw(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void drawBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, getWidth(), getHeight()), paint);
    }

    private void drawBird(float dstWidth, float dstHeight) {
        canvas.drawBitmap(Bitmap.createScaledBitmap(sprite, 150, 150, true),
                dstWidth, dstHeight, new Paint());
    }


    Thread updateThread = new Thread() {
        public void run() {
            while (bird.getY() > 0) {
                try {
                    Thread.sleep(500);
                    bird.updatePosition(-30);
                    sprite = Bitmap.createScaledBitmap(sprite, 100, 100, false);
                    drawBird(bird.getX(), bird.getY());
		    update.start(); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Runnable update = new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println("111" + "update.run");
                if (millis >= 130 && millis % 2 == 0)
                    dy += 3;
                bird.updatePosition(dy);
                pipes.moveX();

                Canvas canvas = holder.lockCanvas();
                canvas.drawPaint(textPaint);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bottomPipe, pipes.getX(), pipes.getBottomY(), bitmapPaint);
                canvas.drawBitmap(topPipe, pipes.getX(), pipes.getTopY(), bitmapPaint);
                canvas.drawBitmap(sprite, bird.getX(), bird.getY(), bitmapPaint);
                if (!responded)
                    canvas.drawText("Score: " + score, 5, 200, textPaint);
                else {
                    canvas.drawText("Score: " + score, 5, 200, textPaint);
                    TextPaint tp = new TextPaint();
                    tp.setTextSize(50);
                    StaticLayout sl = new StaticLayout(numberFact, tp, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
                    sl.draw(canvas);
                }
                holder.unlockCanvasAndPost(canvas);


                spriteRect = new RectF(bird.getX(), bird.getY(), bird.getX() + sprite.getWidth(), bird.getY() + sprite.getHeight());
                topPipeRect = new RectF(pipes.getX(), pipes.getTopY(), pipes.getX() + topPipe.getWidth(), pipes.getTopY() + topPipe.getHeight());
                bottomPipeRect = new RectF(pipes.getX(), pipes.getBottomY(), pipes.getX() + bottomPipe.getWidth(), getHeight());

                if (spriteRect.intersect(topPipeRect) || spriteRect.intersect(bottomPipeRect) || bird.getY() > getHeight()) {
                    bird.setAlive(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            bird.updatePosition(-1);
            return true;
        }
        return false;
    }
}
