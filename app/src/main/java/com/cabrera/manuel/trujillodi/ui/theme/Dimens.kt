package com.cabrera.manuel.trujillodi.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Dimensions in dp (density-independent pixels).
 */
val dimen0dp = 0.dp
val dimen1dp = 1.dp
val dimen2dp = 2.dp
val dimen3dp = 3.dp
val dimen4dp = 4.dp
val dimen5dp = 5.dp
val dimen6dp = 6.dp
val dimen7dp = 7.dp
val dimen8dp = 8.dp
val dimen9dp = 9.dp
val dimen10dp = 10.dp
val dimen11dp = 11.dp
val dimen12dp = 12.dp
val dimen13dp = 13.dp
val dimen14dp = 14.dp
val dimen15dp = 15.dp
val dimen16dp = 16.dp
val dimen17dp = 17.dp
val dimen18dp = 18.dp
val dimen20dp = 20.dp
val dimen21dp = 21.dp
val dimen22dp = 22.dp
val dimen24dp = 24.dp
val dimen25dp = 25.dp
val dimen26dp = 26.dp
val dimen28dp = 28.dp
val dimen30dp = 30.dp
val dimen32dp = 32.dp
val dimen33dp = 33.dp
val dimen40dp = 40.dp
val dimen43dp = 43.dp
val dimen44dp = 44.dp
val dimen48dp = 48.dp
val dimen50dp = 50.dp
val dimen54dp = 54.dp
val dimen56dp = 56.dp
val dimen60dp = 60.dp
val dimen68dp = 68.dp
val dimen72dp = 72.dp
val dimen76dp = 76.dp
val dimen80dp = 80.dp
val dimen90dp = 90.dp
val dimen96dp = 96.dp
val dimen100dp = 100.dp
val dimen120dp = 120.dp
val dimen132dp = 132.dp
val dimen150dp = 150.dp
val dimen160dp = 160.dp
val dimen196dp = 196.dp
val dimen230dp = 230.dp
val dimen250dp = 250.dp
val dimen270dp = 270.dp
val dimen280dp = 280.dp
val dimen295dp = 296.dp
val dimen300dp = 300.dp
val dimen320dp = 320.dp
val dimen360dp = 360.dp
val dimen380dp = 380.dp
val dimen445dp = 445.dp

/**
 * Stack size dimensions based on predefined dp values.
 */
val dimenStackQuarck = dimen4dp
val dimenStackNano = dimen8dp
val dimenStackMicro = dimen12dp
val dimenStack3XS = dimen16dp
val dimenStack2XS = dimen24dp
val dimenStackXS = dimen32dp
val dimenStackSM = dimen40dp
val dimenStackMD = dimen48dp
val dimenStackLG = dimen56dp
val dimenStackXL = dimen72dp
val dimenStack2XL = dimen96dp
val dimenStack3XL = dimen120dp
val dimenStack4XL = dimen160dp

/**
 * Inline size dimensions based on predefined dp values.
 */
val dimenInlineQuarck = dimen4dp
val dimenInlineNano = dimen8dp
val dimenInlineMicro = dimen12dp
val dimenInline3XS = dimen16dp
val dimenInline2XS = dimen24dp
val dimenInlineXS = dimen32dp
val dimenInlineSM = dimen40dp
val dimenInlineMD = dimen48dp
val dimenInlineLG = dimen56dp
val dimenInlineXL = dimen80dp

/**
 * Adds inset padding with a vertical and horizontal size of 4dp.
 */
fun Modifier.insetNano() = this.padding(vertical = dimen4dp, horizontal = dimen4dp)

/**
 * Adds inset padding with a vertical and horizontal size of 8dp.
 */
fun Modifier.insetXS() = this.padding(vertical = dimen8dp, horizontal = dimen8dp)

/**
 * Adds inset padding with a vertical and horizontal size of 16dp.
 */
fun Modifier.insetSM() = this.padding(vertical = dimen16dp, horizontal = dimen16dp)

/**
 * Adds inset padding with a vertical and horizontal size of 24dp.
 */
fun Modifier.insetMD() = this.padding(vertical = dimen24dp, horizontal = dimen24dp)

/**
 * Adds inset padding with a vertical and horizontal size of 32dp.
 */
fun Modifier.insetLG() = this.padding(vertical = dimen32dp, horizontal = dimen32dp)

/**
 * Adds inset padding with a vertical and horizontal size of 48dp.
 */
fun Modifier.insetXL() = this.padding(vertical = dimen48dp, horizontal = dimen48dp)

/**
 * Adds squish padding with a vertical size of 4dp and a horizontal size of 8dp.
 */
fun Modifier.squishNano() = this.padding(vertical = dimen4dp, horizontal = dimen8dp)

/**
 * Adds squish padding with a vertical size of 8dp and a horizontal size of 16dp.
 */
fun Modifier.squishXS() = this.padding(vertical = dimen8dp, horizontal = dimen16dp)

/**
 * Adds squish padding with a vertical size of 16dp and a horizontal size of 24dp.
 */
fun Modifier.squishSM() = this.padding(vertical = dimen16dp, horizontal = dimen24dp)

/**
 * Adds squish padding with a vertical size of 16dp and a horizontal size of 32dp.
 */
fun Modifier.squishMD() = this.padding(vertical = dimen16dp, horizontal = dimen32dp)

/**
 * Text sizes in various scales, provided in sp (scale-independent pixels).
 */
val textNano = 11.sp
val textMicro = 12.sp
val text2XS = 14.sp
val textXS = 16.sp
val textSM = 18.sp
val textMD = 24.sp
val textLG = 32.sp
val textXL = 40.sp
val text2XL = 48.sp
val text3XL = 64.sp
val text4XL = 96.sp

/**
 * Adjusts text line height to match the font size.
 */
fun TextStyle.textLineHeight100() = this.copy(lineHeight = this.fontSize)

/**
 * Adjusts text line height to 128% of the font size.
 */
fun TextStyle.textLineHeight128() = this.copy(lineHeight = this.fontSize * 1.28)

/**
 * Adjusts text line height to 152% of the font size.
 */
fun TextStyle.textLineHeight152() = this.copy(lineHeight = this.fontSize * 1.52)
