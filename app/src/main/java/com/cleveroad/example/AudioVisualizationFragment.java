package com.cleveroad.example;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.GLAudioVisualizationView;

/**
 * Fragment with visualization of audio output.
 */
public class AudioVisualizationFragment extends Fragment {

	public static AudioVisualizationFragment newInstance() {
		return new AudioVisualizationFragment();
	}

	private AudioVisualization audioVisualization;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return new GLAudioVisualizationView.Builder(getContext())
				.setBubblesSize(R.dimen.bubble_size)
				.setBubblesRandomizeSize(true)
				.setWavesHeight(R.dimen.wave_height)
				.setWavesFooterHeight(R.dimen.footer_height)
				.setWavesCount(7)
				.setLayersCount(4)
				.setBackgroundColorRes(R.color.av_color_bg)
				.setLayerColors(R.array.av_colors)
                .setBubblesPerLayer(16)
				.build();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		audioVisualization = (AudioVisualization) view;
		audioVisualization.linkTo(DbmHandler.Factory.newVisualizerHandler(getContext(), 0));
	}

	@Override
	public void onResume() {
		super.onResume();
		audioVisualization.onResume();
	}

	@Override
	public void onPause() {
		audioVisualization.onPause();
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		audioVisualization.release();
		super.onDestroyView();
	}
}