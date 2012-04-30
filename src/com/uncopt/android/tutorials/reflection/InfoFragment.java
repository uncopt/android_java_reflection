package com.uncopt.android.tutorials.reflection;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends Fragment implements CommonFragment {

  private CommonInfoFragment _common = new CommonInfoFragment(this);

  public InfoFragment() {
    super();
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                           final Bundle state) {
    return _common.onCreateView(inflater, container, state);
  }

  public SelectionState getSelectionState() {
    return (SelectionState)getFragmentManager().
      findFragmentByTag(Tags.SELECTION_STATE_FRAGMENT.name());
  }

}
