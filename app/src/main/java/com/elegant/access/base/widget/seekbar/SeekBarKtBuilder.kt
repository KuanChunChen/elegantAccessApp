package com.elegant.access.base.widget.seekbar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull

class SeekBarKtBuilder internal constructor(val context: Context) {
    //seek bar
    var max = 100f
    var min = 0f
    var progress = 0f
    var progressValueFloat = false
    var seekSmoothly = false
    var r2l = false
    var userSeekable = true
    var onlyThumbDraggable = false
    var clearPadding = false

    //indicator
    var showIndicatorType: Int = IndicatorType.ROUNDED_RECTANGLE
    var indicatorColor = Color.parseColor("#FF4081")
    var indicatorTextColor = Color.parseColor("#FFFFFF")
    var indicatorTextSize = 0
    var indicatorContentView: View? = null
    var indicatorTopContentView: View? = null

    //track
    var trackBackgroundSize = 0
    var trackBackgroundColor = Color.parseColor("#D7D7D7")
    var trackProgressSize = 0
    var trackProgressColor = Color.parseColor("#FF4081")
    var trackRoundedCorners = false

    //thumbText
    var thumbTextColor = Color.parseColor("#FF4081")
    var showThumbText = false

    //thumb
    var thumbSize = 0
    var thumbColor = Color.parseColor("#FF4081")
    var thumbColorStateList: ColorStateList? = null
    var thumbDrawable: Drawable? = null

    //tickTexts
    var showTickText = false
    var tickTextsColor = Color.parseColor("#FF4081")
    var tickTextsSize = 0
    lateinit var tickTextsCustomArray: Array<String>
    var tickTextsTypeFace = Typeface.DEFAULT
    var tickTextsColorStateList: ColorStateList? = null

    //tickMarks
    var tickCount = 0
    var showTickMarksType: Int = TickMarkType.NONE
    var tickMarksColor = Color.parseColor("#FF4081")
    var tickMarksSize = 0
    var tickMarksDrawable: Drawable? = null
    var tickMarksEndsHide = false
    var tickMarksSweptHide = false
    var tickMarksColorStateList: ColorStateList? = null

    /**
     * call this to new an IndicatorSeekBar
     *
     * @return IndicatorSeekBar
     */
    fun build(): SeekBarKt {
        return SeekBarKt(this)
    }

    /**
     * Set the upper limit of this seek bar's range.
     *
     * @param max the max range
     * @return Builder
     */
    fun max(max: Float): SeekBarKtBuilder {
        this.max = max
        return this
    }

    /**
     * Set the  lower limit of this seek bar's range.
     *
     * @param min the min range
     * @return Builder
     */
    fun min(min: Float): SeekBarKtBuilder {
        this.min = min
        return this
    }

    /**
     * Sets the current progress to the specified value.
     *
     * @param progress the current level of seek bar
     * @return Builder
     */
    fun progress(progress: Float): SeekBarKtBuilder {
        this.progress = progress
        return this
    }

    /**
     * make the progress in float type. default in int type.
     *
     * @param isFloatProgress true for float progress,default false.
     * @return Builder
     */
    fun progressValueFloat(isFloatProgress: Boolean): SeekBarKtBuilder {
        progressValueFloat = isFloatProgress
        return this
    }

    /**
     * seek continuously or discrete
     *
     * @param seekSmoothly true for seek continuously ignore having tickMarks.
     * @return Builder
     */
    fun seekSmoothly(seekSmoothly: Boolean): SeekBarKtBuilder {
        this.seekSmoothly = seekSmoothly
        return this
    }

    /**
     * right to left,compat local problem.
     *
     * @param r2l true for local which read text from right to left
     * @return Builder
     */
    fun r2l(r2l: Boolean): SeekBarKtBuilder {
        this.r2l = r2l
        return this
    }

    /**
     * seek bar has a default padding left and right(16 dp) , call this method to set both to zero.
     *
     * @param clearPadding true to clear the default padding, false to keep.
     * @return Builder
     */
    fun clearPadding(clearPadding: Boolean): SeekBarKtBuilder {
        this.clearPadding = clearPadding
        return this
    }

    /**
     * prevent user from touching to seek or not
     *
     * @param userSeekable true user can seek.
     * @return Builder
     */
    fun userSeekable(userSeekable: Boolean): SeekBarKtBuilder {
        this.userSeekable = userSeekable
        return this
    }

    /**
     * user change the thumb's location by touching thumb,touching track will not worked.
     *
     * @param onlyThumbDraggable true for seeking only by drag thumb. default false;
     * @return Builder
     */
    fun onlyThumbDraggable(onlyThumbDraggable: Boolean): SeekBarKtBuilder {
        this.onlyThumbDraggable = onlyThumbDraggable
        return this
    }

