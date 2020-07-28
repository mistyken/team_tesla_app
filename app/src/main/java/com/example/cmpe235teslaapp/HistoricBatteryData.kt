package com.example.cmpe235teslaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartModel
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartType
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartView
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AASeriesElement

class HistoricBatteryData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic_battery_data)

        val aaChartView: AAChartView = findViewById(R.id.AAChartView)

        /**
         * populating graph with fake data for now
         */
        val aaChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .title("title")
            .subtitle("subtitle")
            .backgroundColor("#4b2b7f")
            .dataLabelsEnabled(true)
            .series(arrayOf(

                AASeriesElement()
                    .name("Model 3")
                    .data(arrayOf(70, 69, 95, 100, 98, 83, 85, 86, 73, 72, 73, 69))
            )
        )

        aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }
}