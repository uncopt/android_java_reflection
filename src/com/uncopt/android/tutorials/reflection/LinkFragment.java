package com.uncopt.android.tutorials.reflection;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LinkFragment extends Fragment {

  public LinkFragment() {
    super();
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                           final Bundle state) {
    return inflater.inflate(R.layout.uncopt, container, false);
  }

}
