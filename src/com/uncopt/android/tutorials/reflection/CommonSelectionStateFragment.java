package com.uncopt.android.tutorials.reflection;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import android.os.Bundle;

public class CommonSelectionStateFragment {

  private final Set<SelectionState.Element>
    _selection = Collections.synchronizedSet(new HashSet<SelectionState.Element>());

  private static final String SELECTION_STATE_KEY = "SelectionState";

  private List<SelectionState.SelectionListener> _selectionListeners =
    new LinkedList<SelectionState.SelectionListener>();

  public void onSaveInstanceState(final Bundle state) {
    // save the selection in the state bundle.
    String[] selection = new String[_selection.size()];
    int i = 0;
    for (SelectionState.Element element: _selection) {
      selection[i++] = element.name();
    }
    state.putStringArray(SELECTION_STATE_KEY, selection);
  }

  public void onCreate(final Bundle state) {
    if (state != null) {
      // restore the selection from the state bundle.
      final String[] selection = state.getStringArray(SELECTION_STATE_KEY);
      if (selection != null) {
        for (final String element: selection) {
          _selection.add(SelectionState.Element.valueOf(element));
        }
      }
    }
  }

  public void addSelectionListener(final SelectionState.SelectionListener listener) {
    _selectionListeners.add(listener);
  }

  public void removeSelectionListener(final SelectionState.SelectionListener listener) {
    _selectionListeners.remove(listener);
  }

  public boolean isSelected(final SelectionState.Element element) {
    return _selection.contains(element);
  }

  public void setSelected(final SelectionState.Element element, final boolean selected) {
    if ((selected && _selection.add(element)) || (!selected && _selection.remove(element))) {
      for (SelectionState.SelectionListener listener: _selectionListeners) {
        listener.selectionChanged();
      }
    }
  }

}
