package fun.android.textcarousel.fun;

import android.annotation.SuppressLint;
import android.content.Context;

public class Fun {
    // 获取状态栏高度的方法
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        @SuppressLint("InternalInsetResource")
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
