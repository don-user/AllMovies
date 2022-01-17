package ru.yundon.allmovies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.yundon.allmovies.R
import ru.yundon.allmovies.data.local.database.AllMoviesListEntity
import ru.yundon.allmovies.databinding.ItemAllMoviesBinding
import ru.yundon.allmovies.utils.Constants.RELEASE_TEXT


class AllMoviesAdapter(private val onItemClickListener: ItemClickListener)
    : RecyclerView.Adapter<AllMoviesAdapter.AllMoviesViewHolder>() {

    private lateinit var binding: ItemAllMoviesBinding

    private val allMoviesListEntity: MutableList<AllMoviesListEntity> = mutableListOf()

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): AllMoviesViewHolder {
        binding = ItemAllMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {
        holder.bind(allMoviesListEntity[position], onItemClickListener)

    }
    override fun getItemCount(): Int = allMoviesListEntity.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(listMovies: List<AllMoviesListEntity>){
        allMoviesListEntity.clear()
        allMoviesListEntity.addAll(listMovies)
        notifyDataSetChanged()
    }

    class AllMoviesViewHolder(private val viewBinding: ItemAllMoviesBinding): RecyclerView.ViewHolder(viewBinding.root){

        @SuppressLint("SetTextI18n")
        fun bind(moviesListEntity: AllMoviesListEntity, onItemCallback: ItemClickListener) = with(viewBinding){

            var cnt = 0.0
            tViewTitle.text = moviesListEntity.title
            tViewRelease.text = "$RELEASE_TEXT ${moviesListEntity.releaseDate}"
            tViewRating.text = moviesListEntity.voteAverage.toString()

            ratingImage1.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage2.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage3.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage4.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage5.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage6.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage7.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage8.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage9.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )
            cnt++
            ratingImage10.setImageResource( starRate(moviesListEntity.voteAverage ?: 0.0, cnt) )

            Picasso.get()
                .load(moviesListEntity.backdropPath)
                .fit()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(viewBinding.imageView)

            favoritesImage.setImageResource(
                if (moviesListEntity.isFavourite) R.drawable.favorite_black
                else R.drawable.favorite_border_black
            )
            favoritesImage.setOnClickListener{
                onItemCallback.onFavoriteClick(moviesListEntity.id)
            }
            root.setOnClickListener{
                onItemCallback.onItemClick(moviesListEntity.title, moviesListEntity.id)
            }
        }
    }


    interface ItemClickListener{
        fun onItemClick(title: String?, id: Int)

        fun onFavoriteClick(id: Int){

        }
    }
}
