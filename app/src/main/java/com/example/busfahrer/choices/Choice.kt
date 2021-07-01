package com.example.busfahrer.choices

import androidx.annotation.DrawableRes
import com.example.busfahrer.R

enum class Choice(@DrawableRes val drawable: Int) {
    TREE(R.drawable.ic_baseline_park_24), NOT_TREE(R.drawable.ic_baseline_local_fire_department_24),
    ABOVE(R.drawable.ic_baseline_arrow_upward_24), BELOW(R.drawable.ic_baseline_arrow_downward_24),
    IN_BETWEEN(R.drawable.ic_baseline_compare_arrows_24), OUTSIDE(R.drawable.ic_baseline_swap_horiz_24),
    HAVE(R.drawable.ic_baseline_check_24), NOT_HAVE(R.drawable.ic_baseline_close_24)
}