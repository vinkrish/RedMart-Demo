package com.redmart.app.productdetails;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redmart.app.R;
import com.redmart.app.model.DescriptionField;
import com.redmart.app.model.DescriptionFields;
import com.redmart.app.model.Image;
import com.redmart.app.model.Product;
import com.redmart.app.util.DividerItemDecoration;
import com.redmart.app.util.NetworkUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsView{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.product_title) TextView productTitleTV;
    @BindView(R.id.measure) TextView measureTV;
    @BindView(R.id.price) TextView priceTV;
    @BindView(R.id.img_viewpager) ViewPager imageViewPager;
    @BindView(R.id.layoutDots) LinearLayout dotsLayout;
    @BindView(R.id.desc_fields) RecyclerView descRecyclerView;

    private ProductDetailsPresenter presenter;
    ImagePagerAdapter imagePagerAdapter;
    private DescriptionFieldsAdapter descriptionAdapter;
    private int id;
    private String title;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            title = extras.getString("title");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        presenter = new ProductDetailsPresenterImpl(this, new ProductDetailsInteractorImpl());

        setupDescriptionRecyclerView();
    }

    private void setupDescriptionRecyclerView() {
        descRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        descRecyclerView.setNestedScrollingEnabled(false);
        descRecyclerView.setItemAnimator(new DefaultItemAnimator());
        descRecyclerView.addItemDecoration(new DividerItemDecoration(this));

        descriptionAdapter = new DescriptionFieldsAdapter();
        descRecyclerView.setAdapter(descriptionAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setProduct(Product product) {
        productTitleTV.setText(product.getTitle());
        measureTV.setText(product.getMeasure().getWtOrVol());
        priceTV.setText(String.format(Locale.ENGLISH, "$%.2f", product.getPricing().getPrice()));
        loadProductImages(product.getImages());
        extractDetails(product.getDescriptionFields());
    }

    private void loadProductImages(ArrayList<Image> images) {
        List<String> imgList = new ArrayList<>();
        for(Image img: images) {
            imgList.add(img.getName());
            Log.d("ImageName", ": " + img.getName());
        }
        imagePagerAdapter = new ImagePagerAdapter(imgList);
        imageViewPager.setAdapter(imagePagerAdapter);
        imageViewPager.addOnPageChangeListener(viewPagerListener);
        addBottomDots(0);
    }

    private void extractDetails(DescriptionFields descriptionFields) {
        List<DescriptionField> descriptionFieldList = new ArrayList<>();
        for(DescriptionField descriptionField: descriptionFields.getPrimary()) {
            descriptionFieldList.add(new DescriptionField(descriptionField.getName(), descriptionField.getContent()));
        }

        for(DescriptionField descriptionField: descriptionFields.getSecondary()) {
            descriptionFieldList.add(new DescriptionField(descriptionField.getName(), descriptionField.getContent()));
        }
        descriptionAdapter.setItems(descriptionFieldList);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtil.isNetworkAvailable(this)) {
            presenter.getProduct(id);
        } else {
            //Network Not Available
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public class ImagePagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private List<String> imgList;

        ImagePagerAdapter(List<String> imgList) {
            this.imgList = imgList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.image_pager_item, container, false);

            ImageView img = view.findViewById(R.id.img);
            Picasso.with(ProductDetailsActivity.this)
                    .load("http://media.redmart.com/newmedia/200p" + imgList.get(position))
                    .into(img);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return imgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void addBottomDots(int currentPage) {
        dots = new TextView[imagePagerAdapter.getCount()];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.divider_light));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.colorPrimary));
    }

}
