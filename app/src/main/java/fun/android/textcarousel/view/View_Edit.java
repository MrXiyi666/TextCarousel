package fun.android.textcarousel.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import fun.android.textcarousel.R;
import fun.android.textcarousel.system.Static;

public class View_Edit extends View_Main{
    private EditText edit_view;
    private AppCompatButton button_add, button_start;
    private LinearLayout linear_list;
    public View_Edit(Context context){
        view =  LayoutInflater.from(context).inflate(R.layout.view_edit, null);
        linear_list = view.findViewById(R.id.linear_list);
        button_add = view.findViewById(R.id.button_add);
        button_start = view.findViewById(R.id.button_start);
        edit_view = view.findViewById(R.id.edit_view);
        button_add.setOnClickListener(V->{
            String text = edit_view.getText().toString();
            if(text.isEmpty()){
                return;
            }
            getTextView(context, text, linear_list);
            edit_view.setText("");
        });

        button_start.setOnClickListener(V->{

            Static.main.removeAllViews();
            Static.view_main = new View_Show(context);
            Static.main.setGravity(Gravity.CENTER);
            Static.main.addView(Static.view_main.getView());
        });

        Static.text_list.clear();
    }

    public void getTextView(Context context, String text, LinearLayout linear){
        View text_view = LayoutInflater.from(context).inflate(R.layout.text_view, null);
        TextView textView = text_view.findViewById(R.id.text_view);
        textView.setText(text);
        linear_list.addView(text_view);
        Static.text_list.add(text);
        text_view.setOnLongClickListener(v -> {
            linear_list.removeView(text_view);
            Static.text_list.remove(text);
            return true;
        });
    }
}