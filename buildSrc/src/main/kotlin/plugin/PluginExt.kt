package plugin

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

fun Project.android(): LibraryExtension = extensions.getByType(LibraryExtension::class.java)