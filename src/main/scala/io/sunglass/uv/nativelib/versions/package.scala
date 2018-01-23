package io.sunglass.uv.nativelib

import scala.scalanative.native._

package object versions {

  @link("uv")
  @extern
  object lib {
    def uv_version(): UInt = extern

    def uv_version_string(): UInt = extern
  }

  @link("sunglass0")
  @extern
  object extra {

    @name("sunglass_UV_VERSION_MAJOR")
    def UV_VERSION_MAJOR: CInt = extern

    @name("sunglass_UV_VERSION_MINOR")
    def UV_VERSION_MINOR: CInt = extern

    @name("sunglass_UV_VERSION_PATCH")
    def UV_VERSION_PATCH: CInt = extern

    @name("sunglass_UV_VERSION_IS_RELEASE")
    def UV_VERSION_IS_RELEASE: CInt = extern
  }

}
