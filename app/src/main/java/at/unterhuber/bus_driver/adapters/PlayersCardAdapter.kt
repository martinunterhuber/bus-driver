package at.unterhuber.bus_driver.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import at.unterhuber.bus_driver.cards.Card
import at.unterhuber.bus_driver.R
import com.google.android.flexbox.FlexboxLayoutManager

class PlayersCardAdapter(private val cards: List<ArrayList<Card>>, private val players: List<String>, private val context: Context): RecyclerView.Adapter<PlayersCardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val wrapper: ViewGroup = itemView.findViewById(R.id.wrapper)

        internal fun bindTo(@DrawableRes resourceId: Int, j: Int) {
            imageView.visibility = View.VISIBLE
            name.visibility = View.GONE
            imageView.setImageResource(resourceId)
            val lp = wrapper.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.isWrapBefore = j == 0 || j == 1
            }
        }

        internal fun bindTo(player: String) {
            name.visibility = View.VISIBLE
            imageView.visibility = View.GONE
            name.text = player
            val lp = wrapper.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.isWrapBefore = true
            }
        }
    }

    class Position2D(val i: Int, val j: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_player_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val position2D = convertPosition(position)
        if (position2D.j == 0) {
            holder.bindTo(players[position2D.i])
        } else {
            val drawableName = cards[position2D.i][position2D.j - 1].getDrawableName()
            holder.bindTo(
                context.resources.getIdentifier(drawableName, "drawable", context.packageName),
                position2D.j
            )
        }
    }

    private fun convertPosition(position: Int): Position2D {
        var p = position
        var i = 0
        while (p - (cards[i].size + 1) >= 0) {
            p -= (cards[i].size + 1)
            i++
        }
        return Position2D(i, p)
    }

    override fun getItemCount(): Int {
        return cards.sumOf { c -> c.size } + players.size
    }
}