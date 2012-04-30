package com.uncopt.android.tutorials.reflection;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TutorialIllustrationActivity extends Activity {

  @Override
  public void onCreate(final Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.main);
    final FragmentManager fragmentManager = getFragmentManager();
    if (fragmentManager.findFragmentByTag(Tags.SELECTION_STATE_FRAGMENT.name()) == null) {
      final FragmentTransaction transaction = fragmentManager.beginTransaction();
      transaction.add(new SelectionStateFragment(), Tags.SELECTION_STATE_FRAGMENT.name());
      transaction.commit();
    }
  }

  public void redirect(final View view) {
    final Intent intent =
      new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:UNCOPT%20LLC"));
    try {
      startActivity(intent);
    }
    catch (final ActivityNotFoundException e1) {
      final Intent alternate =
        new Intent(Intent.ACTION_VIEW, Uri.parse("http://uncopt.com/android/filebrowser/"));
      try {
        startActivity(alternate);
      }
      catch (final ActivityNotFoundException e2) {
        Toast.makeText(this, R.string.visit_uncopt, Toast.LENGTH_SHORT).show();
      }
    }

  }

}
