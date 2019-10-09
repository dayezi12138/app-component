package core.app.zh.com.core.view.verifyCodeView.wrapper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class CircleWrapper implements VerifyCodeWrapper {

    @Override
    public boolean isCovered() {
        //the circle and verify code will display together
        return false;
    }

    @Override
    public void drawWrapper(Canvas canvas, Paint paint, RectF rectF, RectF textRectF) {
        //calculate radius and draw circle
        float radius = Math.min(rectF.width(), rectF.height()) / 2;
        canvas.drawCircle(rectF.left + rectF.width() / 2, rectF.top + rectF.height() / 2, radius, paint);
    }
}