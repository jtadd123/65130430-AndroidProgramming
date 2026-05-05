
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}


allprojects {
    val dirName = if (path == ":") "root" else path.removePrefix(":").replace(":", "-")
    layout.buildDirectory.set(file("C:/ViecCanLam-build/$dirName"))
}