package ru.yundon.allmovies.adapter

import ru.yundon.allmovies.R

fun starRate(rate: Double, cnt: Double): Int{
    return if (rate < cnt || rate == 0.0) R.drawable.star_border
    else if (rate > cnt && rate < (cnt + 1.0)) R.drawable.star_half
    else R.drawable.star
}