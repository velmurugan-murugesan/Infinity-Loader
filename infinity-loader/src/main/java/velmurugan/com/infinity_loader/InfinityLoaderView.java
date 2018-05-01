package velmurugan.com.infinity_loader;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class InfinityLoaderView extends View {
    private int animValue;
    private int strokeWidth = 15;
    private int defaultColor,loaderColor,loaderLength;
    private int FULL_CIRCLE_RADIUS=360;
    private int defaultLoaderLength = 80;
    private int defaultAnimDuration = 1000;
    private int animDuration = 0;

    public InfinityLoaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        animValue = 0;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.infinityloader);
        try {
            defaultColor = typedArray.getColor(R.styleable.infinityloader_default_color,getResources().getColor(R.color.default_color));
            loaderColor = typedArray.getColor(R.styleable.infinityloader_loader_color,getResources().getColor(R.color.loader_color));
            loaderLength = typedArray.getInt(R.styleable.infinityloader_loader_length,defaultLoaderLength);
            animDuration = typedArray.getInt(R.styleable.infinityloader_anim_duration,defaultAnimDuration);
        }catch (Exception e){
            e.printStackTrace();
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(defaultColor);
        RectF rectF = new RectF();
        rectF.set(strokeWidth,strokeWidth,getWidth() - strokeWidth  ,getWidth() - strokeWidth);
        canvas.drawArc(rectF,0,FULL_CIRCLE_RADIUS,false,paint);
        paint.setColor(loaderColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF,animValue,loaderLength,false,paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final ValueAnimator valueAnimator = ValueAnimator.ofInt(1,FULL_CIRCLE_RADIUS);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(animDuration);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("Anim",animation.getAnimatedValue()+"");
                setValue((Integer) animation.getAnimatedValue());
            }
        });

        valueAnimator.start();
    }

    private void setValue(int animatedValue) {
        animValue = animatedValue;
        invalidate();
    }

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
        invalidate();
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        invalidate();
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        invalidate();
    }

    public void setLoaderColor(int loaderColor) {
        this.loaderColor = loaderColor;
        invalidate();
    }

    public void setLoaderLength(int loaderLength) {
        this.loaderLength = loaderLength;
        invalidate();
    }
}
