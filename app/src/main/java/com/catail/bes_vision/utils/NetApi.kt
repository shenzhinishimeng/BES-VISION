package com.catail.bes_vision.utils

object NetApi  {

    //正式库地址
//    var BaseUrl = "https://www.beehives.sg/cms_qa/dbapi?cmd="
//    var UPLOAD_IMG = "https://shell.cmstech.sg/appupload" // 图片上传地址
//    var IMG_SHOW_URL = "https://shell.cmstech.sg" // 图片显示地址
//    const val SERVER_IMG_URL = "https://shell.cmstech.sg" // 正式
//    var PDF_CATALOG_PATH_2 = "https://shell.cmstech.sg" // pdf路径正式
//    var webBaseUrl = "https://www.beehives.sg/" //web页面//正式库

    //   测试库地址
    var BaseUrl: String? = "https://t.cmstech.aoepos.cn/cms_qa/dbapi?cmd="
    var UPLOAD_IMG = "https://t.cmstech.aoepos.cn/appupload" // 图片上传地址
    var IMG_SHOW_URL = "https://t.cmstech.aoepos.cn" // 图片显示地址
    const val SERVER_IMG_URL = "https://t.cmstech.aoepos.cn" // 安全检查显示图片
    var PDF_CATALOG_PATH_2 = "https://t.cmstech.aoepos.cn" // pdf路径测试
    var webBaseUrl = "https://shell.cmstech.sg/" //webs页面//正式库

    //00    系统模块
    var Login = BaseUrl + "CMSC0001" //登录接口
    var changePassword = BaseUrl + "CMSC0002" //我的界面,修改密码
    var QueryUserInfo = BaseUrl + "CMSC0003" //查询个人信息
    var electronicSignature = BaseUrl + "CMSC0004" //上传签名信息
    var ResetPassword = BaseUrl + "CMSC0006" //重置密码
    var UpdateXML = Config.UPDATE_URL //检查更新的接口


}