    /**
     * call this method to show different shape of indicator.
     *
     * @param showIndicatorType see[IndicatorType]
     * IndicatorType.NONE;
     * IndicatorType.CIRCULAR_BUBBLE;
     * IndicatorType.ROUNDED_RECTANGLE;
     * IndicatorType.RECTANGLE;
     * IndicatorType.CUSTOM;
     * @return Builder
     */
    fun showIndicatorType(showIndicatorType: Int): SeekBarKtBuilder {
        this.showIndicatorType = showIndicatorType
        return this
    }

    /**
     * set the seek bar's indicator's color. have no influence on custom indicator.
     *
     * @param indicatorColor colorInt
     * @return Builder
     */
    fun indicatorColor(@ColorInt indicatorColor: Int): SeekBarKtBuilder {
        this.indicatorColor = indicatorColor
        return this
    }

    /**
     * set the color for indicator text . have no influence on custom tickDrawable.
     *
     * @param indicatorTextColor ColorInt
     * @return Builder
     */
    fun indicatorTextColor(@ColorInt indicatorTextColor: Int): SeekBarKtBuilder {
        this.indicatorTextColor = indicatorTextColor
        return this
    }

    /**
     * change the size for indicator text.have no influence on custom indicator.
     *
     * @param indicatorTextSize The scaled pixel size.
     * @return Builder
     */
    fun indicatorTextSize(indicatorTextSize: Int): SeekBarKtBuilder {
        this.indicatorTextSize = SizeUtils.sp2px(context, indicatorTextSize.toFloat())
        return this
    }

    /**
     * set the seek bar's indicator's custom indicator view. only effect on custom indicator type.
     *
     * @param indicatorContentView the custom indicator view
     * @return Builder
     */
    fun indicatorContentView(@NonNull indicatorContentView: View?): SeekBarKtBuilder {
        this.indicatorContentView = indicatorContentView
        return this
    }

    /**
     * set the seek bar's indicator's custom indicator layout identify. only effect on custom indicator type.
     *
     * @param layoutId the custom indicator layout identify
     * @return Builder
     */
    fun indicatorContentViewLayoutId(@LayoutRes layoutId: Int): SeekBarKtBuilder {
        indicatorContentView = View.inflate(context, layoutId, null)
        return this
    }

    /**
     * set the seek bar's indicator's custom top content view.
     * no effect on custom and circular_bubble indicator type.
     *
     * @param topContentView the custom indicator top content view
     * @return Builder
     */
    fun indicatorTopContentView(topContentView: View?): SeekBarKtBuilder {
        indicatorTopContentView = topContentView
        return this
    }

    /**
     * set the seek bar's indicator's custom top content view layout identify.
     * no effect on custom and circular_bubble indicator type.
     *
     * @param layoutId the custom view for indicator top content layout identify.
     * @return Builder
     */
    fun indicatorTopContentViewLayoutId(@LayoutRes layoutId: Int): SeekBarKtBuilder {
        indicatorTopContentView = View.inflate(context, layoutId, null)
        return this
    }

    /**
     * set the seek bar's background track's Stroke Width
     *
     * @param trackBackgroundSize The dp size.
     * @return Builder
     */
    fun trackBackgroundSize(trackBackgroundSize: Int): SeekBarKtBuilder {
        this.trackBackgroundSize = SizeUtils.dp2px(context, trackBackgroundSize.toFloat())
        return this
    }

    /**
     * set the seek bar's background track's color.
     *
     * @param trackBackgroundColor colorInt
     * @return Builder
     */
    fun trackBackgroundColor(@ColorInt trackBackgroundColor: Int): SeekBarKtBuilder {
        this.trackBackgroundColor = trackBackgroundColor
        return this
    }

    /**
     * set the seek bar's progress track's Stroke Width
     *
     * @param trackProgressSize The dp size.
     * @return Builder
     */
    fun trackProgressSize(trackProgressSize: Int): SeekBarKtBuilder {
        this.trackProgressSize = SizeUtils.dp2px(context, trackProgressSize.toFloat())
        return this
    }

    /**
     * set the seek bar's progress track's color.
     *
     * @param trackProgressColor colorInt
     * @return Builder
     */
    fun trackProgressColor(@ColorInt trackProgressColor: Int): SeekBarKtBuilder {
        this.trackProgressColor = trackProgressColor
        return this
    }

    /**
     * call this method to show the seek bar's ends to square corners.default rounded corners.
     *
     * @param trackRoundedCorners false to show square corners.
     * @return Builder
     */
    fun trackRoundedCorners(trackRoundedCorners: Boolean): SeekBarKtBuilder {
        this.trackRoundedCorners = trackRoundedCorners
        return this
    }

