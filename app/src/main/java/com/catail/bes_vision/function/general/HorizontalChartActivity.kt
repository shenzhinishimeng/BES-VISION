package com.catail.bes_vision.function.general

import android.view.ViewGroup
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.view.HorizontalChartView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_horizontal_chart.*
import java.util.*


internal class HorizontalChartActivity : BaseActivity(), OnChartValueSelectedListener {
    //    private var chart: HorizontalBarChart? = null
    private var horizontalChartView: HorizontalChartView? = null
    override fun layoutResId(): Int {
        return R.layout.activity_horizontal_chart
    }

    override fun initView() {
//        chart = findViewById(R.id.chart)
        horizontalChartView = HorizontalChartView(this@HorizontalChartActivity, chart);
        chart.setOnChartValueSelectedListener(this)

    }

    override fun initData() {
        val stringList: MutableList<String> = ArrayList()
        for (i in 0..12) {
            stringList.add("CMS technology$i")
        }


        val linearParams = chart.getLayoutParams() //取控件chart当前的布局参数

        linearParams.height = stringList.size * 200 // 控件的高强制设成 数量*200
        linearParams.width = ViewGroup.LayoutParams.MATCH_PARENT // 控件的宽强制设


        chart.setLayoutParams(linearParams) //使设置好的布局参数应用到控件</pre>


        horizontalChartView?.setHistogramData(stringList, 10)
    }


    override fun onValueSelected(e: Entry, h: Highlight) {}
    override fun onNothingSelected() {}

}