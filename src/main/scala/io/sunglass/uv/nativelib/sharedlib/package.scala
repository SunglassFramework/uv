package io.sunglass.uv.nativelib

import scala.scalanative.native._

package object sharedlib {

  @link("uv")
  @extern
  object lib {
    type uv_lib_t = extern

    def uv_dlopen(filename: CString, lib: Ptr[uv_lib_t]): CInt = extern


    def uv_dlclose(lib: Ptr[uv_lib_t]): Unit = extern

    def uv_dlsym(lib: Ptr[uv_lib_t], name: CString, ptr: Ptr[Ptr[Byte]]): CInt = extern

    def uv_dlerror(lib: Ptr[uv_lib_t]): CString = extern
  }

}
