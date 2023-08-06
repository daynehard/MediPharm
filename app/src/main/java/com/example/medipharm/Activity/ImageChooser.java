package com.example.medipharm.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageChooser implements DefaultLifecycleObserver {

    private final ActivityResultRegistry mRegistry;
    private final Context context;
    private ActivityResultLauncher<String> chooseImage;
    private ActivityResultLauncher<Void> takeImage;
    private ImagesSelectedListener imagesSelectedListener;

    ImageChooser(Context context, @NonNull ActivityResultRegistry registry) {
        this.mRegistry = registry;
        this.context = context;
    }

    public void onCreate(@NonNull LifecycleOwner owner) {
        chooseImage = mRegistry.register("chooseImage", owner, new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
            @Override
            public void onActivityResult(List<Uri> uri) {

                final List<Bitmap> bitmaps = new ArrayList<>();

                // convert uri to bitmaps
                try {
                    for (int i = 0; i < uri.size(); i++) {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri.get(i));
                        bitmaps.add(bitmap);
                    }
                    imagesSelectedListener.images(bitmaps, uri, uri.size());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        takeImage = mRegistry.register("takeImage", owner, new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap bitmap) {
                final List<Bitmap> bitmaps = new ArrayList<>();
                bitmaps.add(bitmap);

                imagesSelectedListener.images(bitmaps, null, 1);
            }
        });
    }

    public void selectImages(ImagesSelectedListener imagesSelectedListener) {
        this.imagesSelectedListener = imagesSelectedListener;
        chooseImage.launch("image/*");
    }

    public void takeImage(ImagesSelectedListener imagesSelectedListener) {
        this.imagesSelectedListener = imagesSelectedListener;
        takeImage.launch(null);}
}

