package com.uncopt.android.tutorials.reflection;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Non-UI fragment used to store the selection. Queried and updated by other fragments.
 * This implementation is based on the compatibility library.
 */
public class SupportSelectionStateFragment extends Fragment implements SelectionState {

  private CommonSelectionStateFragment _common = new CommonSelectionStateFragment();

  public SupportSelectionStateFragment() {
    super();
    setRetainInstance(true);
    setUserVisibleHint(false);
  }

  @Override
  public void onSaveInstanceState(final Bundle state) {
    super.onSaveInstanceState(state);
    _common.onSaveInstanceState(state);
  }

  @Override
  public void onCreate(final Bundle state) {
    super.onCreate(state);
    _common.onCreate(state);
  }

  public void addSelectionListener(final SelectionListener listener) {
    _common.addSelectionListener(listener);
  }

  public void removeSelectionListener(final SelectionListener listener) {
    _common.removeSelectionListener(listener);
  }

  public boolean isSelected(final Element element) {
    return _common.isSelected(element);
  }

  public void setSelected(final Element element, final boolean selected) {
    _common.setSelected(element, selected);
  }

}
