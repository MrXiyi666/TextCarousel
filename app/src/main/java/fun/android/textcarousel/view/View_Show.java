package fun.android.textcarousel.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import fun.android.textcarousel.R;
import fun.android.textcarousel.system.Static;

public class View_Show extends View_Main{
    private Context context;
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
                    StringBuilder sb = new StringBuilder();
                    for (char c : Static.text_list.get(文本编号).toCharArray()) {
                        sb.append(c).append("\n");
                    }
                    text_view.setText(sb.toString());
                    setAlpha255(text_view, alpha);
                }
            }

            if(流程 == 1){
                if(计时 < 500){
                    计时 = 计时 + 1;
                }else{
                    /*
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

                     */
                    // 1. 创建粒子系统（爆炸效果）
                    new ParticleSystem((Activity) context, 100, R.drawable.ic_particle, 800)
                            .setSpeedRange(0.2f, 0.5f) // 粒子速度范围
                            .setScaleRange(0.5f, 1.5f) // 粒子缩放范围
                            .setRotationSpeedRange(0, 180) // 旋转角度范围
                            .setFadeOut(10) // 淡出时间
                            .oneShot(text_view, 300); // 对目标View执行一次爆炸，100个粒子
                    alpha=0;
                    计时=0;
                    text_view.setAlpha(alpha);
                    if(文本编号 < Static.text_list.size()-1){
                        文本编号 = 文本编号 + 1;
                        流程=0;
                    }else{
                        text_view.setText("");
                        关闭 = true;
                        handler.removeCallbacks(this);
                    }

                }
            }
            if(!关闭){
                handler.postDelayed(this, 10);
            }
        }
    };
    public View_Show(Context context){
        this.context = context;
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
