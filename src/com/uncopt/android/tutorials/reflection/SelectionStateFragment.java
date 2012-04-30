package com.uncopt.android.tutorials.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

/**
 * Non-UI fragment used to store the selection. Queried and updated by other fragments.
 * This implementation is based on the API 11+ fragments.
 */
public class SelectionStateFragment extends Fragment implements SelectionState {

  private CommonSelectionStateFragment _common = new CommonSelectionStateFragment();

  public SelectionStateFragment() {
    super();
    setRetainInstance(true);
    setUserVisibleHintWithReflection();
  }

  private void setUserVisibleHintWithoutReflection() {
    // only set it if the version is 4.0.3 or later.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
      setUserVisibleHint(false);
    }
  }

  private void setUserVisibleHintWithReflection() {
    // only call it if the method exists (maybe it was already there on 4.0.2).
    try {
      final Method method = Fragment.class.getMethod("setUserVisibleHint", boolean.class);
      method.setAccessible(true); // it might have existed but not be public prior to 4.0.3.
      try {
        method.invoke(this, true);
      }
      catch (final IllegalAccessException ignore) {}
      catch (final InvocationTargetException ignore) {}
    }
    catch (final NoSuchMethodException ignore) {
      // do nothing, this is not an essential call.
    }
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
