package com.example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * A Dagger-Hilt di számára.
 * AndroidManifestben regisztrálni kell.
 */
@HiltAndroidApp
class NoteApp : Application()