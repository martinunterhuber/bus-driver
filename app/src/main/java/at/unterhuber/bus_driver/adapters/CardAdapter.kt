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

private const val EPSILON = 0.001f

class CardAdapter(private val cards: ArrayList<Card>, private val context: Context): RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private var selectedPosition = 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        internal fun bindTo(@DrawableRes resourceId: Int, isSelected: Boolean) {
            imageView.setImageResource(resourceId)
            val lp = imageView.layoutParams
            if (isSelected) {
                imageView.setBackgroundResource(R.drawable.border)
            } else {
                imageView.setBackgroundResource(0)
            }
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.flexBasisPercent = 0.2f - EPSILON
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.card_view2, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drawableName = cards[position].getDrawableName()
        holder.bindTo(context.resources.getIdentifier(drawableName, "drawable", context.packageName), position == selectedPosition)
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}