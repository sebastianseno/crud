package com.sebastianseno.crudapps.extensions

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import coil.Coil
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequestBuilder
import coil.request.RequestDisposable
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

fun ImageView.loadImage(
    url: String?,
    imageLoader: ImageLoader = Coil.loader(),
    builder: LoadRequestBuilder.() -> Unit = {}
): RequestDisposable {
    return imageLoader.load(context, url) {
        target(this@loadImage)
        builder()
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.visibleIf(condition: Boolean, invisible: Boolean = false) {
    val hide = if (invisible) View.INVISIBLE else View.GONE
    this.visibility = if (condition) View.VISIBLE else hide
}


fun openImageChooser(fragment: Fragment, title: String) {
    CropImage.activity()
        .setActivityTitle(title)
        .setAllowFlipping(false)
        .setCropMenuCropButtonTitle("Selesai")
        .setGuidelines(CropImageView.Guidelines.ON)
        .start(fragment.requireContext(), fragment)
}


inline fun handleImageChooserResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?,
    onResult: (Uri?, error: String?) -> Unit
) {
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
        val result = CropImage.getActivityResult(data)
        if (resultCode == Activity.RESULT_OK) {
            onResult(result.uri, null)
        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
            onResult(null, result.error.toString())
        }
    }
}

fun ImageView.loadUri(
    uri: String?
) {
    if (uri != null) {
        val photo : Bitmap = BitmapFactory.decodeFile(uri)
        this.setImageBitmap(photo)
    }
}
