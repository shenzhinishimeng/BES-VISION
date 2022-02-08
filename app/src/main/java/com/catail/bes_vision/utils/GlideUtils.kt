package com.catail.bes_vision.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.catail.bes_vision.R

object GlideUtils {
    /**
     * 加载普通图片
     */
    @JvmStatic
    fun load(context: Context?, imageView: ImageView?, url: String?) {
        // 不能崩
        if (imageView == null) {
            return
        }
        val viewContext = imageView.context
        var activity: Activity? = null

        // View你还活着吗？
        if (viewContext is Activity) {
            activity = viewContext
            if (activity!!.isFinishing) {
                return
            }
        }
        if (null != activity) {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            //                    .diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(activity)
                .load(url)
                .apply(options) //                    .error(R.mipmap.empty_picture)
                //.placeholder(R.mipmap.empty_picture)
                //.crossFade(1000)//设置动画时间
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //all:缓存源资源和转换后的资源 none:不作任何磁盘缓存
                //source:缓存源资源   result：缓存转换后的资源
                //.override(80,80)//设置最终显示的图片像素为80*80,注意:这个是像素,而不是控件的宽高
                //                                        .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        } else {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            Glide.with(context!!)
                .load(url)
                .apply(options) //                    .error(R.mipmap.empty_picture)
                //.placeholder(R.mipmap.empty_picture)
                //.crossFade(1000)
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        }
    }

    /**
     * 加载普通图片
     */
    @JvmStatic
    fun loader(context: Application?, imageView: ImageView?, url: String?) {
        // 不能崩
        if (imageView == null) {
            return
        }
        val viewContext = imageView.context
        var activity: Activity? = null

        // View你还活着吗？
        if (viewContext is Activity) {
            activity = viewContext
            if (activity!!.isFinishing) {
                return
            }
        }
        if (null != activity) {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            Glide.with(activity)
                .load(url)
                .apply(options) //                    .error(R.mipmap.empty_picture)
                //.placeholder(R.mipmap.empty_picture)
                //.crossFade(1000)//设置动画时间
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //all:缓存源资源和转换后的资源 none:不作任何磁盘缓存
                //source:缓存源资源   result：缓存转换后的资源
                //.override(80,80)//设置最终显示的图片像素为80*80,注意:这个是像素,而不是控件的宽高
                //                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        } else {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            Glide.with(context!!)
                .load(url)
                .apply(options) //                    .error(R.mipmap.empty_picture)
                //.placeholder(R.mipmap.empty_picture)
                //.crossFade(1000)
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        }
    }

    /**
     * 加载本地图片
     */
    @JvmStatic
    fun load(context: Context?, imageView: ImageView?, resoure: Int) {
        // 不能崩
        if (imageView == null) {
            return
        }
        val viewContext = imageView.context
        var activity: Activity? = null

        // View你还活着吗？
        if (viewContext is Activity) {
            activity = viewContext
            if (activity!!.isFinishing) {
                return
            }
        }
        if (null != activity) {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            Glide.with(activity)
                .load(resoure)
                .apply(options) //                    .error(R.mipmap.empty_picture)
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        } else {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            Glide.with(context!!)
                .load(resoure)
                .apply(options) //                    .error(R.mipmap.empty_picture)
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        }
    }

    /**
     * 加载普通图片
     */
    @JvmStatic
    fun load(context: Context?, imageView: ImageView?, defaultdrawable: Int, url: String?) {
        // 不能崩
        if (imageView == null) {
            return
        }
        val viewContext = imageView.context
        var activity: Activity? = null

        // View你还活着吗？
        if (viewContext is Activity) {
            activity = viewContext
            if (activity!!.isFinishing) {
                return
            }
        }
        if (null != activity) {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            Glide.with(activity)
                .load(url)
                .apply(options) //                    .error(defaultdrawable)
                //.placeholder(R.mipmap.empty_picture)
                //.crossFade(1000)//设置动画时间
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //all:缓存源资源和转换后的资源 none:不作任何磁盘缓存
                //source:缓存源资源   result：缓存转换后的资源
                //.override(80,80)//设置最终显示的图片像素为80*80,注意:这个是像素,而不是控件的宽高
                //                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        } else {
            val options = RequestOptions()
                .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
                .error(R.mipmap.empty_picture) //加载错误之后的错误图
                //                    .dontAnimate()
                .skipMemoryCache(false) //跳过内存缓存
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
            Glide.with(context!!)
                .load(url)
                .apply(options) //                    .error(defaultdrawable)
                //.placeholder(R.mipmap.empty_picture)
                //.crossFade(1000)
                //                    .dontAnimate()//禁止动画，防止图片变形
                //                    .priority(Priority.NORMAL) //下载的优先级
                //                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略-磁盘
                .into(imageView)
        }
    }

    /**
     * 图片宽度占满，高度自适应
     */
    @JvmStatic
    fun loadIntoUseFitWidth(context: Context?, imageUrl: String?, imageView: ImageView) {
        val options = RequestOptions()
            .placeholder(R.mipmap.empty_picture) //加载成功之前占位图
            .error(R.mipmap.empty_picture) //加载错误之后的错误图
            //                    .dontAnimate()
            .skipMemoryCache(false) //跳过内存缓存
            .priority(Priority.NORMAL)
            .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
        Glide.with(context!!)
            .load(imageUrl)
            .apply(options)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val bd = resource as BitmapDrawable
                    val bitmap = bd.bitmap
                    val params = imageView.layoutParams
                    params.width = imageView.width - imageView.paddingLeft - imageView.paddingRight
                    params.height = bitmap.height
                    imageView.layoutParams = params
                    return false
                }
            })
            .into(imageView)
    }

    /**
     * //原图 -> 圆图
     * Glide.with(conx).load(url).bitmapTransform(new CropCircleTransformation(conx)).crossFade(500).into(imageView);
     *
     *
     * //原图的毛玻璃、高斯模糊效果
     * Glide.with(conx).load(url).bitmapTransform(new BlurTransformation(conx, 25)).crossFade(1000).into(imageView);
     *
     *
     * //原图基础上复合变换成圆图 +毛玻璃（高斯模糊）
     * Glide.with(conx).load(url).bitmapTransform(new BlurTransformation(conx, 25),
     * new CropCircleTransformation(conx)).crossFade(1000).into(imageView);
     *
     *
     * //原图处理成圆角，如果是四周都是圆角则是RoundedCornersTransformation.CornerType.ALL
     * Glide.with(conx).load(url).bitmapTransform(new RoundedCornersTransformation(conx, 30, 0,
     * RoundedCornersTransformation.CornerType.ALL)).crossFade(1000).into(imageView);
     */
    /*====================================== 加载圆形图片 ==========================================*/
    @JvmStatic
    fun loadCirclePic(obj: Any?, iv: ImageView, defaultPic: Int = R.mipmap.empty_head_picture) {
        loadCirclePic(obj, iv, defaultPic, defaultPic)
    }

    @JvmStatic
    fun loadCirclePic(obj: Any?, iv: ImageView, placeholder: Int, error: Int) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .dontAnimate()
            .placeholder(placeholder)
            .error(error)
            .transform(CircleCrop())
            .placeholder(R.mipmap.empty_head_picture) //加载成功之前占位图
            .skipMemoryCache(false) //跳过内存缓存
            .priority(Priority.NORMAL)
            .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
        Glide.with(iv.context)
            .load(obj)
            .apply(requestOptions)
            .into(iv)
    }
}