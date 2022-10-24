load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.2"

http_archive(
    name = "rules_jvm_external",
    sha256 = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")


bind(
    name = "databinding_annotation_processor",
    actual = "//tools:compiler_annotation_processor",
)

android_sdk_repository(
    name = "androidsdk",
    api_level = 30,
    build_tools_version = "30.0.3", # Build tools 31+ is not supported https://github.com/bazelbuild/bazel/issues/10240#issuecomment-884419566
)

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = "946747acdbeae799b085d12b240ec346f775ac65236dfcf18aa0cd7300f6de78",
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/v1.7.0-RC-2/rules_kotlin_release.tgz"],
)

# http_archive(
#     name = "io_bazel_rules_kotlin",
#     urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/1.6.0-RC-1/rules_kotlin_release.tgz"],
#     sha256 = "f1a4053eae0ea381147f5056bb51e396c5c494c7f8d50d0dee4cc2f9d5c701b0",
# )


load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()

load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")

kt_register_toolchains()


load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

git_repository(
    name = "grab_bazel_common",
    commit = "4db6d0703b240b29eefcc7353ddfaf52139fb052",
    remote = "https://github.com/pswaminathan/grab-bazel-common.git",
)

# http_archive(
#     name = "grab_bazel_common",
#     sha256 = "9f53b1a95ac20dbef11ef2e9fa23aa9632209aa3fa7a0047d87696a61bb073a1",
#     urls = ["https://github.com/grab/grab-bazel-common/archive/240a1c393180c5121032e82e4603a124544c241b.zip"],
#     strip_prefix = "grab-bazel-common-240a1c393180c5121032e82e4603a124544c241b",
# )
# local_repository(
#     name = "grab_bazel_common",
#     path = "/Users/p/Code/grab-bazel-common",
# )

load("@grab_bazel_common//:workspace_defs.bzl", "GRAB_BAZEL_COMMON_ARTIFACTS")

DAGGER_TAG = "2.43.2"
http_archive(
    name = "dagger",
    sha256 = "f7fbc3e417b3cdc10e76e818a6854ada777ad6d408408b65c23a096f3ff6daf7",
    strip_prefix = "dagger-dagger-%s" % DAGGER_TAG,
    urls = ["https://github.com/google/dagger/archive/dagger-%s.zip" % DAGGER_TAG],
)
load("@dagger//:workspace_defs.bzl", "DAGGER_ARTIFACTS", "DAGGER_REPOSITORIES")


maven_install(
    artifacts = GRAB_BAZEL_COMMON_ARTIFACTS + DAGGER_ARTIFACTS + [
        # "com.google.dagger:dagger:2.35.1",
        # "com.google.dagger:dagger-compiler:2.35.1",
        # "com.google.dagger:dagger-producers:2.35.1",
        # "com.squareup.moshi:moshi:1.9.2",
        'androidx.core:core-ktx:1.3.2',
        "com.google.android.material:material:1.3.0",
        "com.squareup.moshi:moshi-kotlin-codegen:1.13.0",
        "androidx.paging:paging-runtime-ktx:2.1.2",
        'androidx.recyclerview:recyclerview:1.2.0',
        "androidx.databinding:databinding-adapters:3.4.2",
        "androidx.databinding:databinding-common:3.4.2",
        "androidx.databinding:databinding-compiler:3.4.2",
        "androidx.databinding:databinding-runtime:3.4.2",
        "androidx.databinding:viewbinding:7.0.4",
    ],
    repositories = [
        "https://jcenter.bintray.com/",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)
