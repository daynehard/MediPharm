package com.example.medipharm.Activity;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.util.List;

public interface ImagesSelectedListener {

    void images(List<Bitmap> bitmaps, @Nullable List<Uri> uris, int selectedImagesCount);
}
