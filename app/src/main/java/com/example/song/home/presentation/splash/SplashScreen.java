package com.example.song.home.presentation.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.song.databinding.FragmentHomeBinding;
import com.example.song.databinding.FragmentSplashBinding;
import com.example.song.home.presentation.Home.mvvm.HomeMvvm;
import com.example.song.home.presentation.splash.mvvm.SplashMvvm;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashScreen extends Fragment {

    private FragmentSplashBinding binding;

    SplashMvvm splashMvvm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout and return the View.
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        splashMvvm = new ViewModelProvider(this).get(SplashMvvm.class);
        splashMvvm.getModeLiveData().observe(
                getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean == false) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }
                    }
                }
        );

//        binding.slogan.setOnClickListener(
//                v -> Navigation.findNavController(view).navigate(SplashScreenDirections.actionSplashScreenToHomeScreen())
//
//        );

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if ( getView() != null) {
                            Navigation.findNavController(requireView())
                                    .navigate(SplashScreenDirections.actionSplashScreenToHomeScreen());
                        }                    }
                },
                1000
        );
    }

}

