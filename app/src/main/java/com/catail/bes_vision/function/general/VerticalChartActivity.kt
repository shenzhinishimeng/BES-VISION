package com.catail.bes_vision.function.general

import android.view.ViewGroup
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.view.VerticalChartView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_vertical_chart.*
import java.util.*


internal class VerticalChartActivity : BaseActivity(), OnChartValueSelectedListener {
    //    private var chart: HorizontalBarChart? = null
    private var verticalChartView: VerticalChartView? = null
    override fun layoutResId(): Int {
        return R.layout.activity_vertical_chart
    }

    override fun initView() {
//        chart = findViewById(R.id.chart)
        verticalChartView = VerticalChartView(this@VerticalChartActivity, chart)
        chart.setOnChartValueSelectedListener(this)

    }

    override fun initData() {
        val stringList: MutableList<String> = ArrayList()
        for (i in 0..12) {
            stringList.add("CMS technology$i")
        }
        val linearParams = chart.getLayoutParams() //取控件chart当前的布局参数

        linearParams.height = ViewGroup.LayoutParams.MATCH_PARENT // 控件的高强制设成
        linearParams.width = stringList.size * 200 // 控件的宽强制设 数量*200


        chart.setLayoutParams(linearParams) //使设置好的布局参数应用到控件</pre>


        verticalChartView?.setHistogramData(stringList, 10)
    }


    override fun onValueSelected(e: Entry, h: Highlight) {}
    override fun onNothingSelected() {}

}