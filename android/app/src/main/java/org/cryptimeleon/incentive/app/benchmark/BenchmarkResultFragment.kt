package org.cryptimeleon.incentive.app.benchmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import org.cryptimeleon.incentive.app.R
import org.cryptimeleon.incentive.app.databinding.BenchmarkResultFragmentBinding

/**
 * Fragment for the Benchmark Result
 */
class BenchmarkResultFragment : Fragment() {
    private lateinit var binding: BenchmarkResultFragmentBinding
    private lateinit var viewModel: BenchmarkResultViewModel

    private val args: BenchmarkResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.benchmark_result_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(
            this, BenchmarkResultViewModelFactory(
                requireActivity().application,
                args.result
            )
        ).get(BenchmarkResultViewModel::class.java)
        binding.resultViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.shareEvent.observe(viewLifecycleOwner) {
            if (it == true) {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Benchmark Results")
                shareIntent.putExtra(Intent.EXTRA_TEXT, viewModel.computeShareData())
                startActivity(Intent.createChooser(shareIntent, "Share results via"))

                viewModel.shareFinished()
            }
        }

        return binding.root
    }
}