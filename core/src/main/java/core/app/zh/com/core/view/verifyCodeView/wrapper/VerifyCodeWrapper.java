package core.app.zh.com.core.view.verifyCodeView.wrapper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public interface VerifyCodeWrapper {
    /**
     * whether the wrapper will be covered by verify code
     * @return true: wrapper will be covered by verify code
     *          false: wrapper and verify code will display together
     */
    boolean isCovered();

    /**
     * here you can draw your wrapper for VerifyCodeView
     * @param canvas canvas to draw wrapper
     * @param paint paint to draw wrapper
     * @param rectF outer boundary of every verify code
     * @param textRectF boundary of verify code text
     */
    void drawWrapper(Canvas canvas, Paint paint, RectF rectF, RectF textRectF);
}