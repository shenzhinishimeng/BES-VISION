package com.catail.bes_vision.view

import android.content.Context
import android.util.AttributeSet
import com.catail.bes_vision.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_horizontal_chart.*
import java.util.*

class HorizontalChartView : HorizontalBarChart {
    private var mChart: BarChart? = null

    constructor(context: Context?, chart: BarChart) : super(context) {
        initPieChart(chart);
    }

    constructor(context: Context?, attrs: AttributeSet?, chart: BarChart) : super(context, attrs) {
        initPieChart(chart);
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int, chart: BarChart) : super(
        context,
        attrs,
        defStyle
    ) {
        initPieChart(chart);
    }

    fun initPieChart(chart: BarChart) {
        this.mChart = chart;
        // chart.setHighlightEnabled(false);
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)
        chart.getDescription().isEnabled = false
        chart.getLegend().isEnabled = false

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60)

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false)

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);
        chart.setDrawGridBackground(false)
        val xl = chart.getXAxis()
        xl.position = XAxis.XAxisPosition.BOTTOM
        //        xl.setTypeface(tfLight);
        xl.setDrawAxisLine(true)
        xl.setDrawGridLines(false)
        xl.granularity = 10f
        val yl = chart.getAxisLeft()
        //        yl.setTypeface(tfLight);
        yl.setDrawAxisLine(true)
        yl.setDrawGridLines(true)
        yl.axisMinimum = 0f // this replaces setStartAtZero(true)
        //        yl.setInverted(true);
        val yr = chart.getAxisRight()
        //        yr.setTypeface(tfLight);
        yr.setDrawAxisLine(true)
        yr.setDrawGridLines(false)
        yr.axisMinimum = 0f // this replaces setStartAtZero(true)
        //        yr.setInverted(true);
        chart.setFitBars(true)
        chart.animateY(2500)
        val l = chart.getLegend()
        l.verticalAlignment =
            Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment =
            Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.formSize = 8f
        l.xEntrySpace = 4f
    }


    private fun setData(ylistStr: List<BarEntry>) {
//        List<BarEntry> entryList = new ArrayList<>();
//        entryList.add(new BarEntry(0, 60.51f));
//        entryList.add(new BarEntry(1, 26.28f));
//        entryList.add(new BarEntry(2, 13.20f));
        val barDataSet = BarDataSet(ylistStr, "")

//        barDataSet.setColors(Color.GREEN, Color.BLUE, Color.RED);
        if (ylistStr.size > 5) {
            val colors: MutableList<Int> = ArrayList()
            for (i in ColorTemplate.VORDIPLOM_COLORS.indices) {
                colors.add(ColorTemplate.VORDIPLOM_COLORS[i])
            }
            val therestOfColors = ylistStr.size - 5
            for (i in 0 until therestOfColors) {
                colors.add(R.color.gray_background_D7D7D7)
            }
            barDataSet.colors = colors
        } else {
            barDataSet.setColors(*ColorTemplate.VORDIPLOM_COLORS)
        }
        barDataSet.valueTextColor = R.color.blue_textcolor_39bcca
        barDataSet.valueTextSize = 15f
        barDataSet.valueFormatter =
            IValueFormatter { v, entry, i, viewPortHandler -> //                return v + "%";
                (return@IValueFormatter v.toString())
            }
        val barData = BarData(barDataSet)
        barData.barWidth = 0.4f // 设置柱子的宽度
        mChart!!.data = barData
    }

    /**
     * 因为此处的柱状图为水平柱状图，所以x轴变y轴，y轴变x轴
     *
     * @param xlists
     * @param all_count
     */
    private fun setAxis(xlists: List<String>, all_count: Int) {
        val xAxis = mChart!!.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 13f
        xAxis.labelCount = xlists.size
        xAxis.granularity = 1f // 防止放大图后，标签错乱
        //        final String label[] = {"西瓜", "蓝莓", "草莓"};
        xAxis.valueFormatter = IAxisValueFormatter { v, axisBase ->
            try {
                return@IAxisValueFormatter xlists[v.toInt()]
                //                    return label[(int) v];
            } catch (e: Exception) {
                return@IAxisValueFormatter ""
            }
        }
        val yAxis_right = mChart!!.axisRight
        yAxis_right.axisMinimum = 0f
        //        yAxis_right.setAxisMaximum(100f);
        yAxis_right.axisMaximum = all_count.toFloat()
        yAxis_right.textSize = 13f
        yAxis_right.valueFormatter =
            IAxisValueFormatter { v, axisBase -> //                return v + "0%";
                (return@IAxisValueFormatter v.toString())
            }

        // 不显示最顶部的轴
        val yAxis_left = mChart!!.axisLeft
        yAxis_left.axisMinimum = 0f
        //        yAxis_left.setAxisMaximum(100f);
        yAxis_left.axisMaximum = all_count.toFloat()
        yAxis_left.isEnabled = false
    }


    fun setHistogramData(stringList: List<String>, Allcount: Int) {
        val Xlists: MutableList<String> = ArrayList()
        val YlistStr: MutableList<BarEntry> = ArrayList()
        for (i in stringList.indices) {
//            Xlists.add(result.getLevels().get(i).getType_1() + " - " +
//                    result.getLevels().get(i).getType_2() + " - " +
//                    result.getLevels().get(i).getType_3());
            Xlists.add(stringList[i])

//            YlistStr.add(new BarEntry(i, (float) (Integer.parseInt(result.getLevels().get(i).getCount()) * 100
//                    / result.getAll_count())));

            YlistStr.add(BarEntry(i.toFloat(), 5.0f))
        }
        setAxis(Xlists, Allcount)
        setData(YlistStr)
    }
}