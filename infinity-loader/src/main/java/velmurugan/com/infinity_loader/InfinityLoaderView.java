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
    private int defaultColor,loaderColor;

    public InfinityLoaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        animValue = 0;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.infinityloader);
        try {
            defaultColor = typedArray.getColor(R.styleable.infinityloader_default_color,getResources().getColor(R.color.default_color));
            loaderColor = typedArray.getColor(R.styleable.infinityloader_loader_color,getResources().getColor(R.color.loader_color));
        }catch (Exception e){
            e.printStackTrace();
        }
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
        canvas.drawArc(rectF,0,360,false,paint);
        paint.setColor(loaderColor);
        canvas.drawArc(rectF,animValue,80,false,paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final ValueAnimator valueAnimator = ValueAnimator.ofInt(1,360);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(1000);
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

    public void setValue(int animatedValue) {
        animValue = animatedValue;
        invalidate();
    }
}
