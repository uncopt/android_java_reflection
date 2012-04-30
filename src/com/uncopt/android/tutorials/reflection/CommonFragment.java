package com.uncopt.android.tutorials.reflection;

import android.app.Activity;
import android.view.View;

public interface CommonFragment {

  public Activity getActivity();

  public SelectionState getSelectionState();

  public View getView();

}
