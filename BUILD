load("@io_bazel_rules_kotlin//kotlin:android.bzl", "kt_android_library")
load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_compiler_plugin")


kt_compiler_plugin(
    name = "moshi_kotlin_codegen",
    id = "com.squareup.moshi.kotlin.codegen",
    target_embedded_compiler = True,
    compile_phase = True,
    stubs_phase = True,
    deps = ["@maven//:com_squareup_moshi_moshi_kotlin_codegen",]
)


kt_android_library(
    name = "app_lib",
    enable_data_binding = True,
    custom_package = "com.example.databinding",
    srcs = glob(["src/main/java/**/*.kt",]),
    resource_files = glob(["src/main/res/**/*"]),
    manifest = "src/main/AndroidManifest.xml",
    deps = [
        "@maven//:com_squareup_moshi_moshi",
    ],
    plugins = [
        ":moshi_kotlin_codegen",
    ]
)

android_binary(
    name = "app",
    enable_data_binding = True,
    custom_package = "com.example.databinding",
    manifest = "src/main/AndroidManifest.xml",
    deps = [
        ":app_lib",
    ],
)
