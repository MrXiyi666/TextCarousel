package fun.android.textcarousel.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import fun.android.textcarousel.R;
import fun.android.textcarousel.system.Static;

public class View_Show extends View_Main{
    private TextView text_view;
    private int alpha=0;
    private int 计时 = 0;
    private int 流程 = 0;
    private int 文本编号 = 0;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean 关闭 = false;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(流程==0){
                if(计时 < 300){
                    计时 = 计时 + 1;
                }else{
                    alpha = alpha + 3;
                    if (alpha >= 255) {
                        alpha = 255;
                        流程 = 1;
                        计时=0;
                    }
                    text_view.setText(Static.text_list.get(文本编号));
                    setAlpha255(text_view, alpha);
                }
            }

            if(流程 == 1){
                if(计时 < 500){
                    计时 = 计时 + 1;
                }else{
                    alpha = alpha - 3;
                    if (alpha <= 0) {
                        alpha = 0;
                        计时=0;
                        if(文本编号 < Static.text_list.size()-1){
                            文本编号 = 文本编号 + 1;
                            流程=0;
                        }else{
                            text_view.setText("");
                            关闭 = true;
                            handler.removeCallbacks(this);
                        }
                    }
                    setAlpha255(text_view, alpha);
                }
            }
            if(!关闭){
                handler.postDelayed(this, 10);
            }
        }
    };
    public View_Show(Context context){
        view =  LayoutInflater.from(context).inflate(R.layout.view_show, null);
        text_view = view.findViewById(R.id.text_view);
        text_view.setText("");
        text_view.setAlpha(0);
        handler.post(runnable);
    }
    public static void setAlpha255(View view, int alphaInt) {
        alphaInt = Math.max(0, Math.min(alphaInt, 255));
        view.setAlpha(alphaInt / 255f);
    }
}
