package com.redmart.app.productdetails;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
    @BindView(R.id.root_layout) LinearLayout rootLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        transitionAnimation(savedInstanceState);
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

        if (NetworkUtil.isNetworkAvailable(this)) {
            presenter.getProduct(id);
        } else {
            showSnackbar(getString(R.string.no_network));
        }
    }

    private void transitionAnimation(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            rootLayout.setVisibility(View.INVISIBLE);
            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    private void setupDescriptionRecyclerView() {
        descRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        descRecyclerView.setNestedScrollingEnabled(false);
        descRecyclerView.setItemAnimator(new DefaultItemAnimator());
        descRecyclerView.addItemDecoration(new DividerItemDecoration(this));

        descriptionAdapter = new DescriptionFieldsAdapter();
        descRecyclerView.setAdapter(descriptionAdapter);
    }

    private void circularRevealActivity() {
        int cx = rootLayout.getWidth() / 2;
        int cy = rootLayout.getHeight() / 2;

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, finalRadius);
            circularReveal.setDuration(300);
            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        showSnackbar(getString(R.string.request_error));
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
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
        imagePagerAdapter = new ImagePagerAdapter(this, imgList);
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
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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
        TextView[] dots = new TextView[imagePagerAdapter.getCount()];

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
