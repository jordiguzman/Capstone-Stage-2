package appkite.jordiguzman.com.astronomyapp.planets.ui;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import appkite.jordiguzman.com.astronomyapp.R;
import appkite.jordiguzman.com.astronomyapp.apod.ui.utils.DynamicHeightNetworkImageView;
import appkite.jordiguzman.com.astronomyapp.apod.ui.utils.ImageLoaderHelper;

import static appkite.jordiguzman.com.astronomyapp.planets.data.Urls.BASE_URL_EXTRACT;
import static appkite.jordiguzman.com.astronomyapp.planets.data.Urls.PLANETS;
import static appkite.jordiguzman.com.astronomyapp.planets.data.Urls.PLANETS_API;
import static appkite.jordiguzman.com.astronomyapp.planets.data.Urls.URL_PLANETS;
import static appkite.jordiguzman.com.astronomyapp.planets.ui.SolarSystemActivity.wikiPlanetsText;

public class SolarSystemDetailFragment extends Fragment{

    private Context mContext;
    private FrameLayout mFrameLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        assert container != null;
        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_solar_system_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPager mViewPager = view.findViewById(R.id.pager_solar_system);
        mViewPager.setAdapter(new SolarSystemAdapter());
        mViewPager.setCurrentItem(SolarSystemActivity.itemPositionSolar);


    }



    class SolarSystemAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return PLANETS.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return object == view;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.pager_item_solar_system, container, false);
            container.addView(view);
            TextView tv_title_solar_system_item = view.findViewById(R.id.tv_title_pager_solar_system_item);
            Typeface typeface = ResourcesCompat.getFont(mContext, R.font.alfa_slab_one);
            tv_title_solar_system_item.setTypeface(typeface);
            tv_title_solar_system_item.setText(PLANETS[position]);

            TextView tv_explanation_solar_system_item = view.findViewById(R.id.tv_explanation_pager_solar_system_item);
            if (wikiPlanetsText != null){
                tv_explanation_solar_system_item.setText(wikiPlanetsText.get(position));
            }else {
                snackBar();
                wikiApiText(BASE_URL_EXTRACT);
            }


            DynamicHeightNetworkImageView photo_solar_system_detail = view.findViewById(R.id.photo_solar_system_detail);
            photo_solar_system_detail.setImageUrl(URL_PLANETS[position],
                    ImageLoaderHelper.getInstance(mContext).getImageLoader());

            return view;
        }

        private void snackBar() {
            Snackbar snackbar = Snackbar
                    .make(mFrameLayout, "Loading...", 4000)
                    .setActionTextColor(Color.RED);

            snackbar.show();
        }

        private void wikiApiText(String type){
            for (String aTITLE : PLANETS_API) {
                new SolarSystemActivity.HttpAsyncTaskText().execute(type + aTITLE);
            }
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }



}
