package com.uncopt.android.tutorials.reflection;

public interface SelectionState {

  public static enum Element {

    INTERNAL_MEMORY, SDCARD

  }

  public static interface SelectionListener {

    public void selectionChanged();

  }

  public void addSelectionListener(final SelectionListener listener);

  public void removeSelectionListener(final SelectionListener listener);

  public boolean isSelected(final Element element);

  public void setSelected(final Element element, final boolean selected);

}
