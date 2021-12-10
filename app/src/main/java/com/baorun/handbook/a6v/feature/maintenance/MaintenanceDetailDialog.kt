package com.baorun.handbook.a6v.feature.maintenance

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ScreenUtils
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.databinding.DialogMaintenaceBinding

class MaintenanceDetailDialog:DialogFragment() {

    private lateinit var viewBinding: DialogMaintenaceBinding

    private val mListAdapter1: MaintenanceListAdapter by lazy {
        MaintenanceListAdapter()
    }
    private val mListAdapter2: MaintenanceListAdapter by lazy {
        MaintenanceListAdapter()
    }

    private val mViewModel: MaintenanceViewModel by viewModels()

    private val id:String by lazy {
        requireArguments().getString(Constant.KEY_ID,"")
    }

    private var listener:OnDismissListener? = null

    override fun onStart() {
        super.onStart()
        setDialogStyle()
    }


    private fun setDialogStyle() {
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.let {

            it.setGravity(Gravity.CENTER)
            val lp = it.attributes
            lp.width = ScreenUtils.getAppScreenWidth()
            it.attributes = lp
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = DialogMaintenaceBinding.inflate(inflater,container,false)
        return viewBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getData(id)

        mViewModel.maintenanceLiveData.observe(this){
            initView(it)
        }
    }

    private fun initView(data: ChildrenData) {
        with(viewBinding){
            titleTv.text = data.name
            subTitleTv1.text = data.children.first().name

            initSubTitle(data)

            initRecyclerView(data)

            closeIv.setOnClickListener {
                listener?.onDismiss()
                dismiss()
            }
        }

    }

    private fun DialogMaintenaceBinding.initSubTitle(data: ChildrenData){
        val size = data.children.size

        //只有一个
        if(size==1){
            subTitleTv1.text = data.children.first().name
            subTitleTv2Layout.visibility = View.GONE
        }else{
            subTitleTv2Layout.visibility = View.VISIBLE
            subTitleTv2Layout.background.alpha = 100
            subTitleTv2.text = data.children[1].name
            timeIv2.setImageResource(R.drawable.time_unlive)


            subTitleTv1Layout.setOnClickListener {
                timeIv1.setImageResource(R.drawable.time)
                timeIv2.setImageResource(R.drawable.time_unlive)
                subTitleTv1Layout.background.alpha = 255
                subTitleTv2Layout.background.alpha = 100
                mListAdapter1.setList(data.children.first().data1)
                mListAdapter2.setList(data.children.first().data2)

            }
            subTitleTv2Layout.setOnClickListener {
                timeIv1.setImageResource(R.drawable.time_unlive)
                timeIv2.setImageResource(R.drawable.time)
                subTitleTv2Layout.background.alpha = 255
                subTitleTv1Layout.background.alpha = 100
                mListAdapter1.setList(data.children[1].data1)
                mListAdapter2.setList(data.children[1].data2)

            }

        }

    }

    private fun DialogMaintenaceBinding.initRecyclerView(data: ChildrenData) {
        dataLayout1List.layoutManager = LinearLayoutManager(requireActivity())
        dataLayout2List.layoutManager = LinearLayoutManager(requireActivity())

        mListAdapter1.setList(data.children.first().data1)
        mListAdapter2.setList(data.children.first().data2)
        dataLayout1List.adapter = mListAdapter1
        dataLayout2List.adapter = mListAdapter2
    }


    fun interface OnDismissListener{
        fun onDismiss()
    }
    companion object{
        fun newInstance(id:String,callback:OnDismissListener): MaintenanceDetailDialog {
            return MaintenanceDetailDialog().apply {
                listener = callback
                arguments = Bundle().apply {
                    putString(Constant.KEY_ID,id)
                }
            }
        }
    }
}