    /**
     * set the seek bar's thumb's text color.
     *
     * @param thumbTextColor colorInt
     * @return Builder
     */
    fun thumbTextColor(@ColorInt thumbTextColor: Int): SeekBarKtBuilder {
        this.thumbTextColor = thumbTextColor
        return this
    }

    /**
     * call this method to show the text below thumb or not
     *
     * @param showThumbText show the text below thumb or not
     * @return Builder
     */
    fun showThumbText(showThumbText: Boolean): SeekBarKtBuilder {
        this.showThumbText = showThumbText
        return this
    }

    /**
     * set the seek bar's thumb's color.
     *
     * @param thumbColor colorInt
     * @return Builder
     */
    fun thumbColor(@ColorInt thumbColor: Int): SeekBarKtBuilder {
        this.thumbColor = thumbColor
        return this
    }

    /**
     * set the seek bar's thumb's selector color.
     *
     * @param thumbColorStateList color selector
     * @return Builder
     * selector format like:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:color="@color/colorAccent" android:state_pressed="true" />  <!--this color is for thumb which is at pressing status-->
    //<item android:color="@color/color_blue" />                                <!--for thumb which is at normal status-->
    //</selector>
    fun thumbColorStateList(@NonNull thumbColorStateList: ColorStateList?): SeekBarKtBuilder {
        this.thumbColorStateList = thumbColorStateList
        return this
    }

    /**
     * set the seek bar's thumb's Width.will be limited in 30dp.
     *
     * @param thumbSize The dp size.
     * @return Builder
     */
    fun thumbSize(thumbSize: Int): SeekBarKtBuilder {
        this.thumbSize = SizeUtils.dp2px(context, thumbSize.toFloat())
        return this
    }

    /**
     * set a new thumb drawable.
     *
     * @param thumbDrawable the drawable for thumb.
     * @return Builder
     */
    fun thumbDrawable(@NonNull thumbDrawable: Drawable?): SeekBarKtBuilder {
        this.thumbDrawable = thumbDrawable
        return this
    }

    /**
     * call this method to custom the thumb showing drawable by selector Drawable.
     *
     * @param thumbStateListDrawable the drawable show as Thumb.
     * @return Builder
     *
     *
     * selector format:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:drawable="Your drawableA" android:state_pressed="true" />  <!--this drawable is for thumb when pressing-->
    //<item android:drawable="Your drawableB" />  < !--for thumb when normal-->
    //</selector>
    fun thumbDrawable(@NonNull thumbStateListDrawable: StateListDrawable?): SeekBarKtBuilder {
        thumbDrawable = thumbStateListDrawable
        return this
    }

    /**
     * show the tick texts or not
     *
     * @param showTickText show the text below track or not.
     * @return Builder
     */
    fun showTickTexts(showTickText: Boolean): SeekBarKtBuilder {
        this.showTickText = showTickText
        return this
    }

    /**
     * set the color for text below/above seek bar's tickText.
     *
     * @param tickTextsColor ColorInt
     * @return Builder
     */
    fun tickTextsColor(@ColorInt tickTextsColor: Int): SeekBarKtBuilder {
        this.tickTextsColor = tickTextsColor
        return this
    }

    /**
     * set the selector color for text below/above seek bar's tickText.
     *
     * @param tickTextsColorStateList ColorInt
     * @return SeekBarKtBuilder
     * selector format like:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:color="@color/colorAccent" android:state_selected="true" />  <!--this color is for texts those are at left side of thumb-->
    //<item android:color="@color/color_blue" android:state_hovered="true" />     <!--for thumb below text-->
    //<item android:color="@color/color_gray" />                                 <!--for texts those are at right side of thumb-->
    //</selector>
    fun tickTextsColorStateList(@NonNull tickTextsColorStateList: ColorStateList?): SeekBarKtBuilder {
        this.tickTextsColorStateList = tickTextsColorStateList
        return this
    }

    /**
     * set the size for tickText which below/above seek bar's tick .
     *
     * @param tickTextsSize The scaled pixel size.
     * @return SeekBarKtBuilder
     */
    fun tickTextsSize(tickTextsSize: Int): SeekBarKtBuilder {
        this.tickTextsSize = SizeUtils.sp2px(context, tickTextsSize.toFloat())
        return this
    }

    /**
     * call this method to replace the seek bar's tickMarks' below/above tick texts.
     *
     * @param tickTextsArray the length should same as tickCount.
     * @return SeekBarKtBuilder
     */
    fun tickTextsArray(tickTextsArray: Array<String>): SeekBarKtBuilder {
        tickTextsCustomArray = tickTextsArray
        return this
    }

    /**
     * call this method to replace the seek bar's tickMarks' below/above tick texts.
     *
     * @param tickTextsArray the length should same as tickNum.
     * @return SeekBarKtBuilder
     */
    fun tickTextsArray(@ArrayRes tickTextsArray: Int): SeekBarKtBuilder {
        tickTextsCustomArray = context.resources.getStringArray(tickTextsArray)
        return this
    }

