load("@io_bazel_rules_kotlin//kotlin:android.bzl", "kt_android_library")
load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_compiler_plugin")
load("@grab_bazel_common//tools/databinding:databinding.bzl", "kt_db_android_library")


# kt_android_library(
kt_db_android_library(
    name = "app_lib",
    srcs = glob(["src/main/java/**/*.kt"]),
    custom_package = "com.example.databinding.lib", # All databinding targets must have unique package name
    # enable_data_binding = True,
    manifest = "src/main/AndroidManifest.xml",
    # plugins = [
    #     ":moshi_kotlin_codegen",
    # ],
    resource_files = glob(["src/main/res/**/*"]),
    deps = [
        # "@maven//:androidx_annotation_annotation",
        # "@maven//:androidx_databinding_databinding_adapters",
        # "@maven//:androidx_databinding_databinding_common",
        # "@maven//:androidx_databinding_databinding_runtime",
        "//third_party/moshi-kotlin-codegen",
        "@maven//:com_squareup_moshi_moshi",
        "@maven//:androidx_core_core",
        "@maven//:androidx_core_core_ktx",
        "@maven//:androidx_paging_paging_runtime",
        "@maven//:androidx_recyclerview_recyclerview",
        "@maven//:com_google_android_material_material",
    ],
)

android_binary(
    name = "app",
    custom_package = "com.example.databinding",
    enable_data_binding = True,
    manifest = "src/main/AndroidManifest.xml",
    deps = [
        ":app_lib",
        "@maven//:androidx_annotation_annotation",
        "@maven//:androidx_databinding_databinding_adapters",
        "@maven//:androidx_databinding_databinding_common",
        "@maven//:androidx_databinding_databinding_runtime",
    ],
)
