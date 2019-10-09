package core.app.zh.com.core.view.verifyCodeView.wrapper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class CenterLineWrapper implements VerifyCodeWrapper {

    @Override
    public boolean isCovered() {
        //the center line will be covered by verify code
        return true;
    }

    @Override
    public void drawWrapper(Canvas canvas, Paint paint, RectF rectF, RectF textRectF) {
        //make center line width always twice of text width
        canvas.drawLine(textRectF.left - textRectF.width()/2, rectF.height()/2, textRectF.right + textRectF.width() / 2, rectF.height()/2, paint);
    }
}