    /**
     * set the tick text's / thumb text textTypeface .
     *
     * @param tickTextsTypeFace The text textTypeface.
     * @return SeekBarKtBuilder
     */
    fun tickTextsTypeFace(tickTextsTypeFace: Typeface): SeekBarKtBuilder {
        this.tickTextsTypeFace = tickTextsTypeFace
        return this
    }

    /**
     * set the tickMarks number.
     *
     * @param tickCount the tickMarks count show on seek bar.
     * if you want the seek bar's block size is N , this tickCount should be N+1.
     * @return SeekBarKtBuilder
     */
    fun tickCount(tickCount: Int): SeekBarKtBuilder {
        this.tickCount = tickCount
        return this
    }

    /**
     * call this method to show different tickMark shape.
     *
     * @param tickMarksType see[TickMarkType]
     * TickMarkType.NONE;
     * TickMarkType.OVAL;
     * TickMarkType.SQUARE;
     * TickMarkType.DIVIDER;
     * @return SeekBarKtBuilder
     */
    fun showTickMarksType(tickMarksType: Int): SeekBarKtBuilder {
        showTickMarksType = tickMarksType
        return this
    }

    /**
     * set the seek bar's tick's color.
     *
     * @param tickMarksColor colorInt
     * @return SeekBarKtBuilder
     */
    fun tickMarksColor(@ColorInt tickMarksColor: Int): SeekBarKtBuilder {
        this.tickMarksColor = tickMarksColor
        return this
    }

    /**
     * set the seek bar's tick's color.
     *
     * @param tickMarksColorStateList colorInt
     * @return SeekBarKtBuilder
     * selector format like:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:color="@color/colorAccent" android:state_selected="true" />  <!--this color is for marks those are at left side of thumb-->
    //<item android:color="@color/color_gray" />                                 <!--for marks those are at right side of thumb-->
    //</selector>
    fun tickMarksColor(@NonNull tickMarksColorStateList: ColorStateList?): SeekBarKtBuilder {
        this.tickMarksColorStateList = tickMarksColorStateList
        return this
    }

    /**
     * set the seek bar's tick width , if tick type is divider, call this method will be not worked(tick type is divider,has a regular value 2dp).
     *
     * @param tickMarksSize the dp size.
     * @return SeekBarKtBuilder
     */
    fun tickMarksSize(tickMarksSize: Int): SeekBarKtBuilder {
        this.tickMarksSize = SizeUtils.dp2px(context, tickMarksSize.toFloat())
        return this
    }

    /**
     * call this method to custom the tick showing drawable.
     *
     * @param tickMarksDrawable the drawable show as tickMark.
     * @return SeekBarKtBuilder
     */
    fun tickMarksDrawable(@NonNull tickMarksDrawable: Drawable?): SeekBarKtBuilder {
        this.tickMarksDrawable = tickMarksDrawable
        return this
    }

    /**
     * call this method to custom the tick showing drawable by selector.
     *
     * @param tickMarksStateListDrawable the StateListDrawable show as tickMark.
     * @return SeekBarKtBuilder
     * selector format like :
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:drawable="@mipmap/ic_launcher_round" android:state_pressed="true" />  <!--this drawable is for thumb when pressing-->
    //<item android:drawable="@mipmap/ic_launcher" />  <!--for thumb when normal-->
    //</selector>
    fun tickMarksDrawable(@NonNull tickMarksStateListDrawable: StateListDrawable?): SeekBarKtBuilder {
        tickMarksDrawable = tickMarksStateListDrawable
        return this
    }

    /**
     * call this method to hide the tickMarks which show in the both ends sides of seek bar.
     *
     * @param tickMarksEndsHide true for hide.
     * @return SeekBarKtBuilder
     */
    fun tickMarksEndsHide(tickMarksEndsHide: Boolean): SeekBarKtBuilder {
        this.tickMarksEndsHide = tickMarksEndsHide
        return this
    }

    /**
     * call this method to hide the tickMarks on seekBar's thumb left;
     *
     * @param tickMarksSweptHide true for hide.
     * @return SeekBarKtBuilder
     */
    fun tickMarksSweptHide(tickMarksSweptHide: Boolean): SeekBarKtBuilder {
        this.tickMarksSweptHide = tickMarksSweptHide
        return this
    }

    init {
        indicatorTextSize = SizeUtils.sp2px(context, 14f)
        trackBackgroundSize = SizeUtils.dp2px(context, 2f)
        trackProgressSize = SizeUtils.dp2px(context, 2f)
        tickMarksSize = SizeUtils.dp2px(context, 10f)
        tickTextsSize = SizeUtils.sp2px(context, 13f)
        thumbSize = SizeUtils.dp2px(context, 14f)
    }
}