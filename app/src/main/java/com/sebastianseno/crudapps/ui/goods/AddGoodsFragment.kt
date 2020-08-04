package com.sebastianseno.crudapps.ui.goods

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.sebastianseno.crudapps.R
import com.sebastianseno.crudapps.common.UiState
import com.sebastianseno.crudapps.extensions.handleImageChooserResult
import com.sebastianseno.crudapps.extensions.loadUri
import com.sebastianseno.crudapps.extensions.openImageChooser
import com.sebastianseno.crudapps.inject.BaseFragment
import com.sebastianseno.crudapps.inject.getViewModel
import com.sebastianseno.crudapps.inject.observe
import kotlinx.android.synthetic.main.fragment_add_goods.*

class AddGoodsFragment : BaseFragment() {

    private val viewModel by getViewModel<GoodsViewModel>()

    private var selectedPhotoUri: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSave.setOnClickListener {
            viewModel.inpuitProducts(
                inputMerek.text.toString(),
                inputHarga.text.toString().toIntOrNull() ?: 0,
                inputStok.text.toString().toIntOrNull() ?: 0,
                inputDiskripsi.text.toString(),
                selectedPhotoUri
            )
        }

        deleteButton.setOnClickListener {
            selectedPhotoUri = null
            imagePreviewContainer.isVisible = false
            buttonTakeImage.isVisible = true
        }

        retakeButton.setOnClickListener {
            openImageChooser(this, "Pilih Foto Product")

        }
        buttonTakeImage.setOnClickListener {
          openImageChooser(this, "Pilih Foto Product")
        }

        observe(viewModel.state) {
            progressBar.isVisible = it == UiState.Loading
            buttonSave.isVisible = it != UiState.Loading
            when (it) {
                UiState.Success -> findNavController().popBackStack().also {
                    Toast.makeText(requireContext(), "Barang Berhasil di tambahkan", Toast.LENGTH_SHORT).show()
                }
                is UiState.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        handleImageChooserResult(requestCode, resultCode, data) { uri, error ->
            imagePreviewContainer.isVisible = uri != null
            buttonTakeImage.isVisible = uri == null
            selectedPhotoUri = uri?.path
            imageThumbnail.loadUri(selectedPhotoUri)
        }

    }
}