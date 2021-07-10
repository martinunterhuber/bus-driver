package at.unterhuber.bus_driver.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import at.unterhuber.bus_driver.cards.Card
import com.example.bus_driver.R
import com.google.android.flexbox.FlexboxLayoutManager


class PyramidAdapter(private val cards: ArrayList<Card>, private val context: Context): RecyclerView.Adapter<PyramidAdapter.ViewHolder>() {
    var displayedCards = 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        internal fun bindTo(@DrawableRes resourceId: Int, wrap: Boolean) {
            imageView.setImageResource(resourceId)
            val lp = imageView.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.isWrapBefore = wrap
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_fixed, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drawableName = cards[position].getDrawableName()
        if (position >= itemCount - displayedCards) {
            holder.bindTo(context.resources.getIdentifier(drawableName, "drawable", context.packageName), requiresWrapBefore(position))
        } else {
            holder.bindTo(R.drawable.backside, requiresWrapBefore(position))
        }
    }

    private fun requiresWrapBefore(position: Int): Boolean {
        var temp = position
        var i = 0
        while (temp > 0) {
            temp -= i
            i++
        }
        return temp == 0
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun nextCard(): Card {
        displayedCards++
        return cards[itemCount - displayedCards]
    }

    fun reachedLimit(): Boolean {
        return displayedCards == itemCount
    }
}