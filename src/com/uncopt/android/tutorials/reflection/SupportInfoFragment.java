package com.uncopt.android.tutorials.reflection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupportInfoFragment extends Fragment implements CommonFragment {

  private CommonInfoFragment _common = new CommonInfoFragment(this);

  public SupportInfoFragment() {
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
