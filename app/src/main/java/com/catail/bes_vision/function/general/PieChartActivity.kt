package com.catail.bes_vision.function.general

import android.view.View
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.view.PieChartView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_pie_chart.*
import kotlinx.android.synthetic.main.title_bar.*
import java.util.*
import kotlin.collections.ArrayList

class PieChartActivity : BaseActivity(), View.OnClickListener, OnChartValueSelectedListener {

    var pieChartView1: PieChartView? = null
    override fun layoutResId(): Int {
        return R.layout.activity_pie_chart
    }

    override fun initView() {
        title_bar_left_menu.setOnClickListener(this)
        title_bar_left_menu.visibility = View.VISIBLE


        initChart()
    }

    override fun initData() {
        val stringList: ArrayList<Int> = ArrayList()
        for (i in 0..4) {
            stringList.add((i + 1) * 8)
        }
        //设置 统计图
        if (stringList.size > 5) {
            pieChartView1?.setData( this@PieChartActivity, 5, 100f, stringList)
//            setData(5, 100f, stringList)
        } else {
            pieChartView1?.setData( this@PieChartActivity, stringList.size, 100f, stringList)
//            setData(stringList.size, 100f, stringList)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.title_bar_left_menu -> this@PieChartActivity.finish()
        }
    }


    private fun initChart() {
        pieChartView1 = PieChartView(this@PieChartActivity, chart)
//        pieChartUtils1.setData(this@PieChartActivity,);

//        PieChartUtils1()
//        PieChartUtils.initPieChart(chart)
//        chart.setOnChartValueSelectedListener(this)


    }


    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun onNothingSelected() {

    }


}