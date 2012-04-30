package com.uncopt.android.tutorials.reflection;

import android.widget.TextView;

public class CommonSDCardFragment implements SelectionState.SelectionListener {

  private final CommonFragment _fragment;

  public CommonSDCardFragment(final CommonFragment fragment) {
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
    selected = selection != null && selection.isSelected(SelectionState.Element.SDCARD);
    ((TextView)_fragment.getView().findViewById(R.id.sd_card_selected)).setText(
      selected ? R.string.selected : R.string.not_selected
    );
  }

}
