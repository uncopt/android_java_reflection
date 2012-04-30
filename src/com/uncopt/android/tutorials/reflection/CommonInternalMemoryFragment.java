package com.uncopt.android.tutorials.reflection;

import android.widget.TextView;

public class CommonInternalMemoryFragment implements SelectionState.SelectionListener {

  private final CommonFragment _fragment;

  public CommonInternalMemoryFragment(final CommonFragment fragment) {
    _fragment = fragment;
  }

  public void onStart() {
    final SelectionState selection = _fragment.getSelectionState();
    if (selection != null) {
      selection.addSelectionListener(this);
    }
    selectionChanged();
  }

  public void onStop() {
    final SelectionState selection = _fragment.getSelectionState();
    if (selection != null) {
      selection.removeSelectionListener(this);
    }
  }

  public void selectionChanged() {
    final boolean selected;
    final SelectionState selection = _fragment.getSelectionState();
    selected = selection != null && selection.isSelected(SelectionState.Element.INTERNAL_MEMORY);
    ((TextView)_fragment.getView().findViewById(R.id.internal_memory_selected)).setText(
      selected ? R.string.selected : R.string.not_selected
    );
  }

}
