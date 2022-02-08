package com.catail.bes_vision.view

import android.content.Context
import android.util.AttributeSet
import com.catail.bes_vision.R
import com.catail.bes_vision.utils.Logger
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.PURPLE_COLORS
import java.util.*

class ResourcesHorizontalChartView : HorizontalBarChart {
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
        chart.getLegend().isEnabled = true
        chart.legend.isWordWrapEnabled = true
        chart.setScaleEnabled(false)

        chart.setMaxVisibleValueCount(60)
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)


        val xl = chart.getXAxis()
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.setDrawAxisLine(true)
        xl.setDrawGridLines(false)
        xl.axisLineColor= PURPLE_COLORS[0]
        xl.granularity = 10f

        val yl = chart.getAxisLeft()
        yl.setDrawAxisLine(false)
        yl.setDrawGridLines(false)
        yl.axisMinimum = 0f // this replaces setStartAtZero(true)

        val yr = chart.getAxisRight()
        yr.setDrawAxisLine(false)
        yr.setDrawLabels(false)
        yr.setDrawGridLines(false)
        yr.axisMinimum = 0f // this replaces setStartAtZero(true)

        chart.setFitBars(true)
//        chart.animateY(2500)
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
        val colors: MutableList<Int> = ArrayList()
        for (i in 0 until ylistStr.size) {
            colors.add(PURPLE_COLORS[0])
        }
        Logger.e("colors"+colors.size);
        barDataSet.colors = colors


        barDataSet.valueTextColor = R.color.blue_textcolor_39bcca
        barDataSet.valueTextSize = 15f
        barDataSet.setDrawValues(false)
        barDataSet.valueFormatter =
            IValueFormatter { v, entry, i, viewPortHandler -> //                return v + "%";
                (return@IValueFormatter v.toString())
            }
        val barData = BarData(barDataSet)
        barData.barWidth = 0.4f // 设置柱子的宽度
        mChart!!.data = barData
        mChart?.data?.setDrawValues(false)
        mChart?.data?.isHighlightEnabled = false

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


    fun setHistogramData(stringList: List<String>, YlistStr: MutableList<BarEntry>, Allcount: Int) {
        val Xlists: MutableList<String> = ArrayList()
        val arrayList = ArrayList<LegendEntry>();
        for (i in stringList.indices) {
            val legendApply = LegendEntry().apply {
                label = stringList.get(i)
                form = Legend.LegendForm.SQUARE
                formSize = 14f
                formLineWidth = 1f
                formLineDashEffect = null;
                formColor = PURPLE_COLORS[0]
            }
            arrayList.add(legendApply)

//            Xlists.add(result.getLevels().get(i).getType_1() + " - " +
//                    result.getLevels().get(i).getType_2() + " - " +
//                    result.getLevels().get(i).getType_3());
//            Xlists.add(stringList[i])
            Xlists.add("")

//            YlistStr.add(new BarEntry(i, (float) (Integer.parseInt(result.getLevels().get(i).getCount()) * 100
//                    / result.getAll_count())));

//            YlistStr.add(BarEntry(i.toFloat(), 5.0f))
        }

        mChart?.legend?.setCustom(arrayList)


        setAxis(Xlists, Allcount)
        setData(YlistStr)
    }
}