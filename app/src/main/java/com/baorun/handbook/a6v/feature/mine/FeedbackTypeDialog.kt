package com.baorun.handbook.a6v.feature.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.DialogFeedbackBinding


class FeedbackTypeDialog:DialogFragment() {


    private lateinit var viewBinding: DialogFeedbackBinding


    var callback: OnTypeCheckedCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DialogFeedbackBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
           val content =  when(checkedId){
                R.id.rb1->getString(R.string.mine_feedback_type_car)
                R.id.rb2->getString(R.string.mine_feedback_type_sevice)
                R.id.rb3->getString(R.string.mine_feedback_type_book)
                R.id.rb4->getString(R.string.mine_feedback_type_advice)
               else->""
            }
            callback?.onTypeChecked(content)
            dismissAllowingStateLoss()

        }
    }

   fun interface OnTypeCheckedCallback{
        fun onTypeChecked(content:String)
    }


    companion object{
        fun show(fm:FragmentManager,callback: OnTypeCheckedCallback){
            val dialog =  FeedbackTypeDialog()
            dialog.callback = callback
            dialog.show(fm,"FeedbackTypeDialog")
        }
    }

}