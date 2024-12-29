package at.unterhuber.bus_driver.choices

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import at.unterhuber.bus_driver.R

enum class Choice(@DrawableRes val drawable: Int, @StringRes val text: Int) {
    TREE(R.drawable.ic_baseline_park_24, R.string.tree),
    NOT_TREE(R.drawable.ic_baseline_local_fire_department_24, R.string.not_tree),
    ABOVE(R.drawable.ic_baseline_arrow_upward_24, R.string.above),
    BELOW(R.drawable.ic_baseline_arrow_downward_24, R.string.below),
    IN_BETWEEN(R.drawable.ic_baseline_compare_arrows_24, R.string.inside),
    OUTSIDE(R.drawable.ic_baseline_swap_horiz_24, R.string.outside),
    HAVE(R.drawable.ic_baseline_check_24_black, R.string.have),
    NOT_HAVE(R.drawable.ic_baseline_close_24_black, R.string.not_have),
    EQUALS(R.drawable.ic_baseline_equals_24, R.string.equals)
}