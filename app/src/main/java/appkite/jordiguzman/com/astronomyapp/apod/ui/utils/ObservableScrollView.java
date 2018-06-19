package appkite.jordiguzman.com.astronomyapp.apod.ui.utils;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView{
    private Callbacks mCallbacks;

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallbacks != null){
            mCallbacks.onScrollChanged();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int scrollY = getScrollY();
        if (scrollY >0 && mCallbacks != null){
            mCallbacks.onScrollChanged();
        }
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void setCallbacks(Callbacks listener){
        mCallbacks = listener;
    }
    public static interface Callbacks{
        void onScrollChanged();
    }
}