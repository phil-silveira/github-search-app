package br.edu.dio.githubportifolio.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
           .inflate(R.layout.search_result_repo_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = searchResult.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchResult[position]
        holder.apply {
            title.text = item.title
            username.text = item.owner.username
            if (item.description?.trim()?.isNotEmpty() == true) {
                description.visibility = View.VISIBLE
                description.text = item.description
            } else {
                description.visibility = View.GONE
            }
            numberOfStars.text = item.numberOfStars.toString()
            shareIcon.setOnClickListener { onSharePressed(item.htmlUrl) }
            root.setOnClickListener { onCardPressed(item.htmlUrl) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val root: ConstraintLayout
        val title: TextView
        val username: TextView
        val description: TextView
        val numberOfStars: TextView
        val shareIcon: ImageView

        init {
            itemView.apply {
                root = findViewById(R.id.repo_item_container)
                title = findViewById(R.id.tv_repo_title)
                username = findViewById(R.id.tv_username)
                description = findViewById(R.id.tv_repo_description)
                numberOfStars = findViewById(R.id.tv_stars)
                shareIcon = findViewById(R.id.iv_share)
            }
        }
    }
}

