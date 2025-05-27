package com.zb.baselibmodule

import com.google.gson.annotations.SerializedName


/**
 *  author : 86175
 *  time   : 2025/5/23 9:25
 *  com.zb.baselibmodule
 */
data class DictBean(
    @SerializedName("Code")
    val code: Int?,
    @SerializedName("Count")
    val count: Int?,
    @SerializedName("Data")
    val `data`: List<Data>?,
    @SerializedName("Msg")
    val msg: String?,
    @SerializedName("Page")
    val page: Int?,
    @SerializedName("PageCount")
    val pageCount: Int?
) {
    data class Data(
        @SerializedName("BatchError")
        val batchError: Any?,
        @SerializedName("Checked")
        val checked: Boolean?,
        @SerializedName("CreateBy")
        val createBy: Any?,
        @SerializedName("CreateTime")
        val createTime: String?,
        @SerializedName("DictImagePath")
        val dictImagePath: String?,
        @SerializedName("DictLabel")
        val dictLabel: String?,
        @SerializedName("DictLabelType")
        val dictLabelType: String?,
        @SerializedName("DictSort")
        val dictSort: Int?,
        @SerializedName("DictStatus")
        val dictStatus: Int?,
        @SerializedName("DictType")
        val dictType: String?,
        @SerializedName("DictValue")
        val dictValue: String?,
        @SerializedName("ExcelIndex")
        val excelIndex: Int?,
        @SerializedName("ID")
        val iD: String?,
        @SerializedName("IsValid")
        val isValid: Boolean?,
        @SerializedName("Remark")
        val remark: String?,
        @SerializedName("UpdateBy")
        val updateBy: Any?,
        @SerializedName("UpdateTime")
        val updateTime: Any?
    )
}