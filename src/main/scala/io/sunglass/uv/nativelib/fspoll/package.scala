package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.filesystem.lib.uv_stat_t
import io.sunglass.uv.nativelib.loop.lib._

import scala.scalanative.native._

package object fspoll {

  @link("uv")
  @extern
  object lib {
    type uv_fs_poll_t = extern
    type uv_fs_poll_cb = CFunctionPtr4[Ptr[uv_fs_poll_t], CInt, Ptr[uv_stat_t], Ptr[uv_stat_t], Unit]

    def uv_fs_poll_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_fs_poll_t]): CInt = extern

    def uv_fs_poll_start(fs_poll: Ptr[uv_fs_poll_t], cb: uv_fs_poll_cb, path: CString, interval: CUnsignedInt): CInt = extern

    def uv_fs_poll_stop(fs_poll: Ptr[uv_fs_poll_t]): CInt = extern

    def uv_fs_poll_getpath(handle: Ptr[uv_fs_poll_t], buffer: CString, size: Ptr[CSize]): CInt = extern
  }

}
