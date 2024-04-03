package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimePBinding


class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeHolder_P(
    private val binding: ListItemCrimePBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.contactPolice.setOnClickListener{
            Toast.makeText(
                binding.root.context,
                "Contacting the police!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val NORMAL: Int = 0
    private val REQUIRES_POLICE: Int = 1
    override fun getItemViewType(position: Int): Int {
        // Return different view types based on your criteria
        return if (crimes[position].requiresPolice) {
            REQUIRES_POLICE
        } else {
            NORMAL
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if(viewType==0){
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            return CrimeHolder(binding)
        }else{
            val binding = ListItemCrimePBinding.inflate(inflater, parent, false)
            return CrimeHolder_P(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder.itemViewType) {
            NORMAL -> {
                val NormalHolder = holder as CrimeHolder
                NormalHolder.bind(crime)
            }
            REQUIRES_POLICE -> {
                val PoliceHolder = holder as CrimeHolder_P
                PoliceHolder.bind(crime)
            }
        }
    }

    override fun getItemCount() = crimes.size
}
