package com.catail.bes_vision.home.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class HomeDataBean : MultiItemEntity {
    companion object {
        val ITEM_TYPE_RESOURCES = 0  //RESOURCES
        val ITEM_TYPE_SAFETY = 1  //SAFETY
        val ITEM_TYPE_STATISTICS = 2  //STATISTICS
        val ITEM_TYPE_HCS = 3  //HCS
        val ITEM_TYPE_DFMA = 4  //DFMA
        val ITEM_TYPE_RFI_RFA = 5  //RFI_RFA
        val ITEM_TYPE_QUALITY = 6  //QUALITY
        val ITEM_TYPE_TOWERCRANE = 7  //TOWERCRANE
    }


    var function_icon = 0
    var fuction_name = 0
    var fuction_msg: String? = null
    var function_item_type=0


    override fun getItemType(): Int {
        return function_item_type;
    }




}