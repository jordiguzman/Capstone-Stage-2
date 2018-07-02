package appkite.jordiguzman.com.astronomyapp.hubble.ui;


import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import appkite.jordiguzman.com.astronomyapp.R;
import appkite.jordiguzman.com.astronomyapp.hubble.data.HubbleContract;
import appkite.jordiguzman.com.astronomyapp.mainUi.utils.DynamicHeightNetworkImageView;
import appkite.jordiguzman.com.astronomyapp.mainUi.utils.ImageLoaderHelper;

import static appkite.jordiguzman.com.astronomyapp.hubble.ui.FavoritesHubbleActivity.dataLoadedHubble;
import static appkite.jordiguzman.com.astronomyapp.hubble.ui.FavoritesHubbleActivity.hubbleArrayList;
import static appkite.jordiguzman.com.astronomyapp.hubble.ui.FavoritesHubbleActivity.itemPositionFavoritesHubble;

public class FavoritesHubbleDetailFragment extends Fragment implements View.OnClickListener {

    private Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_hubble_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPager mViewPager = view.findViewById(R.id.pager_hubble);
        mViewPager.setAdapter(new FavoritesHubblePageAdapter());
        mViewPager.setCurrentItem(FavoritesHubbleActivity.itemPositionFavoritesHubble);
        FloatingActionButton mFloatingActionButton = view.findViewById(R.id.fb_favorites_hubble);
        mFloatingActionButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_delete_black_24dp));
        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri uri = HubbleContract.HubbleEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(dataLoadedHubble[itemPositionFavoritesHubble][5]).build();
        contentResolver.delete(uri, null, null);
        snackBarDelete();
    }

    private void snackBarDelete() {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.card_fragment_hubble), R.string.data_deleted, Snackbar.LENGTH_LONG );
        View snackbarView = snackbar.getView();
        int snackbarTextId = android.support.design.R.id.snackbar_text;
        TextView textView = snackbarView.findViewById(snackbarTextId);
        textView.setTextColor(ContextCompat.getColor(mContext,  R.color.colorAccent));
        snackbar.show();
    }

    class FavoritesHubblePageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return hubbleArrayList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return object == view;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.pager_item_hubble, container, false);
            container.addView(view);
            boolean isFavorited = true;

            TextView tv_title_pager_hubble_item = view.findViewById(R.id.tv_title_pager_hubble_item);
            Typeface typeface = ResourcesCompat.getFont(mContext, R.font.alfa_slab_one);
            tv_title_pager_hubble_item.setTypeface(typeface);
            tv_title_pager_hubble_item.setText(dataLoadedHubble[position][0]);

            TextView tv_description_hubble_item = view.findViewById(R.id.tv_description_hubble_item);
            tv_description_hubble_item.setText(dataLoadedHubble[position][1]);

            TextView tv_creditt_hubble_item = view.findViewById(R.id.tv_creditt_hubble_item);
            tv_creditt_hubble_item.setText(dataLoadedHubble[position][2]);

            DynamicHeightNetworkImageView photo_hubble_detail = view.findViewById(R.id.photo_hubble_detail);
            photo_hubble_detail.setImageUrl(dataLoadedHubble[position][3],
                    ImageLoaderHelper.getInstance(mContext).getImageLoader());

            return view;

        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
