package br.edu.dio.githubportifolio.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import br.edu.dio.githubportifolio.R
import br.edu.dio.githubportifolio.data.ReposRepository
import br.edu.dio.githubportifolio.domain.Repository
import br.edu.dio.githubportifolio.ui.adapters.SearchResultAdapter
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var usernameField: TextInputEditText
    private lateinit var searchButton: AppCompatButton
    private lateinit var progressIndicator: ProgressBar
    private lateinit var searchResultListView: RecyclerView

    private val repository = ReposRepository
    private var searchResult: List<Repository>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupRecycleView()
    }

    private fun setupView() {
        usernameField = findViewById(R.id.username_field)
        searchButton = findViewById(R.id.search_button)
        progressIndicator = findViewById(R.id.circular_progress_indicator)
        searchResultListView = findViewById(R.id.search_result_listview)
    }

    private fun setupListeners() {
        searchButton.setOnClickListener {
            val view = currentFocus
            if (view != null) {
                val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken, 0)
            }

            val username: String = usernameField.text.toString().trim()

            if (username.isEmpty()) return@setOnClickListener

            searchRepositories(username)
        }
        progressIndicator.visibility = View.GONE
    }

    private fun setupRecycleView() {
        val adapter = SearchResultAdapter(searchResult ?: emptyList(), { openUrl(it) }, { shareUrl(it) })
        searchResultListView.adapter = adapter
    }

    private fun openUrl(url: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }
        startActivity(intent)
    }
    private fun shareUrl(url: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, url)
        }
        startActivity(Intent.createChooser(intent, null))
    }

    private fun searchRepositories(username: String) {
        progressIndicator.visibility = View.VISIBLE

        repository.listReposByUsername(username).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                if (response.isSuccessful) {
                    progressIndicator.visibility = View.GONE
                    searchResult = response.body()
                    setupRecycleView()
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                progressIndicator.visibility = View.GONE
            }
        })
    }
}