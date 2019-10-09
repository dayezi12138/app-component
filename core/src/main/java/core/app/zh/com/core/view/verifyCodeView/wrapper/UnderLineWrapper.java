package core.app.zh.com.core.view.verifyCodeView.wrapper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class UnderLineWrapper implements VerifyCodeWrapper {

    @Override
    public boolean isCovered() {
        //the under line and verify code will display together
        return false;
    }

    @Override
    public void drawWrapper(Canvas canvas, Paint paint, RectF rectF, RectF textRectF) {
        //make under line width always twice of text width
        canvas.drawLine(textRectF.left - textRectF.width()/2, rectF.bottom, textRectF.right + textRectF.width() / 2, rectF.bottom, paint);
    }

}