package coding_academy.crinimalintent

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.crime_list_fragment.*
import java.text.DateFormat
private const val TAG = "CrimeListFragment"

class CrimeListFragment: Fragment() {
    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter:  CrimeAdapter? =  CrimeAdapter(emptyList())

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }



    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.crime_list_fragment, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view)
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        crimeRecyclerView.adapter = adapter
        return view
    }
    private fun updateUI(crimes: List<Crime>) {

        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(crimes)
                }
                })
            }


                public abstract inner class BaseViewHolder(itemView: View) :
                    RecyclerView.ViewHolder(itemView) {
                    abstract fun bind(crime: Crime)
                }

                private inner class CrimeHolder(view: View) : BaseViewHolder(view),
                    View.OnClickListener {
                    private lateinit var crime: Crime
                    val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
                    val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
                    private val solvedImageView1: ImageView =
                        itemView.findViewById(R.id.crime_solved)

                    init {
                        itemView.setOnClickListener(this)
                    }

                    override fun bind(crime: Crime) {
                        this.crime = crime
                        titleTextView.text = this.crime.title
                        dateTextView.text =
                            DateFormat.getTimeInstance(DateFormat.FULL).format(this.crime.date)
                                .toString()
                        solvedImageView1.visibility = if (crime.isSolved) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                    }

                    override fun onClick(v: View) {
                        Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                private inner class SeriousCrimeHolder(view: View) : BaseViewHolder(view),
                    View.OnClickListener {
                    private lateinit var crime: Crime
                    val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
                    val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
                    private val solvedImageView: ImageView =
                        itemView.findViewById(R.id.crime_solved)
                    private val police: Button = itemView.findViewById(R.id.button)

                    init {
                        itemView.setOnClickListener(this)
                    }

                    override fun bind(crime: Crime) {
                        this.crime = crime
                        titleTextView.text = this.crime.title
                        dateTextView.text =
                            DateFormat.getTimeInstance(DateFormat.FULL).format(this.crime.date)
                                .toString()
                        solvedImageView.visibility = if (crime.isSolved) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                    }

                    override fun onClick(v: View) {
                        Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                private inner class CrimeAdapter(var crimes: List<Crime>) :
                    RecyclerView.Adapter<BaseViewHolder>() {

                    override fun onCreateViewHolder(
                        parent: ViewGroup,
                        viewType: Int
                    ): BaseViewHolder {
                        if (viewType == 1) {
                            val view = layoutInflater.inflate(R.layout.serious_crime, parent, false)
                            return SeriousCrimeHolder(view)
                        } else {
                            val view =
                                layoutInflater.inflate(R.layout.list_item_crime, parent, false)
                            return CrimeHolder(view)

                        }
                    }

                    override fun getItemCount(): Int {
                        return crimes.size
                    }

                    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                        val crime = crimes[position]
                        holder.bind(crime)

                    }

                   override fun getItemViewType(position: Int): Int {
                        if (crimes[position].isSolved)
                            return 1;
                        else return 2;

                    }
                }
            }

