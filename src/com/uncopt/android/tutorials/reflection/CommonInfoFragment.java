package com.uncopt.android.tutorials.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CommonInfoFragment {

  private String _internalMemoryPath = null;
  private String _sdcardPath = null;
  private final CommonFragment _fragment;

  public CommonInfoFragment(final CommonFragment fragment) {
    _fragment = fragment;
  }

  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                           final Bundle state) {
    final View view = inflater.inflate(R.layout.info, container, false);
    final TextView apiLevelText = (TextView)view.findViewById(R.id.api_level);
    final int level = Build.VERSION.SDK_INT;

    String name = null;
    Build.VERSION_CODES.class.getFields();
    for (final Field field: Build.VERSION_CODES.class.getDeclaredFields()) {
      try {
        if (field.getInt(null) == level) {
          name = field.getName();
          break;
        }
      }
      catch (IllegalAccessException ignore) {}
    }
    String apiLevel = String.valueOf(level);
    if (name != null) {
      apiLevel += " (" + name + ")";
    }
    final SpannableString spannableString = new SpannableString(apiLevel);
    if (name != null) {
      spannableString.setSpan(new AbsoluteSizeSpan(12, true), apiLevel.indexOf('('),
                              spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }
    apiLevelText.setText(spannableString);

    final CheckBox internalMemoryCheckBox = (CheckBox)view.findViewById(R.id.internal_memory);
    final CheckBox sdcardCheckBox = (CheckBox)view.findViewById(R.id.sd_card);
    internalMemoryCheckBox.setOnCheckedChangeListener(
      new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(final CompoundButton view, final boolean checked) {
          onInternalMemorySelected(view);
        }
      });
    sdcardCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      public void onCheckedChanged(final CompoundButton view, final boolean checked) {
        onSDCardSelected(view);
      }
    });
    final SelectionState selection = _fragment.getSelectionState();
    if (selection != null) {
      internalMemoryCheckBox.setChecked(
        selection.isSelected(SelectionState.Element.INTERNAL_MEMORY));
      sdcardCheckBox.setChecked(selection.isSelected(SelectionState.Element.SDCARD));
    }
    final TextView internalMemoryText = (TextView)view.findViewById(R.id.internal_memory_path);
    final TextView sdcardText = (TextView)view.findViewById(R.id.sd_card_path);
    getStoragePaths();
    if (_internalMemoryPath == null) {
      internalMemoryCheckBox.setEnabled(false);
      internalMemoryText.setText(R.string.unknown);
    }
    else {
      internalMemoryCheckBox.setEnabled(true);
      internalMemoryText.setText(_internalMemoryPath);
    }
    if (_sdcardPath == null) {
      sdcardCheckBox.setEnabled(false);
      sdcardText.setText(R.string.unknown);
    }
    else {
      sdcardCheckBox.setEnabled(true);
      sdcardText.setText(_sdcardPath);
    }
    return view;
  }

  private void getStoragePaths() {
    final StorageManager storageManager =
      (StorageManager)_fragment.getActivity().getSystemService(Context.STORAGE_SERVICE);
    if (storageManager != null) {
      // use unofficial api market with the @hide annotation.
      try {
        final Object[] volumes =
          (Object[])StorageManager.class.getMethod("getVolumeList").invoke(storageManager);
        final Class<?> clz = Class.forName("android.os.storage.StorageVolume");
        final Method getDescriptionMethod = clz.getMethod("getDescription");
        final Method getPathMethod = clz.getMethod("getPath");
         // those methods should be public but if they aren't, we try to use them anyway.
        getDescriptionMethod.setAccessible(true);
        getPathMethod.setAccessible(true);
        for (final Object volume: volumes) {
          final String description = (String)getDescriptionMethod.invoke(volume);
          final String path = (String)getPathMethod.invoke(volume);
          if (description != null && description.matches("[Ii]nternal[ ]?[Ss]torage")) {
            _internalMemoryPath = path;
          }
          else if (description != null && description.matches("[Ss][Dd][ ]?[Cc]ard[0-9]?")) {
            _sdcardPath = path;
          }
        }
      }
      catch (Exception e) {
        final String directory = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (Environment.isExternalStorageRemovable()) {
          // external storage is removable, it must be an sdcard (and there is no internal memory).
          _sdcardPath = directory;
        }
        else {
          // external storage is not removable, it must be internal memory.
          _internalMemoryPath = directory;
          // if external_sd subdirectory exists.
          final File subDirectory = new File(directory, "external_sd");
          if (subDirectory.exists()) {
            _sdcardPath = subDirectory.getAbsolutePath();
          }
          else {
            final File subDirectoryAlternate = new File(directory, "sd");
            if (subDirectoryAlternate.exists()) {
              _sdcardPath = subDirectoryAlternate.getAbsolutePath();
            }
          }
        }
      }
    }
  }

  private void onInternalMemorySelected(final View view) {
    final SelectionState selection = _fragment.getSelectionState();
    if (selection != null) {
      selection.setSelected(SelectionState.Element.INTERNAL_MEMORY,((CheckBox)view).isChecked());
    }
  }

  private void onSDCardSelected(final View view) {
    final SelectionState selection = _fragment.getSelectionState();
    if (selection != null) {
      selection.setSelected(SelectionState.Element.SDCARD,((CheckBox)view).isChecked());
    }
  }

}
