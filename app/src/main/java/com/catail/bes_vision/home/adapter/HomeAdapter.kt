package com.catail.bes_vision.home.adapter

import android.view.ViewGroup
import com.catail.bes_vision.R
import com.catail.bes_vision.home.bean.HomeDataBean
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_DFMA
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_HCS
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_QUALITY
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_RESOURCES
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_RFI_RFA
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_SAFETY
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_STATISTICS
import com.catail.bes_vision.home.bean.HomeDataBean.Companion.ITEM_TYPE_TOWERCRANE
import com.catail.bes_vision.view.HorizontalChartView
import com.catail.bes_vision.view.ResourcesHorizontalChartView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarEntry
import java.util.ArrayList

class HomeAdapter(data: List<HomeDataBean?>?) :
    BaseMultiItemQuickAdapter<HomeDataBean?, BaseViewHolder?>(data) {
    private var themeStyleFlag = 0;

    init {
        addItemType(ITEM_TYPE_RESOURCES, R.layout.adapter_home_item_resources) //RESOURCES
        addItemType(ITEM_TYPE_SAFETY, R.layout.adapter_home_item_safety) //SAFETY
        addItemType(ITEM_TYPE_STATISTICS, R.layout.adapter_home_item_statistics) //STATISTICS
        addItemType(ITEM_TYPE_HCS, R.layout.adapter_home_item_hcs) //HCS
        addItemType(ITEM_TYPE_DFMA, R.layout.adapter_home_item_dfma) //DFMA
        addItemType(ITEM_TYPE_RFI_RFA, R.layout.adapter_home_item_rfi_rfa) //RFI_RFA
        addItemType(ITEM_TYPE_QUALITY, R.layout.adapter_home_item_quality) //QUALITY
        addItemType(ITEM_TYPE_TOWERCRANE, R.layout.adapter_home_item_towercrane) //TOWERCRANE
    }

    override fun convert(helper: BaseViewHolder?, item: HomeDataBean?) {
        when (helper?.itemViewType) {
            ITEM_TYPE_RESOURCES -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function1)
                )
                    .addOnClickListener(R.id.tv_more)

                val arrayList = ArrayList<String>();
                for (i in 1..3){
                    arrayList.add("测试"+i);
                }

                val xStringList = ArrayList<String>()
                val yStringLists = ArrayList<BarEntry>()
                for (i in arrayList.indices) {
                    xStringList.add(arrayList.get(i))
                    val barEntry = BarEntry(
                        (i.toString() + "").toFloat(),
                        5f
                    )
                    yStringLists.add(barEntry)
                }

                val barchart = helper.getView<HorizontalBarChart>(R.id.barchart);
                val horizontalChartView = ResourcesHorizontalChartView(mContext, barchart)

                val layoutParams: ViewGroup.LayoutParams = barchart.getLayoutParams() //取控件chart当前的布局参数
                layoutParams.height = xStringList.size * 150 // 控件的高强制设成 数量*200
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT // 控件的宽强制设
                barchart.setLayoutParams(layoutParams) //使设置好的布局参数应用到控件</pre>


                horizontalChartView.setHistogramData(xStringList, yStringLists, arrayList.size)
                barchart.isHighlightFullBarEnabled=false

            }
            ITEM_TYPE_SAFETY -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function2)
                )
                    .addOnClickListener(R.id.tv_more)
            }
            ITEM_TYPE_STATISTICS -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function3)
                )
                    .addOnClickListener(R.id.tv_more)
            }
            ITEM_TYPE_HCS -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function4)
                )
                    .addOnClickListener(R.id.tv_more)

            }
            ITEM_TYPE_DFMA -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function5)
                )
                    .addOnClickListener(R.id.tv_more)
            }
            ITEM_TYPE_RFI_RFA -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function6)
                )
                    .addOnClickListener(R.id.tv_more)
            }
            ITEM_TYPE_QUALITY -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function7)
                )
                    .addOnClickListener(R.id.tv_more)
            }
            ITEM_TYPE_TOWERCRANE -> {
                helper.setText(
                    R.id.tv_title_name,
                    mContext.resources.getString(R.string.preject_function8)
                )
                    .addOnClickListener(R.id.tv_more)
            }
        }
    }

    fun setThemeStyleFlag(themeStyleFlag: Int) {
        this.themeStyleFlag = themeStyleFlag
    }
}