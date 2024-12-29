package at.unterhuber.bus_driver.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import at.unterhuber.bus_driver.R

class PlayerAdapter(private val context: Context) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    var players = ArrayList<String>(listOf(""))
    private var count = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_name, parent, false)
        val holder =  ViewHolder(view)
        holder.setIsRecyclable(false)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.playerName.hint = context.getString(R.string.player_n, position + 1)
        holder.playerName.setText(players[position])
        holder.playerName.doOnTextChanged { _, _, _, _ -> players[position] = holder.playerName.text.toString() }
    }

    override fun getItemCount(): Int {
        return count
    }

    fun addPlayer() {
        players.add("")
        count++
        notifyItemInserted(count - 1)
    }

    fun removePlayer() {
        if (count > 1) {
            players.removeLast()
            count--
            notifyDataSetChanged()
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.playerName.doOnTextChanged{ _, _, _, _ -> run {} }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerName: EditText = view.findViewById(R.id.player_name)
    }
}