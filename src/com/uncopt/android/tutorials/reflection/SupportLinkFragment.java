package com.uncopt.android.tutorials.reflection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupportLinkFragment extends Fragment {

  public SupportLinkFragment() {
    super();
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                           final Bundle state) {
    return inflater.inflate(R.layout.uncopt, container, false);
  }

}
