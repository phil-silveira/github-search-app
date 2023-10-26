package br.edu.dio.githubportifolio.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.edu.dio.githubportifolio.R
import br.edu.dio.githubportifolio.domain.Repository

class SearchResultAdapter(
    private val searchResult: List<Repository>,
    private val onCardPressed: (String) -> Unit,
    private val onSharePressed: (String) -> Unit
): Adapter<SearchResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.search_result_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = searchResult.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchResult[position]
        holder.apply {
            title.text = item.name
            shareIcon.setOnClickListener { onSharePressed(item.htmlUrl) }
            cardContainer.setOnClickListener { onCardPressed(item.htmlUrl) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val shareIcon: ImageView
        val cardContainer: CardView

        init {
            itemView.apply {
                title = findViewById(R.id.title_tv)
                shareIcon = findViewById(R.id.share_iv)
                cardContainer = findViewById(R.id.card_container)
            }
        }
    }
}

