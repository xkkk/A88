package com.baorun.handbook.a6v.feature.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baorun.handbook.a6v.BuildConfig
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.FragmentAboutBinding

/**
 * 功能：
 * 描述：关于
 * Created by xukun on 2021/8/15.
 */
class AboutFragment:Fragment() {

    private lateinit var viewBinding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentAboutBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.idAppVersion.text = getString(R.string.mine_about_app_version, BuildConfig.VERSION_NAME)
        viewBinding.idDataVersion.text = getString(R.string.mine_about_data_version,BuildConfig.VERSION_NAME)
    }
}