package com.bharat.forsale.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bharat.forsale.R;
import com.bharat.forsale.adapter.SelectionPagerAdapter;
import com.bharat.forsale.databinding.ActivitySearchBinding;
import com.bharat.forsale.ui.fragment.PostFragment;
import com.bharat.forsale.ui.fragment.SearchFragment;
import com.bharat.forsale.ui.fragment.WatchlistFragment;
import com.bharat.forsale.viewModels.FirebaseViewModel;
import com.bharat.forsale.viewModels.FirebaseViewModelFactory;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private ActivitySearchBinding binding;
    private SelectionPagerAdapter selectionPagerAdapter;
    private FirebaseViewModel firebaseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        setupViewPagerAdapter();
        FirebaseViewModelFactory factory = new FirebaseViewModelFactory(getApplication());
        firebaseViewModel = new ViewModelProvider(this,factory).get(FirebaseViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.signOutMenu : firebaseViewModel.signOut();
                 startActivity(new Intent(this,MainActivity.class));
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPagerAdapter(){
        selectionPagerAdapter = new SelectionPagerAdapter(getSupportFragmentManager(),getLifecycle());
        selectionPagerAdapter.addFragment(new SearchFragment());
        selectionPagerAdapter.addFragment(new WatchlistFragment());
        selectionPagerAdapter.addFragment(new PostFragment());
        Log.d(TAG, "setupViewPagerAdapter: "+ selectionPagerAdapter.getItemCount());
        binding.viewPagerContainer.setAdapter(selectionPagerAdapter);
        List<String> tabList = new ArrayList();
        tabList.add(getString(R.string.search_fragment));
        tabList.add(getString(R.string.watchlist_fragment));
        tabList.add(getString(R.string.post_fragment));
        new TabLayoutMediator(binding.tabLayout, binding.viewPagerContainer, (tab, position) -> {
            Log.d(TAG, "setupViewPagerAdapter: "+ position);
            tab.setText(tabList.get(position));
//            tab.setCustomView(selectionPagerAdapter.createFragment(position).getView());
        }).attach();
    }
}