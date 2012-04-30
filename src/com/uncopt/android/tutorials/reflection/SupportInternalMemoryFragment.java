package com.uncopt.android.tutorials.reflection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupportInternalMemoryFragment extends Fragment implements CommonFragment {

  private CommonInternalMemoryFragment _common = new CommonInternalMemoryFragment(this);

  public SupportInternalMemoryFragment() {
    super();
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                           final Bundle state) {
    return inflater.inflate(R.layout.internal_memory, container, false);
  }

  @Override
  public void onStart() {
    super.onStart();
    _common.onStart();
  }

  @Override
  public void onStop() {
    super.onStop();
    _common.onStop();
  }

  public SelectionState getSelectionState() {
    return (SelectionState)getFragmentManager().
      findFragmentByTag(Tags.SELECTION_STATE_FRAGMENT.name());
  }

}
