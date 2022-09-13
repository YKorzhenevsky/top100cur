package com.example.top100cur.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.top100cur.R

//абстрактный базовый класс адаптера
abstract class BaseAdapter<VH : BaseAdapter.BaseViewHolder> : RecyclerView.Adapter<VH>() {

    //список элементов списка
    var items : ArrayList<Any> = ArrayList()

    //возвращающает размер списка
    override fun getItemCount(): Int {
        return items.size
    }

    //связывает views с содержимым
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    //возвращает позицию элемента в списке
    fun getItem(position: Int): Any {
        return items[position]
    }

    //функция добавления одного элемента
    fun add(newItem: Any) {
        items.add(newItem)
    }

    //функция добавления всех элементов
    fun add(newItems: List<Any>) {
        items.addAll(newItems)
    }

    //абстрактный класс ViewHolder
    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Any)
    }
}


class CurrenciesAdapter : BaseAdapter<CurrenciesAdapter.CurrencyViewHolder>() {


    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return CurrencyViewHolder(v)

    }

    //реализация вьюхолдера
    class CurrencyViewHolder(view: View) : BaseAdapter.BaseViewHolder(view) {



        init {
            //слушатель клика по элементам списка
            itemView.setOnClickListener {

            }
        }


        //привязываем элементы представления списка к RecyclerView и заполняем данными
        override fun bind(item: Any) {
            let {
                item as Currency
                Glide.with(view.context).load(item.image).into(view.findViewById<ImageView>(R.id.ivCurrencyIcon))
                view.findViewById<TextView>(R.id.tvCurrencySym).text = item.symbol
                view.findViewById<TextView>(R.id.tvCurrencyName).text = item.name
                view.findViewById<TextView>(R.id.tvCurrencyMarketCap).text = item.marketCap
                view.findViewById<TextView>(R.id.tvCurrencyPrice).text = item.price.toString()
            }


        }
    }

    //класс данных для элемента списка
    data class Currency(
        val id: String,
        val symbol: String,
        val name: String,
        val image: String,
        val price: Float,
        val marketCap: String,
        val marketCapRank: Int,
        val totalVolume: Float,
        val priceChangePercentage24h: Float,
        val marketCapChangePercentage24h: Float,
        val circulatingSupply: Double,
        val totalSupply: Float,
        val ath: Float,
        val athChangePercentage: Float
    )
}