package com.example.fashionboomer;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

        private ToastUtil() {
            throw new UnsupportedOperationException("u can't instantiate me...");
        }

        private static Context context;
        // Toast对象
        private static Toast toast;
        // 文字显示的颜色 <color name="white">#FFFFFFFF</color>
        private static int messageColor = R.color.white;

        public static void cancel() {
            if (toast != null) {
                toast.cancel();
                toast = null;
            }
        }
}
