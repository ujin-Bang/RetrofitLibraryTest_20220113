package com.neppplus.retrofitlibrarytest_20220113

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofitlibrarytest_20220113.databinding.ActivityEditReviewBinding
import com.neppplus.retrofitlibrarytest_20220113.datas.ProductData
import com.neppplus.retrofitlibrarytest_20220113.utils.GlobalData

class EditReviewActivity : BaseActivity() {

    lateinit var binding: ActivityEditReviewBinding

    lateinit var mPorductData: ProductData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_review)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {



    }

    override fun setValues() {

        mPorductData = intent.getSerializableExtra("product") as ProductData

        binding.txtProductName.text = mPorductData.name
        binding.txtUserNickname.text = GlobalData.loginUser!!.nickname

    }
}